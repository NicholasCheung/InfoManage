package com.info.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.info.dao.UserDAO;
import com.info.dao.impl.UserDAOImpl;
import com.info.entity.UserDO;

public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = -2774545038842957201L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "add")) {
				register(request, response);

			} else if (StringUtils.equals(operate, "to")) {
				toRegister(request, response);

			}
		} else {
			toRegister(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void toRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");

		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			response.getWriter().write("请输入账号密码");
			return;
		}

		if (!StringUtils.equals(password, rePassword)) {
			response.getWriter().write("两次输入的密码不一致");
			return;
		}

		UserDAO userDAO = new UserDAOImpl();

		UserDO userDO = new UserDO();
		userDO.setUserName(userName);
		List<UserDO> userDOs = userDAO.queryUserDOs(userDO, false);

		if (!userDOs.isEmpty()) {
			response.getWriter().write("该用户已经存在");
			return;
		} else {
			userDO.setUserPasswd(password);
			userDO.setGmtCreate(new Date());
			userDO.setStatus(1);
			int result = userDAO.addUserDO(userDO);
			if (result == 1) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("fail");
			}
		}
	}
}
