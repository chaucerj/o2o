package com.chaucer.o2o.dao;

import java.util.List;

import com.chaucer.o2o.entity.ProductImg;

/**
 * 批量添加图片
 * 
 * @author Chaucer
 *
 */
public interface ProductImgDao {
	//批量插入产品图片
	int batchInsertProductImg(List<ProductImg> productImgList);
	//通过产品编号批量删除产品图片（编辑操作时）
	int deleteProductImgByProductId(Long productId);
}
