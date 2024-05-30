<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

<!-- museum.do?command=artwork 의 목적지 -->

<section>
	<form action="museum.do?command=artwork" method="post" name="searchForm" class="search-form">
		<h2>예술품 검색</h2>
		<div>
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요" name="searchWord" class="search-input" value="${searchWord}">
			<input type="submit" value="검색" onclick="return go_search_artwork()" class="search-btn">
		</div>
	</form>
	<div class="category-btn-container">
		<c:forEach items="${artworkCategory}" var="category" varStatus="status">
			<c:choose>
				<c:when test="${categoryNum == status.index && titleState == 'on'}">
					<a href="museum.do?command=artwork&category=${status.index}" class="selected-category-btn">${category.name()}</a>
				</c:when>
				<c:otherwise>
					<a href="museum.do?command=artwork&category=${status.index}" class="artwork-category-btn">${category.name()}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</section>
<hr>

<main>
	<article>
		<div class="artwork-list">
			<c:forEach items="${artworkList}" var="artwork">
				<div onclick="location.href='museum.do?command=artworkDetail&aseq=${artwork.aseq}'">
					<img src="images/artwork/${artwork.savefilename}" alt="artwork_image" class="artwork_img"/>
					<div class="artwork_artist">${artwork.artist}</div>
					<div class="artwork_name">${artwork.name}</div>
					<div class="artwork_year">${artwork.year}</div>
				</div>
			</c:forEach>
		</div>
	</article>
</main>

<%@ include file="/footer.jsp"%>