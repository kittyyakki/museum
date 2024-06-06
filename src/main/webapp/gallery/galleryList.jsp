<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/gallery.css" />
	<jsp:param name="script" value="script/gallery.js" />
</jsp:include>
<main class="gallery-list">
	<section class="gallery-list-header">
		<form action="museum.do?command=" method="post" name="searchForm" class="">
			<h2>갤러리 검색</h2>
			<div>
				<input type="text" placeholder="컬렉션명을 검색하세요" name="searchWord" class="" value="${searchWord}"> <input
					type="submit" value="검색" onclick="return go_search_artwork()" class="artwork-search-form_btn">
			</div>
		</form>
	</section>
	<section class="gallery-list-main">
		<div class="gallery-list-main-title">
			<h4>검색결과가 총 ${pagination.itemCount}건 입니다</h4>
			<a href="museum.do?command=artworkWriteForm" class="">갤러리 등록</a>
		</div>
		<div class="gallery-list-main-content">
			<c:forEach items="${galleryList}" var="mgvo">
				<div onclick="location.href='museum.do?command=galleryView&mseq=${mgvo.mseq}'">
					<img src="images/gallery/${mgvo.savefilename}" alt="member_gallery_image"/>
					<div class="">
						<span>${mgvo.title}</span>
						<p>${mgvo.authorName}님의갤러리</p>
						<span>조회수 : ${mgvo.readcount}</span> <span>좋아요 : ${mgvo.likecount}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<%@ include file="/util/pagination.jsp"%>
	</section>
</main>
<%@ include file="/footer.jsp"%>