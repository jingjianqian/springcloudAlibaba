package com.ucap.ms.cache.util;


import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformCacheUtil {


	/**
	 * 获取缓存
	 * @param appName 缓存分块名称
	 * @return CacheService实例
	 */
//	public static <T> CacheService<T> getCache(String appName) {
//		String cacheStrategy = CommonCacheConfig.cacheStrategy;
//		if ("redis".equals(cacheStrategy)) {
//			String redisIps = CommonCacheConfig.redisIp;
//			if(BaseTools.checkEmpty(redisIps)) {
//				logger.error("redis缓存服务器不能为空");
//			}
//			CacheService service = serviceMap.get(appName);
//			if(service == null) {
//				service = new SingleRedisService(appName);
//				serviceMap.put(appName,service);
//			}
//			return service;
//		} else if ("memcache".equals(cacheStrategy)) {
//			logger.error("暂不支持memcache");
//		}
//		return null;
//	}




}
