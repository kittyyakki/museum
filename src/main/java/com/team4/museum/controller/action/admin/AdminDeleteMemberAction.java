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
		String[] mseqList = request.getParameter("memberIds").split(",");

		for (String mseq : mseqList) {
			MemberDao.getInstance().deleteMember(mseq);
		}

		request.getRequestDispatcher("museum.do?command=adminMemberList").forward(request, response);
	}

}
