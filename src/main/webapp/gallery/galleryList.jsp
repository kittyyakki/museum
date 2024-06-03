<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

<section>
	<form action="museum.do?command=galleryList" method="post" name="searchForm" class="search-form">
		
		<h2>이용자 갤러리</h2>
		<div>
			<input type="text" placeholder="컬렉션명을 입력해주세요" name="searchWord" class="search-input" value="${searchWord}">
			<input type="submit" value="검색" onclick="return go_search_gallery()" class="search-btn">
			<a href="museum.do?command=galleryWrite">갤러리 만들기</a>
		</div>
	</form>	
</section>
<hr>

<main>
	<article>
		<div class="gallery-list">
			<c:forEach items="${galleryList}" var="gallery">
				<div onclick="location.href='museum.do?command=galleryDetail&aseq=${gallery.mseq}'">
				</div>
			</c:forEach>
		</div>
	</article>
</main>














<%@ include file="/footer.jsp"%>