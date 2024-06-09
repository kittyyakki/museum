package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDeleteNoticeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] nseqList = request.getParameter("memberIds").split(",");

		for (String nseq : nseqList) {
			NoticeDao.getInstance().deleteNotice(Integer.parseInt(nseq));
		}

		request.getRequestDispatcher("museum.do?command=adminNoticeList").forward(request, response);
	}

}
