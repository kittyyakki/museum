package com.team4.museum.controller.action.member;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.util.ajax.AjaxException;
import com.team4.museum.util.ajax.AjaxResult;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdCheckAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response)
			throws AjaxException {
		MemberVO mvo = MemberDao.getInstance().getMember(mustGetString("id"));
		if (mvo != null) {
			return badRequest("이미 사용중인 아이디입니다");
		}

		return created("사용 가능한 아이디입니다");
	}

}
