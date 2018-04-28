package com.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.info.dao.UserDAO;
import com.info.dao.impl.UserDAOImpl;
import com.info.entity.UserDO;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -2774545038842957201L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");

		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(userType)) {
			response.getWriter().write("请输入账号密码");
			return;
		}

		if ("1".equals(userType)) {
			UserDAO userDAO = new UserDAOImpl();

			UserDO userDO = new UserDO();
			userDO.setUserName(userName);
			userDO.setUserPasswd(password);
			userDO.setStatus(1);
			List<UserDO> userDOs = userDAO.queryUserDOs(userDO, false);

			if (userDOs.isEmpty() || userDOs.size() > 1) {
				response.getWriter().write("账号密码错误");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", userDOs.get(0));
				response.getWriter().write("success");
			}
		} else if ("2".equals(userType)) {
			if ("admin".equals(userName) && "admin".equals(password)) {
				UserDO userDO = new UserDO();
				userDO.setUserName("admin");
				userDO.setUserId(0L);
				HttpSession session = request.getSession();
				session.setAttribute("user", userDO);
				response.getWriter().write("success");
			} else {
				response.getWriter().write("账号密码错误");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
