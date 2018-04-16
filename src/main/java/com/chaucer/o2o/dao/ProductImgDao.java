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
	int batchInsertProductImg(List<ProductImg> productImgList);
}
