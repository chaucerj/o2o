package com.chaucer.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

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
import com.chaucer.o2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream ips,String filename) {
		if(shop==null){
			return new ShopExecution(ShopStatusEnum.NULL_SHOP);
		}
		try{
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectNum = shopDao.insertShop(shop);
			if(effectNum<=0){
				throw new ShopOperationException("add shop ERROR");
			}else{
				if(ips!=null){
					try{
						addShopImg(shop,ips,filename);
					
					}catch(Exception e){
						throw new ShopOperationException("更新图片出错"+e.getMessage());
					}
					effectNum = shopDao.updateShop(shop);
					if(effectNum<=0){
						throw new ShopOperationException("更新图片出错");
					}
				}
			}
			
		}catch(Exception e){
			throw new ShopOperationException("add shop ERROR"+e.getMessage());
		}
		return new ShopExecution(ShopStatusEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, InputStream shopImg,String filename) {
		// 获取shop图片相对路径
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, filename,dest);
		shop.setShopImg(shopImgAddr);
		
		
	}
     
}
