package com.info.dao;

import java.util.List;

import com.info.entity.CategoryDO;

public interface CategoryDAO {

    List<CategoryDO> queryCategorys(CategoryDO categoryDO);

    CategoryDO queryCategoryById(Long categoryId);

    int addCategory(CategoryDO categoryDO);
    
    int updateCategory(CategoryDO categoryDO);
}
