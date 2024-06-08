<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/artwork.css" />
</jsp:include>
<section class="artwork-list-header"></section>
<main class="artwork-list-main">
	<div class="artwork-list-head">
		<h4>총 ${pagination.itemCount}개의 관심 예술품이 존재합니다.</h4>
	</div>
	<div class="artwork-list">
		<c:forEach items="${artworkList}" var="artwork">
			<div onclick="location.href='museum.do?command=artworkView&aseq=${artwork.aseq}'">
				<img src="${artwork.fullSavefilename}" alt="artwork_image" class="artwork-list_img" />
				<div class="artwork-list_info">
					<span>${artwork.artist}</span>
					<p>${artwork.name}</p>
					<span>${artwork.year}</span>
				</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="/util/pagination.jsp"%>
</main>
<%@ include file="/footer.jsp"%>