package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.util.ArtworkCategory;
import com.team4.museum.vo.ArtworkVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		ArtworkVO avo = adao.selectArtworkOne(Integer.parseInt(request.getParameter("aseq")));

		request.setAttribute("category", ArtworkCategory.values());
		request.setAttribute("artwork", avo);
		request.getRequestDispatcher("artwork/artworkUpdateForm.jsp").forward(request, response);
	}

}
