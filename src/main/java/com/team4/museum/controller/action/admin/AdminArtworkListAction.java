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

public class AdminArtworkListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		String searchWord = request.getParameter("searchWord");
		
		Pagination pagination = Pagination
				.fromRequest(request)
				.setUrlTemplate("museum.do?command=adminArtworkList&page=%d")
				.setItemCount(adao.getAllCount())
				.setItemsPerPage(10);

		request.setAttribute("pagination", pagination);

		if(searchWord != null) {
			request.setAttribute("artworkList", adao.searchArtworkAdmin(pagination, searchWord));			
		}else {
			request.setAttribute("artworkList", adao.selectArtwork(pagination));
		}
		request.getRequestDispatcher("admin/adminArtworkList.jsp").forward(request, response);
	}

}
