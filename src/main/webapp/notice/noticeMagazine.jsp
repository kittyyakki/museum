<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>

<div class="noticeMagazine_container">
	<h1>미술관 아카이브</h1>
	<nav class="noticeMagazine_nav">
		<ul>
			<li><a href="#">Home</a></li>
			<li><a href="#">Archive</a></li>
			<li><a href="#">About</a></li>
		</ul>
	</nav>
</div>
<div>
	<div class="noticeMagazine_category">
		<h2>카테고리</h2>
		<ul>
			<li><a href="#">전시</a></li>
			<li><a href="#">이벤트</a></li>
			<li><a href="#">교육</a></li>
		</ul>
	</div>
	<div class="noticeMagazine_archive-list">
		<h2>아카이브 목록</h2>
		<div class="noticeMagazine_item">
			<h3>아카이브 제목</h3>
			<p>아카이브 내용 설명</p>
		</div>
		<!-- 다른 아카이브 아이템들 -->
	</div>
</div>








<%@ include file="/footer.jsp"%>
