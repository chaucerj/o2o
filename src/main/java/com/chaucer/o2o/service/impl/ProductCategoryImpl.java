package com.chaucer.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaucer.o2o.dao.ProductCategoryDao;
import com.chaucer.o2o.dao.ProductDao;
import com.chaucer.o2o.dto.ProductCategoryExecution;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.enums.ProductCategoryStatusEnum;
import com.chaucer.o2o.exceptions.ProductCategoryOperationException;
import com.chaucer.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

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

	/**
	 * 优先清空该类品id的商品关联id为空
	 */
	@Override
	@Transactional
	public ProductCategoryExecution delProductCategory(long productCategoryId,
			long shopId) throws ProductCategoryOperationException {
		try{
			//解除tb_product中product_categoryId的关联
			int effectNum = productDao.updateProductCategory2Null(productCategoryId);
			if(effectNum<0){
				throw new ProductCategoryOperationException("更新分类失败");
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("删除商品分类操作失败"+e.toString());
		}
		
		try {
			int effectNum = productCategoryDao
					.deleteProductCategory(productCategoryId, shopId);
			if (effectNum <= 0) {
				throw new ProductCategoryOperationException("删除分类失败");
			} else {
				return new ProductCategoryExecution(
						ProductCategoryStatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException(
					"delete productCategory error" + e.getMessage());
		}
	}

}
