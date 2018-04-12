package com.chaucer.o2o.enums;

public enum ProductCategoryStatusEnum {
	SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加数少于1");

	private int status;
	private String statusInfo;

	private ProductCategoryStatusEnum(int status, String statusInfo) {
		this.status = status;
		this.statusInfo = statusInfo;
	}

	public static ProductCategoryStatusEnum statusOf(int status) {
		for (ProductCategoryStatusEnum ss : values()) {
			if (ss.getStatus() == status) {
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
