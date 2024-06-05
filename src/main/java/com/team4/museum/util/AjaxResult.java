package com.team4.museum.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public class AjaxResult {
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int ACCEPTED = 202;
	public static final int NO_CONTENT = 204;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int INTERNAL_SERVER_ERROR = 500;

	public int code = INTERNAL_SERVER_ERROR;
	public String message = "";
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

	public void applyToResponse(HttpServletResponse response) {
		response.setStatus(code);
		response.setContentType("application/json");
		try {
			response.getWriter().write(toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}