package com.team4.museum.util;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_IMPLEMENTED;
import static jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

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

	public static AjaxResult success() {
		return success("성공");
	}

	public static AjaxResult success(String message) {
		return success(message, "");
	}

	public static AjaxResult success(String message, String url) {
		return new AjaxResult(SC_OK, message, url);
	}

	public static AjaxResult fail() {
		return fail("실패");
	}

	public static AjaxResult fail(String message) {
		return fail(message, "");
	}

	public static AjaxResult fail(String message, String url) {
		return new AjaxResult(SC_INTERNAL_SERVER_ERROR, message, url);
	}

	public static AjaxResult internalServerError() {
		return internalServerError("서버 오류가 발생했습니다");
	}

	public static AjaxResult internalServerError(String message) {
		return internalServerError(message, "");
	}

	public static AjaxResult internalServerError(String message, String url) {
		return new AjaxResult(SC_INTERNAL_SERVER_ERROR, message, url);
	}

	public static AjaxResult badRequest() {
		return badRequest("잘못된 요청입니다");
	}

	public static AjaxResult badRequest(String message) {
		return badRequest(message, "");
	}

	public static AjaxResult badRequest(String message, String url) {
		return new AjaxResult(SC_BAD_REQUEST, message, url);
	}

	public static AjaxResult unauthorized() {
		return unauthorized("로그인이 필요합니다");
	}

	public static AjaxResult unauthorized(String message) {
		return unauthorized(message, "");
	}

	public static AjaxResult unauthorized(String message, String url) {
		return new AjaxResult(SC_UNAUTHORIZED, message, url);
	}

	public static AjaxResult forbidden() {
		return forbidden("접근 권한이 없습니다");
	}

	public static AjaxResult forbidden(String message) {
		return forbidden(message, "");
	}

	public static AjaxResult forbidden(String message, String url) {
		return new AjaxResult(SC_FORBIDDEN, message, url);
	}

	public static AjaxResult noContent() {
		return noContent("내용이 없습니다");
	}

	public static AjaxResult noContent(String message) {
		return noContent(message, "");
	}

	public static AjaxResult noContent(String message, String url) {
		return new AjaxResult(SC_NO_CONTENT, message, url);
	}

	public static AjaxResult notFound() {
		return notFound("찾을 수 없습니다");
	}

	public static AjaxResult notFound(String message) {
		return notFound(message, "");
	}

	public static AjaxResult notFound(String message, String url) {
		return new AjaxResult(SC_NOT_FOUND, message, url);
	}

	public static AjaxResult notImplemented() {
		return notImplemented("구현되지 않은 요청입니다");
	}

	public static AjaxResult notImplemented(String message) {
		return notImplemented(message, "");
	}

	public static AjaxResult notImplemented(String message, String url) {
		return new AjaxResult(SC_NOT_IMPLEMENTED, message, url);
	}

	public static AjaxResult requireParameter(String parameter) {
		return badRequest("'" + parameter + "'를 입력해주세요");
	}

}