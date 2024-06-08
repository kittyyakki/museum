package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.member.LoginAjaxAction.isAdmin;
import static com.team4.museum.util.AjaxResult.BAD_REQUEST;
import static com.team4.museum.util.AjaxResult.NO_CONTENT;
import static com.team4.museum.util.AjaxResult.OK;
import static com.team4.museum.util.AjaxResult.UNAUTHORIZED;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaPwdCheckAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터에 'qseq'가 없으면 BAD_REQUEST 를 반환
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return new AjaxResult(BAD_REQUEST, "'qseq'를 입력해주세요");
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 'qseq' 파라미터에 해당하는 'QnaVO'가 없으면 NO_CONTENT 를 반환
		if (qnaVO == null) {
			return new AjaxResult(NO_CONTENT, "존재하지 않는 문의입니다");
		}

		HttpSession session = request.getSession();
		String url, mode = request.getParameter("mode");
		switch (mode) {
		case "view":
			url = "museum.do?command=qnaView&qseq=" + qseqStr;

			// 'qnaVO'가 공개 상태면 OK 를 반환
			if (qnaVO.isPublic()) {
				return new AjaxResult(OK, "공개된 문의입니다", url);
			}

			// 관리자 일 경우 OK 를 반환
			if (isAdmin(request)) {
				return new AjaxResult(OK, "관리자로 확인되었습니다", url);
			}
			break;
		case "edit":
			url = "museum.do?command=qnaWriteForm&qseq=" + qseqStr;
			break;
		default:
			// 'mode'가 'view'나 'edit'가 아니면 BAD_REQUEST 를 반환
			return new AjaxResult(BAD_REQUEST, "'mode' 파라미터가 'view'나 'edit'가 아닙니다");
		}

		// 세션에 비밀번호 확인 기록이 있는 경우 OK 를 반환
		if (session.getAttribute("qnaPass" + qseq) != null) {
			return new AjaxResult(OK, "비밀번호 확인 기록이 존재합니다", url);
		}

		// 'pwd' 파라미터가 없으면 UNAUTHORIZED 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return new AjaxResult(UNAUTHORIZED, "'pwd'를 입력해주세요");
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 OK 를 반환
		if (qnaVO.getPwd().equals(pwd)) {
			session.setAttribute("qnaPass" + qseq, qseq);
			return new AjaxResult(OK, "비밀번호가 확인되었습니다", url);
		}

		// 비밀번호가 틀리면 BAD_REQUEST 를 반환
		return new AjaxResult(BAD_REQUEST, "잘못된 비밀번호입니다");
	}

}
