package com.kwong.boot.base.tips;

/**
 * @Description: tips抽象类，用于返回给前台的提示
 * @author: kwong
 * @date: Oct 9, 2018
 */
public abstract class Tip {

	protected int code;
	protected String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
