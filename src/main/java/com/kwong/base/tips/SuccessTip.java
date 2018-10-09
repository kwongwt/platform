package com.kwong.base.tips;

/**
 * @Description: 请求成功提示
 * @author: kwong
 * @date: Oct 9, 2018
 */
public class SuccessTip extends Tip {
	public SuccessTip() {
		super.code = 200;
		super.message = "操作成功";
	}
}
