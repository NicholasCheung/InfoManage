package com.info.dao;

import java.util.List;

import com.info.dto.CommentDTO;
import com.info.entity.CommentDO;

public interface CommentDAO {

	List<CommentDTO> queryComments(CommentDO commentDO);

	CommentDO queryCommentById(Long categoryId);

	int addComment(CommentDO commentDO);

	int updateComment(CommentDO commentDO);
}
