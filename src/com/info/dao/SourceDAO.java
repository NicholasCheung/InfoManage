package com.info.dao;

import java.util.List;

import com.info.dto.SourceDTO;

public interface SourceDAO {

	List<SourceDTO> querySourceDTOs(SourceDTO sourceDTO);

	int addSource(SourceDTO sourceDTO);

	int updateSource(SourceDTO sourceDTO);
}
