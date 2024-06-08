package com.team4.museum.controller.action.member;

import static com.team4.museum.util.AjaxResult.BAD_REQUEST;
import static com.team4.museum.util.AjaxResult.INTERNAL_SERVER_ERROR;
import static com.team4.museum.util.AjaxResult.OK;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.util.UrlUtil;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberVO mvo = new MemberVO();

		// 파라미터에 'name'가 없으면 BAD_REQUEST 를 반환
		String name = request.getParameter("name");
		if (name == null || name.equals("")) {
			return new AjaxResult(BAD_REQUEST, "'name'를 입력해주세요");
		}
		mvo.setName(name);

		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return new AjaxResult(BAD_REQUEST, "'id'를 입력해주세요");
		}
		mvo.setId(id);

		// 파라미터에 'pwd'가 없으면 BAD_REQUEST 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.equals("")) {
			return new AjaxResult(BAD_REQUEST, "'pwd'를 입력해주세요");
		}
		mvo.setPwd(pwd);

		// 파라미터에 'email'가 없으면 BAD_REQUEST 를 반환
		String email = request.getParameter("email");
		if (email == null || email.equals("")) {
			return new AjaxResult(BAD_REQUEST, "'email'를 입력해주세요");
		}
		mvo.setEmail(email);

		// 파라미터에 'pwd'가 없으면 BAD_REQUEST 를 반환
		String phone = request.getParameter("phone");
		if (phone == null || phone.equals("")) {
			return new AjaxResult(BAD_REQUEST, "'phone'를 입력해주세요");
		}
		mvo.setPhone(phone);

		// 회원가입 실패 시 INTERNAL_SERVER_ERROR 를 반환
		if (MemberDao.getInstance().insertMember(mvo) == 0) {
			return new AjaxResult(INTERNAL_SERVER_ERROR, "회원가입 실패\n관리자에게 문의하세요");
		}

		// 돌아갈 페이지 정보를 확인하고, 없으면 index 페이지로 이동
		String returnUrl = "museum.do?command=index";
		String returnUrlParam = (String) request.getParameter("returnUrl");
		if (returnUrlParam != null && !returnUrlParam.isEmpty()) {
			returnUrl = UrlUtil.decode(returnUrlParam);
		}

		// 돌아갈 페이지 정보와 함께 OK 를 반환
		return new AjaxResult(OK, "회원가입에 성공하였습니다", returnUrl);
	}

}
