package cn.tedu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ToViewController {
	/**
	 * 转发跳转相应页面
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
	@RequestMapping("/toManage.action")
	public String toManage() throws IOException, Exception{
		return "backend/manage";
	}
	@RequestMapping("/toTop.action")
	public String toTop() throws IOException, Exception{
		return "backend/_top";
	}
	@RequestMapping("/toLeft.action")
	public String toLeft() throws IOException, Exception{
		return "backend/_left";
	}
	@RequestMapping("/toRight.action")
	public String toRight() throws IOException, Exception{
		return "backend/_right";
	}
	
}
