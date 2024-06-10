package com.team4.museum.controller.action.gallery;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;
import com.team4.museum.vo.MemberGalleryVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mseq = Integer.parseInt(request.getParameter("mseq"));
		
		MemberGalleryDao mgdao = MemberGalleryDao.getInstance();
		
		// 조회수 1 증가
		mgdao.increaseReadCount(mseq);
		
		
		MemberGalleryVO mgvo = mgdao.getMemberGalleryOne(mseq);
		request.setAttribute("galleryVO", mgvo);
		
		request.getRequestDispatcher("/WEB-INF/views/gallery/galleryView.jsp").forward(request, response);
	}

}
