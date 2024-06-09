package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.util.ArtworkCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("category", ArtworkCategory.values());
		request.getRequestDispatcher("artwork/artworkWriteForm.jsp").forward(request, response);
	}

}
