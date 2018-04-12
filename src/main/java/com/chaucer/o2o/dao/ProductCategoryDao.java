package com.chaucer.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaucer.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> queryProductCategory(
			@Param("productCategoryCondition") ProductCategory productCategoryCondition);

}
