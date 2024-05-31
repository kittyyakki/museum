package com.team4.museum.controller.action.member;

import java.io.IOException;

import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;
import com.team4.museum.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EditMemberAction 	implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) {
	    	response.sendRedirect("museum.do?command=loginForm");
	    }else {
	    	
	    	MemberVO mvo1 = new MemberVO();
			mvo1.setId(request.getParameter("id"));
			mvo1.setPwd(request.getParameter("pwd"));
			mvo1.setName(request.getParameter("name"));
			mvo1.setEmail(request.getParameter("email"));
			mvo1.setPhone(request.getParameter("phone"));
			mvo1.setAdminyn("N");
			MemberDao mdao = MemberDao.getInstance();
			mdao.updateMember(mvo1);
			
			session.setAttribute("loginUser", mvo1);
			
			
			
	    	response.sendRedirect("museum.do?command=index");
	    
	    }
	}
}