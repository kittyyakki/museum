<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/member/mypage/mypage.css" />
</jsp:include>
<main>
	<div>
		<h1 class="title">나의 페이지</h1>
	</div>
	<div class="mypageTopInfo">
		<p class="infoName">${loginUser.name}</p>
		<p class="infoEmail">${loginUser.email}</p>
	</div>
	<div class="infoBtnWrap">
		<a class="btn" href="museum.do?command=mypageEdit">
			<button>개인정보수정</button>
		</a>
		<a class="btn" href="museum.do?command=memberWithdraw">
			<button>회원탈퇴</button>
		</a>
	</div>
</main>