package com.chaucer.o2o.dto;

import java.util.List;

import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.enums.ShopStatusEnum;

public class ShopExecution {
     
	private int status;
	private String statusInfo;
	private Shop shop;
	private String amount;
	private List<Shop> shopList;
	public ShopExecution(){
		
	}
	//上架失败的使用的构造器
	public ShopExecution(ShopStatusEnum statusEnum ){
		this.status = statusEnum.getStatus();
		this.statusInfo = statusEnum.getStatusInfo();
	}
	public ShopExecution(ShopStatusEnum statusEnum ,Shop shop){
		this.status = statusEnum.getStatus();
		this.statusInfo = statusEnum.getStatusInfo();
		this.shop = shop;
	}
	
	public ShopExecution(ShopStatusEnum statusEnum ,List<Shop> shopList){
		this.status = statusEnum.getStatus();
		this.statusInfo = statusEnum.getStatusInfo();
		this.shopList = shopList;
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
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
