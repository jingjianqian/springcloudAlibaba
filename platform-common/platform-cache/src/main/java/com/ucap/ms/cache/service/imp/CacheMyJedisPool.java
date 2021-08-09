package com.ucap.ms.cache.service.imp;

import com.ucap.ms.cache.util.CommonCacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CacheMyJedisPool {
	private static CacheMyJedisPool redisPool;
	@SuppressWarnings("rawtypes")
	private static Pool pool;
	static JedisPoolConfig config = new JedisPoolConfig();// 创建jedis池配置实例
	static HashMap<String, Integer> cacheMap = new HashMap<String, Integer>();
	private Logger log= LoggerFactory.getLogger(CacheMyJedisPool.class);
	private CacheMyJedisPool() {
		try {
			String redisAddress= CommonCacheConfig.redisIp;
			//分号分割代表多个 ; 此处采用哨兵redis主从模式
			String password =CommonCacheConfig.redisPassword;
			
			// 设置池配置项值
			config.setMaxTotal(CommonCacheConfig.redisPoolMaxActive);
			config.setMaxIdle(CommonCacheConfig.redisPoolMaxIdle);
			// 表示idle object evitor两次扫描之间要sleep的毫秒数
			config.setTimeBetweenEvictionRunsMillis(30000);
			// 表示idle object evitor每次扫描的最多的对象数
			config.setNumTestsPerEvictionRun(10);
			config.setMinIdle(CommonCacheConfig.redisPoolminIdle);
			config.setMaxWaitMillis(Long.valueOf(CommonCacheConfig.redisPoolMaxWait));
			config.setTestOnBorrow(Boolean.valueOf(CommonCacheConfig.redisPoolTestOnBorrow));

			// Idle时进行连接扫描
			config.setTestWhileIdle(true);
			config.setTestOnReturn(Boolean.valueOf(CommonCacheConfig.redisPoolTestOnReturn));
			
			if(redisAddress.indexOf(";")>0) {
				Set<String> sentinels = new HashSet<String>();
				String redisIps[]=redisAddress.split(";");
				for (int i = 0; i < redisIps.length; i++) {
					sentinels.add(redisIps[i]);
				}
				String clusterName =CommonCacheConfig.redisClusterName;
				pool = new JedisSentinelPool(clusterName, sentinels, config,password);
			}else {
				String ips=CommonCacheConfig.redisIp;
				String ip=ips.split(":")[0];
				int port=Integer.parseInt(ips.split(":")[1]);
				int connTimeOut=Integer.parseInt(CommonCacheConfig.redisConnectTimeOut);
				// 根据配置实例化jedis池
				pool = new JedisPool(config,ip,port,connTimeOut,password);
			}
		} catch (Exception e) {
			log.error("redis缓存构建失败"+e);
		}
	}
	public synchronized static CacheMyJedisPool getInstance() {
		if(redisPool==null) {
			redisPool=new CacheMyJedisPool();
		}
		return redisPool;
	}
	/** 获得jedis对象 */

	public static Jedis getJedis() {

		return (Jedis) pool.getResource();

	}

	/** 归还jedis对象 */

	public static void recycleJedisOjbect(Jedis jedis) {

		pool.returnResource(jedis);

	}

	/** 销毁jedis对象 */

	public static void destoryJedisOjbect(Jedis jedis) {

		pool.returnBrokenResource(jedis);

	}

	/**
	 * 
	 * 测试jedis池方法
	 */

	public static void main(String[] args) {

		Jedis jedis = getJedis();// 获得jedis实例
		// 获取jedis实例后可以对redis服务进行一系列的操作
		jedis.set("name", "zhuxun");
		System.out.println(jedis.get("name"));
		jedis.del("name");
		System.out.println(jedis.exists("name"));
	}

}
