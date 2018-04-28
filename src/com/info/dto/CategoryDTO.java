package com.info.dto;

import com.info.entity.CategoryDO;
import com.info.entity.UserDO;

public class CategoryDTO {

	private UserDO userDO;

	private CategoryDO categoryDO;

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public CategoryDO getCategoryDO() {
		return categoryDO;
	}

	public void setCategoryDO(CategoryDO categoryDO) {
		this.categoryDO = categoryDO;
	}

}
