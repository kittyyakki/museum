<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/qna_list.css" />
	<jsp:param name="script" value="script/qna.js" />
</jsp:include>
<section class="qna-list">
	<h1>Q &amp; A</h1>
	<div class="qna-list_subheader">
		<p>
			총 <span>${pagination.itemCount}</span> 건이 검색되었습니다.
		</p>
		<a href="museum.do?command=qnaWriteForm">
			<button class="qna-list_submit">질문하기</button>
		</a>
	</div>
	<div class="qna-list_table">
		<ul class="header">
			<li>번호</li>
			<li>답변여부</li>
			<li>제목</li>
			<li>작성일</li>
		</ul>
		<c:forEach items="${qnaList}" var="qnaVO">
			<ul onclick="qnaPwdCheck(${qnaVO.qseq}, 'view')">
				<li>${qnaVO.qseq}</li>
				<li><c:choose>
						<c:when test="${empty qnaVO.reply}">NO</c:when>
						<c:otherwise>YES</c:otherwise>
					</c:choose></li>
				<li><span> <c:if test="${!qnaVO.isPublic()}">
							<span>🔒</span>
						</c:if>${qnaVO.title}</span></li>
				<li><fmt:formatDate value="${qnaVO.writedate}" pattern="yyyy-MM-dd" /></li>
			</ul>
		</c:forEach>
	</div>
	<%@ include file="/util/pagination.jsp"%>
</section>
<%@ include file="/footer.jsp"%>