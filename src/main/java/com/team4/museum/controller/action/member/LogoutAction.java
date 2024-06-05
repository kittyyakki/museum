package com.team4.museum.controller.action.member;

import static com.team4.museum.util.AjaxResult.OK;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.util.UrlUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getResult(request, response).applyToResponse(response);
	}

	private AjaxResult getResult(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("loginUser");

		// 돌아갈 페이지 정보를 확인하고, 없으면 index 페이지로 이동
		String returnUrl = "museum.do?command=index";
		String returnUrlParam = (String) request.getParameter("returnUrl");
		if (returnUrlParam != null && !returnUrlParam.isEmpty()) {
			returnUrl = UrlUtil.decode(returnUrlParam);
		}

		// 돌아갈 페이지 정보와 함께 OK 를 반환
		return new AjaxResult(OK, "로그아웃 되었습니다", returnUrl);
	}
}
