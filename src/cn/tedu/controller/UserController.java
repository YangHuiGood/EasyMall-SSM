package cn.tedu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.tedu.beans.User;
import cn.tedu.service.UserService;
import cn.tedu.util.MD5Util;
import cn.tedu.util.WebUtils;

@Controller
@SessionAttributes("code")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 转发跳转相应页面
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/toFile.action")
	public String toFile(HttpServletRequest req) throws IOException, Exception{
		String vname = req.getParameter("vname");
		return vname;
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/regist.action")
	public String regist(HttpSession session,@Valid User user,BindingResult br,
			String password2,String valistr,Model model,
			HttpServletRequest req,HttpServletResponse resp) throws IOException{
		//表单验证
		// 验证码验证
		if (WebUtils.isEmpty(valistr)) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "验证码不能为空！");
			// 将请求转发给regist.jsp
			return "regist";
		} else {
			boolean flag = true;// 默认验证码没问题
			String text = (String) session.getAttribute("code");
			if (session == null || text == null) {
				// 没有session对象，或者session中没有正确的验证码文本
				flag = false;
			} else {
				if (!text.equalsIgnoreCase(valistr)) {
					// 验证码不匹配
					flag = false;
				}
			}
			if (flag == false) {
				model.addAttribute("errMsg", "验证码错误");
				// 将请求转发给regist.jsp
				return "regist";
			}
		}
		
		// 2)密码一致验证
		String password = user.getPassword();
		if (!password.equals(password2)) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "验证码错误");
			// 将请求转发给regist.jsp
			return "regist";
		}
		if(br.hasErrors()){
			model.addAttribute("errMsg", br.getFieldError().getDefaultMessage());
			return "regist";
		}
		
		
		//4)用户名是否存在验证
		String username = user.getUsername();
		boolean flag = userService.hasUsername(username);
		if (flag) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "用户名已存在！");
			// 将请求转发给regist.jsp
			return "regist";
		}
		
		
		//将密码进行MD5加密
		password = MD5Util.md5(password);
		user.setPassword(password);
		System.out.println(user.toString());
		boolean flag2 = userService.registUser(user);
		if(flag2){
			//5.保存成功-提示成功信息，定时刷新到首页
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			resp.getWriter().write("<h1 style='text-align: center;color:red'>恭喜您，注册成功！3秒后跳转至首页</h1>");
			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
			return null;
		}else{
			//向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "注册出现异常，请稍后重试....");
			//将请求转发给regist.jsp
			return "regist";
		}	
	}
	@RequestMapping("/login.action")
	public String login(String username,String password,String remname,String autologin,
			Model model,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		if (WebUtils.isEmpty(username)) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "用户名不能为空！");
			// 返回login.jsp
			return "login";
		}
		if (WebUtils.isEmpty(password)) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "密码不能为空！");
			// 返回login.jsp
			return "login";
		}
		if(remname != null && "true".equals(remname)){
			//勾选了记住用户名
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username, "UTF-8"));
			//设置有效时间
			cookie.setMaxAge(60*60*24*7);//保存7天
			//手动设置一个路径-web应用干的根路径
			//EasyMall被配置成了虚拟主机的默认web应用导致req.getContextPath()返回 ""
			//setPath("")->无效，所以setPath(""+"/")
			cookie.setPath(req.getContextPath()+"/");
			//将cookie天骄到response中
			resp.addCookie(cookie);
		}else{
			//没有勾选记住用户名
			Cookie cookie = new Cookie("remname","");
			cookie.setPath(req.getContextPath()+"/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		// 登录
		// 将密码进行MD5加密
		password = MD5Util.md5(password);
		System.out.println(username+"==="+password);
		User user = null;
		try {
			user = userService.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			//请求转发
			return "login";
		}
		if(user != null){
			//直接添加用户对象在session中，供后续使用
			req.getSession().setAttribute("user", user);
			//执行30天自动登录
			if("true".equals(autologin)){
				//用户勾选了30天自动登录
				//创建cookie保存用户名和密码
				Cookie cookie = new Cookie("autologin",URLEncoder.encode(username,"UTF-8")+"#"+password);
				//设置时长
				cookie.setMaxAge(60*60*24*30);//30天
				//设置cookie的path
				cookie.setPath(req.getContextPath()+"/");
				//将cookie添加到response中
				resp.addCookie(cookie);
			}
			//重定向到首页
			return "redirect:/index.jsp";
			
		}else{
			model.addAttribute("errMsg", "用户名或密码错误");
			//请求转发
			return "login";
		}
	}
	
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest req,HttpServletResponse resp){
		// 执行注销
		// 销毁用户绑定的session
		req.getSession().invalidate();
		// 删除30自动登录的cookie
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath(req.getContextPath() + "/");
		resp.addCookie(cookie);

		// 请求重定向到首页
		return "redirect:/index.jsp";
		
	}
}
