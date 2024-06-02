package com.team4.museum.controller.action.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;

public class LoginAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberVO mvo = MemberDao.getInstance().getMember(id);

		HttpSession session = request.getSession();
		String returnUrl = (String) session.getAttribute("returnUrl");
		
		String url = "member/loginForm.jsp";
		if (mvo == null) {
			request.setAttribute("message", "아이디가 없습니다");
		} else if (!mvo.getPwd().equals(pwd)) {
			request.setAttribute("message", "패스워드가 틀립니다");
		}else if(returnUrl != null) {	
			// artworkView.jsp 에서 관심작품으로 추가하기를 클릭했을때 로그아웃상태인 경우에 로그인후 해당 작품으로 돌아가기 위해 설정
			url = returnUrl;
		}else {
			url = "museum.do?command=index";
			request.getSession().setAttribute("loginUser", mvo);
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
