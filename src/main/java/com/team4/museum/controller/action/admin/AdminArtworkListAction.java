package com.team4.museum.controller.action.admin;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.ArtworkVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminArtworkListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		String searchWord = request.getParameter("searchWord");
		String displayState = request.getParameter("displayState");
		String artworkCategory = request.getParameter("artworkCategory");

		Pagination pagination = Pagination.with(request, adao.getAllCount(), "command=adminArtworkList");
		
		List<ArtworkVO> artworkList = null;
		
		if (searchWord != null) {
			artworkList = adao.searchArtworkAdmin(pagination, searchWord);
			request.setAttribute("searchWord", searchWord);
		} else if (displayState != null) {
			artworkList = adao.selectArtworkAsDisplayyn(pagination, displayState);
			request.setAttribute("displayState", displayState);
		}else if (artworkCategory != null) {
			artworkList = adao.selectArtworkByCategory(artworkCategory, pagination);
			request.setAttribute("selectedCategory", artworkCategory);
		} else {
			artworkList = adao.selectArtwork(pagination);
		}

		request.setAttribute("artworkList", artworkList);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminArtworkList.jsp").forward(request, response);
	}

}
