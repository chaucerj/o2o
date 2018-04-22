package com.chaucer.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaucer.o2o.entity.Product;

public interface ProductDao {
	// 新增产品信息
	int insertProduct(Product product);

	// 通过产品id查询
	Product queryProductById(long productId);

	// 更新产品信息
	int updateProduct(Product product);

	List<Product> queryProductList(@Param("productCondition")Product productCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

	int queryProductCount(@Param("productCondition")Product productCondition);

	int deleteProduct(@Param("productId")Long productId, @Param("shopId")long shopId);

}
