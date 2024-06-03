<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<section class="admin-list">
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
		<ul>
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
			<li>${qvo.title}</li>
			<li>${qvo.writedate}</li>
		</ul>
	</c:forEach>
</section>

<%@ include file="/util/pagination.jsp"%>
<%@ include file="/footer.jsp"%>