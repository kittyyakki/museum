package com.team4.museum.controller.action.qna;

import static com.team4.museum.controller.action.qna.QnaPwdCheckAjaxAction.isAlreadyPwdChecked;
import static com.team4.museum.controller.action.qna.QnaPwdCheckAjaxAction.savePwdCheckLog;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.QnaVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaWriteAjaxAction extends AjaxAction {

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		QnaVO qnaVO = getQnaVO(request, response);

		// 삭제 버튼을 누른 경우
		if (request.getParameter("delete") != null) {
			if (qnaVO != null) {
				QnaDao.getInstance().deleteQna(qnaVO.getQseq());
			}
			return created("문의글이 삭제되었습니다", "museum.do?command=qnaList");
		}

		// 등록 버튼을 누른 경우
		boolean isNew = qnaVO == null;
		if (isNew) {
			// 새로 작성하는 경우 qnaVO를 새로 생성
			qnaVO = new QnaVO();
		}

		qnaVO.setTitle(request.getParameter("title"));
		qnaVO.setContent(request.getParameter("content"));
		qnaVO.setEmail(request.getParameter("email"));
		qnaVO.setPhone(request.getParameter("phone"));
		qnaVO.setPublic(request.getParameter("publicyn") != null && request.getParameter("publicyn").equals("on"));
		qnaVO.setPwd(request.getParameter("pwd"));

		QnaDao dao = QnaDao.getInstance();
		int qseq = isNew ? dao.insertQna(qnaVO) : dao.updateQna(qnaVO);

		// 세션에 비밀번호 확인 기록 저장
		savePwdCheckLog(request, qseq);

		// 문의글 페이지로 이동
		return created(isNew ? "문의글이 등록되었습니다" : "문의글이 수정되었습니다", "museum.do?command=qnaView&qseq=" + qseq);
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
