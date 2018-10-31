package cn.tedu.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdImageController {
	@RequestMapping("/prodImage.action")
	public void prodImage(String imgurl,HttpServletRequest req,HttpServletResponse resp){
		// 表单验证（略）
		// 3. 执行逻辑-通过输入流读取图片的内容，通过输出流写给用户
		FileInputStream fis = null;
		try {
			// 根据图片url获取图片的真实路径
			String realPath = req.getServletContext().getRealPath(imgurl);
			System.out.println("真实路径为："+realPath);
			// 需要的路径是图片在服务器上的绝对路径
			fis = new FileInputStream(realPath);
			// 获取向应答实体内容中输入内容的输出流
			ServletOutputStream sos = resp.getOutputStream();
			// 从服务器本地读取图片的一部分内容
			byte[] array = new byte[1024];
			int len = fis.read(array);
			while (len != -1) {
				// 向应答实体内容中输出图片的内容
				sos.write(array, 0, len);
				len = fis.read(array);
			}
			sos.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					fis = null;
				}
			}
		}
	}
}
