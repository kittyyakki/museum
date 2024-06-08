package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.member.LoginAjaxAction.isAdmin;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaPwdCheckAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		// 'qseq' 파라미터가 없는 경우
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return requireParameter("qseq");
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);
		request.setAttribute("qseq", qseq);

		// 입력된 'qseq'에 해당하는 문의글이 없는 경우
		if (qnaVO == null) {
			return noContent("해당 문의가 존재하지 않습니다");
		}

		String url, mode = request.getParameter("mode");
		switch (mode) {
		case "view":
			url = "museum.do?command=qnaView&qseq=" + qseqStr;

			// 'qnaVO'가 공개 상태인 경우
			if (qnaVO.isPublic()) {
				return ok("공개된 문의입니다", url);
			}

			// 사용자가 관리자인 경우
			if (isAdmin(request)) {
				return ok("관리자로 확인되었습니다", url);
			}
			break;
		case "edit":
			url = "museum.do?command=qnaWriteForm&qseq=" + qseqStr;
			break;
		default:
			// 'mode'가 'view'나 'edit'가 아니면 SC_BAD_REQUEST 를 반환
			return badRequest("'mode' 파라미터가 'view'나 'edit'가 아닙니다");
		}

		// 세션에 비밀번호 확인 기록이 있는 경우
		if (isAlreadyPwdChecked(request, qseq)) {
			return ok("비밀번호 확인 기록이 존재합니다", url);
		}

		// 'pwd' 파라미터가 없으면 SC_UNAUTHORIZED 를 반환
		String pwd = request.getParameter("pwd");
		if (pwd == null || pwd.trim().equals("")) {
			return unauthorized("비밀번호를 입력해주세요");
		}

		// 'pwd'가 비밀번호와 같으면 세션에 비밀번호 확인 기록을 남기고 SC_OK 를 반환
		if (qnaVO.getPwd().equals(pwd)) {
			savePwdCheckLog(request, qseq);
			return ok("비밀번호가 확인되었습니다", url);
		}

		// 비밀번호가 틀리면 SC_BAD_REQUEST 를 반환
		return badRequest("비밀번호가 일치하지 않습니다");
	}

	/**
	 * 세션에 문의글 비밀번호 확인 기록이 있는지 확인합니다.
	 * 
	 * @param request
	 * @param qseq
	 * @return 비밀번호 확인 기록이 있으면 true, 없으면 false
	 */
	public static boolean isAlreadyPwdChecked(HttpServletRequest request, int qseq) {
		return request.getSession().getAttribute("qnaPass" + qseq) != null;
	}

	/**
	 * 세션에 문의글 비밀번호 확인 기록을 남깁니다.
	 * 
	 * @param request
	 * @param qseq
	 */
	public static void savePwdCheckLog(HttpServletRequest request, int qseq) {
		request.getSession().setAttribute("qnaPass" + qseq, true);
	}
}
