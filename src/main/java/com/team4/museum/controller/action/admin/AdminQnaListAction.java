package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaDao qdao = QnaDao.getInstance();
		String isReply = request.getParameter("isReply");
		String searchWord = request.getParameter("searchWord");

		Pagination pagination = Pagination.with(request, qdao.getAllCount(), "command=adminQnaList");
		if (searchWord != null) {
			/* pagination.setItemCount(qdao.getSearchCount(searchWord)); */
			pagination.setUrlTemplate("museum.do?command=adminQnaList&page=%d&searchWord=" + searchWord);
			request.setAttribute("qnaList", qdao.searchQna(pagination, searchWord));

		} else if (isReply != null) {
			request.setAttribute("qnaList", qdao.selectQna(pagination, isReply));
			request.setAttribute("isReply", isReply);
		} else {
			request.setAttribute("qnaList", qdao.selectQna(pagination));
		}

		request.getRequestDispatcher("/WEB-INF/views/admin/adminQnaList.jsp").forward(request, response);
	}

}
