package com.chaucer.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.dao.ProductDao;
import com.chaucer.o2o.dao.ProductImgDao;
import com.chaucer.o2o.dto.ImageHolder;
import com.chaucer.o2o.dto.ProductExecution;
import com.chaucer.o2o.entity.Product;
import com.chaucer.o2o.entity.ProductImg;
import com.chaucer.o2o.enums.ProductStatusEnum;
import com.chaucer.o2o.exceptions.ProductOperationException;
import com.chaucer.o2o.service.ProductService;
import com.chaucer.o2o.util.ImageUtil;
import com.chaucer.o2o.util.PathUtil;

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	public ProductExecution getProductList(Product productCondition,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，获取productId
	// 3.结合productId批量处理商品详情图
	// 4.将商品详情图列表批量插入tb_product_img中

	@Override
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException {
		if (product != null && product.getShop() != null
				&& product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectNum = productDao.insertProduct(product);
				if (effectNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
				// 商品详情图不为空
				if (productImgList != null && productImgList.size() > 0) {
					addProductImgList(product, productImgList);
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败" + e.toString());
			}
			return new ProductExecution(ProductStatusEnum.SUCCESS);
		} else {
			return new ProductExecution(ProductStatusEnum.EMPTY);
		}
	}

	private void addProductImgList(Product product,
			List<ImageHolder> imgHolderList) {
		// 获取图片存储路径，这里直接存放到相应店铺文件夹下
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片一次去处理，并添加进productImg实体类
		for (ImageHolder imgHolder : imgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(imgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);

		}
		// 如果是有图片需要添加就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectNum = productImgDao
						.batchInsertProductImg(productImgList);
				if (effectNum <= 0) {
					throw new ProductOperationException("创建商品详情图失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图失败" + e.toString());
			}
		}
	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

}
