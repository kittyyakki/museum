package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] memberIds = request.getParameter("memberIds").split(",");
		
		for (String id : memberIds) {
			QnaDao.getInstance().deleteQna(Integer.parseInt(id));
		}
		
		request.getRequestDispatcher("museum.do?command=adminQnaList").forward(request, response);
	}

}
