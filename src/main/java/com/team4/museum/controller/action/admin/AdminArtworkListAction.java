package com.team4.museum.controller.action.admin;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.util.ArtworkCategory;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.ArtworkVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminArtworkListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청의 파라미터를 받아옵니다.
		String category = request.getParameter("category");
		String displayState = request.getParameter("displayState");
		String searchWord = request.getParameter("searchWord");

		// 요청의 속성으로 저장합니다.
		request.setAttribute("category", category);
		request.setAttribute("displayState", displayState);
		request.setAttribute("searchWord", searchWord);

		// 빈 파라미터를 빈 문자열로 치환합니다.
		if ("분류".equals(category)) {
			category = null;
		}

		// 예술품 목록 및 페이지네이션을 저장합니다.
		ArtworkDao adao = ArtworkDao.getInstance();
		Pagination pagination = Pagination.with(
				request,
				adao.getCount(category, displayState, searchWord),
				"command=adminArtworkList"
						+ "&artworkCategory=" + category
						+ "&displayState=" + displayState
						+ "&searchWord=" + searchWord);

		request.setAttribute("artworkList", adao.getAll(category, displayState, searchWord, pagination));
		request.getRequestDispatcher("/WEB-INF/views/admin/adminArtworkList.jsp").forward(request, response);
	}

}
