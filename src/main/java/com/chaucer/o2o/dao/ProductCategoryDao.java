package com.chaucer.o2o.dao;

import java.util.List;

import com.chaucer.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> queryProductCategoryList(long id);

	/**
	 * 批量新增商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
