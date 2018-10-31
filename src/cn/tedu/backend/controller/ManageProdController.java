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
		//��ȡServletContext����
		ServletContext sc = req.getServletContext();
//		System.out.println(fx.getOriginalFilename());
//		System.out.println(prod.toString());
		// ������ʱ�ļ��к��ϴ��ļ��е����·��
		String uploadPath = "/WEB-INF/upload";
		// ����һ��������������ƷͼƬ��ʵ��url
		String imgurl = null;
		//��ȡ�ϴ��ļ���
		String fileName = fx.getOriginalFilename();
		//ie�������bug����
		if(fileName.contains("\\")){
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		}
		//�ļ����ظ�����
		fileName = UUID.randomUUID().toString()+"_"+fileName;
		//�ļ�·������
		String hexStr = Integer.toHexString(fileName.hashCode());
		// ����8λ
		while(hexStr.length() < 8){
			hexStr = "0"+hexStr;
		}
		//��ֳ�·��
		String midPath = "/";
		for(int i=0;i<hexStr.length();i++){
			midPath += hexStr.charAt(i)+"/";
		}
		// /WEB-INF/upload/a/b/c/d/1/2/3/4/1231231.jpg
		imgurl = uploadPath+midPath+fileName;
		// ����Ŀ¼
		// d:/web/workspace/easymall/webroot/....
		uploadPath = sc.getRealPath(uploadPath+midPath);
		new File(uploadPath).mkdirs();
		try {
			FileUtils.writeByteArrayToFile(new File(uploadPath+"/"+fileName)
			, fx.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("��ƷͼƬ�ϴ�ʧ��");
		}
		prod.setImgurl(imgurl);
		boolean flag = prodService.addProd(prod);
		if(flag){
			//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			resp.getWriter().write("<h1 style='text-align:center;color:red'>��Ʒ��ӳɹ�,3����Զ���ת��ҳ</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/toRight.action");
			return null;
		}else{
			//��request����������Ӵ�����ʾ��Ϣ
			model.addAttribute("errMsg", "��Ʒ���ʧ��");
			//������ת����regist.jsp
			return "backend/manageAddProd";
		}
		
	}
}
