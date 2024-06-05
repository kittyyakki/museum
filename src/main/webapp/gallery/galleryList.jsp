<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="script" value="script/gallery.js" />
</jsp:include>
<section class="gallery-list-header">
	<form action="museum.do?command=galleryList" method="post" name="searchForm" class="search-form">
		<h2>이용자 갤러리</h2>
		<div>
			<input type="text" placeholder="컬렉션명을 입력해주세요" name="searchWord" class="search-input" value="${searchWord}">
			<input type="submit" value="검색" onclick="return go_search_gallery()" class="search-btn">
			<a href="museum.do?command=galleryWrite">갤러리 만들기</a>
		</div>
	</form>
</section>
<%@ include file="/footer.jsp"%>