package com.ucap.ms.approve.exception;


import com.ucap.ms.base.exception.BaseErrorCode;

/**
 * 请求外部接口异常
 * @author loncx
 * @date 2019/11/25
 */
public class RequestInferfaceException extends RuntimeException {
    /** 响应结果码 */
    private BaseErrorCode code;

    public RequestInferfaceException()
    {
        super();
    }
    /**
     * 构造函数
     * @param code 响应结果码
     */
    public RequestInferfaceException(BaseErrorCode code)
    {
        super(code.getMessage());
        this.code = code;
    }
    /**
     * 构造函数
     * @param message 错误消息
     */
    public RequestInferfaceException(String message)
    {
        super(message);
    }
    /**
     * 构造函数
     * @param code 响应结果码
     * @param message 错误消息
     */
    public RequestInferfaceException(BaseErrorCode code, String message)
    {
        super(message);
        this.code = code;
    }
    /**
     * 构造函数
     * @param message 异常消息
     * @param cause Throwable对象
     */
    public RequestInferfaceException(String message, Throwable cause)
    {
        super(message, cause);
    }
    /**
     * 构造函数
     * @param cause Throwable对象
     */
    public RequestInferfaceException(Throwable cause)
    {
        super(cause);
    }
    public void setCode(BaseErrorCode code)
    {
        this.code = code;
    }
    public BaseErrorCode getCode()
    {
        return code;
    }
}
