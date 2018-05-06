package com.chaucer.o2o.service;

public interface CacheService {
	/**
	 * 依据key前缀删除匹配该模式下所有key-value的键值对
	 * 
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
