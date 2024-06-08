<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/qna/qna_icon.css" />
	<jsp:param name="stylesheet" value="css/qna/qna_view.css" />
	<jsp:param name="script" value="script/qna/qna.js" />
</jsp:include>
<section class="qna-view">
	<div class="qna-view_title">
		<h1>Q &amp; A</h1>
		<button class="qna-view_submit" onclick="qnaPwdCheck(${qnaVO.qseq}, 'edit')">수정하기</button>
		<button class="qna-view_submit" onclick="qnaPwdCheck(${qnaVO.qseq}, 'delete')">삭제하기</button>
	</div>
	<div class="qna-view_header">
		<h1>${qnaVO.title}</h1>
		<ul>
			<li><strong>작성일</strong>: <fmt:formatDate value="${qnaVO.writedate}" pattern="yyyy-MM-dd" /></li>
			<li><strong>작성자</strong>: <c:choose>
					<c:when test="${isAdmin}">
					 ${qnaVO.email} (${qnaVO.phone})
				</c:when>
					<c:otherwise>${qnaVO.email.substring(0, 3)}**** (010-****-****)</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
	<div class="qna-view_content">
		<c:out value="${qnaVO.content}" />
	</div>
	<div class="qna-view_reply">
		<h2>답변</h2>
		<c:choose>
			<c:when test="${isAdmin}">
				<form id="qnaReplyForm" action="museum.do?command=qnaReply" method="post" onsubmit="ajaxSubmit(event)">
					<input type="hidden" name="qseq" value="${qnaVO.qseq}" />
					<textarea name="reply" placeholder="답변을 입력하세요"><c:out value="${qnaVO.reply}" /></textarea>
					<input type="submit" value="답변 등록" />
				</form>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${empty qnaVO.reply}">
						<span class="qna-view_reply_no">답변이 아직 없습니다.</span>
					</c:when>
					<c:otherwise>
						<c:out value="${qnaVO.reply}" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</section>
<%@ include file="/footer.jsp"%>