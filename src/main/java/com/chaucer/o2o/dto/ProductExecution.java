package com.chaucer.o2o.dto;

import java.util.List;

import com.chaucer.o2o.entity.Product;
import com.chaucer.o2o.enums.ProductStatusEnum;

public class ProductExecution {
	// 结果状态
	private int status;

	// 状态标识
	private String statusInfo;

	// 商品数量
	private int count;

	// 操作的product（增删改商品的时候用）
	private Product product;

	// 获取的product列表(查询商品列表的时候用)
	private List<Product> productList;

	public ProductExecution() {
	}

	// 失败的构造器
	public ProductExecution(ProductStatusEnum stateEnum) {
		this.status = stateEnum.getStatus();
		this.statusInfo = stateEnum.getStatusInfo();
	}

	// 成功的构造器
	public ProductExecution(ProductStatusEnum stateEnum, Product product) {
		this.status = stateEnum.getStatus();
		this.statusInfo = stateEnum.getStatusInfo();
		this.product = product;
	}

	// 成功的构造器
	public ProductExecution(ProductStatusEnum stateEnum,
			List<Product> productList) {
		this.status = stateEnum.getStatus();
		this.statusInfo = stateEnum.getStatusInfo();
		this.productList = productList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
