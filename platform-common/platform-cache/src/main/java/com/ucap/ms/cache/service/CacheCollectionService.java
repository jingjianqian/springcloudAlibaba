package com.ucap.ms.cache.service;

import java.util.Set;

public interface CacheCollectionService {
    /**
     * 添加集合元素
     * @param key 键
     * @param value 值
     * @return long 返回结果
     */
    long addForSet(String key, String... value);

    /**
     * 删除集合元素
     * @param key 键
     * @param value 值
     * @return long 返回结果
     */
    long deleteForSet(String key, String... value);

    /**
     * 获取集合长度
     * @param key 查询键
     * @return long
     */
    long lengthForSet(String key);

    /**
     * 判断集合元素是否存在
     * @param key 键
     * @param value 值
     * @return  boolean
     */
    boolean ifExistForSet(String key, String value);

    /**
     * 获取所有集合元素
     * @param key 键
     * @return Set 返回结果集
     */
    Set AllForSet(String key);

  /*  public  long addForList(String key, String... value);
    public  long deleteForList(String key, String... value);
    public  long lengthForList(String key);
    public  boolean ifExistForList(String key, String value);
    public Set AllForList(String key);*/
}
