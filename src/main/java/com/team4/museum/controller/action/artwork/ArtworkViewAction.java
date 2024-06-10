package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.vo.ArtworkVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aseq = Integer.parseInt(request.getParameter("aseq"));

		ArtworkDao adao = ArtworkDao.getInstance();
		ArtworkVO avo = adao.selectArtworkOne(aseq);

		request.setAttribute("artwork", avo);
		request.getRequestDispatcher("/WEB-INF/views/artwork/artworkView.jsp").forward(request, response);
	}

}
