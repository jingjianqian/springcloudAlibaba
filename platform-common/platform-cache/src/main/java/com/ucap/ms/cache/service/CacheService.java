package com.ucap.ms.cache.service;

import java.util.Map;

public interface CacheService<T> extends CacheCollectionService {
	/**
	 * 获取缓存中的数据
	 * @param key 键
	 * @return 返回值
	 */
	T get(String key);

	/**
	 * 获取缓存中的字符串
	 * @param key 键
	 * @return 返回值
	 */
	String getString(String key);

	/**
	 * 把数据放入缓存 如果存在与key对应的值，则返回失败
	 * @param key 键
	 * @param value
	 * @return 返回值
	 */
	boolean add(String key, T value);
	
	/**
	 * 把数据放入缓存 如果存在与key对应的值，则返回失败
	 * 过期时间 默认单位为分钟
	 * @param key 键
	 * @param value
	 * @return 返回值
	 */
	boolean add(String key, T value,Long overTime);

	/**
	 * 把数据放入缓存 如果存在与key对应的值，则覆盖原有的值
	 * 
	 * @param key 键
	 * @param value
	 * @return 返回值
	 */
	boolean set(String key, T value);

	/**
	 * 把数值放入缓存 如果存在与key对应的值，则覆盖原有的值
	 * @param key 键
	 * @param value
	 * @return 返回值
	 */
	boolean setNumber(String key, Long value);

	/**
	 * 缓存更新 如果不存在与key对应的缓存值，则不更新
	 * @param key 键
	 * @param value
	 * @return 返回值
	 */
	boolean update(String key, T value);

	/**
	 * 删除缓存
	 * @param key 键
	 * @return 返回值
	 */
	boolean delete(String key);

	/**
	 * 按key前缀删除缓存
	 * @param keyPrefix 键前缀
	 * @return 返回值
	 */
	boolean deleteByKeyPrefix(String keyPrefix);

	/**
	 * 值自增
	 * @param key 键
	 * @param value 值
	 * @return 返回值
	 */
	Long incrBy(String key,Long value);

	/**
	 * HashMap 中字段自增，一天有效期
	 * @param key 键
	 * @param mapKey mapKey
	 * @param value  value
	 * @return 返回值
	 */
	Long incrCount(String key,String mapKey,Long value);

	/**
	 * HashMap 中字段自增
	 * @param key		map自身key
	 * @param mapKey	map中的key
	 * @param value		自增值
	 * @param expire	过期时间，单位秒，null表示永久
	 * @return 返回值
	 */
	Long hincrBy(String key,String mapKey,Long value,Integer expire);

	/**
	 * HashMap 中设置字段值
	 * @param key		map自身key
	 * @param mapKey	map中的key
	 * @param value		map中的value
	 * @param expire	过期时间，单位秒，null表示永久
	 * @return 返回值
	 */
	Long hset(String key,String mapKey,String value,Integer expire);

	/**
	 * HashMap 中获取字段值
	 * @param key		map自身key
	 * @param mapKey	map中的key
	 * @return String
	 */
	String hget(String key,String mapKey);

	/**
	 * 获取整个map 表中的数据
	 * @param key 键
	 * @return Map
	 */
	Map<String, String> getHashMapAll(String key);

	/**
	 * 获取分布式锁
	 * @param lockKey		锁key
	 * @param requestId		请求资源的keyID
	 * @param expireTime	过期时间
	 * @return				是否获取成功
	 */
	boolean getDistributedLock(String lockKey, String requestId, int expireTime);
	/**
	 * 释放分布式锁
	 * @param lockKey			锁key
	 * @param requestId		请求资源的keyID
	 * @return					是否释放成功
	 */
	boolean releaseDistributedLock(String lockKey, String requestId);

	/**
	 * 尝试获取分布式锁
	 * @param lockKey 锁
	 * @param requestId 锁标识
	 * @return 是否获取成功
	 */
	 boolean tryGetDistributedLock(String lockKey, String requestId);

	/**
	 * 根据分布式锁获得标识
	 * @param lockKey 锁
	 * @return 锁标识
	 */
	 String getRequestIdByLockKey(String lockKey);

	/**
	 * 设置key的数据过期时间
	 * @param key 键
	 * @param overTime 过期时间 默认单位为分钟
	 * @return boolean
	 */
	boolean expire(String key, Long overTime);


}