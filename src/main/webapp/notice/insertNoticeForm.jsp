<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/notice.css" />
	<jsp:param name="script" value="script/notice.js" />
</jsp:include>

<div class="notice_insert_box">
	<h2>소식지 등록</h2>
	<div class="notice_insert_header_box">
		<form class="insertNotice" method="post" name="insertNotice" action="museum.do" enctype="multipart/form-data">
			<input type="hidden" name="command" value="insertNotice" />
			<div class="notice_insert_field">
				<label for="category">카테고리</label> <select name="category" id="category">
					<option value="">선택하세요</option>
					<c:forEach items="${noticeCategory}" var="category">
						<c:choose>
							<c:when test="${category.name() == '공지사항' || category.name() == '이벤트' }">
								<option value="${category.name()}">${category.name()}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select>
			</div>

			<div class="notice_insert_field">
				<label>제목</label> <input type="text" name="title" />
			</div>
			<div class="notice_insert_field">
				<label>내용</label>
				<textarea name="content" rows="10" cols="100"></textarea>
			</div>
			<!-- 			<div class="notice_insert_field">
				<label>이미지</label> <input type="file" name="image" />
			</div> -->
			<div class="notice_insert_field">
				<input type="submit" value="작성완료" onClick="return noticeCheck()" /> 
				<input type="submit" value="취소" onclick="window.location.href = 'museum.do?command=noticeList';">
			</div>
		</form>
	</div>
</div>

<%@ include file="/footer.jsp"%>
