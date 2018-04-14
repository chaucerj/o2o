package com.chaucer.o2o.dao;

import java.util.List;

import com.chaucer.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> queryProductCategoryList(long id);

}
