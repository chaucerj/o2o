package dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dao.ShopDao;
import com.chaucer.o2o.entity.Area;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.entity.ShopCategory;
import com.chaucer.o2o.entity.UserInfo;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		UserInfo owner = new UserInfo();
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 1, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println(shopList.size());
		ShopCategory sc = new ShopCategory();
		sc.setCategoryId(3L);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 2);
		System.out.println(shopList.size());
		count = shopDao.queryShopCount(shopCondition);
		System.out.println(count);
	}

	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 1L;
		Shop shop = shopDao.queryShopById(shopId);
		System.out.println(shop.getArea().getAreaId());
		System.out.println(shop.getArea().getAreaName());
	}

	@Test
	@Ignore
	public void testinsertShop() {
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
		shop.setShopName("测试一");
		shop.setShopContact("112");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setShopDescription("test");
		int effectNum = shopDao.insertShop(shop);
		assertEquals(1, effectNum);
	}

	@Test
	@Ignore
	public void testupdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setLastEditTime(new Date());
		shop.setShopDescription("测试描述");
		shop.setShopImg("测试图片");
		int effectNum = shopDao.updateShop(shop);
		assertEquals(1, effectNum);
	}
}
