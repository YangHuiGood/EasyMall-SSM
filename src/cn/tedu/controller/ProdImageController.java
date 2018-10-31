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
		// ����֤���ԣ�
		// 3. ִ���߼�-ͨ����������ȡͼƬ�����ݣ�ͨ�������д���û�
		FileInputStream fis = null;
		try {
			// ����ͼƬurl��ȡͼƬ����ʵ·��
			String realPath = req.getServletContext().getRealPath(imgurl);
			System.out.println("��ʵ·��Ϊ��"+realPath);
			// ��Ҫ��·����ͼƬ�ڷ������ϵľ���·��
			fis = new FileInputStream(realPath);
			// ��ȡ��Ӧ��ʵ���������������ݵ������
			ServletOutputStream sos = resp.getOutputStream();
			// �ӷ��������ض�ȡͼƬ��һ��������
			byte[] array = new byte[1024];
			int len = fis.read(array);
			while (len != -1) {
				// ��Ӧ��ʵ�����������ͼƬ������
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
