<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>


<div id="notice_container">
	<h2>상세 보기</h2>
	<div class="noticeView_box">
		<div class="noticeView_field">
			<div class="label">작성자</div>
			<div class="text">${loginUser.id}</div>
		</div>
		<div class="noticeView_field">
			<div class="label">제목</div>
			<div class="text">${noticeView.title}</div>
		</div>
		<div class="noticeView_field" style="margin-bottom: 15px;">
			<div class="label">내용</div>
			<div class="text" style="font-size: 140%; flex: 1.5;">
				<pre>${noticeView.content}</pre>
			</div>
		</div>
		<div class="noticeView_field">
			<div class="label">조회수</div>
			<div class="text">${noticeView.readcount}</div>
		</div>
		<div class="noticeView_field">
			<div class="label">카테고리</div>
			<div class="text">${noticeView.category}</div>
		</div>
		<div class="noticeView_field">
			<div class="label">작성일</div>
			<div class="text">
				<fmt:formatDate value="${noticeView.writedate}" />
			</div>
		</div>
		<%-- <div class="label" style="flex:0.5;">이미지</div>
			<div class="text" style="flex:2;">
				<c:choose>
					<c:when test="${empty  noticeView.savefilename}">
						<img src="images/noname.jpg" width="250" />
					</c:when>
					<c:otherwise>
						<img src="images/${noticeView.savefilename}" width="350" />
					</c:otherwise>
				</c:choose>
			</div> --%>
		<div class="noticeView-button">
			<input type="button" class="btn-notice" value="수정" 
			onClick="location.href='museum.do?command=updateNoticeForm&nseq=${notice.nseq}'" /> 
			<input type="button" class="btn-notice" value="삭제" 
			onClick="deleteNotice('${loginUser.id}')" /> 
			<input type="button" class="btn-notice" value="목록" 
			onClick="location.href='museum.do?command=notice'" />
		</div>
	</div>
</div>




<%@ include file="/footer.jsp"%>
