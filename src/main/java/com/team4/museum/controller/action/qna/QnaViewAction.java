package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaViewAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.removeAttribute("qnaContent");
		request.removeAttribute("qvo");

		String url = "qna/qnaView.jsp?qseq=" + request.getParameter("qseq");

		// 파라미터에 'qseq'가 없으면 'result'를 RESULT_NOT_FOUND 로 설정
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 'result'를 RESULT_NOT_FOUND 로 설정
		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = qdao.getQna(qseq);
		request.setAttribute("qvo", qvo);
		if (qvo == null) {
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}

		// 'qvo'가 공개 상태면 'qnaContent'를 제공
		if (qvo.isPublic()) {
			request.setAttribute("qnaContent", qvo.getContent());
		}

		// 어드민이면 'qnaContent'를 제공
		if (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin")) {
			request.setAttribute("qnaContent", qvo.getContent());
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 'qnaContent'를 제공
		if (session.getAttribute("qnaPass" + qseq) != null) {
			request.setAttribute("qnaContent", qvo.getContent());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
