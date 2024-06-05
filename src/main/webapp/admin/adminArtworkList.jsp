<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/admin.css" />
	<jsp:param name="script" value="script/admin.js" />
</jsp:include>
<%@ include file="/admin/sub_menu.jsp"%>
<%
String displayState = request.getParameter("displayState") != null ? request.getParameter("displayState") : "";
%>
<section class="admin-list">
	<form method="post" name="adminForm">
		<div class="admin-list-btn">
			<input type="hidden" name="memberIds">
			<input type="button" value="추가" onclick="location.href='museum.do?command=artworkWrite'">
			<input type="button" value="수정" onclick="updateArtwork()">
			<input type="button" value="삭제" onclick="deleteArtwork()">
			<input type="text" placeholder="작품명 또는 작가명을 검색하세요" name="searchWord" value="${searchWord}">
			<input type="button" value="검색" onclick="searchAdmin('adminArtworkList')">
		</div>
		<ul class="admin-list-header admin-artwork-list">
			<li><input type="checkbox" onclick="checkAll()" class="select-all-box"></li>
			<li><select onchange="displayFilter('adminArtworkList', 'displayState')" name="selectFilter" class="admin-select">
					<option value="state">전시 상태</option>
					<option value="Y" <%=displayState.equals("Y") ? "selected" : ""%>>Y</option>
					<option value="N" <%=displayState.equals("N") ? "selected" : ""%>>N</option>
			</select></li>
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
		<c:forEach items="${artworkList}" var="avo" varStatus="index">
			<ul class="admin-list-main admin-artwork-list" onclick="go_check(event)">
				<li><input type="checkbox" class="check-box"></li>
				<li>${avo.displayyn}</li>
				<li>${avo.aseq}</li>
				<li class="view-link" onclick="location.href='museum.do?command=artworkView&aseq=${avo.aseq}'">${avo.name}</li>
				<li>${avo.artist}</li>
				<li>${avo.category}</li>
				<li>${avo.year}</li>
				<li>${avo.material}</li>
				<li>${avo.size}</li>
				<li>${avo.indate}</li>
				<li><img alt="artwork-img" src="${avo.fullSavefilename}" onmouseover="previewImg('artwork-${avo.aseq}-${index}')" onmouseleave="previewImg('artwork-${avo.aseq}-${index}')"></li>
			</ul>
			<div id="artwork-${avo.aseq}-${index}" class="preview hidden">
				<img alt="artwork-img" src="${avo.fullSavefilename}">
			</div>
		</c:forEach>
	</form>
</section>
<%@ include file="/util/pagination.jsp"%>
<%@ include file="/footer.jsp"%>