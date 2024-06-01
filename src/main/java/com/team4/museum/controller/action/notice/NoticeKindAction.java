package com.team4.museum.controller.action.notice;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeKindAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");

        request.setAttribute("categoryNotice", NoticeDAO.getInstance().selectCategoryNotice(category));
        request.setAttribute("category", category);
        request.getRequestDispatcher("notice/noticeCategory.jsp").forward(request, response);
		
	}

}
