<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/admin.css" />
	<jsp:param name="script" value="script/admin.js" />
</jsp:include>
<%@ include file="/admin/sub_menu.jsp"%>

<section class="admin-list">
	<form method="post" name="adminForm">
		<div class="admin-list-btn">
			<!-- 체크된 id들이 배열값으로 들어오고 String 변환되어 넘어감 -->
			<input type="hidden" name="memberIds">
			<input type="button" value="게시글 등록" onclick="location.href='museum.do?command=insertNoticeForm'">
			<input type="button" value="수정" onclick="">
			<input type="button" value="삭제" onclick="">
			<input type="text" placeholder="검색어를 입력하세요" name="searchWord" value="${searchWord}">
			<input type="button" value="검색" onclick="searchAdmin('adminNoticeList')">
		</div>
		<ul class="admin-list-header admin-notice-list">
			<li>
				<input type="checkbox" onclick="checkAll()" class="select-all-box">
			</li>
			<li>번호</li>
			<li>분류</li>
			<li>제목</li>
			<li>내용</li>
			<li>작성일</li>
			<li>작성자</li>
			<li>조회수</li>
		</ul>
		<c:forEach items="${noticeList}" var="nvo">
			<ul class="admin-list-main admin-notice-list" onclick="go_check(event)">
				<li>
					<input type="checkbox" class="check-box">
				</li>
				<li>${nvo.nseq}</li>
				<li>${nvo.category}</li>
				<li onclick="location.href='museum.do?command=noticeView&nseq=${nvo.nseq}'" class="view-link">${nvo.title}</li>
				<c:choose>
					<c:when test="${nvo.content.length() > 50}">
						<li>${nvo.content.substring(0, 50)}...</li>
					</c:when>
					<c:otherwise>
						<li>${nvo.content}</li>
					</c:otherwise>
				</c:choose>
				<li>${nvo.writedate}</li>
				<li>${nvo.author}</li>
				<li>${nvo.readcount}</li>
			</ul>
		</c:forEach>
	</form>
</section>

<%@ include file="/footer.jsp" %>