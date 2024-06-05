package com.team4.museum.controller.action.member;

import static com.team4.museum.controller.action.member.LoginAction.getLoginUrl;
import static com.team4.museum.controller.action.member.LoginAction.getLoginUserFrom;
import static com.team4.museum.util.AjaxResult.BAD_REQUEST;
import static com.team4.museum.util.AjaxResult.OK;
import static com.team4.museum.util.AjaxResult.UNAUTHORIZED;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.FavoriteDao;
import com.team4.museum.util.AjaxResult;
import com.team4.museum.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageFavoriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getResult(request, response).applyToResponse(response);
	}

	private AjaxResult getResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 파라미터에 'aseq'가 없거나 정수가 아니면 BAD_REQUEST 를 반환
		String aseq = request.getParameter("aseq");
		if (aseq == null || aseq.equals("") || !aseq.matches("^[0-9]*$")) {
			// 사용자 입력이 아니므로 어떤 요소가 잘못되었는지 알려줄 필요가 없음
			return new AjaxResult(BAD_REQUEST, "잘못된 요청입니다");
		}

		// 로그인이 되어있지 않으면 UNAUTHORIZED 를 반환
		MemberVO mvo = getLoginUserFrom(request);
		if (mvo == null) {
			String returnUrl = "museum.do?command=artworkView&aseq=" + aseq;
			return new AjaxResult(UNAUTHORIZED, "로그인이 필요합니다", getLoginUrl(returnUrl));
		}

		FavoriteDao fdao = FavoriteDao.getInstance();
		Boolean result = fdao.toggleFavorite(mvo.getId(), Integer.parseInt(aseq));

		// 즐겨찾기 추가/삭제 결과에 따라 메시지와 함께 OK 를 반환
		return new AjaxResult(OK, result ? "즐겨찾기에 추가되었습니다" : "즐겨찾기에서 삭제되었습니다");
	}

}
