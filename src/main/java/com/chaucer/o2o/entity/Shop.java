package com.chaucer.o2o.entity;

import java.util.Date;

public class Shop {
	private Long shopId;
	private String shopName;
	private String shopImg;
	private String shopDescription;
	private String shopContact;
	private String shopAddress;
	private Integer priority;
	//-1不可用，0创建审核中（关闭），1正常展示
	private Integer enableStatus;
	private String advice;
	private Date createTime;
	private Date lastEditTime;
	private Area area;
	private UserInfo owner;
	private ShopCategory shopCategory;
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public UserInfo getOwner() {
		return owner;
	}
	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Long getShopId() {
		return shopId;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public String getShopContact() {
		return shopContact;
	}
	public void setShopContact(String shopContact) {
		this.shopContact = shopContact;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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

}
