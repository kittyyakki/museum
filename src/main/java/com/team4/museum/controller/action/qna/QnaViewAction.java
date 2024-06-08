package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.member.LoginAjaxAction.isAdmin;
import static com.team4.museum.controller.action.qna.QnaPwdCheckAjaxAction.isAlreadyPwdChecked;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaViewAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaVO qnaVO = getQnaVO(request, response);

		// 'qnaVO'가 null 이면 잘못된 요청임을 표시하고 뒤로 가기 실행
		if (qnaVO == null) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<script>alert('잘못된 요청입니다.'); history.back();</script>");
			return;
		}

		request.setAttribute("qnaVO", qnaVO);
		request.getRequestDispatcher("qna/qnaView.jsp").forward(request, response);
	}

	private QnaVO getQnaVO(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 null 을 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return null;
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaVO qnaVO = QnaDao.getInstance().getQna(qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 있고, 세션에 비밀번호 확인 기록이 있거나 관리자이거나 문의글이 공개 상태인 경우
		if (qnaVO != null && isAlreadyPwdChecked(request, qseq) || isAdmin(request) || qnaVO.isPublic()) {
			// qnaVO 를 반환
			return qnaVO;
		}

		// 아니면 null 을 반환
		return null;
	}

}
