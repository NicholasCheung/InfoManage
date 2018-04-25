package com.info.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.info.dao.SourceDAO;
import com.info.database.DataBase;
import com.info.dto.SourceDTO;
import com.info.entity.CategoryDO;
import com.info.entity.SourceDO;

public class SourceDAOImpl extends DataBase implements SourceDAO {

	@Override
	public List<SourceDTO> querySourceDTOs(SourceDTO sourceDTO) {
		// TODO Auto-generated method stub
		return null;
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
				int resultCatSource = this.executeUpdate(update, params);
				return resultCatSource;
			}
		}
		return 0;
	}

	@Override
	public int updateSource(SourceDTO sourceDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
