package com.chaucer.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaucer.o2o.entity.Shop;

public interface ShopDao {
	
	/**
	 * 分页查询店铺，可输入的条件有:店铺名，店铺状态，店铺类别，区域id，owner
	 *
	 * @param shopCondition查询条件
	 * @param rowIndex 起始行
	 * @param pageSize 返回条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	/**
	 * 
	 * @param shopCondition返回分页查询总条数
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 
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
