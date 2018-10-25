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
			HttpServletRequest req) throws IOException{
		//����֤
		// ��֤����֤
		if (WebUtils.isEmpty(valistr)) {
			// ��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "��֤�벻��Ϊ�գ�");
			// ������ת����regist.jsp
			return "forward:/regist.action";
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
				return "forward:/regist.action";
			}
		}
		return "forward:/regist.action";
		
//		// 2)����һ����֤
//		String password = user.getPassword();
//		if (!password.equals(password2)) {
//			// ��request����������Ӵ�����ʾ��Ϣ
//			model.addAttribute("errMsg", "��֤�����");
//			// ������ת����regist.jsp
//			return "forward:/regist.action";
//		}
//		if(br.hasErrors()){
//			model.addAttribute("errMsg", br.getFieldError().getDefaultMessage());
//			return "forward:/regist.action";
//		}
//		
//		//4)�û����Ƿ������֤
//		String username = user.getUsername();
//		boolean flag = userService.hasUsername(username);
//		if (flag) {
//			// ��request����������Ӵ�����ʾ��Ϣ
//			model.addAttribute("errMsg", "�û����Ѵ��ڣ�");
//			// ������ת����regist.jsp
//			return "forward:/regist.action";
//		}
//		
//		//���������MD5����
//		password = MD5Util.md5(password);
//		user.setPassword(password);
//		
//		boolean flag2 = userService.registUser(user);
//		if(flag2){
//			//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
//			resp.getWriter().write("<h1 style='text-align: center;color:red'>��ϲ����ע��ɹ���3�����ת����ҳ</h1>");
//			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
//			return null;
//		}else{
//			//��request����������Ӵ�����ʾ��Ϣ
//			model.addAttribute("errMsg", "ע������쳣�����Ժ�����....");
//			//������ת����regist.jsp
//			return "forward:/regist.action";
//		}
		
	}
}
