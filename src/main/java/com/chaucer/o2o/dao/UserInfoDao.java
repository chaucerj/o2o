package com.chaucer.o2o.dao;

import com.chaucer.o2o.entity.UserInfo;

public interface UserInfoDao {

	/**
	 * 通过用户Id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	UserInfo queryUserInfoById(long userId);

	/**
	 * 添加用户信息
	 * 
	 * @param personInfo
	 * @return
	 */
	int insertUserInfo(UserInfo personInfo);

}
