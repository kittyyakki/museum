<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>


<div class="noticeView_container">
	<h2>상세 보기</h2>
	<div class="noticeView_box">
		<div class="noticeView_field">
			<div class="noticeView_label">작성자</div>
			<div class="noticeView_text">${loginUser.id}</div>
		</div>
		<div class="noticeView_field">
			<div class="noticeView_label">제목</div>
			<div class="noticeView_text">${noticeView.title}</div>
		</div>
		<div class="noticeView_label" style="flex: 0.5;">이미지</div>
		<%-- <div class=noticeView_text style="flex: 2;">
			<c:choose>
				<c:when test="${empty  noticeView.savefilename}">
					<img src="images/noname.jpg" width="250" />
				</c:when>
				<c:otherwise>
					<img src="images/${noticeView.savefilename}" width="350" />
				</c:otherwise>
			</c:choose>
		</div> --%>
		<div class="noticeView_field">
			<div class="noticeView_label">내용</div>
			<div class="noticeView_text">
				<pre>${noticeView.content}</pre>
			</div>
		</div>
		<%-- 		<div class="noticeView_field">
			<div class="noticeView_label">조회수</div>
			<div class="noticeView_text">${noticeView.readcount}</div>
		</div> --%>
		<div class="noticeView_field">
			<div class="noticeView_label">카테고리</div>
			<div class="noticeView_text">${noticeView.category}</div>
		</div>
		<div class="noticeView_field">
			<div class="noticeView_label">작성일</div>
			<div class="noticeView_text">
				<fmt:formatDate value="${noticeView.writedate}" />
			</div>
		</div>
		<div class="noticeView-button">
			<input type="button" class="btn-noticeView" value="수정" onClick="location.href='museum.do?command=updateNoticeForm&nseq=${noticeView.nseq}'" /> 
			<input type="button" class="btn-noticeView" value="삭제" onClick="deleteNotice('${loginUser.pwd}', '${noticeView.nseq}')"/>
			<input type="button" class="btn-noticeView" value="목록" onClick="location.href='museum.do?command=noticeList'" />
		</div>
	</div>
</div>




<%@ include file="/footer.jsp"%>
