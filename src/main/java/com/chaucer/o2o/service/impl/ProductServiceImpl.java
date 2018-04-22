package com.chaucer.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.chaucer.o2o.util.PageCalculator;
import com.chaucer.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	public ProductExecution getProductList(Product productCondition,
			int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculate(pageIndex, pageSize);
		List<Product> productList = productDao
				.queryProductList(productCondition, rowIndex, pageSize);
		int amount = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		if (productList != null) {
			pe.setProductList(productList);
			pe.setCount(amount);
		} else {
			pe.setStatus(ProductStatusEnum.EMPTY.getStatus());
		}
		return pe;
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return productDao.queryProductById(productId);
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

	/**
	 * 1.若缩略图参数有值，则处理缩略图（原先有值，则清空原有值，之后获取缩略图相对路径并赋值到表给对应product
	 * 2.若商品详情图列表参数有值，对商品详情图进行操作 3.删除tb_product_img中对应商品图片信息 4.更新保存tb_product信息
	 */
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product,
			ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
					throws ProductOperationException {
		if (product != null && product.getShop() != null
				&& product.getShop().getShopId() != null) {
			product.setLastEditTime(new Date());
			// 1判断是否需要处理图片
			if (thumbnail != null) {
				Product tempProduct = productDao
						.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			if (productImgHolderList != null
					&& productImgHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgHolderList);
			}
			try {
				// 更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStatusEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStatusEnum.EMPTY);
		}
	}

	/**
	 * 删除所有原来产品对应的详情图(第一步
	 * 
	 * @param productId
	 */
	private void deleteProductImgList(Long productId) {
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}

	/**
	 * 添加缩略图
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
}