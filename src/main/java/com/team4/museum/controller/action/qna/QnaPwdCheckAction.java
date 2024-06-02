package com.team4.museum.controller.action.qna;

import java.io.IOException;

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
		// 파라미터에 'qseq'가 없으면 FAILURE 를 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.FAILURE, "잘못된 요청입니다.");
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 FAILURE 를 반환
		if (qnaVO == null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.FAILURE, "존재하지 않는 문의입니다.");
		}

		HttpSession session = request.getSession();
		String url, mode = request.getParameter("mode");
		switch (mode) {
		case "view":
			url = "museum.do?command=qnaView&qseq=" + qseqStr;

			// 'qnaVO'가 공개 상태면 SUCCESS 를 반환
			if (qnaVO.isPublic()) {
				return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
			}

			// 관리자 일 경우 SUCCESS 를 반환
			if (session.getAttribute("isAdmin") != null) {
				return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
			}
			break;
		case "edit":
			url = "museum.do?command=qnaWriteForm&qseq=" + qseqStr;
			break;
		default:
			return new QnaPwdCheckResult(QnaPwdCheckResult.FAILURE, "잘못된 모드입니다.");
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 SUCCESS 를 반환
		if (session.getAttribute("qnaPass" + qseq) != null) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		// 'pwd' 파라미터가 없으면 PWD_REQUEST 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return new QnaPwdCheckResult(QnaPwdCheckResult.PWD_REQUEST, "비밀번호를 입력해주세요.");
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 SUCCESS 를 반환
		if (qnaVO.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			return new QnaPwdCheckResult(QnaPwdCheckResult.SUCCESS, url);
		}

		// 아니면 FAILURE 를 반환
		return new QnaPwdCheckResult(QnaPwdCheckResult.FAILURE, "잘못된 비밀번호입니다.");
	}

	public class QnaPwdCheckResult {
		public static final String SUCCESS = "success";
		public static final String FAILURE = "failure";
		public static final String PWD_REQUEST = "pwd_request";

		public String code;
		public String data;

		public QnaPwdCheckResult(String code, String data) {
			this.code = code;
			this.data = data;
		}

		public String toJson() {
			return "{\"code\":\"" + code + "\",\"data\":\"" + data + "\"}";
		}
	}
}
