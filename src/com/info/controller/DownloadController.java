package com.info.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = -8502081766749841735L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		String path = getServletContext().getRealPath("/");
		String fileName = req.getParameter("filename");
		String filename = null;
		filename = new String(fileName.getBytes("8859_1"), "utf-8");
		// filename = new String(filename.getBytes("8859_1"),"uft-8");
		System.out.println("路径：" + path + "文件名：" + filename);
		File file = new File(path + filename);
		if (file.exists()) {
			// 由于下载的时候与浏览器的编码不符，浏览器不能识别中文编码，这里要进行转换
			String value = new String(filename.getBytes("utf-8"), "ISO-8859-1");
			resp.setContentType("application/x-msdownload");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + value + "\"");
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream outputStream = resp.getOutputStream();
			byte b[] = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);
			}

			outputStream.close();
			inputStream.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
