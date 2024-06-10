<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/notice.css" />
	<jsp:param name="script" value="script/notice.js" />
</jsp:include>
<div class="notice_box">
	<div class="notice_header_box">
		<c:forEach items="${noticeCategory}" var="category" varStatus="status">
			<c:choose>
				<c:when test="${categoryName.equals(category.name())}">
					<a href="museum.do?command=noticeList&category=${category.name()}" class="notice-list_btn">${category.name()}</a>
				</c:when>
				<c:otherwise>
					<a href="museum.do?command=noticeList&category=${category.name()}" class="notice-list_btn">${category.name()}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<div class="writebutton">
			<c:if test="${isAdmin}">
				<input type="button" value="게시글 등록" onClick="location.href='museum.do?command=insertNoticeForm'" />
			</c:if>
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
			<div class="notice_title_col col_category">분류</div>
		</div>
		<c:forEach items="${noticeList}" var="noticeList">
			<div class="row">
				<%-- <div class="col col_number">${noticeList.nseq}</div> --%>
				<div class="col col_number">
				<a href="museum.do?command=noticeView&nseq=${noticeList.nseq}"> ${noticeList.nseq} </a>
					&nbsp;
				</div>
				<div class="col col_title">
					<a href="museum.do?command=noticeView&nseq=${noticeList.nseq}"> ${noticeList.title} </a>
					&nbsp;
				</div>
				<div class="col col_title">
					<a href="museum.do?command=noticeView&nseq=${noticeList.nseq}"> ${noticeList.content} </a>
					&nbsp;
				</div>
				<%-- <div class="col col_content">${noticeList.content}</div> --%>
				<div class="col col_date">
					<fmt:formatDate value="${noticeList.writedate}" pattern="yyyy-MM-dd" />
				</div>
				<div class="col col_author">${noticeList.author}</div>
				<div class="col col_views">${noticeList.readcount}</div>
				<div class="col col_category">${noticeList.category}</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="/util/pagination.jsp"%>
</div>
<%@ include file="/footer.jsp"%>