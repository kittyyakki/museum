<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>
<section class="qna-list">
	<h1>Q &amp; A</h1>
	<p>
		총 <span>${totalCount}</span> 건이 검색되었습니다.
	</p>
	<div class="qna-list_table">
		<ul class="header">
			<li>번호</li>
			<li>답변여부</li>
			<li>제목</li>
			<li>작성일</li>
		</ul>
		<c:forEach items="${qnaList}" var="qnaVO">
			<ul>
				<li>${qnaVO.qseq}</li>
				<li><c:choose>
						<c:when test="${empty qnaVO.reply}">NO</c:when>
						<c:otherwise>YES</c:otherwise>
					</c:choose></li>
				<li><a href="museum.do?command=qnaView&qseq=${qnaVO.qseq}"> ${qnaVO.isPublic() ? "🔒" : ""}${qnaVO.title}</a></li>
				<li><fmt:formatDate value="${qnaVO.writedate}" pattern="yyyy-MM-dd" /></li>
			</ul>
		</c:forEach>
	</div>
	<c:set var="pageListPrefix" value="museum.do?command=qnaList&page=" />
	<div class="paging">
		<c:choose>
			<c:when test="${paging.prev}">
				<a class="paging_button" href="${pageListPrefix}1">«</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">«</span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${paging.prev}">
				<a class="paging_button" href="${pageListPrefix}${paging.beginPage-1}">‹</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">‹</span>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
			<c:if test="${index==paging.page}">
				<span class="current-page">${index}</span>
			</c:if>
			<c:if test="${index!=paging.page}">
				<a href="${pageListPrefix}${index}">${index}</a>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${paging.next}">
				<a class="paging_button" href="${pageListPrefix}${paging.endPage+1}">›</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">›</span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${paging.next}">
				<a class="paging_button" href="${pageListPrefix}${paging.totalPage}">»</a>
			</c:when>
			<c:otherwise>
				<span class="paging_button">»</span>
			</c:otherwise>
		</c:choose>
	</div>
	<input class="qna-list_submit" type="button" value="질문하기" onClick="location.href='museum.do?command=writeQnaForm'" />
</section>
<%@ include file="../footer.jsp"%>