package com.team4.museum.controller.action.artwork;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.util.MultipartFileInfo;
import com.team4.museum.vo.ArtworkVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		ArtworkVO avo = new ArtworkVO();
		avo.setName(request.getParameter("artname"));
		avo.setCategory(request.getParameter("category"));
		avo.setArtist(request.getParameter("artist"));
		avo.setYear(request.getParameter("year"));
		avo.setMaterial(request.getParameter("material"));
		avo.setSize(request.getParameter("size"));
		avo.setDisplayyn(request.getParameter("displayYn"));
		avo.setContent(request.getParameter("content"));

		MultipartFileInfo info = MultipartFileInfo.getFromRequest(request, "images/artwork");
		avo.setImage(info.getFileName());
		avo.setSavefilename(info.getSaveFileName());

		adao.insertArtwork(avo);
		request.getRequestDispatcher("museum.do?command=artworkList").forward(request, response);
	}

}
