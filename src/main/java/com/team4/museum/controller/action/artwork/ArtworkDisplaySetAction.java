package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkDisplaySetAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aseq = Integer.parseInt(request.getParameter("aseq"));
		ArtworkDao.getInstance().displayChangeArtwork(aseq);
		
		request.getRequestDispatcher("museum.do?command=artworkView&aseq=" + aseq).forward(request, response);
	}

}
