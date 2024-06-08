package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.member.LoginAjaxAction.isAdmin;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaReplyAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		// 사용자가 관리자가 아닌 경우
		if (!isAdmin(request)) {
			return forbidden("접근 권한이 없습니다");
		}

		// 'qseq' 파라미터가 없는 경우
		String qseqParam = request.getParameter("qseq");
		if (qseqParam == null || qseqParam.equals("") || !qseqParam.matches("^[0-9]*$")) {
			return requireParameter("qseq");
		}

		int qseq = Integer.parseInt(qseqParam);
		QnaDao qdao = QnaDao.getInstance();

		// 입력된 'qseq'에 해당하는 문의글이 없는 경우
		QnaVO qnaVO = qdao.getQna(qseq);
		if (qnaVO == null) {
			return noContent("해당 문의가 존재하지 않습니다");
		}
		request.setAttribute("qnaVO", qnaVO);

		try {
			// 답변을 업데이트
			qdao.updateQnaReply(qseq, request.getParameter("reply"));
		} catch (Exception e) {
			// 업데이트에 실패한 경우
			e.printStackTrace();
			return internalServerError("답변 업데이트에 실패했습니다");
		}

		return ok("답변이 업데이트되었습니다", "museum.do?command=qnaView&qseq=" + qseq);
	}

}
