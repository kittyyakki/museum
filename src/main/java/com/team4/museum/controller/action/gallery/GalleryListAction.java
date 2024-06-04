package com.team4.museum.controller.action.gallery;

import java.io.IOException;
import com.team4.museum.controller.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("gallery/galleryList.jsp").forward(request, response);
	}
	
	
}