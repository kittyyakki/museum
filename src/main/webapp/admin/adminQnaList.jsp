<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/admin.css" />
	<jsp:param name="script" value="script/admin.js" />
</jsp:include>
<%@ include file="/admin/sub_menu.jsp"%>

<section class="admin-list">
	<form method="post" name="adminForm">
		<div class="admin-list-btn">
			<input type="hidden" name="memberIds">
			<input type="button" value="글 삭제" onclick="deleteQna()">
		</div>
		<ul class="admin-list-header">
			<li>
				<input type="checkbox" onclick="checkAll()" class="select-all-box">
			</li>
			<li>번호</li>
			<li>답변여부</li>
			<li>제목</li>
			<li>작성일</li>
		</ul>
		<c:forEach items="${qnaList}" var="qvo">
			<ul class="admin-list-main" onclick="go_check(event)">
				<li>
					<input type="checkbox" onclick="" class="check-box">
				</li>
				<li>${qvo.qseq}</li>
				<li>
					<c:choose>
						<c:when test="${empty qvo.reply}">NO</c:when>
						<c:otherwise>YES</c:otherwise>
					</c:choose>
				</li>
				<li class="view-link" onclick="location.href='museum.do?command=qnaView&qseq=${qvo.qseq}'">${qvo.title}</li>
				<li>${qvo.writedate}</li>
			</ul>
		</c:forEach>
	</form>
</section>

<%@ include file="/util/pagination.jsp"%>
<%@ include file="/footer.jsp"%>