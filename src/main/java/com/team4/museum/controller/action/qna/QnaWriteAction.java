package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaWriteAction extends QnaWriteFormAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaVO qnaVO = getQnaVO(request, response);

		// 삭제 버튼을 누른 경우
		if (request.getParameter("delete") != null) {
			if (qnaVO != null) {
				QnaDao.getInstance().deleteQna(qnaVO.getQseq());
			}
			response.sendRedirect("museum.do?command=qnaList");
			return;
		}

		// 등록 버튼을 누른 경우
		if (qnaVO == null) {
			// 새로 작성하는 경우 qnaVO를 새로 생성
			qnaVO = new QnaVO();
		}

		qnaVO.setTitle(request.getParameter("title"));
		qnaVO.setContent(request.getParameter("content"));
		qnaVO.setEmail(request.getParameter("email"));
		qnaVO.setPhone(request.getParameter("phone"));
		qnaVO.setPublic(request.getParameter("publicyn") != null && request.getParameter("publicyn").equals("on"));
		qnaVO.setPwd(request.getParameter("pwd"));

		QnaDao dao = QnaDao.getInstance();
		int qseq;
		if (qnaVO.getQseq() == null) { // 새로 작성하는 경우
			qseq = dao.insertQna(qnaVO);
		} else { // 수정하는 경우
			qseq = dao.updateQna(qnaVO);
		}

		// 세션에 비밀번호 확인 기록 저장
		request.getSession().setAttribute("qnaPass" + qseq, true);

		// 문의글 페이지로 이동
		response.sendRedirect("museum.do?command=qnaView&qseq=" + qseq);
	}
}
