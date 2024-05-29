<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Museum</title>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/artwork.css">
<script src="script/header.js"></script>
</head>
<body>
	<div>
		<header>
			<nav>
				<a href="#">
					<img alt="logo" src="images/logo.png" class="logo">
				</a>
				<div class="menu">
					<a href="museum.do?command=artwork&category=0">예술품</a>
					<a href="museum.do?command=notice">소식지</a>
					<a href="museum.do?command=gallery">이용자 갤러리</a>
					<a href="">고객센터</a>
				</div>
				<div class="search">
					<div>
						<input type="text" placeholder="검색어를 입력하세요">
					</div>
					<div>
						<img alt="검색아이콘" src="">
					</div>
				</div>
				<div>
					<input type="button" value="로그인">
					<input type="button" value="회원가입">
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