<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp" />
<section class="qna-view">
	<form action="museum.do?command=qnaWrite" method="post">
		<h1>
			Q &amp; A &nbsp;&nbsp;
			<c:choose>
				<c:when test="${empty qnaVO}">새로운 문의 작성</c:when>
				<c:otherwise>${qnaVO.qseq}번 문의 수정</c:otherwise>
			</c:choose>
		</h1>
		<div class="qna-view_header">
			<h1>
				<label>
					제목:
					<input type="text" name="title" value="${qnaVO.title}" placeholder="제목을 입력하세요" required />
				</label>
			</h1>
			<ul>
				<li><label>
						이메일:
						<input type="email" name="email" value="${qnaVO.email}" placeholder="이메일을 입력하세요" required />
					</label></li>
				<li><label>
						전화번호
						<input type="tel" name="phone" value="${qnaVO.phone}" placeholder="전화번호를 입력하세요" required />
					</label></li>
				<li><label>
						공개여부:
						<input type="checkbox" name="publicyn" <c:if test="${qnaVO.isPublic()}">checked</c:if> />
					</label></li>
				<li><label>
						비밀번호:
						<input type="password" name="pwd" value="${qnaVO.pwd}" placeholder="비밀번호를 입력하세요" required />
					</label></li>
			</ul>
		</div>
		<div class="qna-view_content">
			<textarea name="content" placeholder="내용을 입력하세요" required>${qnaVO.content}</textarea>
		</div>
		<input type="submit" value="등록" />
		<c:if test="${not empty qnaVO}">
			<input type="hidden" name="qseq" value="${qnaVO.qseq}" />
			<input type="submit" value="삭제" formaction="museum.do?command=qnaWrite&delete=1" />
		</c:if>
	</form>
</section>
<%@ include file="/footer.jsp"%>