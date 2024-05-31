package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.util.Paging;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO ndao = NoticeDAO.getInstance();
		Paging paging = new Paging();

		int count = ndao.getNoticeAllCount();
		paging.setTotalCount(count);

		List<NoticeVO> list = ndao.selectNoticeList(paging);

		for (NoticeVO nvo : list) {
			count = ndao.getReplyCount(nvo.getNseq());
			nvo.setReadcount(count);

		}
		request.setAttribute("noticeList", list);
		request.setAttribute("paging", paging);
		
		RequestDispatcher rd = request.getRequestDispatcher("noticeList.jsp");
		rd.forward(request, response);

	}
}
