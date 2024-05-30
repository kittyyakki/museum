package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.ArrayList;


import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	// 게시판의 게시물을 모두 조회해서  request 에 담고 noticeList.jsp  로  포워딩합니다
			NoticeDAO ndao = NoticeDAO.getInstance();
			NoticeVO mvo = ndao.getNotice( num );
			
			request.setAttribute("boardList", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
}

