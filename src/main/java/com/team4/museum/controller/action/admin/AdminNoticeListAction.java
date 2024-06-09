package com.team4.museum.controller.action.admin;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDao;
import com.team4.museum.util.Pagination;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminNoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDao ndao = NoticeDao.getInstance();
		String searchWord = request.getParameter("searchWord");
		String noticeCategory = request.getParameter("noticeCategory");

		Pagination pagination = Pagination.with(request, ndao.getNoticeCount(), "command=adminNoticeList");
		List<NoticeVO> noticeList;
		if (searchWord != null) {
			noticeList = ndao.searchNoticeList(pagination, searchWord);
		} else if (noticeCategory != null) {
			noticeList = ndao.selectCategoryNotice(noticeCategory, pagination);
			request.setAttribute("selectedCategory", noticeCategory);
		} else {
			noticeList = ndao.selectNoticeList(pagination);
		}

		request.setAttribute("noticeList", noticeList);

		request.getRequestDispatcher("admin/adminNoticeList.jsp").forward(request, response);
	}

}
