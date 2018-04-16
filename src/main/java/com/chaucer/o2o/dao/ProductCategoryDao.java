package com.chaucer.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 删除商品，2个以上参数Mybatis无法识别
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int deleteProductCategory(@Param("produCategoryId") long productCategoryId,
			@Param("shopId") long shopId);
}
