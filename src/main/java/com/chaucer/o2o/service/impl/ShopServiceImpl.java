package com.chaucer.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaucer.o2o.dao.ShopDao;
import com.chaucer.o2o.dto.ShopExecution;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.enums.ShopStatusEnum;
import com.chaucer.o2o.exceptions.ShopOperationException;
import com.chaucer.o2o.service.ShopService;
import com.chaucer.o2o.util.ImageUtil;
import com.chaucer.o2o.util.PageCalculator;
import com.chaucer.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException {
		int rowIndex = PageCalculator.calculate(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int amount = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList!=null){
			se.setShopList(shopList);
			se.setAmount(amount);
		}else{
			se.setStatus(ShopStatusEnum.NULL_SHOP.getStatus());
		}
		return se;
	}

	@Override
	public Shop getShopById(long shopId) {
		// TODO Auto-generated method stub
		return shopDao.queryShopById(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream ips, String filename) throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStatusEnum.NULL_SHOP);
		} else {
			try {
				// 1判断是否需要处理图片
				if (ips != null && filename != null && !"".equals(filename)) {
					Shop tempShop = shopDao.queryShopById(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, ips, filename);
				}

				// 2.修改商户信息
				int effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					return new ShopExecution(ShopStatusEnum.INNER_ERROR);
				} else {
					return new ShopExecution(ShopStatusEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ShopOperationException(e.getMessage());
			}
		}
	}

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream ips, String filename) {
		if (shop == null) {
			return new ShopExecution(ShopStatusEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectNum = shopDao.insertShop(shop);
			if (effectNum <= 0) {
				throw new ShopOperationException("add shop ERROR");
			} else {
				if (ips != null) {
					try {
						addShopImg(shop, ips, filename);

					} catch (Exception e) {
						throw new ShopOperationException("更新图片出错" + e.getMessage());
					}
					effectNum = shopDao.updateShop(shop);
					if (effectNum <= 0) {
						throw new ShopOperationException("更新图片出错");
					}
				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("add shop ERROR" + e.getMessage());
		}
		return new ShopExecution(ShopStatusEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImg, String filename) {
		// 获取shop图片相对路径
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, filename, dest);
		shop.setShopImg(shopImgAddr);

	}

}
