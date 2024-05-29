package com.team4.museum.controller.action;

import java.io.IOException;
import java.util.List;

import com.team4.museum.util.ArtworkCategory;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.dao.ArtworkDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtworkListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtworkDao adao = ArtworkDao.getInstance();
		List<ArtworkVO> list = null;
		// 메인페이지에서 "예술품" 메뉴를 선택했을때 category == 0 (분류 : 전체)
		String category = request.getParameter("category");
		int categoryNum = Integer.parseInt(category);
		if(category.equals("0")) {
			list = adao.selectArtwork();
		}else {
			list = adao.selectCategoryArtwork(category);
		}
		// 카테고리로 조회한 예술품 리스트 전달
		request.setAttribute("artworkList", list);
		// 분류명 목록을 배열로 전달
		request.setAttribute("artworkCategory", ArtworkCategory.values());
		request.setAttribute("categoryNum", categoryNum);
		request.getRequestDispatcher("artworkList.jsp").forward(request, response);
	}

	
}
