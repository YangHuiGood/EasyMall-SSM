package cn.tedu.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.beans.Prod;
import cn.tedu.service.ProdService;

@Controller
public class ManageProdController {
	@Autowired
	private ProdService prodService;
	@RequestMapping("/addProd.action")
	public String addProd(MultipartFile fx,Prod prod,HttpServletRequest req,HttpServletResponse resp,Model model) throws IOException{
		//获取ServletContext对象
		ServletContext sc = req.getServletContext();
//		System.out.println(fx.getOriginalFilename());
//		System.out.println(prod.toString());
		// 声明临时文件夹和上传文件夹的相对路径
		String uploadPath = "/WEB-INF/upload";
		// 声明一个变量，保存商品图片的实际url
		String imgurl = null;
		//获取上传文件名
		String fileName = fx.getOriginalFilename();
		//ie浏览器的bug问题
		if(fileName.contains("\\")){
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		}
		//文件名重复问题
		fileName = UUID.randomUUID().toString()+"_"+fileName;
		//文件路径问题
		String hexStr = Integer.toHexString(fileName.hashCode());
		// 补足8位
		while(hexStr.length() < 8){
			hexStr = "0"+hexStr;
		}
		//拆分成路径
		String midPath = "/";
		for(int i=0;i<hexStr.length();i++){
			midPath += hexStr.charAt(i)+"/";
		}
		// /WEB-INF/upload/a/b/c/d/1/2/3/4/1231231.jpg
		imgurl = uploadPath+midPath+fileName;
		// 生成目录
		// d:/web/workspace/easymall/webroot/....
		uploadPath = sc.getRealPath(uploadPath+midPath);
		new File(uploadPath).mkdirs();
		try {
			FileUtils.writeByteArrayToFile(new File(uploadPath+"/"+fileName)
			, fx.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("商品图片上传失败");
		}
		prod.setImgurl(imgurl);
		boolean flag = prodService.addProd(prod);
		if(flag){
			//5.保存成功-提示成功信息，定时刷新到首页
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			resp.getWriter().write("<h1 style='text-align:center;color:red'>商品添加成功,3秒后自动跳转首页</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/toRight.action");
			return null;
		}else{
			//向request作用域中添加错误提示信息
			model.addAttribute("errMsg", "商品添加失败");
			//将请求转发给regist.jsp
			return "backend/manageAddProd";
		}
		
	}
}
