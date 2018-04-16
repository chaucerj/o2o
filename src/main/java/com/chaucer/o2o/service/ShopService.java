package com.chaucer.o2o.service;

import com.chaucer.o2o.dto.ImageHolder;
import com.chaucer.o2o.dto.ShopExecution;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.exceptions.ShopOperationException;

public interface ShopService {
	Shop getShopById(long shopId);

	/**
	 * 修改商铺信息
	 * 
	 * @param shop
	 * @param ips
	 * @param filename
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException;

	/**
	 * 添加商铺信息
	 * 
	 * @param shop
	 * @param ips
	 * @param filename
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException;

	/**
	 * 分页查询店铺信息
	 * 
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize)
			throws ShopOperationException;
}
