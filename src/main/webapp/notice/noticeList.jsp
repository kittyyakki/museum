<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>

<div class="notice_box">
<div class="notice_header_box">
	<h2>전체</h2>
	<h3>공지사항</h3>
	<h3>이벤트</h3>
	<div class="writebutton">
		<input type="button" value="게시글 등록" onClick="location.href='notice.do?command=insertNoticeForm'" />
	</div>
	</div>
	<div class="notice_title_box">
		<div class="notice_title_row">
			<div class="notice_title_col">번호</div>
			<div class="notice_title_col">제목</div>
			<div class="notice_title_col">작성자</div>
			<div class="notice_title_col">작성일</div>
			<div class="notice_title_col">조회수</div>
		</div>
		<c:forEach items="${noticeList}" var="notice">
			<div class="notice_reply_row">
				<div class="notice_reply_col">${notice.nseq}</div>
				<div class="notice_reply_col">
					<a style="text-decoration: none" href="museum.do?command=noticeView&nseq=${notice.nseq}"> 
						${notice.title} 
					</a>&nbsp;
					<c:if test="${notice.replycnt>0}">
						<span style="color: red; font-weight: bold">[${notice.replycnt}]</span>
					</c:if>

					<c:if test="${not empty notice.image}">
						<span style="color: blue; font-weight: bold; font-size: 90%">[img]</span>
					</c:if>

				</div>
				<div class="notice_reply_col">${notice.id}</div>
				<div class="notice_reply_col">
					<fmt:formatDate value="${notice.writedate}" />
				</div>
				<div class="notice_reply_col">${notice.readcount}</div>
			</div>
		</c:forEach>
		<!-- 페이징 시작 -->
		<c:set var="pageListPrefix" value="museum.do?command=noticeList&page=" />
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
	</div>
</div>


<%@ include file="/footer.jsp"%>