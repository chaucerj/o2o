package com.chaucer.o2o.enums;

public enum ProductStatusEnum {
	OFFLINE(-1, "非法商品"), DOWN(0, "下架"), SUCCESS(1, "操作成功"), INNER_ERROR(-1001,
			"操作失败"), EMPTY(-1002, "商品为空");

	private int status;

	private String statusInfo;

	private ProductStatusEnum(int state, String stateInfo) {
		this.status = state;
		this.statusInfo = stateInfo;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public static ProductStatusEnum stateOf(int index) {
		for (ProductStatusEnum state : values()) {
			if (state.getStatus() == index) {
				return state;
			}
		}
		return null;
	}
}
