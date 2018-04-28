package com.info.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.dao.CategoryDAO;
import com.info.database.DataBase;
import com.info.dto.CategoryDTO;
import com.info.entity.CategoryDO;
import com.info.entity.UserDO;

public class CategoryDAOImpl extends DataBase implements CategoryDAO {

	@Override
	public List<CategoryDTO> queryCategorys(CategoryDTO categoryDTO, Boolean isLike) {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<Object> params = new ArrayList<Object>();

		CategoryDO categoryDO = categoryDTO.getCategoryDO();
		UserDO userDO = categoryDTO.getUserDO();

		String query = "select tc.*, tu.user_name from t_category tc inner join t_user tu on tc.user_id = tu.user_id ";
		boolean bool = false;
		if (null != categoryDO) {
			query = query.concat("where ");
			if (StringUtils.isNotBlank(categoryDO.getCategoryName())) {
				if (isLike) {
					query = query.concat("category_name like ?");
					params.add("%" + categoryDO.getCategoryName() + "%");
				} else {
					query = query.concat("category_name = ?");
					params.add(categoryDO.getCategoryName());
				}
				bool = true;
			}

			if (null != categoryDO.getStatus()) {
				if (bool) {
					query = query.concat("and ");
				}
				query = query.concat("tc.status = ? ");
				params.add(categoryDO.getStatus());
				bool = true;
			}

			if (null != categoryDO.getUserId() && !categoryDO.getUserId().equals(0L)) {
				if (bool) {
					query = query.concat("and ");
				}
				query = query.concat("tc.user_id = ? ");
				params.add(categoryDO.getUserId());
				bool = true;
			}
		}

		if (null != userDO) {
			if (!bool) {
				query = query.concat("where ");
			} else if (StringUtils.isNotBlank(userDO.getUserName())) {
				query = query.concat("and ");
				query = query.concat("user_name like ?");
				params.add("%" + userDO.getUserName() + "%");
				bool = true;
			}

		}

		query = query + " order by tc.gmt_create desc";
		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {
				CategoryDO categoryDB = new CategoryDO();
				categoryDB.setCategoryId(rs.getLong("category_id"));
				categoryDB.setUserId(rs.getLong("user_id"));
				categoryDB.setCategoryName(rs.getString("category_name"));
				categoryDB.setCategoryDesc(rs.getString("category_desc"));
				categoryDB.setGmtCreate(rs.getTimestamp("gmt_create"));
				categoryDB.setGmtModify(rs.getTimestamp("gmt_modify"));
				categoryDB.setStatus(rs.getInt("status"));

				UserDO userDB = new UserDO();
				userDB.setUserName(rs.getString("user_name"));

				CategoryDTO categoryDTODB = new CategoryDTO();
				categoryDTODB.setCategoryDO(categoryDB);
				categoryDTODB.setUserDO(userDB);

				result.add(categoryDTODB);
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
	public CategoryDO queryCategoryById(Long categoryId) {
		List<Object> params = new ArrayList<Object>();

		String query = "select * from t_category where category_id = ?";
		params.add(categoryId);
		CategoryDO categoryDB = new CategoryDO();
		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {

				categoryDB.setCategoryId(rs.getLong("category_id"));
				categoryDB.setUserId(rs.getLong("user_id"));
				categoryDB.setCategoryName(rs.getString("category_name"));
				categoryDB.setCategoryDesc(rs.getString("category_desc"));
				categoryDB.setGmtCreate(rs.getTimestamp("gmt_create"));
				categoryDB.setGmtModify(rs.getTimestamp("gmt_modify"));
				categoryDB.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-" + e);
			return null;
		} finally {
			this.close();
		}
		return categoryDB;
	}

	@Override
	public int addCategory(CategoryDO categoryDO) {

		String update = "insert into t_category(user_id,category_name, category_desc, gmt_create, status)values(?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(categoryDO.getUserId());
		params.add(categoryDO.getCategoryName());
		params.add(categoryDO.getCategoryDesc());
		params.add(categoryDO.getGmtCreate());
		params.add(categoryDO.getStatus());
		return this.executeUpdate(update, params);
	}

	@Override
	public int updateCategory(CategoryDO categoryDO) {
		String update = "update t_category set ";
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotBlank(categoryDO.getCategoryName())) {
			update = update.concat("category_name = ?, ");
			params.add(categoryDO.getCategoryName());
		}

		if (StringUtils.isNotBlank(categoryDO.getCategoryDesc())) {
			update = update.concat("category_desc = ?, ");
			params.add(categoryDO.getCategoryDesc());
		}

		if (null != categoryDO.getStatus()) {
			update = update.concat("status = ?, ");
			params.add(categoryDO.getStatus());
		}

		update = update.concat("gmt_modify = ?  where category_id = ? ");
		params.add(new Date());
		params.add(categoryDO.getCategoryId());

		return this.executeUpdate(update, params);
	}
}
