package com.team4.museum.controller.action.gallery;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;


import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;
import com.team4.museum.vo.MemberGalleryVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


public class GalleryWriteAction  implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("gallery/galleryWriteForm.jsp").forward(request, response);
	}

	
}
