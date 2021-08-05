package com.ucap.ms.base.enums;

/**
 * 缓存分区枚举
 * @version
 * 1,fujl 2019-11-23 增加枚举OUTERWEB
 */
public enum CacheCodeEnum {
	//根据模板对缓存进行分区
    INNERWEB(1,"INNERWEB_CACHE"),
	//对外服务的缓存库 fujl 2019-11-23 ++
	OUTERWEB(2,"OUTERWEB_CACHE"),
	API(3,"API_CACHE"),
	SYS(4,"SYS_CACHE"),
	AUTH(5,"AUTH_CACHE"),
    FLOW(6,"FLOW_CACHE"),

	INTERFACES(11, "INTERFACES_CACHE"),
	OTHER(14, "OTHER_CACHE");
    // 警告：默认情况下Redis只支持[0-15]号分区


	private final int key;
	private final String value;

	CacheCodeEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
