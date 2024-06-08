package com.team4.museum.util;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class AjaxResult {

	public int code = SC_INTERNAL_SERVER_ERROR;
	public String message = "알 수 없는 오류가 발생했습니다";
	public String url = "";

	public AjaxResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public AjaxResult(int code, String message, String url) {
		this(code, message);
		this.url = url;
	}

	public String toJson() {
		return "{" +
				"\"message\":\"" + message + "\"," +
				"\"url\":\"" + url + "\"" +
				"}";
	}

}