package com.chaucer.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaucer.o2o.dto.ProductCategoryExecution;
import com.chaucer.o2o.dto.Result;
import com.chaucer.o2o.entity.ProductCategory;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.enums.ProductCategoryStatusEnum;
import com.chaucer.o2o.exceptions.ProductCategoryOperationException;
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

	@RequestMapping(value = "addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategorys(
			@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession()
				.getAttribute("currentShop");
		for (ProductCategory pc : productCategoryList) {
			pc.setShopId(currentShop.getShopId());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService
						.batchAddProductCategory(productCategoryList);
				if (pe.getStatus() == ProductCategoryStatusEnum.SUCCESS
						.getStatus()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStatusInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	@RequestMapping(value = "/delproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> delProductCategory(Long productCategoryId,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop currentShop = (Shop) request.getSession()
						.getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService
						.delProductCategory(productCategoryId,
								currentShop.getShopId());
				if (pe.getStatus() == ProductCategoryStatusEnum.SUCCESS
						.getStatus()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStatusInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}
}
