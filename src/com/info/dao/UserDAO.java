package com.info.dao;

import java.util.List;

import com.info.entity.UserDO;

public interface UserDAO {

	List<UserDO> queryUserDOs(UserDO userDO);

	UserDO queryByUserId(Long userId);

	int addUserDO(UserDO userDO);

	int updateUserDO(UserDO userDO);

}
