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
<c:forEach items="${paramValues.script}" var="js">
	<script src="${js}"></script>
</c:forEach>
</head>
<body>
	<header>
		<nav>
			<h1>
				<img class="header_logo" src="images/header_logo.png" alt="로고" id="header_logo">
			</h1>
			<div class="header_gnb">
				<a href="museum.do?command=artworkList">예술품</a> <a href="museum.do?command=noticeList">소식지</a> <a href="museum.do?command=galleryList">이용자 갤러리</a> <a href="museum.do?command=qnaList">고객센터</a> <a href="museum.do?command=myPage">마이페이지</a>
			</div>
			<div class="search">
				<div>
					<input type="text" placeholder="검색어를 입력하세요">
				</div>
				<div>
					<a class="btn_icon" href="#" title="검색"></a>
				</div>
			</div>
			<div class="login_join_box">
				<c:choose>
					<c:when test="${empty loginUser}">
						<a href="museum.do?command=loginForm">로그인</a>
						<a href="museum.do?command=joinForm">회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="#">${loginUser.name}(${loginUser.id})</a>
						<c:if test="${loginUser.adminyn.equals('Y')}">
							<a href="museum.do?command=admin">관리자 페이지</a>	
						</c:if>
						<a href="museum.do?command=logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
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