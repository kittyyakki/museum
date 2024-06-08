package com.team4.museum.controller.action.member;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.util.UrlUtil;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberVO mvo = new MemberVO();

		// 'name' 파라미터가 없는 경우
		String name = request.getParameter("name");
		if (name == null || name.equals("")) {
			return requireParameter("name");
		}
		mvo.setName(name);

		// 'id' 파라미터가 없는 경우
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return requireParameter("id");
		}
		mvo.setId(id);

		// 'pwd' 파라미터가 없는 경우
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.equals("")) {
			return requireParameter("pwd");
		}
		mvo.setPwd(pwd);

		// 'email' 파라미터가 없는 경우
		String email = request.getParameter("email");
		if (email == null || email.equals("")) {
			return requireParameter("email");
		}
		mvo.setEmail(email);

		// 'phone' 파라미터가 없는 경우
		String phone = request.getParameter("phone");
		if (phone == null || phone.equals("")) {
			return requireParameter("phone");
		}
		mvo.setPhone(phone);

		// 회원가입에 실패한 경우
		if (MemberDao.getInstance().insertMember(mvo) == 0) {
			return internalServerError("회원가입에 실패하였습니다");
		}

		// 돌아갈 페이지 정보를 확인하고, 없으면 index 페이지로 이동
		String returnUrl = "museum.do?command=index";
		String returnUrlParam = (String) request.getParameter("returnUrl");
		if (returnUrlParam != null && !returnUrlParam.isEmpty()) {
			returnUrl = UrlUtil.decode(returnUrlParam);
		}

		// 돌아갈 페이지 정보와 함께 성공 메시지를 반환
		return ok("회원가입이 완료되었습니다", returnUrl);
	}

}
