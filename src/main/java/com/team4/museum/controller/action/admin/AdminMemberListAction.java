package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminMemberListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDao mdao = MemberDao.getInstance();
		String searchWord = request.getParameter("searchWord");

		Pagination pagination = Pagination.with(request, mdao.getAllCount(), "command=adminMemberList");
		if (searchWord != null) {
			pagination.setItemCount(mdao.getSearchCount(searchWord));
			pagination.setUrlTemplate("museum.do?command=adminMemberList&page=%d&searchWord=" + searchWord);
			request.setAttribute("memberList", mdao.searchMemberList(pagination, searchWord));
			request.setAttribute("searchWord", searchWord);
		} else {
			request.setAttribute("memberList", mdao.getMemberList(pagination));
		}

		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberList.jsp").forward(request, response);
	}

}
