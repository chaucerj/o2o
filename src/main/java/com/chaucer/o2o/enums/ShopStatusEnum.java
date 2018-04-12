package com.chaucer.o2o.enums;

public enum ShopStatusEnum {
	CHECK(0,"审核中"),ILLEGAL(-1,"非法下线"),SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"内部错误"),PASS(2,"认证通过"),NULL_SHOP_ID(-1002,"shopid为空"),
	NULL_SHOP(-1003,"shop信息为空");
	private int status;
	private String statusInfo;
	
	private ShopStatusEnum(int status,String statusInfo){
		this.status = status;
		this.statusInfo = statusInfo;
	}
    public static ShopStatusEnum statusOf(int status){
    	for(ShopStatusEnum ss : values()){
    		if(ss.getStatus()==status){
    			return ss;
    		}
    	}
    	return null;
    }
	public int getStatus() {
		return status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

}
