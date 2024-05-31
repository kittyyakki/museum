package com.team4.museum.controller.action.qna;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaPwdCheckFormAction implements Action {
	private final int RESULT_SUCCESS = 0;
	private final int RESULT_REQUEST_PWD = 1;
	private final int RESULT_NOT_FOUND = 2;
	private final int RESULT_PWD_WRONG = 3;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 파라미터에 'qseq'가 없으면 'result'를 RESULT_NOT_FOUND 로 설정
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			setResult(request, response, RESULT_NOT_FOUND);
			return;
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 'result'를 RESULT_NOT_FOUND 로 설정
		if (qvo == null) {
			setResult(request, response, RESULT_NOT_FOUND);
			return;
		}

		// 'qvo'가 공개 상태면 'result'를 RESULT_SUCCESS 로 설정
		if (qvo.isPublic()) {
			setResult(request, response, RESULT_SUCCESS);
			return;
		}

		// 어드민이면 'result'를 RESULT_SUCCESS 로 설정
		if (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin")) {
			setResult(request, response, RESULT_SUCCESS);
			return;
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 'result'를 RESULT_SUCCESS 로 설정
		if (session.getAttribute("qnaPass" + qseq) != null) {
			setResult(request, response, RESULT_SUCCESS);
			return;
		}

		// 'pwd' 파라미터가 없으면 'result'를 RESULT_REQUEST_PWD 으로 설정
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			setResult(request, response, RESULT_REQUEST_PWD);
			return;
		}

		// 'pwd' 파라미터에 입력된 값이 'QnaVO'의 'pwd'와 같으면 'result'를 RESULT_SUCCESS 로 설정
		if (qvo.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			setResult(request, response, RESULT_SUCCESS);
		} else { // 다르면 'result'를 RESULT_PWD_WRONG 으로 설정
			setResult(request, response, RESULT_PWD_WRONG);
		}
	}

	private void setResult(HttpServletRequest request, HttpServletResponse response, int result) {
		request.setAttribute("qnaPwdResult", result);
		try {
			request.getRequestDispatcher("qna/qnaPwdCheck.jsp?qseq=" + request.getParameter("qseq"))
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
