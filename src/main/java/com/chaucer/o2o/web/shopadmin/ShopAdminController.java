package com.chaucer.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist")
	public String shoplsit() {
		// 转发至店铺列表
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		// 转发至店铺管理页面
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "/productcategorymanagement")
	public String produCtcategoryManagement() {
		// 转发至商品类别管理页面
		return "shop/productcategorymanagement";
	}

	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		// 转发至商品类别管理页面
		return "shop/productoperation";
	}

}
