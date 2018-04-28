package com.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.info.entity.UserDO;

public class LoginOutController extends HttpServlet {

	private static final long serialVersionUID = -70642360782885143L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDO userDO = (UserDO) session.getAttribute("user");
		if (null != userDO) {
			session.setAttribute("user", null);
		}
		request.getRequestDispatcher("/").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
