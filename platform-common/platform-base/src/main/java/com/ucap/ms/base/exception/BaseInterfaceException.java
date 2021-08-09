package com.ucap.ms.base.exception;

/**
 * 业务层接口异常抛出用，继承RuntimeException
 * @author lizhiyin
 */
@SuppressWarnings("serial")
public class BaseInterfaceException extends RuntimeException
{
	/** 响应结果码 */
	private BaseErrorCode code;

	public BaseInterfaceException()
	{
		super();
	}
	/**
	 * 构造函数
	 * @param code 响应结果码
	 */
	public BaseInterfaceException(BaseErrorCode code)
	{
		super(code.getMessage());
		this.code = code;
	}
	/**
	 * 构造函数
	 * @param message 错误消息
	 */
	public BaseInterfaceException(String message)
	{
		super(message);
	}
	/**
	 * 构造函数
	 * @param code 响应结果码
	 * @param message 错误消息
	 */
	public BaseInterfaceException(BaseErrorCode code, String message)
	{
		super(message);
		this.code = code;
	}
	/**
	 * 构造函数
	 * @param message 异常消息
	 * @param cause Throwable对象
	 */
	public BaseInterfaceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	/**
	 * 构造函数
	 * @param cause Throwable对象
	 */
	public BaseInterfaceException(Throwable cause)
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
