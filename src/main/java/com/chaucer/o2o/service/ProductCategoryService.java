package com.chaucer.o2o.service;

import java.util.List;

import com.chaucer.o2o.dto.ProductCategoryExecution;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);

	ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategory)
					throws ProductCategoryOperationException;

	/**
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution delProductCategory(long productCategoryId,
			long shopId) throws ProductCategoryOperationException;
}
