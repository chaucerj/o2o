package com.chaucer.o2o.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chaucer.o2o.entity.ProductCategory;

@Service
public interface ProductCategoryService {
	public List<ProductCategory> getProductCategoryList();

}
