package com.team4.museum.controller;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MuseumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MuseumServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 세션에 로그인 정보가 있으면 관리자인지 확인 후 'isAdmin'을 설정
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo != null && mvo.isAdmin()) {
			session.setAttribute("isAdmin", true);
		} else {
			session.removeAttribute("isAdmin");
		}

		// 'command' 파라미터로 요청된 'Action'을 찾아 실행
		String command = request.getParameter("command");
		Action ac = ActionFactory.getInstance().getAction(command);
		if (ac == null) {
			System.out.println("Action not found : " + command);
			return;
		}

		ac.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
