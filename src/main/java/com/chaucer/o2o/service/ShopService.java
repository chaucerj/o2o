package com.chaucer.o2o.service;

import java.io.InputStream;

import com.chaucer.o2o.dto.ShopExecution;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution addShop(Shop shop,InputStream ips,String filename) throws ShopOperationException;
}
