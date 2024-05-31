<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp" />
<c:if test="${empty qnaContent or empty qvo}">
	<script type="text/javascript">
	<!-- qnaContent 혹은 qvo 값이 존재하지 않을 때 -->
		alert("잘못된 접근입니다.");
		history.back();
	</script>
</c:if>
<section class="qna-view">
	<h1>Q &amp; A</h1>
	<div class="qna-view_header">
		<h1>${qvo.title}</h1>
		<ul>
			<li><strong>작성일</strong>: <fmt:formatDate value="${qvo.writedate}" pattern="yyyy-MM-dd" /></li>
			<li><strong>작성자</strong>: <c:choose>
					<c:when test="${isAdmin}">
					 ${qvo.email} (${qvo.phone})
				</c:when>
					<c:otherwise>${qvo.email.substring(0, 3)}**** (010-****-****)</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
	<div class="qna-view_content">
		<c:out value="${qnaContent}" />
	</div>
	<div class="qna-view_reply">
		<h2>답변</h2>
		<c:choose>
			<c:when test="${isAdmin}">
				<form id="qnaReplyForm" action="museum.do?command=qnaReply" method="post">
					<input type="hidden" name="qseq" value="${qvo.qseq}" />
					<textarea name="reply" placeholder="답변을 입력하세요"><c:out value="${qvo.reply}" /></textarea>
					<input type="submit" value="답변 등록" onclick="onReplySubmit(); return false;" />
				</form>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${empty qvo.reply}">
						<span class="qna-view_reply_no">답변이 아직 없습니다.</span>
					</c:when>
					<c:otherwise>
						<c:out value="${qvo.reply}" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</section>
<%@ include file="/footer.jsp"%>