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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		ArtworkDao adao = ArtworkDao.getInstance();
		session.removeAttribute("category");

		String category = request.getParameter("category") == null ? ArtworkCategory.전체.name()
				: request.getParameter("category");

		// 검색창에 입력한 단어 전달받음
		String searchWord = request.getParameter("searchWord");

		List<ArtworkVO> list = null;

		Pagination pagination = Pagination.with(request, 0, "command=artworkList&category=" + category);
		pagination.setItemsPerPage(8);
		if (searchWord != null) { // 검색어로 조회
			pagination.setItemCount(adao.getSearchCount(searchWord));
			pagination.setUrlTemplate("museum.do?command=artworkList&page=%d&searchWord=" + searchWord);
			list = adao.searchPublicArtwork(searchWord, pagination);
			category = null;
		} else if (category.equals(ArtworkCategory.전체.name())) { // 전체목록 조회
			pagination.setItemCount(adao.getDisplayCount());
			list = adao.selectPublicArtwork(pagination);
		} else { // 카테고리 조회
			pagination.setItemCount(adao.getPublicCategoryCount(category));
			list = adao.selectPublicArtworkByCategory(category, pagination);
		}

		request.setAttribute("searchWord", searchWord);

		// 카테고리 이름 전달
		request.setAttribute("categoryName", category);
		session.setAttribute("category", category);

		// 카테고리로 조회한 예술품 리스트 전달
		request.setAttribute("artworkList", list);

		// 분류명 목록을 배열로 전달
		request.setAttribute("artworkCategory", ArtworkCategory.values());
		request.getRequestDispatcher("/WEB-INF/views/artwork/artworkList.jsp").forward(request, response);
	}

}
