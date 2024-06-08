package com.team4.museum.controller.action;

import java.io.IOException;

import com.team4.museum.util.AjaxResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

abstract public class AjaxAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjaxResult result = handleAjaxRequest(request, response);

		response.setStatus(result.code);
		response.setContentType("application/json");
		try {
			response.getWriter().write(result.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	abstract protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
