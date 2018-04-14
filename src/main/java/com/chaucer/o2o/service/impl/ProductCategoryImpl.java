package com.chaucer.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaucer.o2o.dao.ProductCategoryDao;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {

		return productCategoryDao.queryProductCategoryList(shopId);
	}

}
