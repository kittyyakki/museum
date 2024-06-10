package com.team4.museum.controller.action.artwork;

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
import jakarta.servlet.http.HttpSession;

public class ArtworkListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청의 파라미터를 받아옵니다.
		String category = request.getParameter("category");
		String searchWord = request.getParameter("searchWord");

		// 요청의 속성으로 저장합니다.
		request.setAttribute("category", category);
		request.setAttribute("searchWord", searchWord);

		// 빈 파라미터를 빈 문자열로 치환합니다.
		if (ArtworkCategory.전체.name().equals(category)) {
			category = null;
		}

		// 예술품 목록 및 페이지네이션을 저장합니다.
		ArtworkDao adao = ArtworkDao.getInstance();
		Pagination pagination = Pagination.with(
				request,
				adao.getCount(category, "Y", searchWord),
				"command=artworkList"
						+ "&artworkCategory=" + category
						+ "&displayState=" + "Y"
						+ "&searchWord=" + searchWord);

		request.setAttribute("artworkList", adao.getAll(category, "Y", searchWord, pagination));
		request.getRequestDispatcher("/WEB-INF/views/artwork/artworkList.jsp").forward(request, response);
	}

}
