package com.team4.museum.controller.action.qna;

import java.util.HashMap;
import java.util.Map;

import com.team4.museum.controller.action.AjaxAction;
import com.team4.museum.dao.QnaDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.QnaVO;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaPwdCheckAjaxAction extends AjaxAction {

	private final Map<String, SubAjaxAction> subActions = new HashMap<>();

	public QnaPwdCheckAjaxAction() {
		super();

		// 서브 액션들을 등록
		subActions.put("view", new SubAjaxAction(QnaAccessValidator.RESTRICT,
				(v, r) -> ok("접근이 허용되었습니다", "museum.do?command=qnaView&qseq=" + v.getQseq())));

		subActions.put("edit", new SubAjaxAction(QnaAccessValidator.PERSONAL,
				(v, r) -> ok("접근이 허용되었습니다", "museum.do?command=qnaWriteForm&qseq=" + v.getQseq())));

		subActions.put("delete", new SubAjaxAction(QnaAccessValidator.PRIVILEGE,
				(v, r) -> {
					try {
						QnaDao.getInstance().deleteQna(v.getQseq());
					} catch (Exception e) {
						e.printStackTrace();
						return internalServerError("문의글 삭제에 실패했습니다");
					}
					return created("문의글이 삭제되었습니다", "museum.do?command=qnaList");
				}));
	}

	protected AjaxResult handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		String mode = request.getParameter("mode");
		if (mode == null || subActions.get(mode) == null) {
			return requireParameter("mode");
		}
		SubAjaxAction subAction = subActions.get(mode);

		// 'qseq' 파라미터가 없는 경우
		String qseqStr = request.getParameter("qseq");
		if (qseqStr == null || qseqStr.equals("") || !qseqStr.matches("^[0-9]*$")) {
			return requireParameter("qseq");
		}

		int qseq = Integer.parseInt(qseqStr);
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qnaVO = qdao.getQna(qseq);

		// 입력된 'qseq'에 해당하는 문의글이 없는 경우
		if (qnaVO == null) {
			return noContent("해당 문의가 존재하지 않습니다");
		}

		// subAction의 접근 조건을 만족하지 않는 경우
		if (!subAction.validate(qnaVO, request)) {
			// 'pwd' 파라미터가 없는 경우
			String pwd = request.getParameter("pwd");
			if (pwd == null || pwd.trim().equals("")) {
				return unauthorized("비밀번호를 입력해주세요");
			}

			// 비밀번호가 일치하지 않는 경우
			if (!qnaVO.getPwd().equals(pwd)) {
				return badRequest("비밀번호가 일치하지 않습니다");
			}

			// 세션에 비밀번호 확인 기록 저장
			savePwdCheckLog(request, qseq);
		}

		// ResultMapper로 결과를 생성해 반환
		return subAction.execute(qnaVO, request);
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

	private class SubAjaxAction {
		public QnaAccessValidator validator;
		public RequestMapper mapper;

		public SubAjaxAction(QnaAccessValidator validator, RequestMapper mapper) {
			this.validator = validator;
			this.mapper = mapper;
		}

		public boolean validate(@Nonnull QnaVO qnaVO, HttpServletRequest request) {
			return validator.validate(qnaVO, request);
		}

		public AjaxResult execute(@Nonnull QnaVO qnaVO, HttpServletRequest request) {
			return mapper.execute(qnaVO, request);
		}

		@FunctionalInterface
		private interface RequestMapper {
			public AjaxResult execute(@Nonnull QnaVO qnaVO, HttpServletRequest request);
		}
	}
}
