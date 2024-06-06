<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/gallery.css" />
	<jsp:param name="script" value="script/gallery.js" />
</jsp:include>

<section class="gallery-view">
	<ul class="gallery-header">
		<li>${galleryVO.title}</li>
		<li>${galleryVO.content}</li>
		<li>
			<a href="location.href='museum.do?command=memberGallery?mseq=${galleryVO.authorId}'">
				${galleryVO.authorId}님의 갤러리
			</a>
		</li>
		<li>조회수 ${galleryVO.readcount}</li>
		<li>좋아요 ${galleryVO.likecount}</li>
	</ul>
	<ul>
		<li>
			<img alt="gallery-img" src="images/gallery/${galleryVO.savefilename}">
		</li>
	</ul>
</section>

<%@ include file="/footer.jsp"%>