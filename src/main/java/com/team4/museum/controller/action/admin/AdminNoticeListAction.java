package com.team4.museum.controller.action.admin;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminNoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO ndao = NoticeDAO.getInstance();
		String searchWord = request.getParameter("searchWord");
		String noticeCategory = request.getParameter("noticeCategory");

		Pagination pagination = Pagination
				.fromRequest(request)
				.setUrlTemplate("museum.do?command=adminNoticeList&page=%d")
				.setItemCount(ndao.getNoticeCount());

		List<NoticeVO> noticeList = null;

		if (searchWord != null) {
			noticeList = ndao.searchNoticeList(pagination, searchWord);
		} else if (noticeCategory != null) {
			noticeList = ndao.selectCategoryNotice(noticeCategory, pagination);
			request.setAttribute("selectedCategory", noticeCategory);
		} else {
			noticeList = ndao.selectNoticeList(pagination);
		}

		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pagination", pagination);

		request.getRequestDispatcher("admin/adminNoticeList.jsp").forward(request, response);
	}

}
