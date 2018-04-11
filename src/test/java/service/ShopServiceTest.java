package service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dto.ShopExecution;
import com.chaucer.o2o.entity.Area;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.entity.ShopCategory;
import com.chaucer.o2o.entity.UserInfo;
import com.chaucer.o2o.enums.ShopStatusEnum;
import com.chaucer.o2o.exceptions.ShopOperationException;
import com.chaucer.o2o.service.ShopService;

public class ShopServiceTest extends BaseTest {
	@Autowired
	
	private ShopService shopService;
	@Test
	public void testModifyShop() throws ShopOperationException,FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的名称");
		File shopImg = new File("E:/project/image/itachi.jpg");
		InputStream ips = new FileInputStream(shopImg);
		ShopExecution se = shopService.modifyShop(shop, ips, shopImg.getName());
		System.out.println(shop.getShopImg());
	}
	@Test
	@Ignore
	public void testAddShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		ShopCategory sc = new ShopCategory();
		UserInfo owner = new UserInfo();
		Area area = new Area();
		owner.setUserId(1L);
		area.setAreaId(2L);
		sc.setCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(sc);
		shop.setAdvice("审核中");
		shop.setShopAddress("road");
		shop.setShopName("测试3");
		shop.setShopContact("112");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStatusEnum.CHECK.getStatus());
		shop.setShopDescription("test");
		File shopImg = new File("E:/project/image/test.jpg");
		InputStream ips = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, ips, shopImg.getName());
		assertEquals(ShopStatusEnum.CHECK.getStatus(), se.getStatus());

	}
}
