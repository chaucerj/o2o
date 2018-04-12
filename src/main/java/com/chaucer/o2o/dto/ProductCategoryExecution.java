package com.chaucer.o2o.dto;

import java.util.List;

import com.chaucer.o2o.entity.ProductCategory;

public class ProductCategoryExecution {
	private int status;
	private String statusInfo;
	private ProductCategory productCategory;
	private int amount;
	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(
			List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
