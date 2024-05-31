package com.team4.museum.controller.action.notice;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nseq = Integer.parseInt( request.getParameter("nseq") );
		NoticeDAO ndao = NoticeDAO.getInstance();
		
		// 조회수 증가
		ndao.plusReadCount( nseq );
		// 게시물 상세 조회
		NoticeVO nvo = ndao.selectNotice( nseq );
		
		
		request.setAttribute("notice", ndao);
		request.getRequestDispatcher("notice/noticeView.jsp").forward(request, response);
		
	}

}
