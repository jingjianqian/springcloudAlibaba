package com.ucap.ms.base.data;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 统一返回数据模型
 * @author jjq
 * @time 20210805
 */

@ApiModel(value="resultModel",description="统一返回数据模型")
public class ResultModel<T extends Object> implements Serializable {
	@ApiModelProperty(value="数据编码")
	private int code;//返回数据编码，具体参见 Constants 配置
	@ApiModelProperty(value="详细信息")
	private String msg;//返回详细信息
	@ApiModelProperty(value="数据集")
	private T data;//返回数据集

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ResultModel() {
		super();
	}
	public ResultModel(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ResultModel(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
    @Override
    public String toString() {
        return "{\"code\":\"" + this.code + "\", \"msg\":\"" + this.msg + "\", \"data\":" + JSON.toJSONString(this.data) + "}";
    }
}
