package com.chaucer.o2o.entity;

import java.util.Date;

public class UserInfo {
	private Long userId;
	private String name;
	private String profileImg;
	private String gender;
	private String userType;
	private String email;
	private Integer enableStatus;
	private Date createTime;
	private Date lasteditTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Date getLasteditTime() {
		return lasteditTime;
	}
	public void setLasteditTime(Date lasteditTime) {
		this.lasteditTime = lasteditTime;
	}
	
	
	

}