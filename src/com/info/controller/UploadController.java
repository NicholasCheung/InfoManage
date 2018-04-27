package com.info.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadController extends HttpServlet {

	private static final long serialVersionUID = 1663398903824699526L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从request当中获取流信息
		InputStream fileSource = request.getInputStream();
		String routePath = getServletContext().getRealPath("/");
		String tempFileName = routePath + "tempFile";
		// tempFile指向临时文件
		File tempFile = new File(tempFileName);
		// outputStram文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[1024];
		int n;
		while ((n = fileSource.read(b)) != -1) {
			outputStream.write(b, 0, n);
		}
		// 关闭输出流、输入流
		outputStream.close();
		fileSource.close();

		// 获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		String str2 = randomFile.readLine();
		// 编码转换
		str2 = new String(str2.getBytes("8859_1"), "utf-8");
		String str = randomFile.readLine();
		str = new String(str.getBytes("8859_1"), "utf-8");
		int beginIndex = str.lastIndexOf("=") + 2;
		int endIndex = str.lastIndexOf("\"");
		String filename = str.substring(beginIndex, endIndex);

		// 重新定位文件指针到文件头
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;
		// 获取文件内容 开始位置
		while ((n = randomFile.readByte()) != -1 && i <= 4) {
			if (n == '\n') {
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		startPosition = randomFile.getFilePointer() - 1;
		// 获取文件内容 结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while (endPosition >= 0 && j <= 2) {
			endPosition--;
			randomFile.seek(endPosition);
			if (randomFile.readByte() == '\n') {
				j++;
			}
		}
		endPosition = endPosition - 1;

		// 设置保存上传文件的路径
		// 路径可以自行设置
		String pathFile = "upload/";
		String realPath = routePath + pathFile;
		System.out.println("文件地址：" + realPath);
		// String realPath = getServletContext().getRealPath("/") + "images";
		File fileupload = new File(realPath);
		if (!fileupload.exists()) {
			fileupload.mkdir();
		}
		File saveFile = new File(realPath, filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
		// 从临时文件当中读取文件内容（根据起止位置获取）
		randomFile.seek(startPosition);
		while (startPosition < endPosition) {
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		// 关闭输入输出流、删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(filename);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
