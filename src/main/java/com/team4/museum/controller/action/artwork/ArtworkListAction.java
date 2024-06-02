package com.team4.museum.controller.action.artwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team4.museum.util.ArtworkCategory;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.vo.MemberVO;
import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArtworkListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		// artworkView 페이지에서 '목록으로'이동하기 위해 현재 조회중인 category를 세션에 저장
		session.removeAttribute("category");
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");

		ArtworkDao adao = ArtworkDao.getInstance();
		String category = request.getParameter("category") == null ? ArtworkCategory.전체.name()
				: request.getParameter("category");
		String titleState = "on";
		// 검색창에 입력한 단어 전달받음
		String searchWord = request.getParameter("searchWord");

		List<ArtworkVO> list = null;

		// 관리자 계정으로 로그인한 경우
		if(mvo != null && mvo.getAdminyn().equals("Y")) {
			if (searchWord != null) { // 검색어로 조회
				list = adao.searchArtworkAdmin(searchWord);
				category = null;
//				titleState = "off";
			} else if (category.equals(ArtworkCategory.전체.name())) // 전체목록 조회
				list = adao.selectArtworkAdmin();				
			else { // 카테고리 조회
				list = adao.selectCategoryArtworkAdmin(category);
			}
		// 관리자 계정이 아닌경우 displayyn=="N" 인 예술품이 표시되지 않아야함
		}else {
			if (searchWord != null) { // 검색어로 조회
				list = adao.searchArtwork(searchWord);
				category = null;
//				titleState = "off";
			} else if (category.equals(ArtworkCategory.전체.name())) // 전체목록 조회
				list = adao.selectArtwork();				
			else { // 카테고리 조회
				list = adao.selectCategoryArtwork(category);
			}
		}
		
		request.setAttribute("searchWord", searchWord);
//		request.setAttribute("titleState", titleState);
		// 카테고리 이름 전달
		request.setAttribute("categoryName", category);
		session.setAttribute("category", category);
		// 카테고리로 조회한 예술품 리스트 전달
		request.setAttribute("artworkList", list);
		// 분류명 목록을 배열로 전달
		request.setAttribute("artworkCategory", ArtworkCategory.values());
		request.getRequestDispatcher("artwork/artworkList.jsp").forward(request, response);
	}
}
