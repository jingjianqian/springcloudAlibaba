package com.ucap.ms.cache.util;

import com.ucap.ms.base.utils.BaseTools;
import com.ucap.ms.cache.service.CacheService;
import com.ucap.ms.cache.service.imp.CacheSingleRedisService;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonCacheUtil {

    private static Logger logger = Logger.getLogger(CommonCacheUtil.class);
    private static Map<String,CacheService> serviceMap = new ConcurrentHashMap<String,CacheService>();

    public static <T> CacheService<T> getCache(String appName) {
        String cacheStrategy = CommonCacheConfig.cacheStrategy;
        if ("redis".equals(cacheStrategy)) {
            String redisIps = CommonCacheConfig.redisIp;
            if(BaseTools.checkEmpty(redisIps)) {
                logger.error("redis缓存服务器不能为空");
            }
            CacheService service = serviceMap.get(appName);
            if(service == null) {
                service = new CacheSingleRedisService(appName);
                serviceMap.put(appName,service);
            }
            return service;
        } else if ("memcache".equals(cacheStrategy)) {
            logger.error("暂不支持memcache");
        }
        return null;
    }

}
