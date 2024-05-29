package com.team4.museum.controller.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// request.setAttribute("newList", pdao.selectBestProduct());
		// request.setAttribute("bestList", pdao.selectNewProduct());

		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
