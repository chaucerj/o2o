package com.chaucer.o2o.service;

import com.chaucer.o2o.entity.UserInfo;

public interface UserInfoService {

	/**
	 * 根据用户Id获取personInfo信息
	 * 
	 * @param userId
	 * @return
	 */
	UserInfo getUserInfoById(Long userId);

}
