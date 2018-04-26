package com.info.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.info.dao.SourceDAO;
import com.info.database.DataBase;
import com.info.dto.SourceDTO;
import com.info.entity.CategoryDO;
import com.info.entity.SourceDO;
import com.info.entity.UserDO;

public class SourceDAOImpl extends DataBase implements SourceDAO {

	@Override
	public List<SourceDTO> querySourceDTOs(SourceDTO sourceDTO) {
		List<SourceDTO> result = new ArrayList<SourceDTO>();
		List<Object> params = new ArrayList<Object>();

		SourceDO sourceDO = sourceDTO.getSourceDO();
		CategoryDO categoryDO = sourceDTO.getCategoryDO();
		UserDO userDO = sourceDTO.getUserDO();

		String query = "SELECT ts.*, tc.category_id, tc.category_name, tc.category_desc, tu.user_id, tu.user_name "
				+ "FROM t_source ts " + "INNER JOIN t_source_category tsc ON ts.source_id = tsc.source_id "
				+ "INNER JOIN t_category tc ON tsc.category_id = tc.category_id "
				+ "INNER JOIN t_user tu ON tsc.user_id = tu.user_id ";
		query = query.concat("where ts.status = 1");
		if (null != sourceDO) {

			if (StringUtils.isNotBlank(sourceDO.getSourceName())) {
				query = query.concat(" and source_name like ? ");
				params.add("%" + sourceDO.getSourceName() + "%");
			}

		}
		if (null != categoryDO) {
			if (null != categoryDO.getCategoryId()) {
				query = query.concat(" and tc.category_id = ? ");
				params.add(categoryDO.getCategoryId());
			}

			if (StringUtils.isNotBlank(categoryDO.getCategoryName())) {
				query = query.concat(" and tc.category_name like ? ");
				params.add("%" + categoryDO.getCategoryName() + "%");
			}
		}
		if (null != userDO) {
			if (StringUtils.isNotBlank(userDO.getUserName())) {
				query = query.concat(" and tu.user_name like ? ");
				params.add("%" + userDO.getUserName() + "%");
			}
			if (null != userDO.getUserId()) {
				query = query.concat(" and tu.user_id = ? ");
				params.add(userDO.getUserId());
			}
		}

		try {
			ResultSet rs = this.executeQuery(query, params);
			while (rs.next()) {
				SourceDTO sourceDTODB = new SourceDTO();
				CategoryDO categoryDB = new CategoryDO();
				categoryDB.setCategoryId(rs.getLong("tc.category_id"));
				categoryDB.setCategoryName(rs.getString("tc.category_name"));
				categoryDB.setCategoryDesc(rs.getString("tc.category_desc"));

				UserDO userDB = new UserDO();
				userDB.setUserId(rs.getLong("tu.user_id"));
				userDB.setUserName(rs.getString("tu.user_name"));

				SourceDO sourceDB = new SourceDO();
				sourceDB.setSourceId(rs.getLong("ts.source_id"));
				sourceDB.setSourceName(rs.getString("ts.source_name"));
				sourceDB.setSourceDesc(rs.getString("ts.source_desc"));
				sourceDB.setSourceType(rs.getInt("ts.source_type"));
				sourceDB.setSourceUrl(rs.getString("ts.source_url"));
				sourceDB.setGmtCreate(rs.getTimestamp("ts.gmt_create"));
				sourceDB.setStatus(rs.getInt("ts.status"));

				sourceDTODB.setSourceDO(sourceDB);
				sourceDTODB.setCategoryDO(categoryDB);
				sourceDTODB.setUserDO(userDB);
				result.add(sourceDTODB);
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
	public int addSource(SourceDTO sourceDTO) {

		SourceDO sourceDO = sourceDTO.getSourceDO();
		CategoryDO categoryDO = sourceDTO.getCategoryDO();
		Date gmtCreate = new Date();

		if (null != sourceDO && null != categoryDO) {

			String update = "insert into t_source(user_id,source_name, source_desc, source_url, source_type, gmt_create, status)values(?,?,?,?,?,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(sourceDO.getUserId());
			params.add(sourceDO.getSourceName());
			params.add(sourceDO.getSourceDesc());
			params.add(sourceDO.getSourceUrl());
			params.add(sourceDO.getSourceType());
			params.add(gmtCreate);
			params.add(1);
			Long sourceId = this.executeUpdateBack(update, params);

			if (null != sourceId) {
				update = "insert into t_source_category(source_id, category_id, user_id, gmt_create, status)values(?,?,?,?,?)";
				List<Object> paramsCat = new ArrayList<Object>();
				paramsCat.add(sourceId);
				paramsCat.add(categoryDO.getCategoryId());
				paramsCat.add(sourceDO.getUserId());
				paramsCat.add(gmtCreate);
				paramsCat.add(1);
				int resultCatSource = this.executeUpdate(update, paramsCat);
				return resultCatSource;
			}
		}
		return 0;
	}

	@Override
	public int updateSource(SourceDTO sourceDTO) {

		SourceDO sourceDO = sourceDTO.getSourceDO();

		if (null == sourceDO) {
			return 0;
		}

		String update = "update t_source set ";
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotBlank(sourceDO.getSourceName())) {
			update = update.concat("source_name = ?, ");
			params.add(sourceDO.getSourceName());
		}

		if (StringUtils.isNotBlank(sourceDO.getSourceDesc())) {
			update = update.concat("source_desc = ?, ");
			params.add(sourceDO.getSourceDesc());
		}

		if (null != sourceDO.getSourceType()) {
			update = update.concat("source_type = ?, ");
			params.add(sourceDO.getSourceType());
		}

		if (null != sourceDO.getStatus()) {
			update = update.concat("status = ?, ");
			params.add(sourceDO.getStatus());
		}

		update = update.concat("gmt_modify = ?  where source_id = ? ");
		params.add(new Date());
		params.add(sourceDO.getSourceId());

		return this.executeUpdate(update, params);
	}

}
