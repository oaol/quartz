package com.ym.quartz.exception;

import com.ym.quartz.http.HttpCode;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	protected HttpCode getCode() {
		return HttpCode.CONFLICT;
	}
}