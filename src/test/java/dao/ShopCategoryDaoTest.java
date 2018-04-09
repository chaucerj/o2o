package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dao.ShopCategoryDao;
import com.chaucer.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void queryArea() {
		List<ShopCategory> ShopCategoryList = shopCategoryDao
				.queryShopCategory(new ShopCategory());
		;
		assertEquals(1, ShopCategoryList.size());
	}
}
