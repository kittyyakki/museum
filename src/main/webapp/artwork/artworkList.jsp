<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

<!-- museum.do?command=artwork 의 목적지 -->

<section class="artwork-list-header">
	<form action="museum.do?command=artwork" method="post" name="searchForm" class="artwork-search-form">
		<h2>예술품 검색</h2>
		<div>
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요" name="searchWord" class="search-input" value="${searchWord}">
			<input type="submit" value="검색" onclick="return go_search_artwork()" class="search-btn">
		</div>
	</form>
	<div class="category-btn-container">
		<c:forEach items="${artworkCategory}" var="category" varStatus="status">
			<c:choose>
				<c:when test="${categoryName.equals(category.name()) && titleState == 'on'}">
					<a href="museum.do?command=artwork&category=${category.name()}" class="selected-category-btn">${category.name()}</a>
				</c:when>
				<c:otherwise>
					<a href="museum.do?command=artwork&category=${category.name()}" class="artwork-category-btn">${category.name()}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</section>

<main class="artwork-list-main">
	<h4>검색결과가 총 ${artworkList.size()}건 입니다</h4>
	<div class="artwork-list">
		<c:forEach items="${artworkList}" var="artwork">
			<div onclick="location.href='museum.do?command=artworkView&aseq=${artwork.aseq}'">
				<img src="images/artwork/${artwork.savefilename}" alt="artwork_image" class="artwork_img" />
				<div class="artwork_artist">${artwork.artist}</div>
				<div class="artwork_name">${artwork.name}</div>
				<div class="artwork_year">${artwork.year}</div>
			</div>
		</c:forEach>
	</div>
</main>

<%@ include file="/footer.jsp"%>