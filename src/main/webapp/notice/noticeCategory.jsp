<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/header.jsp"%>

<div class="notice_box">
	<div class="notice_header_box">
		<h2>
			<a href="museum.do?command=noticeList">전체</a>
		</h2>
		<h3>
			<a href="museum.do?command=noticeCategory&category=0">공지사항</a>
		</h3>
		<h3>
			<a href="museum.do?command=noticeCategory&category=1">이벤트</a>
		</h3>
		<h3>
			<a href="museum.do?command=noticeCategory&category=2">매거진</a>
		</h3>
		<h3>
			<a href="museum.do?command=noticeCategory&category=3">신문</a>
		</h3>
		<div class="writebutton">
			<input type="button" value="게시글 등록" onClick="location.href='museum.do?command=insertNoticeForm'" />
		</div>
	</div>
	<div class="notice_title_box">
		<div class="notice_title_row">
			<div class="notice_title_col col_number">번호</div>
			<div class="notice_title_col col_title">제목</div>
			<div class="notice_title_col col_content">내용</div>
			<div class="notice_title_col col_date">작성일</div>
			<div class="notice_title_col col_author">작성자</div>
			<div class="notice_title_col col_views">조회수</div>
		</div>
		<c:forEach items="${noticeList}" var="noticeList">
			<div class="row">
				<div class="col col_number">${noticeList.nseq}</div>
				<div class="col col_title">
					<a href="museum.do?command=noticeView&nseq=${noticeList.nseq}"> ${noticeList.title} </a>&nbsp;
				</div>
				<div class="col col_title">
					<a href="museum.do?command=noticeView&nseq=${noticeList.nseq}"> ${noticeList.content} </a>&nbsp;
				</div>
				<div class="col col_content">${noticeList.content}</div>
				<div class="col col_date">
					<fmt:formatDate value="${noticeList.writedate}" pattern="yyyy-MM-dd" />
				</div>
				<div class="col col_author">${noticeList.author}</div>
				<div class="col col_views">${noticeList.readcount}</div>
			</div>
		</c:forEach>
	</div>


	<!-- 페이징 시작 -->
	<c:set var="pageListPrefix" value="museum.do?command=noticeList&page=" />
	<div class="paging_wrap">
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