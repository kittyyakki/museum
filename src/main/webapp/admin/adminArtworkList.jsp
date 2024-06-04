<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<section class="admin-list">
	<div class="admin-list-btn">
		<input type="button" value="추가" onclick="location.href='museum.do?command=artworkWrite'">
		<input type="button" value="수정" onclick="updateArtwork()">
		<input type="button" value="삭제" onclick="">
	</div>
	<ul class="admin-list-header">
		<li>
			<input type="checkbox" onclick="checkAll()" class="select-all-box">
		</li>
		<li>전시상태</li>
		<li>번호</li>
		<li>작품명</li>
		<li>작가명</li>
		<li>분류</li>
		<li>제작연도</li>
		<li>재료</li>
		<li>규격</li>
		<li>등록일</li>
		<li>미리보기</li>
	</ul>
	<c:forEach items="${artworkList}" var="avo">
		<ul class="admin-list-main" onclick="go_check(event)">
			<li>
				<input type="checkbox" onclick="" class="check-box">
			</li>
			<li>${avo.displayyn}</li>
			<li>${avo.aseq}</li>
			<li class="artwork-name" onclick="location.href=artworkView&aseq=${avo.aseq}">${avo.name}</li>
			<li>${avo.artist}</li>
			<li>${avo.category}</li>
			<li>${avo.year}</li>
			<li>${avo.material}</li>
			<li>${avo.size}</li>
			<li>${avo.indate}</li>
			<li>
				<img alt="artwork-img" src="images/artwork/${avo.savefilename}">
			</li>
		</ul>
	</c:forEach>
</section>
<%@ include file="/util/pagination.jsp"%>
<%@ include file="/footer.jsp"%>