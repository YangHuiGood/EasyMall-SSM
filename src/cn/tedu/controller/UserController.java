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
	 * ת����ת��Ӧҳ��
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
	 * ע���û�
	 * @param user
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/regist.action")
	public String regist(HttpSession session,@Valid User user,BindingResult br,
			String password2,String valistr,Model model,
			HttpServletRequest req,HttpServletResponse resp) throws IOException{
		//����֤
		// ��֤����֤
		if (WebUtils.isEmpty(valistr)) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "��֤�벻��Ϊ�գ�");
			// ������ת����regist.jsp
			return "regist";
		} else {
			boolean flag = true;// Ĭ����֤��û����
			String text = (String) session.getAttribute("code");
			if (session == null || text == null) {
				// û��session���󣬻���session��û����ȷ����֤���ı�
				flag = false;
			} else {
				if (!text.equalsIgnoreCase(valistr)) {
					// ��֤�벻ƥ��
					flag = false;
				}
			}
			if (flag == false) {
				model.addAttribute("errMsg", "��֤�����");
				// ������ת����regist.jsp
				return "regist";
			}
		}
		
		// 2)����һ����֤
		String password = user.getPassword();
		if (!password.equals(password2)) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "��֤�����");
			// ������ת����regist.jsp
			return "regist";
		}
		if(br.hasErrors()){
			model.addAttribute("errMsg", br.getFieldError().getDefaultMessage());
			return "regist";
		}
		
		
		//4)�û����Ƿ������֤
		String username = user.getUsername();
		boolean flag = userService.hasUsername(username);
		if (flag) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "�û����Ѵ��ڣ�");
			// ������ת����regist.jsp
			return "regist";
		}
		
		
		//���������MD5����
		password = MD5Util.md5(password);
		user.setPassword(password);
		System.out.println(user.toString());
		boolean flag2 = userService.registUser(user);
		if(flag2){
			//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			resp.getWriter().write("<h1 style='text-align: center;color:red'>��ϲ����ע��ɹ���3�����ת����ҳ</h1>");
			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
			return null;
		}else{
			//��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "ע������쳣�����Ժ�����....");
			//������ת����regist.jsp
			return "regist";
		}	
	}
	@RequestMapping("/login.action")
	public String login(String username,String password,String remname,String autologin,
			Model model,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		if (WebUtils.isEmpty(username)) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "�û�������Ϊ�գ�");
			// ����login.jsp
			return "login";
		}
		if (WebUtils.isEmpty(password)) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "���벻��Ϊ�գ�");
			// ����login.jsp
			return "login";
		}
		if(remname != null && "true".equals(remname)){
			//��ѡ�˼�ס�û���
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username, "UTF-8"));
			//������Чʱ��
			cookie.setMaxAge(60*60*24*7);//����7��
			//�ֶ�����һ��·��-webӦ�øɵĸ�·��
			//EasyMall�����ó�������������Ĭ��webӦ�õ���req.getContextPath()���� ""
			//setPath("")->��Ч������setPath(""+"/")
			cookie.setPath(req.getContextPath()+"/");
			//��cookie�콾��response��
			resp.addCookie(cookie);
		}else{
			//û�й�ѡ��ס�û���
			Cookie cookie = new Cookie("remname","");
			cookie.setPath(req.getContextPath()+"/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		// ��¼
		// ���������MD5����
		password = MD5Util.md5(password);
		System.out.println(username+"==="+password);
		User user = null;
		try {
			user = userService.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			//����ת��
			return "login";
		}
		if(user != null){
			//ֱ������û�������session�У�������ʹ��
			req.getSession().setAttribute("user", user);
			//ִ��30���Զ���¼
			if("true".equals(autologin)){
				//�û���ѡ��30���Զ���¼
				//����cookie�����û���������
				Cookie cookie = new Cookie("autologin",URLEncoder.encode(username,"UTF-8")+"#"+password);
				//����ʱ��
				cookie.setMaxAge(60*60*24*30);//30��
				//����cookie��path
				cookie.setPath(req.getContextPath()+"/");
				//��cookie��ӵ�response��
				resp.addCookie(cookie);
			}
			//�ض�����ҳ
			return "redirect:/index.jsp";
			
		}else{
			model.addAttribute("errMsg", "�û������������");
			//����ת��
			return "login";
		}
	}
	
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest req,HttpServletResponse resp){
		// ִ��ע��
		// �����û��󶨵�session
		req.getSession().invalidate();
		// ɾ��30�Զ���¼��cookie
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath(req.getContextPath() + "/");
		resp.addCookie(cookie);

		// �����ض�����ҳ
		return "redirect:/index.jsp";
		
	}
}
