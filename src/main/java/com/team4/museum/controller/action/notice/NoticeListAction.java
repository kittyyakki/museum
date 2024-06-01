package com.team4.museum.controller.action.notice;

import java.io.IOException;
import java.util.List;


import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.NoticeDAO;
import com.team4.museum.util.Paging;
import com.team4.museum.vo.NoticeVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판의 게시물을 모두 조회해서  request 에 담고 noticeList.jsp  로  포워딩합니다
		NoticeDAO ndao = NoticeDAO.getInstance();
		//NoticeVO nvo = new NoticeVO();
		
		int totalCount = ndao.getNoticeAllCount();
		
		Paging paging = new Paging();
		paging.setPage(getPage(request));
		paging.setTotalCount(totalCount);
		
		/* List<NoticeVO> noticeList = ndao.selectNoticeList(paging); */
		List<NoticeVO> noticeList = ndao.getAllnoitce(paging);

		request.setAttribute("paging", paging);
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalCount", totalCount);

		
		RequestDispatcher rd = request.getRequestDispatcher("notice/noticeList.jsp");
		rd.forward(request, response);
		
		//System.out.println("list ok : " + noticeList);
		//System.out.println(nvo.getTitle());

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
