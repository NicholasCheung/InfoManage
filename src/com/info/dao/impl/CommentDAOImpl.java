package com.info.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.info.dao.CommentDAO;
import com.info.database.DataBase;
import com.info.dto.CommentDTO;
import com.info.entity.CommentDO;
import com.info.entity.UserDO;

public class CommentDAOImpl extends DataBase implements CommentDAO {

	@Override
	public List<CommentDTO> queryComments(CommentDO commentDO) {
		List<CommentDTO> result = new ArrayList<CommentDTO>();
		List<Object> params = new ArrayList<Object>();

		String query = "select tc.*, tu.user_name from t_comment tc inner join t_user tu on tc.user_id = tu.user_id where source_id = ? ";
		params.add(commentDO.getSourceId());

		if (null != commentDO.getStatus()) {
			query = query + " and tc.status= ? ";
			params.add(commentDO.getStatus());
		}
		
		query = query + " order by tc.gmt_create desc";

		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {
				CommentDTO commentDTO = new CommentDTO();

				CommentDO commentDB = new CommentDO();
				commentDB.setCommentId(rs.getLong("comment_id"));
				commentDB.setContent(rs.getString("content"));
				commentDB.setSourceId(rs.getLong("source_id"));
				commentDB.setUserId(rs.getLong("user_id"));
				commentDB.setGmtCreate(rs.getTimestamp("gmt_create"));
				commentDB.setGmtModify(rs.getTimestamp("gmt_modify"));
				commentDB.setStatus(rs.getInt("status"));

				UserDO userDO = new UserDO();
				userDO.setUserName(rs.getString("user_name"));

				commentDTO.setCommentDO(commentDB);
				commentDTO.setUserDO(userDO);

				result.add(commentDTO);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
			return null;
		} finally {
			this.close();
		}
		return result;
	}

	@Override
	public CommentDO queryCommentById(Long commentId) {
		List<Object> params = new ArrayList<Object>();

		String query = "select tc.* from t_comment tc where comment_id = ?";
		params.add(commentId);
		CommentDO commentDB = null;
		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {

				commentDB = new CommentDO();
				commentDB.setCommentId(rs.getLong("comment_id"));
				commentDB.setContent(rs.getString("content"));
				commentDB.setSourceId(rs.getLong("source_id"));
				commentDB.setUserId(rs.getLong("user_id"));
				commentDB.setGmtCreate(rs.getTimestamp("gmt_create"));
				commentDB.setGmtModify(rs.getTimestamp("gmt_modify"));
				commentDB.setStatus(rs.getInt("status"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
			return null;
		} finally {
			this.close();
		}
		return commentDB;
	}

	@Override
	public int addComment(CommentDO commentDO) {
		String update = "insert into t_comment(user_id,source_id, content, gmt_create, status)values(?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(commentDO.getUserId());
		params.add(commentDO.getSourceId());
		params.add(commentDO.getContent());
		params.add(new Date());
		params.add(1);
		return this.executeUpdate(update, params);
	}

	@Override
	public int updateComment(CommentDO commentDO) {
		String update = "update t_comment set ";
		List<Object> params = new ArrayList<Object>();

		if (null != commentDO.getStatus()) {
			update = update.concat("status = ?, ");
			params.add(commentDO.getStatus());
		}

		update = update.concat("gmt_modify = ?  where comment_id = ? ");
		params.add(new Date());
		params.add(commentDO.getCommentId());

		return this.executeUpdate(update, params);
	}

}
