package com.team4.museum.controller.action.notice;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDao;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateNoticeFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nseq = Integer.parseInt(request.getParameter("nseq"));
		NoticeDao ndao = NoticeDao.getInstance();

		// 게시물 상세 조회
		NoticeVO nvo = ndao.getNotice(nseq);

		request.setAttribute("noticeUpdate", nvo);
		request.getRequestDispatcher("/WEB-INF/views/notice/updateNoticeForm.jsp").forward(request, response);
	}

}
