<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Museum</title>
<link rel="stylesheet" href="css/reset01.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/joinForm.css">
<link rel="stylesheet" href="css/artwork.css">
<link rel="stylesheet" href="css/qna_list.css">
<script src="script/member.js"></script>
<script src="script/header.js"></script>
<script src="script/artwork.js"></script>
<script src="join.js"></script>
</head>
<body>
	<div class="body-wrap">
		<header>
			<nav>
				<h1>
					<a href="museum.do?command=index" title="로고"></a>
				</h1>
				<div class="header_gnb">
					<a href="museum.do?command=artwork">예술품</a>
					<a href="museum.do?command=notice">소식지</a>
					<a href="museum.do?command=gallery">이용자 갤러리</a>
					<a href="museum.do?command=qnaList">고객센터</a>
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
							<a href="museum.do?command=">회원가입</a>
						</c:when>
						<c:otherwise>
							<a href="#">${loginUser.name}(${loginUser.id})</a>
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