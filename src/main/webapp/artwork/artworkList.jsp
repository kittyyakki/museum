<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

<!-- museum.do?command=artwork 의 목적지 -->

<section class="artwork-list-header">
	<form action="museum.do?command=artworkList" method="post" name="searchForm" class="artwork-search-form">
		<h2>예술품 검색</h2>
		<div>
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요" name="searchWord" class="artwork-search-form_input" value="${searchWord}">
			<input type="submit" value="검색" onclick="return go_search_artwork()" class="artwork-search-form_btn">
		</div>
	</form>
	<div class="category-btn-container">
		<c:forEach items="${artworkCategory}" var="category" varStatus="status">
			<c:choose>
				<c:when test="${categoryName.equals(category.name())}">
					<a href="museum.do?command=artworkList&category=${category.name()}" class="artwork-list_btn artwork-list_selected-btn">${category.name()}</a>
				</c:when>
				<c:otherwise>
					<a href="museum.do?command=artworkList&category=${category.name()}" class="artwork-list_btn">${category.name()}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</section>

<main class="artwork-list-main">
	<div class="artwork-list-head">
		<h4>검색결과가 총 ${paging.totalCount}건 입니다</h4>
		<c:if test="${loginUser.adminyn.equals('Y')}">
			<a href="museum.do?command=artworkWrite" class="artwork-list_btn">예술품 등록</a>
		</c:if>
	</div>
	<div class="artwork-list">
		<c:forEach items="${artworkList}" var="artwork">
			<div onclick="location.href='museum.do?command=artworkView&aseq=${artwork.aseq}'">
				<img src="images/artwork/${artwork.savefilename}" alt="artwork_image" class="artwork-list_img" />
				<div class="artwork-list_info">
					<span>${artwork.artist}</span>
					<p>${artwork.name}</p>
					<span>${artwork.year}</span>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:set var="pageListPrefix" value="museum.do?command=artworkList&page=" />
	<div class="paging">
		<c:choose>
			<c:when test="${paging.prev}">
				<a class="paging_button" href="${pageListPrefix}1">«</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">«</span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${paging.prev}">
				<a class="paging_button" href="${pageListPrefix}${paging.beginPage-1}">‹</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">‹</span>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
			<c:if test="${index==paging.page}">
				<span class="current-page">${index}</span>
			</c:if>
			<c:if test="${index!=paging.page}">
				<a href="${pageListPrefix}${index}">${index}</a>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${paging.next}">
				<a class="paging_button" href="${pageListPrefix}${paging.endPage+1}">›</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">›</span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${paging.next}">
				<a class="paging_button" href="${pageListPrefix}${paging.totalPage}">»</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">»</span>
			</c:otherwise>
		</c:choose>
</main>

<%@ include file="/footer.jsp"%>