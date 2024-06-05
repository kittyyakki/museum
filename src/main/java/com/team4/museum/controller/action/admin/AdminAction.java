package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/adminMain.jsp").forward(request, response);
	}

}
