package com.info.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.dao.UserDAO;
import com.info.database.DataBase;
import com.info.entity.UserDO;

public class UserDAOImpl extends DataBase implements UserDAO {

	@Override
	public List<UserDO> queryUserDOs(UserDO userDO) {
		List<UserDO> result = new ArrayList<UserDO>();
		List<Object> params = new ArrayList<Object>();

		String query = "select * from t_user ";
		if (null != userDO) {
			query = query.concat("where ");
			boolean bool = false;
			if (StringUtils.isNotBlank(userDO.getUserName())) {
				query = query.concat("user_name = ? ");
				params.add(userDO.getUserName());
				bool = true;
			}

			if (null != userDO.getStatus()) {
				if (bool) {
					query = query.concat("and ");
				}
				query = query.concat("status = ? ");
				params.add(userDO.getStatus());
			}

			if (null != userDO.getUserPasswd()) {
				if (bool) {
					query = query.concat("and ");
				}
				query = query.concat("user_passwd = ? ");
				params.add(userDO.getUserPasswd());
			}
		}

		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {
				UserDO userDODB = new UserDO();
				userDODB.setUserId(rs.getLong("user_id"));
				userDODB.setUserName(rs.getString("user_name"));
				userDODB.setUserPasswd(rs.getString("user_passwd"));
				userDODB.setGmtCreate(rs.getDate("gmt_create"));
				userDODB.setGmtModify(rs.getDate("gmt_modify"));
				userDODB.setStatus(rs.getInt("status"));

				result.add(userDODB);
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
	public UserDO queryByUserId(Long userId) {
		List<Object> params = new ArrayList<Object>();

		String query = "select * from t_user where user_id = ?";
		params.add(userId);
		UserDO userDODB = new UserDO();
		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {

				userDODB.setUserId(rs.getLong("user_id"));
				userDODB.setUserName(rs.getString("user_name"));
				userDODB.setUserPasswd(rs.getString("user_passwd"));
				userDODB.setGmtCreate(rs.getDate("gmt_create"));
				userDODB.setGmtModify(rs.getDate("gmt_modify"));
				userDODB.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
			return null;
		} finally {
			this.close();
		}
		return userDODB;
	}

	@Override
	public int addUserDO(UserDO userDO) {
		String update = "insert into t_user(user_name, user_passwd, gmt_create, status)values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(userDO.getUserName());
		params.add(userDO.getUserPasswd());
		params.add(userDO.getGmtCreate());
		params.add(userDO.getStatus());
		return this.executeUpdate(update, params);
	}
}
