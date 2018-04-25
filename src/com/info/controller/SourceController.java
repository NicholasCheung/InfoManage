package com.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.info.entity.UserDO;

public class SourceController extends HttpServlet {

	private static final long serialVersionUID = 767141281086231494L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "show")) {
				showSource(request, response);

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void showSource(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

}
