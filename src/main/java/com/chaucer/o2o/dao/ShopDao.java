package com.chaucer.o2o.dao;

import com.chaucer.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 根据shopid查询shop信息
	 * @param id
	 * @return
	 */
	Shop queryShopById(long id);
	/**
	 * 新建商铺信息
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * 更新商铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
