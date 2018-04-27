package com.info.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.info.dao.CategoryDAO;
import com.info.dao.impl.CategoryDAOImpl;
import com.info.entity.CategoryDO;
import com.info.entity.UserDO;

public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = -3865177419766201827L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "add")) {
				addCategory(request, response);

			} else if (StringUtils.equals(operate, "show")) {
				showCategory(request, response);

			} else if (StringUtils.equals(operate, "toEdit")) {
				toEditCategory(request, response);

			} else if (StringUtils.equals(operate, "edit")) {
				editCategory(request, response);

			} else if (StringUtils.equals(operate, "del")) {
				delCategory(request, response);

			} else if (StringUtils.equals(operate, "toAdd")) {
				toAddCategory(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 添加类别
	 * 
	 * @param request
	 * @return
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			Long userId = userDO.getUserId();

			CategoryDAO categoryDAO = new CategoryDAOImpl();

			String categoryName = request.getParameter("categoryName");
			String categoryDesc = request.getParameter("categoryDesc");

			if (StringUtils.isBlank(categoryName)) {
				return;
			}

			CategoryDO categoryDO = new CategoryDO();
			categoryDO.setCategoryName(categoryName);
			categoryDO.setUserId(userId);
			categoryDO.setStatus(1);

			int result = 0;
			List<CategoryDO> categoryDOs = categoryDAO.queryCategorys(categoryDO, false);
			if (null == categoryDOs || categoryDOs.isEmpty()) {
				categoryDO.setUserId(userId);
				categoryDO.setCategoryDesc(categoryDesc);
				categoryDO.setStatus(1);
				categoryDO.setGmtCreate(new Date());
				result = categoryDAO.addCategory(categoryDO);
			} else {
				response.getWriter().write("该类别已经存在");
				return;
			}

			if (result == 1) {
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
		return;
	}

	/**
	 * 显示种类列表
	 * 
	 * @param request
	 * @param response
	 */
	private void showCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			Long userId = userDO.getUserId();

			CategoryDAO categoryDAO = new CategoryDAOImpl();
			CategoryDO categoryDOQuery = new CategoryDO();
			categoryDOQuery.setStatus(1);
			categoryDOQuery.setUserId(userId);
			List<CategoryDO> categoryDOs = categoryDAO.queryCategorys(categoryDOQuery, true);
			request.setAttribute("categoryDOs", categoryDOs);
			request.getRequestDispatcher("/category.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 跳转去编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private void toEditCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String categoryIdStr = request.getParameter("categoryId");

			if (StringUtils.isBlank(categoryIdStr)) {
				System.out.println("请传入类别id");
				return;
			}

			Long categoryId = Long.parseLong(categoryIdStr);
			CategoryDAO categoryDAO = new CategoryDAOImpl();
			CategoryDO categoryDO = categoryDAO.queryCategoryById(categoryId);

			request.setAttribute("categoryDO", categoryDO);
			request.getRequestDispatcher("/editCategory.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 跳转去添加辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private void toAddCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 编辑类别
	 * 
	 * @param request
	 * @param response
	 */
	private void editCategory(HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String categoryIdStr = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
			String categoryDesc = request.getParameter("categoryDesc");

			if (StringUtils.isBlank(categoryName)) {
				System.out.println("请输入类别名称");
				return;
			}

			if (StringUtils.isBlank(categoryIdStr)) {
				System.out.println("请传入类别id");
				return;
			}

			Long categoryId = Long.parseLong(categoryIdStr);

			CategoryDAO categoryDAO = new CategoryDAOImpl();
			CategoryDO categoryDO = categoryDAO.queryCategoryById(categoryId);
			if (null != categoryDO) {
				CategoryDO categoryDOUpdate = new CategoryDO();
				categoryDOUpdate.setCategoryDesc(categoryDesc);
				categoryDOUpdate.setCategoryId(categoryId);
				categoryDOUpdate.setCategoryName(categoryName);
				categoryDOUpdate.setGmtModify(new Date());

				int result = categoryDAO.updateCategory(categoryDOUpdate);
				if (result == 1) {
					response.getWriter().write("success");
				}
			} else {
				System.out.println("不存在要编辑的类别");
				return;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 删除类别
	 * 
	 * @param request
	 * @param response
	 */
	private void delCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String categoryIdStr = request.getParameter("categoryId");

			if (StringUtils.isBlank(categoryIdStr)) {
				System.out.println("请传入类别id");
				return;
			}

			Long categoryId = Long.parseLong(categoryIdStr);

			CategoryDAO categoryDAO = new CategoryDAOImpl();
			CategoryDO categoryDOUpdate = new CategoryDO();
			categoryDOUpdate.setCategoryId(categoryId);
			categoryDOUpdate.setStatus(0);
			categoryDOUpdate.setGmtModify(new Date());
			int result = categoryDAO.updateCategory(categoryDOUpdate);
			if (result == 1) {
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}
}
