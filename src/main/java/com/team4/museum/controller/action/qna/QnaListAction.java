package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaListAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaDao qdao = QnaDao.getInstance();

		Paging paging = new Paging();
		paging.setPage(getPage(request));
		paging.setTotalCount(qdao.getAllCount());

		request.setAttribute("paging", paging);
		request.setAttribute("qnaList", qdao.selectQna(paging));

		request.getRequestDispatcher("qna/qnaList.jsp").forward(request, response);
	}

	private int getPage(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			int page = Integer.parseInt(request.getParameter("page"));
			request.getSession().setAttribute("page", page);
			return page;
		} else if (request.getSession().getAttribute("page") != null) {
			return (int) request.getSession().getAttribute("page");
		}
		return 1;
	}
}
