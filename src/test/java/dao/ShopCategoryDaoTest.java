package dao;

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
	public void testShopCategory() {
		List<ShopCategory> ShopCategoryList = shopCategoryDao
				.queryShopCategoryList(null);
		System.out.println(ShopCategoryList.size());
	}
}
