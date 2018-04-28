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

public class UserController extends HttpServlet {

	private static final long serialVersionUID = -5617650356894962958L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDO userDO = (UserDO) session.getAttribute("user");
		if (null == userDO) {
			System.out.println("请先登录");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		response.setContentType("text/html;charset=utf-8");

		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "del")) {
				delUser(request, response);

			}
		} else {
			showUser(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void delUser(HttpServletRequest request, HttpServletResponse response) {
		try {

			String userIdStr = request.getParameter("userId");
			String statusStr = request.getParameter("status");
			if (StringUtils.isBlank(userIdStr)) {

				response.getWriter().write("fail");
				return;
			}

			Long userId = Long.parseLong(userIdStr);

			UserDO userDO = new UserDO();
			userDO.setStatus(Integer.parseInt(statusStr));
			userDO.setUserId(userId);

			UserDAO userDAO = new UserDAOImpl();
			int result = userDAO.updateUserDO(userDO);
			if (result == 1) {
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}

	}

	private void showUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = request.getParameter("userName");
			UserDO userDO = null;

			if (StringUtils.isNotBlank(userName)) {
				userName = new String(userName.getBytes("iso8859-1"), "utf-8");

				userDO = new UserDO();
				userDO.setUserName(userName);
			}

			UserDAO userDAO = new UserDAOImpl();
			List<UserDO> userDOs = userDAO.queryUserDOs(userDO, true);

			request.setAttribute("userName", userName);
			request.setAttribute("userDOs", userDOs);
			request.getRequestDispatcher("/user.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

}
