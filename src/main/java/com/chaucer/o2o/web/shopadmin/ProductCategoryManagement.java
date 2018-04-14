package com.chaucer.o2o.web.shopadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaucer.o2o.dto.Result;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.enums.ProductCategoryStatusEnum;
import com.chaucer.o2o.service.ProductCategoryService;

@Controller
public class ProductCategoryManagement {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(
			HttpServletRequest request) {
		Shop shop = new Shop();
		request.getSession().setAttribute("currentShop", shop);
		Shop currentShop = (Shop) request.getSession()
				.getAttribute("currentShop");
		List<ProductCategory> list = null;

		if (currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService
					.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		} else {
			ProductCategoryStatusEnum pe = ProductCategoryStatusEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, pe.getStatus(),
					pe.getStatusInfo());
		}

	}
}
