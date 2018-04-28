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
import com.info.dao.SourceDAO;
import com.info.dao.impl.CategoryDAOImpl;
import com.info.dao.impl.SourceDAOImpl;
import com.info.dto.CategoryDTO;
import com.info.dto.SourceDTO;
import com.info.entity.CategoryDO;
import com.info.entity.SourceDO;
import com.info.entity.UserDO;

public class SourceController extends HttpServlet {

	private static final long serialVersionUID = 767141281086231494L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "show") || StringUtils.equals(operate, "show_clint")) {
				showSource(request, response);

			} else if (StringUtils.equals(operate, "toAdd")) {
				toAddSource(request, response);

			} else if (StringUtils.equals(operate, "add")) {
				addSource(request, response);

			} else if (StringUtils.equals(operate, "toEdit")) {
				toEditSource(request, response);

			} else if (StringUtils.equals(operate, "edit")) {
				editSource(request, response);

			} else if (StringUtils.equals(operate, "del")) {
				delSource(request, response);

			}
		} else {
			showSource(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 删除资源
	 * 
	 * @param request
	 * @param response
	 */
	private void delSource(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String sourceIdStr = request.getParameter("sourceId");

			if (StringUtils.isBlank(sourceIdStr)) {
				System.out.println("请传入资源id");
				return;
			}

			Long sourceId = Long.parseLong(sourceIdStr);

			SourceDAO sourceDAO = new SourceDAOImpl();

			SourceDO sourceDO = new SourceDO();
			sourceDO.setSourceId(sourceId);
			sourceDO.setStatus(0);
			sourceDO.setGmtModify(new Date());

			SourceDTO sourceDTO = new SourceDTO();
			sourceDTO.setSourceDO(sourceDO);
			int result = sourceDAO.updateSource(sourceDTO);
			if (result == 1) {
				response.getWriter().write("success");
			}
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
	private void toEditSource(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String sourceIdStr = request.getParameter("sourceId");

			if (StringUtils.isBlank(sourceIdStr)) {
				System.out.println("请传入资源id");
				return;
			}

			Long sourceId = Long.parseLong(sourceIdStr);

			SourceDAO sourceDAO = new SourceDAOImpl();

			SourceDO sourceDO = new SourceDO();
			sourceDO.setSourceId(sourceId);

			SourceDTO sourceDTO = new SourceDTO();
			sourceDTO.setSourceDO(sourceDO);

			List<SourceDTO> sourceDTOs = sourceDAO.querySourceDTOs(sourceDTO);
			if (sourceDTOs.isEmpty() || sourceDTOs.size() > 1) {
				return;
			}

			request.setAttribute("sourceDTO", sourceDTOs.get(0));
			request.getRequestDispatcher("/editSource.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 编辑资源
	 * 
	 * @param request
	 * @param response
	 */
	private void editSource(HttpServletRequest request, HttpServletResponse response) {
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
	 * 添加资源
	 * 
	 * @param request
	 * @param response
	 */
	private void addSource(HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			Long userId = userDO.getUserId();

			String sourceName = request.getParameter("sourceName");
			String sourceDesc = request.getParameter("sourceDesc");
			String categoryIdStr = request.getParameter("categoryId");
			String sourceTypeStr = request.getParameter("sourceType");
			String sourceUrl = request.getParameter("sourceUrl");

			if (StringUtils.isBlank(sourceName) || StringUtils.isBlank(categoryIdStr)
					|| StringUtils.isBlank(sourceTypeStr) || StringUtils.isBlank(sourceUrl)) {
				response.getWriter().write("参数错误");
				return;
			}

			SourceDAO sourceDAO = new SourceDAOImpl();

			SourceDO sourceDO = new SourceDO();
			sourceDO.setUserId(userId);
			sourceDO.setSourceDesc(sourceDesc);
			sourceDO.setSourceName(sourceName);
			sourceDO.setSourceType(Integer.parseInt(sourceTypeStr));
			sourceDO.setSourceUrl(sourceUrl);

			CategoryDO categoryDO = new CategoryDO();
			categoryDO.setCategoryId(Long.parseLong(categoryIdStr));

			SourceDTO sourceDTO = new SourceDTO();
			sourceDTO.setCategoryDO(categoryDO);
			sourceDTO.setSourceDO(sourceDO);

			int result = sourceDAO.addSource(sourceDTO);
			if (result == 1) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("fail");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	/**
	 * 跳转去新增资源
	 * 
	 * @param request
	 * @param response
	 */
	private void toAddSource(HttpServletRequest request, HttpServletResponse response) {
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
			CategoryDO categoryDO = new CategoryDO();
			categoryDO.setUserId(userId);
			categoryDO.setStatus(1);
			
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryDO(categoryDO);
			
			List<CategoryDTO> categoryDTOs = categoryDAO.queryCategorys(categoryDTO, true);
			request.setAttribute("categoryDTOs", categoryDTOs);
			request.getRequestDispatcher("/addSource.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}

	}

	/**
	 * 显示资源列表
	 * 
	 * @param request
	 * @param response
	 */
	private void showSource(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String operate = request.getParameter("operate");

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");

			String sourceName = request.getParameter("sourceName");
			String categoryName = request.getParameter("categoryName");
			String userName = request.getParameter("userName");
			String sourceTypeStr = request.getParameter("sourceType");

			if (StringUtils.isNotBlank(sourceName)) {
				sourceName = new String(sourceName.getBytes("iso8859-1"), "utf-8");
			}

			if (StringUtils.isNotBlank(categoryName)) {
				categoryName = new String(categoryName.getBytes("iso8859-1"), "utf-8");
			}

			if (StringUtils.isNotBlank(userName)) {
				userName = new String(userName.getBytes("iso8859-1"), "utf-8");
			}

			SourceDTO sourceDTO = new SourceDTO();

			if (StringUtils.isNotBlank(sourceName) || StringUtils.isNotBlank(sourceTypeStr)) {
				SourceDO sourceDO = new SourceDO();
				sourceDO.setSourceName(sourceName);
				sourceDO.setSourceType(StringUtils.isNotBlank(sourceTypeStr) ? Integer.parseInt(sourceTypeStr) : null);
				sourceDTO.setSourceDO(sourceDO);
			}

			if (StringUtils.isNotBlank(categoryName)) {
				CategoryDO categoryDO = new CategoryDO();
				categoryDO.setCategoryName(categoryName);
				sourceDTO.setCategoryDO(categoryDO);
			}

			if (StringUtils.isNotBlank(userName) || null != userDO) {
				UserDO userDOQuery = new UserDO();
				userDOQuery.setUserName(userName);
				if (StringUtils.equals(operate, "show")) {
					userDOQuery.setUserId(userDO == null ? null : userDO.getUserId());
				}
				sourceDTO.setUserDO(userDOQuery);
			}

			SourceDAO sourceDAO = new SourceDAOImpl();
			List<SourceDTO> sourceDTOs = sourceDAO.querySourceDTOs(sourceDTO);
			request.setAttribute("sourceDTOs", sourceDTOs);

			request.setAttribute("sourceName", sourceName);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("userName", userName);
			request.setAttribute("sourceType", sourceTypeStr);

			if (StringUtils.equals(operate, "show")) {
				request.getRequestDispatcher("/source.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/sourceClient.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

}
