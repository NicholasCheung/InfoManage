package com.info.dao;

import java.util.List;

import com.info.dto.CategoryDTO;
import com.info.entity.CategoryDO;

public interface CategoryDAO {

    List<CategoryDTO> queryCategorys(CategoryDTO categoryDTO, Boolean isLike);

    CategoryDO queryCategoryById(Long categoryId);

    int addCategory(CategoryDO categoryDO);
    
    int updateCategory(CategoryDO categoryDO);
}
