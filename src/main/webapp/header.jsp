<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Museum</title>
<link rel="stylesheet" href="css/reset01.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<c:forEach items="${paramValues.stylesheet}" var="css">
	<link rel="stylesheet" href="${css}">
</c:forEach>
<script src="script/header.js"></script>
<c:forEach items="${paramValues.script}" var="js">
	<script src="${js}"></script>
</c:forEach>
<script>
	
</script>
</head>
<body>
	<header>
		<div class="header_util">
			<ul class="util-link">
				<li><a href="https://www.museum.go.kr/site/child/home" target="_blank">어린이박물관</a></li>
				<li><a href="https://www.museum.go.kr/curator" target="_blank">학예사자격증</a></li>
				<li><a href="http://webzine.museum.go.kr" target="_blank">박물관신문</a></li>
				<li><a class="lang-select" href="javascript:;">Language <i></i></a></li>
				<li><a href="https://blog.naver.com/100museum" target="_blank" title="국립중앙박물관 네이버 블로그 새 창으로 열림" class="naver"> <img src="images/header/ico_header_item1.png" alt="블로그">
				</a></li>
				<li><a href="https://twitter.com/The_NMK" target="_blank" title="국립중앙박물관 X 새 창으로 열림" class="twitter"> <img src="images/header/ico_header_item2.png" alt="X">
				</a></li>
				<li><a href="https://www.facebook.com/NationalMuseumofKorea/" target="_blank" title="국립중앙박물관 페이스북 새 창으로 열림" class="facebook"> <img src="images/header/ico_header_item3.png" alt="페이스북">
				</a></li>
				<li><a href="https://www.instagram.com/nationalmuseumofkorea/" target="_blank" title="국립중앙박물관 인스타그램 새 창으로 열림" class="instagram"> <img src="images/header/ico_header_item4.png" alt="인스타그램">
				</a></li>
				<li><a href="https://www.youtube.com/user/koreanmuseum" target="_blank" title="국립중앙박물관 유튜브 새 창으로 열림" class="youtube"> <img src="images/header/ico_header_item5.png" alt="유튜브">
				</a></li>
				<li><a href="https://audioclip.naver.com/channels/2320" target="_blank" title="국립중앙박물관 네이버 오디오클립 새 창으로 열림" class="audioclip"> <img src="images/header/ico_header_item6.png" alt="오디오클립">
				</a></li>
			</ul>
			<div class="login_join_box">
				<c:choose>
					<c:when test="${empty loginUser}">
						<a href="museum.do?command=loginForm">로그인</a>
						<a href="museum.do?command=joinForm">회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="#">${loginUser.name}(${loginUser.id})</a>
						<c:if test="${isAdmin}">
							<a href="museum.do?command=admin">관리자 페이지</a>
						</c:if>
						<a href="museum.do?command=logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<nav>
			<h1>
				<img class="header_logo" src="images/header/header_logo.png" alt="로고" id="header_logo" style="width: 208px; height: 55px;" onclick="window.location.href = 'museum.do?command=index';">
			</h1>
			<div class="header_gnb">
				<a href="museum.do?command=artworkList">예술품</a> <a href="museum.do?command=noticeList">소식지</a> <a href="museum.do?command=galleryList">이용자 갤러리</a> <a href="museum.do?command=qnaList">고객센터</a> <a href="museum.do?command=mypage">마이페이지</a>
			</div>
			<div class="header_search">
				<div>
					<input type="text" placeholder="검색어를 입력하세요">
				</div>
				<div>
					<a class="btn_icon" href="#" title="검색">
					<img src="images/header/btn_search_open.png" alt="검색">
					</a>
				</div>
			</div>

			<div class="artwork sub-menu">
				<!-- request.setAttribute("kindList", ArtworkKind.values()); -->
				<c:forEach items="${artworkCategory}" var="category" varStatus="status">
					<a href="museum.do?command=artwork&category=${status.index}">${category.name()}</a>
				</c:forEach>
			</div>
			<div class="notice sub-menu">
				<c:forEach items="${noticeCategory}" var="category" varStatus="status">
					<a href="museum.do?command=notice&category=${status.index}">${category.name()}</a>
				</c:forEach>
			</div>
		</nav>
	</header>
	<div class="content-wrap">