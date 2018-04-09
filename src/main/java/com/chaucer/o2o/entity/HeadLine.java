package com.chaucer.o2o.entity;

import java.util.Date;

public class HeadLine {
   private Integer headLineId;
   private String headLineName;
   private String healLineLink;
   private String headLineImg;
   private Integer priority;
   private Integer enableStatus;
   private Date createTime;
   private Date lastEditTime;
public Integer getHeadLineId() {
	return headLineId;
}
public void setHeadLineId(Integer headLineId) {
	this.headLineId = headLineId;
}
public String getHeadLineName() {
	return headLineName;
}
public void setHeadLineName(String headLineName) {
	this.headLineName = headLineName;
}
public String getHealLineLink() {
	return healLineLink;
}
public void setHealLineLink(String healLineLink) {
	this.healLineLink = healLineLink;
}
public String getHeadLineImg() {
	return headLineImg;
}
public void setHeadLineImg(String headLineImg) {
	this.headLineImg = headLineImg;
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
