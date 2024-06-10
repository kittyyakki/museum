package com.team4.museum.controller.action.notice;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.util.NoticeCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertNoticeFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("noticeCategory", NoticeCategory.values());
		request.getRequestDispatcher("/WEB-INF/views/notice/insertNoticeForm.jsp").forward(request, response);
	}

}
