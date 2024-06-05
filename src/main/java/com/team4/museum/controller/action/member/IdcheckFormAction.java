package com.team4.museum.controller.action.member;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdcheckFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(id);

		request.setAttribute("result", mvo == null ? -1 : 1);
		request.setAttribute("id", id);
		request.getRequestDispatcher("member/idcheck.jsp").forward(request, response);
	}

}
