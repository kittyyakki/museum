package com.team4.museum.util.ajax;

final public class AjaxException extends Exception {

	private static final long serialVersionUID = 4010270985151127478L;

	private int statusCode;

	public AjaxException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}