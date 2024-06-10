package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GrantAdminRightsAction implements AdminAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkAdmin(request, response)) {
			return;
		}
		// request.getParameter("memberIds") 는 "Id1,Id2,Id3,..." String 값임
		String action = request.getParameter("action");
		String[] memberIds = request.getParameter("memberIds").split(",");

		for (String Id : memberIds) {
			MemberDao.getInstance().adminRightsAction(Id, action);
		}

		request.getRequestDispatcher("museum.do?command=adminMemberList").forward(request, response);
	}

}
