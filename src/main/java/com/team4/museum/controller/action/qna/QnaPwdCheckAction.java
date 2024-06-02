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
		response.setContentType("application/json");
		response.getWriter().write(getResult(request, response).toJson());
	}

	private QnaPwdCheckResult getResult(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 NOT_FOUND 를 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.NOT_FOUND);
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 NOT_FOUND 를 반환
		if (qnaVO == null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.NOT_FOUND);
		}

		String url, mode = request.getParameter("mode");
		switch (mode) {
		case "view":
			url = "museum.do?command=qnaView&qseq=" + qseqStr;
			break;
		default:
			return new QnaPwdCheckResult(QnaPwdCheckResult.MODE_INVAILD);
		}

		// 'qnaVO'가 공개 상태면 SUCCESS 를 반환
		if (qnaVO.isPublic()) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		HttpSession session = request.getSession();
		// 어드민이면 SUCCESS 를 반환
		if (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 SUCCESS 를 반환
		if (session.getAttribute("qnaPass" + qseq) != null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		// 'pwd' 파라미터가 없으면 PWD_REQUEST 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.PWD_REQUEST);
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 SUCCESS 를 반환
		if (qnaVO.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		// 아니면 PWD_INVAILD 를 반환
		return new QnaPwdCheckResult(QnaPwdCheckResult.PWD_INVAILD);
	}

	public class QnaPwdCheckResult {
		public static final String SUCCESS = "success";
		public static final String NOT_FOUND = "not_found";
		public static final String MODE_INVAILD = "mode_invalid";
		public static final String PWD_REQUEST = "pwd_request";
		public static final String PWD_INVAILD = "pwd_invalid";

		public String code;
		public String url;

		public QnaPwdCheckResult(String code) {
			this(code, "");
		}

		public QnaPwdCheckResult(String code, String qseq) {
			this.code = code;
			this.url = qseq;
		}

		public String toJson() {
			return "{\"code\":\"" + code + "\",\"url\":\"" + url + "\"}";
		}
	}
}
