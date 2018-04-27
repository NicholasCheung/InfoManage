package com.info.dto;

import com.info.entity.CommentDO;
import com.info.entity.UserDO;

public class CommentDTO {

	private UserDO userDO;

	private CommentDO commentDO;

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public CommentDO getCommentDO() {
		return commentDO;
	}

	public void setCommentDO(CommentDO commentDO) {
		this.commentDO = commentDO;
	}

}
