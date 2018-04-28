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

import com.info.dao.CommentDAO;
import com.info.dao.SourceDAO;
import com.info.dao.impl.CommentDAOImpl;
import com.info.dao.impl.SourceDAOImpl;
import com.info.dto.CommentDTO;
import com.info.dto.SourceDTO;
import com.info.entity.CommentDO;
import com.info.entity.SourceDO;
import com.info.entity.UserDO;

public class CommentController extends HttpServlet {

	private static final long serialVersionUID = -5859073832124972675L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operate = request.getParameter("operate");
		if (StringUtils.isNotBlank(operate)) {
			if (StringUtils.equals(operate, "add")) {
				addComment(request, response);

			} else if (StringUtils.equals(operate, "del")) {
				delComment(request, response);

			}
		} else {
			showComment(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void delComment(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			String commentIdStr = request.getParameter("commentId");

			if (StringUtils.isBlank(commentIdStr)) {
				System.out.println("请传入评论id");
				return;
			}

			Long commentId = Long.parseLong(commentIdStr);
			Long userId = userDO.getUserId();

			CommentDAO commentDAO = new CommentDAOImpl();

			CommentDO commentDB = commentDAO.queryCommentById(commentId);
			if (userId.equals(0L) || userId.equals(commentDB.getUserId())) {

				CommentDO commentDO = new CommentDO();

				commentDO.setCommentId(commentId);
				commentDO.setStatus(0);
				commentDO.setGmtModify(new Date());
				int result = commentDAO.updateComment(commentDO);
				if (result == 1) {
					response.getWriter().write("success");
				}
			} else {
				response.getWriter().write("您无权删除该评论");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

	private void addComment(HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO) {
				System.out.println("请先登录");
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

			Long userId = userDO.getUserId();

			String sourceIdStr = request.getParameter("sourceId");
			String content = request.getParameter("content");

			if (StringUtils.isBlank(sourceIdStr) || StringUtils.isBlank(content)) {
				return;
			}

			CommentDAO commentDAO = new CommentDAOImpl();
			CommentDO commentDO = new CommentDO();

			commentDO.setContent(content);
			commentDO.setGmtCreate(new Date());
			commentDO.setSourceId(Long.parseLong(sourceIdStr));
			commentDO.setUserId(userId);
			commentDO.setStatus(1);

			int result = commentDAO.addComment(commentDO);

			if (result == 1) {
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
		return;
	}

	private void showComment(HttpServletRequest request, HttpServletResponse response) {
		try {
			String sourceIdStr = request.getParameter("sourceId");

			if (StringUtils.isBlank(sourceIdStr)) {
				System.out.println("请传入资源id");
				return;
			}

			SourceDTO sourceDTO = new SourceDTO();
			SourceDO sourceDO = new SourceDO();

			sourceDO.setSourceId(Long.parseLong(sourceIdStr));
			sourceDTO.setSourceDO(sourceDO);

			SourceDAO sourceDAO = new SourceDAOImpl();
			List<SourceDTO> sourceDTOs = sourceDAO.querySourceDTOs(sourceDTO);
			request.setAttribute("sourceDTO", sourceDTOs.get(0));

			CommentDAO commentDAO = new CommentDAOImpl();
			CommentDO commentDO = new CommentDO();

			commentDO.setSourceId(Long.parseLong(sourceIdStr));

			HttpSession session = request.getSession();
			UserDO userDO = (UserDO) session.getAttribute("user");
			if (null == userDO || !userDO.getUserId().equals(0L)) {
				commentDO.setStatus(1);
			}

			List<CommentDTO> commentDTOs = commentDAO.queryComments(commentDO);
			request.setAttribute("commentDTOs", commentDTOs);
			request.getRequestDispatcher("/comment.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
		}
	}

}
