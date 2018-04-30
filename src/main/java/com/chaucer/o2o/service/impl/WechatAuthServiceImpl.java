package com.chaucer.o2o.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaucer.o2o.dao.UserInfoDao;
import com.chaucer.o2o.dao.WechatAuthDao;
import com.chaucer.o2o.dto.WechatAuthExecution;
import com.chaucer.o2o.entity.UserInfo;
import com.chaucer.o2o.entity.WechatAuth;
import com.chaucer.o2o.enums.WechatAuthStateEnum;
import com.chaucer.o2o.exceptions.WechatAuthOperationException;
import com.chaucer.o2o.service.WechatAuthService;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = LoggerFactory
			.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private UserInfoDao personInfoDao;

	@Override
	public WechatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WechatAuth wechatAuth)
			throws WechatAuthOperationException {
		// 空值判断
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			// 设置创建时间
			wechatAuth.setCreateTime(new Date());
			// 如果微信帐号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
			// 则自动创建用户信息
			if (wechatAuth.getUser() != null
					&& wechatAuth.getUser().getUserId() == null) {
				try {
					wechatAuth.getUser().setCreateTime(new Date());
					wechatAuth.getUser().setEnableStatus(1);
					UserInfo userInfo = wechatAuth.getUser();
					int effectedNum = personInfoDao.insertUserInfo(userInfo);
					wechatAuth.setUser(userInfo);
					if (effectedNum <= 0) {
						throw new WechatAuthOperationException("添加用户信息失败");
					}
				} catch (Exception e) {
					log.error("insertPersonInfo error:" + e.toString());
					throw new WechatAuthOperationException(
							"insertPersonInfo error: " + e.getMessage());
				}
			}
			// 创建专属于本平台的微信帐号
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new WechatAuthOperationException("帐号创建失败");
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,
						wechatAuth);
			}
		} catch (Exception e) {
			log.error("insertWechatAuth error:" + e.toString());
			throw new WechatAuthOperationException(
					"insertWechatAuth error: " + e.getMessage());
		}
	}

}
