package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.util.NoticeCategory;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		NoticeDAO ndao = NoticeDAO.getInstance();
		session.removeAttribute("category");

		String category = request.getParameter("category") == null ? NoticeCategory.전체.name()
				: request.getParameter("category");

		Pagination pagination = Pagination
				.fromRequest(request)
				.setUrlTemplate("museum.do?command=noticeList&category=" + category + "&page=%d")
				.setItemCount(ndao.getNoticeCount(category));

		List<NoticeVO> noticeList = ndao.selectNoticeList(pagination);
		if (category.equals(NoticeCategory.전체.name())) {// 전체목록 조회
			pagination.setItemCount(ndao.getNoticeCount());
			noticeList = ndao.selectNoticeList(pagination);
		} else if (category.equals(NoticeCategory.매거진.name())) {
			request.getRequestDispatcher("notice/noticeMagazine.jsp").forward(request, response);
			return;
		} else if (category.equals(NoticeCategory.신문.name())) {
			request.getRequestDispatcher("notice/noticeNewpaper.jsp").forward(request, response);
			return;
		} else { // 카테고리 조회
			noticeList = ndao.selectCategoryNotice(category, pagination);
		}

		System.out.println(pagination.getItemCount());

		request.setAttribute("categoryName", category);
		session.setAttribute("category", category);
		request.setAttribute("pagination", pagination);
		request.setAttribute("noticeList", noticeList);

		request.setAttribute("noticeCategory", NoticeCategory.values());
		request.getRequestDispatcher("notice/noticeList.jsp").forward(request, response);

	}

}
