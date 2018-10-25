package cn.tedu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.tedu.util.VerifyCode;

@Controller
@SessionAttributes("code")
public class ValiImageController {
	@RequestMapping("/getValiImage.action")
	public void getValiImage(HttpServletResponse resp,Model model) throws IOException{
		// 1.��������
		// 2.���ù����࣬������֤��ͼƬ
		VerifyCode vc = new VerifyCode();
		// 3.�����ɵ���֤��ͼƬ����responseʵ����
		vc.drawImage(resp.getOutputStream());
		// 4.�����������Ҫ������֤��ͼƬ
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		// �����ɵ���֤����ı��������������̨
		System.out.println(vc.getCode());
		// ��ȡ�û���session����
		model.addAttribute("code", vc.getCode());
	}
}
