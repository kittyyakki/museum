package com.team4.museum.controller.action.member.mypage;

import static com.team4.museum.controller.action.member.LoginAjaxAction.getLoginUser;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageEditAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO mvo = getLoginUser(request, response);
		if (mvo == null) {
			return;
		}

		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		MemberDao.getInstance().updateMember(mvo);

		response.sendRedirect("museum.do?command=index");
	}

}