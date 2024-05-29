<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!-- museum.do?command=artwork 의 목적지 -->

<section>
	<form action="museum.do?command=artwork" method="post">
		<h2>예술품 검색</h2>
		<div>
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요" name="search-word">
			<input type="submit" value="검색" onclick="return go_search_artwork()">
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
	<article>
		<h2>${artworkCategory[categoryNum]}</h2>
		<div class="artwork-list">
			<c:forEach items="${artworkList}" var="artwork">
				<div onclick="location.href='museum.do?command=artworkDetail&aseq=${artwork.aseq}'" class="artwork">
					<img src="images/artwork/${artwork.savefilename}" alt="artwork_image" />
					<div>${artwork.artist}</div>
					<div>${artwork.name}</div>
					<div>${artwork.year}</div>
				</div>
			</c:forEach>
		</div>
	</article>
</main>

<%@ include file="footer.jsp"%>