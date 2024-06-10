<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/header.jsp">
	<jsp:param name="stylesheet" value="static/stylesheet/notice.css" />
	<jsp:param name="script" value="static/script/notice.js" />
</jsp:include>
<div id="notice_update_box">
	<h2>소식지 수정</h2>
	<div class="notice_update_innerbox">
		<form class="notice_insertForm" method="post" name="insertNotice" action="museum.do" enctype="multipart/form-data">
			<div class="notice_update_field">
				<label>작성자</label>
				<input type="text" name="id" value="${noticeUpdate.author}" readonly />
			</div>
			<!-- 			<div class="notice_update_field">
				<label>비밀번호</label>
				<input style="flex:2" type="password" name="pass" />
				<div  style="flex:2; margin-left:20px;">게시물 작성시 입력한 비밀번호 입력</div>
			</div> -->
			<div class="notice_update_field">
				<label>제목</label>
				<input type="text" name="title" value="${noticeUpdate.title}" />
			</div>
			<div class="notice_update_field">
				<label>내용</label>
				<textarea name="content" rows="10" cols="100">${noticeUpdate.content}</textarea>
			</div>
			<div class="notice_update_field">
				<label>카테고리</label>
				<textarea name="category" rows="10" cols="100">${noticeUpdate.category}</textarea>
			</div>
			<%-- 			<div class="notice_update_field">
				<label>이미지</label>
				<input type="file" name="image"/>
			</div>
			<div class="notice_update_field">
					<label>기존이미지</label>
					<c:choose>
								<c:when test="${empty noticeUpdate.savefilename}">
									<img src="static/image/경주.jpg" height="80" style="text-align: left;">
								</c:when>
								<c:otherwise>
									<img src="static/image/${noticeUpdate.savefilename}" height="80" style="text-align: left;">
								</c:otherwise>
					</c:choose>&nbsp;&nbsp;
			</div> --%>
			<input type="hidden" name="oldimage" value="${noticeUpdate.image}" />
			<input type="hidden" name="oldsavefilename" value="${noticeUpdate.savefilename}" />
			<div class="notice_update_field">
				<input type="submit" value="수정완료" onClick="return updateNoticeCheck('${member.pass}')" />
				<input type="button" value="되돌아가기" onClick="location.href='museum.do?command=noticeList'" />
				<input type="hidden" name="command" value="updateNotice" />
				<input type="hidden" name="nseq" value="${noticeUpdate.nseq}" />
			</div>
		</form>
	</div>
	<h2>소식지 수정</h2>
	<div class="notice_update_innerbox">
		<form class="notice_insertForm" method="post" name="insertNotice" action="museum.do" enctype="multipart/form-data">
			<div class="notice_update_field">
				<label>작성자</label> <input type="text" name="id" value="${noticeUpdate.author}" readonly />
			</div>
			<div class="notice_update_field">
				<label>제목</label> <input type="text" name="title" value="${noticeUpdate.title}" />
			</div>
			<div class="notice_update_field">
				<label>내용</label>
				<textarea name="content" rows="10" cols="100">${noticeUpdate.content}</textarea>
			</div>
			<div class="notice_update_field">
				<label>카테고리</label>
				<textarea name="category" rows="10" cols="100">${noticeUpdate.category}</textarea>
			</div>
			<input type="hidden" name="oldimage" value="${noticeUpdate.image}" /> <input type="hidden" name="oldsavefilename" value="${noticeUpdate.savefilename}" />
			<div class="notice_update_field_btn">
				<input type="submit" value="수정완료" onClick="return updateNoticeCheck('${member.pass}')" />
				<input type="button" value="되돌아가기" onClick="location.href='museum.do?command=noticeList'" /> <input type="hidden" name="command" value="updateNotice" /> <input type="hidden" name="nseq" value="${noticeUpdate.nseq}" />
			</div>
		</form>
	</div>
</div>
<div class="updateNotice_bottomClear"></div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
