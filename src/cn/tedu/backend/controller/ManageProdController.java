package cn.tedu.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.beans.Prod;

@Controller
public class ManageProdController {
	@RequestMapping("/addProd.action")
	public String addProd(MultipartFile fx,Prod prod){
		
		System.out.println(fx.getOriginalFilename());
		System.out.println(prod.toString());
		return "forward:/toRight.action";
		
	}
}
