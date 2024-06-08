package com.team4.museum.controller.action;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_IMPLEMENTED;
import static jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.IOException;

import com.team4.museum.util.AjaxResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

abstract public class AjaxAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjaxResult result = handleAjaxRequest(request, response);

		response.setStatus(result.code);
		response.setContentType("application/json");
		try {
			response.getWriter().write(result.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	abstract protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	protected static AjaxResult ok() {
		return ok("성공");
	}

	protected static AjaxResult ok(String message) {
		return ok(message, "");
	}

	protected static AjaxResult ok(String message, String url) {
		return new AjaxResult(SC_OK, message, url);
	}

	protected static AjaxResult internalServerError() {
		return internalServerError("서버 오류가 발생했습니다");
	}

	protected static AjaxResult internalServerError(String message) {
		return internalServerError(message, "");
	}

	protected static AjaxResult internalServerError(String message, String url) {
		return new AjaxResult(SC_INTERNAL_SERVER_ERROR, message, url);
	}

	protected static AjaxResult badRequest() {
		return badRequest("잘못된 요청입니다");
	}

	protected static AjaxResult badRequest(String message) {
		return badRequest(message, "");
	}

	protected static AjaxResult badRequest(String message, String url) {
		return new AjaxResult(SC_BAD_REQUEST, message, url);
	}

	protected static AjaxResult unauthorized() {
		return unauthorized("로그인이 필요합니다");
	}

	protected static AjaxResult unauthorized(String message) {
		return unauthorized(message, "");
	}

	protected static AjaxResult unauthorized(String message, String url) {
		return new AjaxResult(SC_UNAUTHORIZED, message, url);
	}

	protected static AjaxResult forbidden() {
		return forbidden("접근 권한이 없습니다");
	}

	protected static AjaxResult forbidden(String message) {
		return forbidden(message, "");
	}

	protected static AjaxResult forbidden(String message, String url) {
		return new AjaxResult(SC_FORBIDDEN, message, url);
	}

	protected static AjaxResult noContent() {
		return noContent("내용이 없습니다");
	}

	protected static AjaxResult noContent(String message) {
		return noContent(message, "");
	}

	protected static AjaxResult noContent(String message, String url) {
		return new AjaxResult(SC_NO_CONTENT, message, url);
	}

	protected static AjaxResult notFound() {
		return notFound("찾을 수 없습니다");
	}

	protected static AjaxResult notFound(String message) {
		return notFound(message, "");
	}

	protected static AjaxResult notFound(String message, String url) {
		return new AjaxResult(SC_NOT_FOUND, message, url);
	}

	protected static AjaxResult notImplemented() {
		return notImplemented("구현되지 않은 요청입니다");
	}

	protected static AjaxResult notImplemented(String message) {
		return notImplemented(message, "");
	}

	protected static AjaxResult notImplemented(String message, String url) {
		return new AjaxResult(SC_NOT_IMPLEMENTED, message, url);
	}

	protected static AjaxResult requireParameter(String parameter) {
		return badRequest("'" + parameter + "'를 입력해주세요");
	}
}
