package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.member.LoginAjaxAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

interface AdminAction extends Action {

	default boolean checkAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!LoginAjaxAction.isAdmin(request)) {
			request.getRequestDispatcher("/WEB-INF/views/util/404.jsp").forward(request, response);
			return false;
		}
		return true;
	}

}
