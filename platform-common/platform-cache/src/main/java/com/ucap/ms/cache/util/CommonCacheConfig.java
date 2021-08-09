package com.ucap.ms.cache.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * redis缓存相关配置
 * @time 2021-08-09
 * @author jjq
 */
@RefreshScope
public class CommonCacheConfig {


    public static String cacheStrategy;
    public static String redisIp;
    public static String redisPassword;
    public static Integer redisPoolMaxActive;
    public static Integer redisPoolMaxIdle;
    public static Integer redisPoolminIdle;
    public static String redisPoolMaxWait;
    public static String redisPoolTestOnBorrow;
    public static String redisPoolTestOnReturn;
    public static String redisConnectTimeOut;
    public static String redisSoTimeOut;
    public static String redisMaxAttempts;
    public static String redisClusterName;



    @Value(value = "${redis.config.cacheStrategy}")
    public  void setCacheStrategy(String cacheStrategy) {
        CommonCacheConfig.cacheStrategy = cacheStrategy;
    }
    @Value(value = "${redis.config.redisIp}")
    public  void setRedisIp(String redisIp) {
        CommonCacheConfig.redisIp = redisIp;
    }
    @Value(value = "${redis.config.redisPassword}")
    public  void setRedisPassword(String redisPassword) {
        CommonCacheConfig.redisPassword = redisPassword;
    }
    @Value(value = "${redis.config.redisPoolMaxActive}")
    public  void setRedisPoolMaxActive(Integer redisPoolMaxActive) {
        CommonCacheConfig.redisPoolMaxActive = redisPoolMaxActive;
    }
    @Value(value = "${redis.config.redisPoolMaxIdle}")
    public  void setRedisPoolMaxIdle(Integer redisPoolMaxIdle) {
        CommonCacheConfig.redisPoolMaxIdle = redisPoolMaxIdle;
    }
    @Value(value = "${redis.config.redisPoolminIdle}")
    public  void setRedisPoolminIdle(Integer redisPoolminIdle) {
        CommonCacheConfig.redisPoolminIdle = redisPoolminIdle;
    }
    @Value(value = "${redis.config.redisPoolMaxWait}")
    public  void setRedisPoolMaxWait(String redisPoolMaxWait) {
        CommonCacheConfig.redisPoolMaxWait = redisPoolMaxWait;
    }
    @Value(value = "${redis.config.redisPoolTestOnBorrow}")
    public  void setRedisPoolTestOnBorrow(String redisPoolTestOnBorrow) {
        CommonCacheConfig.redisPoolTestOnBorrow = redisPoolTestOnBorrow;
    }
    @Value(value = "${redis.config.redisPoolTestOnReturn}")
    public  void setRedisPoolTestOnReturn(String redisPoolTestOnReturn) {
        CommonCacheConfig.redisPoolTestOnReturn = redisPoolTestOnReturn;
    }
    @Value(value = "${redis.config.redisConnectTimeOut}")
    public  void setRedisConnectTimeOut(String redisConnectTimeOut) {
        CommonCacheConfig.redisConnectTimeOut = redisConnectTimeOut;
    }
    @Value(value = "${redis.config.redisSoTimeOut}")
    public  void setRedisSoTimeOut(String redisSoTimeOut) {
        CommonCacheConfig.redisSoTimeOut = redisSoTimeOut;
    }
    @Value(value = "${redis.config.redisMaxAttempts}")
    public  void setRedisMaxAttempts(String redisMaxAttempts) {
        CommonCacheConfig.redisMaxAttempts = redisMaxAttempts;
    }
    @Value(value = "${redis.config.redisClusterName}")
    public  void setRedisClusterName(String redisClusterName) {
        CommonCacheConfig.redisClusterName = redisClusterName;
    }
}
