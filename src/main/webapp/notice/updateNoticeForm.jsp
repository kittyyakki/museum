<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>

<div id="notice_update_box">
	<h2>소식지 수정</h2>
	<div class="notice_update_innerbox">
		<form class="notice_insertForm" method="post" name="insertNotice" action="museum.do"
		 enctype="multipart/form-data">
			<div class="notice_insertfield">
				<label>작성자</label>
				<input type="text" name="id" value="${notice.id}" readonly/>
			</div>
			<div class="notice_insertfield">
				<label>비밀번호</label>
				<input style="flex:2" type="password" name="pass" />
				<div  style="flex:2; margin-left:20px;">게시물 작성시 입력한 비밀번호 입력</div>
			</div>
			<div class="notice_insertfield">
				<label>이메일</label>
				<input type="text" name="email" value="${notice.email}"/>
			</div>
			<div class="notice_insertfield">
				<label>제목</label><input type="text" name="title" value="${notice.title}"/>
			</div>
			<div class="notice_insertfield">
				<label>내용</label>
				<textarea name="content"rows="10" cols="100">${notice.content}</textarea>
			</div>
			
			<div class="notice_insertfield">
				<label>이미지</label>
				<input type="file" name="image"/>
			</div>
			<div class="notice_insertfield">
					<label>기존이미지</label>
					<c:choose>
								<c:when test="${empty notice.savefilename}">
									<img src="images/경주.jpg" height="80" style="text-align: left;">
								</c:when>
								<c:otherwise>
									<img src="images/${notice.savefilename}" height="80" style="text-align: left;">
								</c:otherwise>
					</c:choose>&nbsp;&nbsp;
			</div>
			<input type="hidden" name="oldimage" value="${notice.image}" />
			<input type="hidden" name="oldsavefilename" value="${notice.savefilename}" />
			
			<div class="notice_insertfield">
				<input type="submit" value="수정완료" onClick="return updateNoticeCheck('${notice.pass}')" />
				<input type="button" value="되돌아가기" onClick="" />
			</div>
			<input type="hidden" name="command" value="updateNotice" />
			<input type="hidden" name="nseq" value="${notice.nseq}" />
		</form>
	</div>
</div>





<%@ include file="/footer.jsp" %>
