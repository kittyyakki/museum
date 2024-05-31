<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>

<div class="notice_insert_box">
	<h2>소식지 등록</h2>
	<div class="notice_insert_header_box">
		<form class="insertNotice" method="post" name="insertNotice" 
			action="museum.do"	enctype="multipart/form-data">
			<input type="hidden" name="command" value="insertNotice" />
			<div class="notice_insert_field">
				<label>작성자</label>
				<input type="text" name="id" value="${loginUser.id}" readonly/>
			</div>
			<div class="field">
				<label>비밀번호</label>
				<input style="flex:2" type="password" name="pass" />
				<div  style="flex:2; margin-left:20px;">게시물 수정 삭제시 필요합니다.</div>
			</div>
			<div class="field">
				<label>이메일</label>
				<input type="text" name="email" value="${loginUser.email}"/>
			</div>
			<div class="field">
				<label>제목</label><input type="text" name="title"/>
			</div>
			<div class="field">
				<label>내용</label><textarea name="content"rows="10" cols="100"></textarea>
			</div>
			<div class="field">
				<label>이미지</label><input type="file" name="image"/>
			</div>
			<div class="field">
				<input type="submit" value="작성완료" onClick="return noticeCheck()" />
			</div>
		</form>
	</div>
</div>




<%@ include file="/footer.jsp" %>
