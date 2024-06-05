package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.member.LoginAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaReplyAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateReply(request, response);
		response.sendRedirect("museum.do?command=qnaView&qseq=" + request.getParameter("qseq"));
	}

	private boolean updateReply(HttpServletRequest request, HttpServletResponse response) {
		// 어드민이 아니면 false 를 반환
		if (!LoginAction.isAdmin(request)) {
			return false;
		}

		// 파라미터에 'qseq'가 없으면 false 를 반환
		String qseqParam = request.getParameter("qseq");
		if (qseqParam == null || qseqParam.equals("") || !qseqParam.matches("^[0-9]*$")) {
			return false;
		}

		int qseq = Integer.parseInt(qseqParam);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qnaVO", qnaVO);

		// 'qseq' 파라미터에 해당하는 데이터가 없으면 false 를 반환
		if (qnaVO == null) {
			return false;
		}
		request.setAttribute("qnaVO", qnaVO);

		try {
			// 답변을 업데이트
			qdao.updateQnaReply(qseq, request.getParameter("reply"));
		} catch (Exception e) {
			// 업데이트 실패 시 false 를 반환
			e.printStackTrace();
			return false;
		}

		// 업데이트 성공 시 true 를 반환
		return true;
	}

}
