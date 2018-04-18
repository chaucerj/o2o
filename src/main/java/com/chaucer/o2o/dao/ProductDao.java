package com.chaucer.o2o.dao;

import com.chaucer.o2o.entity.Product;

public interface ProductDao {
	// 新增产品信息
	int insertProduct(Product product);

	// 通过产品id查询
	Product queryProductById(long productId);

	// 更新产品信息
	int updateProduct(Product product);

}
