package com.team4.museum.controller.action.notice;

import java.io.IOException;



import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertNoticeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeDAO ndao = NoticeDAO.getInstance();
		NoticeVO nvo = new NoticeVO();
		
		/* 소식지에 게시물을 올릴때 필요한 파라미터(관리자용) */
		nvo.setTitle( request.getParameter("title") );
		nvo.setAuthor( request.getParameter("author") );
		nvo.setContent( request.getParameter("content") );
		
		ndao.insertNotice(nvo);
		response.sendRedirect("museum.do?command=index");
	}
}
