package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.Pagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaListAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaDao qdao = QnaDao.getInstance();

		Pagination pagination = Pagination
				.fromRequest(request)
				.setUrlTemplate("museum.do?command=qnaList&page=%d")
				.setItemCount(qdao.getAllCount())
				.setItemsPerPage(10);

		request.setAttribute("pagination", pagination);
		request.setAttribute("qnaList", qdao.selectQna(pagination));

		request.getRequestDispatcher("qna/qnaList.jsp").forward(request, response);
	}
}
