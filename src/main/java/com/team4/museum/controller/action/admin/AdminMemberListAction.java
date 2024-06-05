package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminMemberListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDao mdao = MemberDao.getInstance();
		String searchWord = request.getParameter("searchWord");
		String searchFieldName = request.getParameter("searchFieldName");
		
		Pagination pagination = Pagination
				.fromRequest(request)
				.setUrlTemplate("museum.do?command=adminMemberList&page=%d")
				.setItemCount(mdao.getAllCount())
				.setItemsPerPage(10);

		request.setAttribute("pagination", pagination);
		
		if(searchWord != null) {
			request.setAttribute("memberList", mdao.searchMemberList(pagination, searchWord));
			request.setAttribute("searchWord", searchWord);
		}else {
			request.setAttribute("memberList",mdao.getMemberList(pagination));
		}
		
		request.getRequestDispatcher("admin/adminMemberList.jsp").forward(request, response);
	}

}
