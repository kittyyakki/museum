package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.List;


import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.util.NoticeCategory;
import com.team4.museum.util.Paging;
import com.team4.museum.vo.MemberVO;
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
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		NoticeDAO ndao = NoticeDAO.getInstance();
		session.removeAttribute("category");
		
		
		String category = request.getParameter("category") == null ? NoticeCategory.전체.name()
				: request.getParameter("category");
		
		
		int totalCount = ndao.getNoticeAllCount();
		
		Paging paging = new Paging();
		paging.setPage(getPage(request));
		paging.setTotalCount(totalCount);
		
		
		List<NoticeVO> noticeList = ndao.getAllnoitce(paging);

		request.setAttribute("categoryName", category);
		session.setAttribute("category", category);
		request.setAttribute("paging", paging);
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalCount", totalCount);
        

        request.setAttribute("noticeCategory", NoticeCategory.values());
		request.getRequestDispatcher("notice/noticeList.jsp").forward(request, response);
		
	}
	private int getPage(HttpServletRequest request) {
        if (request.getParameter("page") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            request.getSession().setAttribute("page", page);
            return page;
        } else if (request.getSession().getAttribute("page") != null) {
            return (int) request.getSession().getAttribute("page");
        }
        return 1;
    
	}
	

}
