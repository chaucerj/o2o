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
	public void testShopCategory() {
		List<ShopCategory> ShopCategoryList = shopCategoryDao
				.queryShopCategory(new ShopCategory());

		assertEquals(1, ShopCategoryList.size());
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setCategoryId(1L);
		testCategory.setParent(parentCategory);
		ShopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(1, ShopCategoryList.size());
		System.out.println(ShopCategoryList.get(0).getCategoryName());
	}
}
