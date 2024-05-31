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
		request.setAttribute("qnaPwdResult", getResult(request, response));
		try {
			request.getRequestDispatcher("qna/qnaPwdCheck.jsp?qseq=" + request.getParameter("qseq"))
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private int getResult(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 RESULT_NOT_FOUND 를 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return RESULT_NOT_FOUND;
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 RESULT_NOT_FOUND 를 반환
		if (qvo == null) {
			return RESULT_NOT_FOUND;
		}

		// 'qvo'가 공개 상태면 RESULT_SUCCESS 를 반환
		if (qvo.isPublic()) {
			return RESULT_SUCCESS;
		}

		HttpSession session = request.getSession();
		// 어드민이면 RESULT_SUCCESS 를 반환
		if (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin")) {
			return RESULT_SUCCESS;
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 RESULT_SUCCESS 를 반환
		if (session.getAttribute("qnaPass" + qseq) != null) {
			return RESULT_SUCCESS;
		}

		// 'pwd' 파라미터가 없으면 RESULT_REQUEST_PWD 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return RESULT_REQUEST_PWD;
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 RESULT_SUCCESS 를 반환
		if (qvo.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			return RESULT_SUCCESS;
		}

		// 아니면 RESULT_PWD_WRONG 를 반환
		return RESULT_PWD_WRONG;
	}

}
