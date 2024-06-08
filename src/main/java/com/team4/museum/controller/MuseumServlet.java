package com.team4.museum.controller;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.util.UrlUtil;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
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
		MemberVO mvo = (MemberVO) request.getSession().getAttribute("loginUser");
		if (mvo != null) {
			String userId = mvo.getId();
			if (mvo.isAdmin()) {
				request.setAttribute("isAdmin", true);
				System.out.print("[ADMIN:" + userId + "] ");
			} else {
				System.out.print("[MEMBER:" + userId + "] ");
			}

			request.setAttribute("userId", userId);
		} else {
			System.out.print("[GEUST] ");
		}

		// URL Path 정보를 'urlPath'에 저장
		String urlPath = UrlUtil.getUrlPath(request);
		request.setAttribute("urlPath", UrlUtil.encode(urlPath));

		// 'command' 파라미터로 요청된 'Action'을 찾아 실행
		String command = request.getParameter("command");

		System.out.print("Request : " + urlPath + " -> ");
		Action ac = ActionFactory.getInstance().getAction(command);
		if (ac == null) {
			System.out.println("Action not found : " + command);
			request.getRequestDispatcher("util/404.jsp").forward(request, response);
			return;
		}
		System.out.println(ac.getClass().getSimpleName());

		ac.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
