package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MypageFavoriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		int aseq = Integer.parseInt(request.getParameter("aseq"));
		
		String url = "museum.do?command=writeMypageFavorite";
		if(mvo == null) {
			url = "museum.do?command=loginForm";
			session.setAttribute("returnUrl", "museum.do?command=artworkView&aseq=" + aseq);
		}else {
			request.getRequestDispatcher(url);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
