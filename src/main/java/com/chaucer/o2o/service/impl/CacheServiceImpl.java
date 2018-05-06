package com.chaucer.o2o.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.cache.JedisUtil;
import com.chaucer.o2o.service.CacheService;

public class CacheServiceImpl implements CacheService {

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Override
	public void removeFromCache(String keyPrefix) {
		Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
		for (String key : keySet) {
			jedisKeys.del(key);
		}
	}
}
