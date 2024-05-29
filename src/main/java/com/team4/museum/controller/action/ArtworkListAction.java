package com.team4.museum.controller.action;

import java.io.IOException;

import com.team4.museum.util.ArtworkCategory;  
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		request.setAttribute("artworkCategory", ArtworkCategory.values());
		request.getRequestDispatcher("artworkList.jsp").forward(request, response);
	}

	
}
