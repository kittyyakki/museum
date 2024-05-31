package com.team4.museum.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.team4.museum.controller.action.Action;

@MultipartConfig(	fileSizeThreshold = 1024*1024,	maxFileSize = 1024*1024*5, 	maxRequestSize = 1024*1024*5*5 )
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
