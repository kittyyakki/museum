package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDeleteMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] memberIds = request.getParameter("memberIds").split(",");

		for (String Id : memberIds) {
			MemberDao.getInstance().deleteMember(Id);
		}

		request.getRequestDispatcher("museum.do?command=adminMemberList").forward(request, response);
	}

}
