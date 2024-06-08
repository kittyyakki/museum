package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.qna.QnaPwdCheckAjaxAction.isAlreadyPwdChecked;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaWriteFormAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("qnaVO", getQnaVO(request, response));
		request.getRequestDispatcher("qna/qnaWriteForm.jsp").forward(request, response);
	}

	protected QnaVO getQnaVO(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 null 을 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return null;
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaVO qnaVO = QnaDao.getInstance().getQna(qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 있고, 세션에 비밀번호 확인 기록이 있는 경우
		if (qnaVO != null && isAlreadyPwdChecked(request, qseq)) {
			// qnaVO 를 반환
			return qnaVO;
		}

		// 아니면 null 을 반환
		return null;
	}

}
