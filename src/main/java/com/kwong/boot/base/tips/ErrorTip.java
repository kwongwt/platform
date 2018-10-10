package com.kwong.boot.base.tips;

/**
 * @Description: 请求失败提示
 * @author: kwong
 * @date: Oct 9, 2018
 */
public class ErrorTip extends Tip {
	public ErrorTip(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
