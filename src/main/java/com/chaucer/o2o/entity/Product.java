package com.chaucer.o2o.entity;

import java.util.Date;
import java.util.List;

public class Product {
    private Long productId;
    private String productName;
    private String productDescription;
    private String imgAddr;
    private String normalPrice;
    private String discountPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //0不展示，1可用
    private Integer enableStatus;
    private List<ProductImg> productImgList;
    private Shop shop;
    private ProductCategory category;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public List<ProductImg> getProductImgList() {
		return productImgList;
	}
	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
    
}
