package com.team4.museum.controller.action.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminMainAction implements AdminAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkAdmin(request, response)) {
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMain.jsp").forward(request, response);
	}

}
