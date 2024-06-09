package com.team4.museum.controller.action.gallery;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mseq = Integer.parseInt(request.getParameter("mseq"));
		MemberGalleryDao.getInstance().deleteMemberGallery(mseq);
		request.getRequestDispatcher("museum.do?command=galleryList").forward(request, response);
	}

}
