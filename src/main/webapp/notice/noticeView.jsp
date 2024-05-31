<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>


<div id="notice_container">
	<h2>상세 보기</h2>
	<div class="notice">
		<div class="field">
			<div class="label">작성자</div>
			<div class="text">${loginUser.id}</div>
		</div>
		<div class="field">
			<div class="label">제목</div>
			<div class="text">${notice.title}</div>
		</div>
		<div class="field" style="margin-bottom: 15px;">
			<div class="label">내용</div>
			<div class="text" style="font-size: 140%; flex: 1.5;">
				<pre>${notice.content}</pre>
			</div>
		</div>
		<div class="field">
			<div class="label">조회수</div>
			<div class="text">${notice.readcount}</div>
		</div>
		<div class="field">
			<div class="label">카테고리</div>
			<div class="text">${notice.category}</div>
		</div>
		<div class="field">
			<div class="label">작성일</div>
			<div class="text">
				<fmt:formatDate value="${notice.writedate}" />
			</div>
		</div>
		<div class="notice-button">
			<input type="button" class="btn-notice" value="수정" onClick="location.href='museum.do?command=updateNoticeForm&nseq=${notice.nseq}'" /> 
			<input type="button" class="btn-notice" value="삭제" onClick="deleteNotice('${notice.author}')" /> 
			<input type="button" class="btn-notice" value="목록" onClick="location.href='notice.do?command=notice'" />
		</div>
	</div>
	<div class="reply">
		<div class="reply_row">
			<div class="reply_col reply_title">작성자</div>
			<div class="reply_col reply_title">작성일시</div>
			<div class="reply_col reply_title" style="text-align: center">댓글</div>
			<div class="reply_col reply_title">작성/삭제</div>
		</div>
		<form action="notice.do" name="reply">
			<input type="hidden" name="command" value="insertReply" /> 
			<input type="hidden" name="id" value="${notice.author}" /> 
			<input type="hidden" name="noticeNseq" value="${notice.nseq}" />
			<div class="reply_row">
				<div class="reply_col">${notice.author}</div>
				<div class="reply_col">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<fmt:formatDate value="${now}" pattern="MM/dd hh:mm" />
				</div>
				<div class="reply_col">
					<input type="text" name="content" size="75">
				</div>
				<div class="reply_col">
					<input type="submit" value="답글 작성" onClick="return replyCheck();">
				</div>
			</div>
		</form>

		<c:forEach items="${replyList}" var="reply">
			<div class="reply_row">
				<div class="reply_col">${reply.id}</div>
				<div class="reply_col">
					<fmt:formatDate value="${reply.writedate}" pattern="MM/dd hh:mm" />
				</div>
				<div class="reply_col">${reply.content}</div>
				<div class="reply_col">
					<c:if test="${reply.id == notice.author}">
						<input type="button" value="삭제" onClick="location.href='museum.do?command=deleteReply&replyNseq=${reply.replyNseq}
						&noticeNseq=${reply.noticeNseq}'" />
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</div>




<%@ include file="/footer.jsp"%>
