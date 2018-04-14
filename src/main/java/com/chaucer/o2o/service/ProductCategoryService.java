package com.chaucer.o2o.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chaucer.o2o.dto.ProductCategoryExecution;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.exceptions.ProductCategoryOperationException;

@Service
public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);

	ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategory)
					throws ProductCategoryOperationException;
}
