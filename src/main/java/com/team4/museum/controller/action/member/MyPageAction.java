package com.team4.museum.controller.action.member;

import java.io.IOException;

import com.team4.museum.vo.MemberVO;
import com.team4.museum.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) {
	    	response.sendRedirect("museum.do?command=loginForm");
	    }else {
	    	request.getRequestDispatcher("member/myPage.jsp").forward(request, response);
	    }
	    
	}
}