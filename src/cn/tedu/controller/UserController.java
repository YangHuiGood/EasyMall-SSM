package cn.tedu.controller;

import java.io.IOException;

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
			HttpServletRequest req) throws IOException{
		//表单验证
		// 验证码验证
		if (WebUtils.isEmpty(valistr)) {
			// 向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "验证码不能为空！");
			// 将请求转发给regist.jsp
			return "forward:/regist.action";
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
				return "forward:/regist.action";
			}
		}
		return "forward:/regist.action";
		
//		// 2)密码一致验证
//		String password = user.getPassword();
//		if (!password.equals(password2)) {
//			// 向request作用域中添加错误提示信息
//			model.addAttribute("errMsg", "验证码错误");
//			// 将请求转发给regist.jsp
//			return "forward:/regist.action";
//		}
//		if(br.hasErrors()){
//			model.addAttribute("errMsg", br.getFieldError().getDefaultMessage());
//			return "forward:/regist.action";
//		}
//		
//		//4)用户名是否存在验证
//		String username = user.getUsername();
//		boolean flag = userService.hasUsername(username);
//		if (flag) {
//			// 向request作用域中添加错误提示信息
//			model.addAttribute("errMsg", "用户名已存在！");
//			// 将请求转发给regist.jsp
//			return "forward:/regist.action";
//		}
//		
//		//将密码进行MD5加密
//		password = MD5Util.md5(password);
//		user.setPassword(password);
//		
//		boolean flag2 = userService.registUser(user);
//		if(flag2){
//			//5.保存成功-提示成功信息，定时刷新到首页
//			resp.getWriter().write("<h1 style='text-align: center;color:red'>恭喜您，注册成功！3秒后跳转至首页</h1>");
//			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
//			return null;
//		}else{
//			//向request作用域中添加错误提示信息
//			model.addAttribute("errMsg", "注册出现异常，请稍后重试....");
//			//将请求转发给regist.jsp
//			return "forward:/regist.action";
//		}
		
	}
}
