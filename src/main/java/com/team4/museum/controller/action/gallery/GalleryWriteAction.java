package com.team4.museum.controller.action.gallery;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.member.LoginAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LoginAction.isLogined(request, response)) {
			request.getRequestDispatcher("gallery/galleryWriteForm.jsp").forward(request, response);
		}
	}

}