package com.team4.museum.controller.action;

import java.io.IOException;

import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;
import com.team4.museum.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class JoinAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = new MemberVO();
		
		mvo.setId(request.getParameter("id"));	
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));

		int result = mdao.insertMember(mvo);
		HttpSession session = request.getSession();
		if(result == 1) session.setAttribute("message", "회원가입이 완료되었습니다. 로그인하세요");
		else session.setAttribute("message", "회원가입 실패 관리자에게 문의하세요");

		response.sendRedirect("museum.do?command=loginForm");

	}


}
