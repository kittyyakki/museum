package com.team4.museum.controller.action;

import static com.team4.museum.controller.action.member.LoginAjaxAction.getLoginUrl;
import static com.team4.museum.util.UrlUtil.getUrlPath;
import static com.team4.museum.controller.action.member.LoginAjaxAction.getLoginUserFrom;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_IMPLEMENTED;
import static jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.IOException;

import com.team4.museum.util.UrlUtil;
import com.team4.museum.util.ajax.AjaxException;
import com.team4.museum.util.ajax.AjaxResult;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

abstract public class AjaxAction implements Action {

	private HttpServletRequest currentRequest;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjaxResult result;
		try {
			currentRequest = request;
			result = handleAjaxRequest(request, response);
		} catch (AjaxException e) {
			result = new AjaxResult(e.getStatusCode(), e.getMessage(), e.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			result = internalServerError();
		} finally {
			currentRequest = null;
		}

		response.setStatus(result.code);
		response.setContentType("application/json");
		response.getWriter().write(result.toJson());
	}

	abstract protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AjaxException;

	protected static AjaxResult ok() {
		return ok("성공");
	}

	protected static AjaxResult ok(String message) {
		return ok(message, "");
	}

	protected static AjaxResult ok(String message, String url) {
		return new AjaxResult(SC_OK, message, url);
	}

	protected static AjaxResult created() {
		return created("생성되었습니다");
	}

	protected static AjaxResult created(String message) {
		return created(message, "");
	}

	protected static AjaxResult created(String message, String url) {
		return new AjaxResult(SC_CREATED, message, url);
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

	/**
	 * 돌아갈 페이지 URL을 반환합니다. 없을 경우 기본값으로 index 페이지로 이동합니다.
	 * 
	 * @return
	 * @throws AjaxException
	 */
	protected String getReturnUrl() throws AjaxException {
		if (currentRequest == null) {
			throw new AjaxException(SC_INTERNAL_SERVER_ERROR, "메소드는 반드시 handleAjaxRequest 메소드 내에서만 사용해주세요");
		}

		String returnUrl = "museum.do?command=index";
		String returnUrlParam = (String) currentRequest.getParameter("returnUrl");
		if (returnUrlParam != null && !returnUrlParam.isEmpty()) {
			returnUrl = UrlUtil.decode(returnUrlParam);
		}

		return returnUrl;
	}

	/**
	 * 요구되는 파라미터를 문자열로 반환합니다. 없을 경우 AjaxException을 발생시킵니다.
	 * 
	 * @param request
	 * @param parameter
	 * @return
	 * @throws AjaxException
	 */
	protected String mustGetString(String parameter) throws AjaxException {
		if (currentRequest == null) {
			throw new AjaxException(SC_INTERNAL_SERVER_ERROR, "메소드는 반드시 handleAjaxRequest 메소드 내에서만 사용해주세요");
		}

		String str = currentRequest.getParameter(parameter);
		if (str == null || str.equals("")) {
			throw new AjaxException(SC_BAD_REQUEST, "'" + parameter + "'를 입력해주세요");
		}
		return str;
	}

	/**
	 * 요구되는 파라미터를 정수로 반환합니다. 없거나 정수가 아닐 경우 AjaxException을 발생시킵니다.
	 * 
	 * @param request
	 * @param parameter
	 * @return 정수
	 * @throws AjaxException
	 */
	protected int mustGetInt(String parameter) throws AjaxException {
		try {
			return Integer.parseInt(mustGetString(parameter));
		} catch (NumberFormatException e) {
			throw new AjaxException(SC_BAD_REQUEST, "'" + parameter + "'는 숫자로 입력해주세요");
		}
	}

	/**
	 * 로그인된 사용자 정보를 반환합니다. 로그인되어 있지 않을 경우 AjaxException을 발생시킵니다.
	 * 
	 * @return
	 */
	protected MemberVO mustGetLoginUser() throws AjaxException {
		MemberVO mvo = getLoginUserFrom(currentRequest);
		if (mvo == null) {
			throw new AjaxException(SC_UNAUTHORIZED, "로그인이 필요합니다", getLoginUrl(getUrlPath(currentRequest)));
		}
		return mvo;
	}
}
