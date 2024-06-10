package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDeleteGalleryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] mseqList = request.getParameter("memberIds").split(",");

		for (String mseq : mseqList) {
			MemberGalleryDao.getInstance().deleteMemberGallery(Integer.parseInt(mseq));
		}

		request.getRequestDispatcher("museum.do?command=adminMemberGalleryList").forward(request, response);
	}

}
