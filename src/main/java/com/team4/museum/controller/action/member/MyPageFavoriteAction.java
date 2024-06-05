package com.team4.museum.controller.action.member;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.member.LoginAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageFavoriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aseq = Integer.parseInt(request.getParameter("aseq"));

		if (LoginAction.isLogined(request, response, "museum.do?command=artworkView&aseq=" + aseq)) {
			request.getRequestDispatcher("museum.do?command=writeMypageFavorite").forward(request, response);
		}
	}

}
