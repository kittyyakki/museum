package com.team4.museum.controller.action;

import java.io.IOException;
import java.util.ArrayList;
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		ArtworkDao adao = ArtworkDao.getInstance();
		String category = request.getParameter("category") == null ? "0" : request.getParameter("category"); 
		int categoryNum = Integer.parseInt(category);
		String titleState = "on";
		// 검색창에 입력한 단어 전달받음
		String searchWord = request.getParameter("searchWord");
		
		ArtworkCategory[] categoryList = ArtworkCategory.values();
		String categoryName = categoryList[categoryNum].toString();
		
		List<ArtworkVO> list = null;
		
		// 메인페이지에서 "예술품" 메뉴를 선택했을때 category == 0 (분류 : 전체)
		if(searchWord != null) { // 검색어로 조회
			list = adao.selectSearchArtwork(searchWord);
			titleState = "off";
		}
		else if(category.equals("0")) // 전체목록 조회
			list = adao.selectArtwork();
		else { // 카테고리 조회
			list = adao.selectCategoryArtwork(categoryName);
		}
		
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("titleState", titleState);
		// 카테고리 이름 전달
		request.setAttribute("categoryName", categoryName);
		// 카테고리로 조회한 예술품 리스트 전달
		request.setAttribute("artworkList", list);
		// 분류명 목록을 배열로 전달 
		request.setAttribute("artworkCategory", ArtworkCategory.values());
		request.setAttribute("categoryNum", categoryNum);
		 
		
		request.getRequestDispatcher("artworkList.jsp").forward(request, response);
	}
}
