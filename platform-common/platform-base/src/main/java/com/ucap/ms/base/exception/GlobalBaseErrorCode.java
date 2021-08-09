package com.ucap.ms.base.exception;

/**
 * 全局错误码定义
 */
public enum GlobalBaseErrorCode implements BaseErrorCode {

    SUCCESS(1,          "操作成功"),
    FAILURE(0,          "操作失败"),
    UNKNOWN(10,         "未知错误"),
    INSERT_FAIL(11,     "添加失败"),
    UPDATE_FAIL(12,     "修改失败"),
    DELETE_FAIL(13,     "删除失败"),
    SAVE_FAIL(14,       "保存失败"),
    SEARCH_FAIL(15,     "查询失败"),
    LOGIN_FAIL(16,      "登录失败"),
    PROCESS_FAIL(17,      "执行失败"),

    INVALID_PARAM(100,  "参数错误"),
    NO_DATA(101,        "查询数据为空"),
    NO_LOGIN(102,       "您未登录或登录超时，请重新登录"),
    UNSUPPORTED_FILE_TYPE(110, "不支持的文件格式"),
    FILE_NOT_FOUND(111, "找不到文件"),

    BAD_REQUEST(400,    "错误请求 — 请求中有语法问题，或不能满足请求"),
    NO_AUTH(401,         "您无权查看本页面或执行本次操作"),
    NO_AUTH_GDSY(4011,         "您无权查看本页面或执行本次操作"),
    FORBIDDEN(403,      "没有权限访问"),
    NOT_FOUND(404,      "访问路径错误"),
    METHOD_NOT_ALLOWED(405,     "不被允许的方法:请求方式错误？"),
    SYSTEM_ERROR(500,            "系统内部错误"),
    BAD_GATEWAY(502,             "用户访问请求响应超时"),

    MONGO_COLLECTION_NOT_EXIT(3301, "MONGO COLLECTION NOT EXIST");

    GlobalBaseErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    // 普通方法  
    public static String getName(int code) {  
        for (GlobalBaseErrorCode c : GlobalBaseErrorCode.values()) {
            if (c.getCode() == code) {  
                return c.message;  
            }  
        }  
        return null;  
    }

    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
