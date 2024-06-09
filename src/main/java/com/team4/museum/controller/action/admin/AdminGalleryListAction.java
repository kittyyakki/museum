package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;
import com.team4.museum.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminGalleryListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberGalleryDao mgdao = MemberGalleryDao.getInstance();
		Pagination pagination = Pagination.with(request, mgdao.getAllCount(), "command=adminMemberGalleryList");
		
		
		request.setAttribute("galleryList", mgdao.getAllGallery(pagination));
		request.getRequestDispatcher("admin/adminGalleryList.jsp").forward(request, response);
	}

}
