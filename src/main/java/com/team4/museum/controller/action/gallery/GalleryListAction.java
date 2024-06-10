package com.team4.museum.controller.action.gallery;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.MemberGalleryVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberGalleryDao mgdao = MemberGalleryDao.getInstance();
		Pagination pagination = Pagination.with(request, mgdao.getGalleryAllCount(), "command=galleryList");
		pagination.setItemsPerPage(4);
		List<MemberGalleryVO> list = mgdao.getAllGallery(pagination);
		
		
		request.setAttribute("galleryList", list);
		request.setAttribute("pagination", pagination);
		request.getRequestDispatcher("/WEB-INF/views/gallery/galleryList.jsp").forward(request, response);
	}

}