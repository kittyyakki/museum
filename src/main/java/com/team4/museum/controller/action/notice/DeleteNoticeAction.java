package com.team4.museum.controller.action.notice;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNoticeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nseq = Integer.parseInt( request.getParameter("nseq") );
		NoticeDAO ndao = NoticeDAO.getInstance();
		ndao.deleteNotice(nseq);

		request.getRequestDispatcher("notice/noticeDeleteOk.jsp").forward(request, response);
	}

}
