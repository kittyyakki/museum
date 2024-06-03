package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.ArtworkDao;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.vo.MemberVO;
import com.team4.museum.vo.NoticeVO;
import com.team4.museum.util.NoticeCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeCategoryAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		NoticeDAO ndao = NoticeDAO.getInstance();
		session.removeAttribute("category");

		String category = request.getParameter("category") == null ? NoticeCategory.전체.name()
				: request.getParameter("category");


		List<NoticeVO> list = null;


		request.setAttribute("categoryName", category);
		session.setAttribute("category", category);
		// 카테고리로 조회한 예술품 리스트 전달
		request.setAttribute("categoryNotice",list);
		// 분류명 목록을 배열로 전달
		request.setAttribute("noticeCategory", NoticeCategory.values());
        request.getRequestDispatcher("notice/noticeCategory.jsp").forward(request, response);
	}

}
