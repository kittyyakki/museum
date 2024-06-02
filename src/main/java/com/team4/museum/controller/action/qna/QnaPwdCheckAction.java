package com.team4.museum.controller.action.qna;

import java.io.IOException;
import java.util.List;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaPwdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("qnaPwdCheckResult", getResult(request, response));
		String page = request.getParameter("page");
		response.sendRedirect("museum.do?command=qnaList" + (page != null ? "&page=" + page : ""));
	}

	private QnaPwdCheckResult getResult(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 RESULT_NOT_FOUND 를 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_NOT_FOUND, "");
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 RESULT_NOT_FOUND 를 반환
		if (qnaVO == null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_NOT_FOUND, qseqStr);
		}

		// 'qnaVO'가 공개 상태면 RESULT_SUCCESS 를 반환
		if (qnaVO.isPublic()) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_SUCCESS, qseqStr);
		}

		HttpSession session = request.getSession();
		// 어드민이면 RESULT_SUCCESS 를 반환
		if (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_SUCCESS, qseqStr);
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 RESULT_SUCCESS 를 반환
		if (session.getAttribute("qnaPass" + qseq) != null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_SUCCESS, qseqStr);
		}

		// 'pwd' 파라미터가 없으면 RESULT_REQUEST_PWD 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_REQUEST_PWD, qseqStr);
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 RESULT_SUCCESS 를 반환
		if (qnaVO.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_SUCCESS, qseqStr);
		}

		// 아니면 RESULT_PWD_WRONG 를 반환
		return new QnaPwdCheckResult(QnaPwdCheckResult.RESULT_PWD_WRONG, qseqStr);
	}

	public class QnaPwdCheckResult {
		public static final String RESULT_SUCCESS = "success";
		public static final String RESULT_NOT_FOUND = "notFound";
		public static final String RESULT_REQUEST_PWD = "requestPwd";
		public static final String RESULT_PWD_WRONG = "pwdWrong";

		private String result;
		private String qseq;
		private boolean isUsed;

		public QnaPwdCheckResult(String result, String qseq) {
			this.result = result;
			this.qseq = qseq;
		}

		public String getResult() {
			return result;
		}

		public String getQseq() {
			return qseq;
		}

		public boolean use() {
			if (this.isUsed) {
				return false;
			}
			this.isUsed = true;
			return true;
		}
	}
}
