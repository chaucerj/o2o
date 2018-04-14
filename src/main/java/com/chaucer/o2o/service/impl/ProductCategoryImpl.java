package com.chaucer.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaucer.o2o.dao.ProductCategoryDao;
import com.chaucer.o2o.dto.ProductCategoryExecution;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.enums.ProductCategoryStatusEnum;
import com.chaucer.o2o.exceptions.ProductCategoryOperationException;
import com.chaucer.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {

		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList)
					throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectNum = productCategoryDao
						.batchInsertProductCategory(productCategoryList);
				if (effectNum <= 0) {
					throw new ProductCategoryOperationException("创建店铺分类失败");
				} else {
					return new ProductCategoryExecution(
							ProductCategoryStatusEnum.SUCCESS,
							productCategoryList);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException(
						"batchProductCategory error" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(
					ProductCategoryStatusEnum.EMPTY_LIST);
		}
	}

}
