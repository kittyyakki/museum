package com.team4.museum.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.team4.museum.controller.action.Action;

public class MuseumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MuseumServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

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
