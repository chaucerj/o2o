package com.chaucer.o2o.entity;

import java.util.Date;

public class ProductImg {
  private Long productImgId;
  private String imgAddr;
  private String imgDescription;
  private Integer priority;
  private Long productId;
  private Date createTime;
  public Long getProductImgId() {
	return productImgId;
}
public void setProductImgId(Long productImgId) {
	this.productImgId = productImgId;
}
public String getImgAddr() {
	return imgAddr;
}
public void setImgAddr(String imgAddr) {
	this.imgAddr = imgAddr;
}
public String getImgDescription() {
	return imgDescription;
}
public void setImgDescription(String imgDescription) {
	this.imgDescription = imgDescription;
}
public Integer getPriority() {
	return priority;
}
public void setPriority(Integer priority) {
	this.priority = priority;
}
public Long getProductId() {
	return productId;
}
public void setProductId(Long productId) {
	this.productId = productId;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

}
