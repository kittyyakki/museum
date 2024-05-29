<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<!-- museum.do?command=artwork 의 목적지 -->

<section>
	<form action="" method="post">
		<h2>예술품 검색</h2>
		<div>
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요">
			<input type="button" value="검색" onclick="go_search_artwork()">
		</div>
	</form>
	<div>
		<c:forEach items="${artworkCategory}" var="category" varStatus="status">
			<a href="museum.do?command=artwork&category=${status.index}" class="artwork-category-btn">${category.name()}</a>
		</c:forEach>
	</div>
</section>
<hr>
<main>
	
</main>

<%@ include file="footer.jsp"%>