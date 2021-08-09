package com.ucap.ms.cache.service.imp;

import com.ucap.ms.base.enums.CacheCodeEnum;
import com.ucap.ms.base.utils.BaseSerializationUtil;
import com.ucap.ms.base.utils.BaseTools;
import com.ucap.ms.base.utils.BaseValidUtil;
import com.ucap.ms.cache.service.CacheService;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 单机redis及 redis主从模式 客户端
 */
public class CacheSingleRedisService<T> implements CacheService<T> {
	private static Logger logger = Logger.getLogger(CacheSingleRedisService.class);
	private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	private int jedisDatabaseIndex;

	public CacheSingleRedisService(String appName) {
		if(CacheMyJedisPool.cacheMap == null || CacheMyJedisPool.cacheMap.size() <= 0) {
			for (CacheCodeEnum code : CacheCodeEnum.values()) {
				CacheMyJedisPool.cacheMap.put(code.getValue(), code.getKey());
			}
		}
		if (BaseValidUtil.isEmpty(CacheMyJedisPool.cacheMap) || BaseValidUtil.isNull(appName) || BaseValidUtil.isEmpty(CacheMyJedisPool.cacheMap.get(appName))) {
			jedisDatabaseIndex = 0;
		} else {
			jedisDatabaseIndex = CacheMyJedisPool.cacheMap.get(appName);
		}
	}

	public void recycleJedisToPool(Jedis jedis) {
		if (jedis != null) {
			try {
				CacheMyJedisPool.recycleJedisOjbect(jedis);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

	/** 获得jedis对象 */
	public Jedis getJedis() {
		return CacheMyJedisPool.getInstance().getJedis();
	}

	/** 销毁jedis对象 */
	public void destoryJedisOjbect(Jedis jedis) {
		CacheMyJedisPool.destoryJedisOjbect(jedis);
	}

	@Override
	public T get(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			byte[] b = jedis.get(key.getBytes());
			return (T) BaseSerializationUtil.deserialize(b);
		} catch (Exception e) {
			logger.error("getCacheObjectByKey:key:" + key + ",error:" + e);
			destoryJedisOjbect(jedis);
			return null;
		} finally {
			recycleJedisToPool(jedis);
		}
	}

	@Override
	public String getString(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("getCacheObjectByKey:key:" + key + ",error:" + e);
			destoryJedisOjbect(jedis);
			return null;
		} finally {
			recycleJedisToPool(jedis);
		}
	}

	@Override
	public boolean add(String key, T object) {
		boolean flag = false;
		Jedis jedis = null;
		if (BaseValidUtil.isNotNull(key) && object != null && !"[]".equals(object.toString())) {
			try {
				jedis = getJedis();
				jedis.select(jedisDatabaseIndex);
				String result = jedis.set(key.getBytes(), BaseSerializationUtil.serialize(object));
				if (LOCK_SUCCESS.equals(result)) {
					flag = true;
				}
			} catch (Exception e) {
				logger.error("createCacheObject:key:" + key + "value:" + object + ",error:" + e);
				destoryJedisOjbect(jedis);
			} finally {
				recycleJedisToPool(jedis);
			}
		}

		return flag;
	}

	@Override
	public boolean set(String key, T value) {
		return add(key,value);
	}

    @Override
    public boolean setNumber(String key, Long value) {
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(jedisDatabaseIndex);
            jedis.set(key.getBytes(), String.valueOf(value).getBytes());
            flag = true;
        } catch (Exception e) {
            logger.error("update:error:" + e);
            destoryJedisOjbect(jedis);
        } finally {
            recycleJedisToPool(jedis);
        }
        return flag;
    }

	@Override
	public boolean update(String key, T value) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			delete(key);
			set(key, value);
			flag = true;
		} catch (Exception e) {
			logger.error("update:error:" + e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	@Override
	public boolean delete(String key) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			long cnt = jedis.del(key.getBytes());
			if (cnt > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	@Override
	public boolean deleteByKeyPrefix(String keyPrefix) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			ScanParams scanParams = new ScanParams();
			scanParams.match(keyPrefix + "*");
			scanParams.count(1000);//每次扫描1000个key，设置过高会影响服务器性能
			ScanResult<String> result = jedis.scan("0",scanParams);
			Iterator<String> it = result.getResult().iterator();
			while(it.hasNext()){
				String key = it.next();
				long cnt = jedis.del(key.getBytes());
				if (cnt > 0) {
					flag = true;
				}
				else {
					flag = false;
					logger.error("redis delete fail-key：" + key);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	@Override
	public boolean add(String key, T object, Long overTime) {
		boolean flag = false;
		Jedis jedis = null;
		if (BaseValidUtil.isNotNull(key) && object != null && !"[]".equals(object.toString())) {
			try {
				jedis = getJedis();
				jedis.select(jedisDatabaseIndex);
				String result = jedis.set(key.getBytes(), BaseSerializationUtil.serialize(object));
				jedis.expire(key.getBytes(),Integer.parseInt(overTime*60+""));
				if (LOCK_SUCCESS.equals(result)) {
					flag = true;
				}
			} catch (Exception e) {
				logger.error("createCacheObject:key:" + key + "value:" + object + ",error:" + e);
				destoryJedisOjbect(jedis);
			} finally {
				recycleJedisToPool(jedis);
			}
		}

		return flag;
	}

	@Override
	public Long incrBy(String key, Long value) {
		Jedis jedis = null;
		Long cnt = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			cnt = jedis.incrBy(key.getBytes(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return cnt;
	}

	@Override
	public Long incrCount(String key, String mapKey, Long value) {
		Jedis jedis = null;
		Long cnt = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			cnt = jedis.hincrBy(key, mapKey, value);
			jedis.expire(key.getBytes(),24 * 3600);//默认时间  一天
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return cnt;
	}

	@Override
	public Long hincrBy(String key, String mapKey, Long value, Integer expire) {
		Jedis jedis = null;
		Long cnt = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			cnt = jedis.hincrBy(key, mapKey, Long.valueOf(value));
			if(expire != null) jedis.expire(key.getBytes(),expire);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return cnt;
	}

	@Override
	public Long hset(String key, String mapKey, String value, Integer expire) {
		Jedis jedis = null;
		Long cnt = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			cnt = jedis.hset(key, mapKey, value);
			if(expire != null) jedis.expire(key.getBytes(),expire);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return cnt;
	}

	@Override
	public String hget(String key, String mapKey) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.hget(key, mapKey);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
			return null;
		} finally {
			recycleJedisToPool(jedis);
		}
	}

	@Override
	public Map<String, String> getHashMapAll(String key) {
		Map<String, String> map =null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			map = jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return map;
	}

	@Override
	public boolean getDistributedLock(String lockKey, String requestId, int expireTime) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
			 if (LOCK_SUCCESS.equals(result)) {
				 flag= true;
		     }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	@Override
	public boolean releaseDistributedLock(String lockKey, String requestId) {
		boolean flag = false;
		if(BaseTools.checkEmpty(requestId)) return flag;

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			Object result = jedis.eval(LOCK_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(requestId));
			 if (LOCK_SUCCESS.equals(result)) {
				 flag= true;
		     }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	/**
	 * 尝试获取分布式锁
	 * @param lockKey 锁
	 * @param requestId 锁标识
	 * @return 是否获取成功
	 */
	public boolean tryGetDistributedLock(String lockKey, String requestId) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if(jedis != null){
				jedis.select(jedisDatabaseIndex);
				String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST);//redis锁
				if (LOCK_SUCCESS.equals(result)) {//判断是否成功
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return flag;
	}

	/**
	 * 根据分布式锁获得标识
	 * @param lockKey 锁
	 * @return 锁标识
	 */
	public String getRequestIdByLockKey(String lockKey) {
		String requestId = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if(jedis != null){
				jedis.select(jedisDatabaseIndex);
				requestId = jedis.get(lockKey);//获取锁标识
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			destoryJedisOjbect(jedis);
		} finally {
			recycleJedisToPool(jedis);
		}
		return requestId;
	}

	@Override
	public boolean expire(String key, Long overTime) {
		boolean flag = false;
		Jedis jedis = null;
		if (BaseValidUtil.isNotNull(key)) {
			try {
				jedis = getJedis();
				jedis.select(jedisDatabaseIndex);
				byte[] b = jedis.get(key.getBytes());
				if(b != null)
					jedis.expire(key.getBytes(),Integer.parseInt(overTime*60+""));
				flag = true;
			} catch (Exception e) {
				logger.error("expire:key:" + key + ",overTime：" + overTime + ",error:" + e);
				destoryJedisOjbect(jedis);
			} finally {
				recycleJedisToPool(jedis);
			}
		}

		return flag;
	}

	@Override
	public long addForSet(String key, String... value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.sadd(key,value);
		} catch (Exception e) {
			logger.error("addForSet:key:" + key + ",value:"+value+",error:" + e);
			destoryJedisOjbect(jedis);

		} finally {
			recycleJedisToPool(jedis);
		}
		return -1;
	}

	@Override
	public long deleteForSet(String key, String... value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.srem(key, value);
		} catch (Exception e) {
			logger.error("deleteForSet:key:" + key + ",value:"+value+",error:" + e);
			destoryJedisOjbect(jedis);

		} finally {
			recycleJedisToPool(jedis);
		}
		return -1;
	}

	@Override
	public long lengthForSet(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.scard(key);
		} catch (Exception e) {
			logger.error("lengthForSet:key:" + key + ",error:" + e);
			destoryJedisOjbect(jedis);

		} finally {
			recycleJedisToPool(jedis);
		}
		return -1;
	}

	@Override
	public boolean ifExistForSet(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.sismember(key,value);
		} catch (Exception e) {
			logger.error("ifExistForSet:key:" + key + ",value:"+value+",error:" + e);

			destoryJedisOjbect(jedis);

		} finally {
			recycleJedisToPool(jedis);
		}
		return false;
	}

	@Override
	public Set AllForSet(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(jedisDatabaseIndex);
			return jedis.smembers(key);
		} catch (Exception e) {
			logger.error("AllForSet:key:" + key + ",error:" + e);

			destoryJedisOjbect(jedis);

		} finally {
			recycleJedisToPool(jedis);
		}
		return null;
	}


}