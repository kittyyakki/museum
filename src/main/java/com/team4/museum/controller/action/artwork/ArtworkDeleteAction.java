package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		adao.deleteArtwork(Integer.parseInt(request.getParameter("aseq")));
		request.getRequestDispatcher("museum.do?command=artworkList").forward(request, response);
	}

}
