package com.team4.museum.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.dao.NoticeDao;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDao ndao = NoticeDao.getInstance();
		ArtworkDao adao = ArtworkDao.getInstance();

		for(int i = 1; i <= 4; i++) {
			List<ArtworkVO> randomArtworkList = adao.getRandomList();
			request.setAttribute("artworkList" + i, randomArtworkList);			
		}
		
		List<NoticeVO> noticeList = ndao.getRecentNotice();
		request.setAttribute("noticeList", noticeList);

		request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
	}

}
