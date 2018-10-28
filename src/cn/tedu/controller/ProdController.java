package cn.tedu.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.beans.Prod;
import cn.tedu.service.ProdService;

@Controller
public class ProdController {
	@Autowired
	private ProdService prodService;
	
	@RequestMapping("/prodList.action")
	public String prodList(Model model){
		List<Prod> list = null;
		list = prodService.getProds();
		System.out.println(list);
		model.addAttribute("list", list);
		return "prod_list";
		
	}
}
