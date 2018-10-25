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
		// 1.接收请求
		// 2.调用工具类，生成验证码图片
		VerifyCode vc = new VerifyCode();
		// 3.将生成的验证码图片存入response实体中
		vc.drawImage(resp.getOutputStream());
		// 4.控制浏览器不要缓存验证码图片
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		// 将生成的验证码的文本内容输出到控制台
		System.out.println(vc.getCode());
		// 获取用户的session对象
		model.addAttribute("code", vc.getCode());
	}
}
