package com.team4.museum.controller.action.admin;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDeleteArtworkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] aseqList = request.getParameter("memberIds").split(",");

		for (String aseq : aseqList) {
			ArtworkDao.getInstance().deleteArtwork(Integer.parseInt(aseq));
		}

		request.getRequestDispatcher("museum.do?command=adminArtworkList").forward(request, response);
	}

}
