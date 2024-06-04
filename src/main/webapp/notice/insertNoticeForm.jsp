<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <label>작성자</label>
                <input type="text" name="id" value="${loginUser.id}" readonly/>
            </div>
            <div class="notice_insert_field">
                <label>비밀번호</label>
                <input type="password" name="pass" />
                <div>게시물 수정 삭제시 필요합니다.</div>
            </div>
            <div class="notice_insert_field">
                <label>카테고리</label>
                <input type="text" name="category" value="${notice.category}"/>
            </div>
            <div class="notice_insert_field">
                <label>제목</label>
                <input type="text" name="title"/>
            </div>
            <div class="notice_insert_field">
                <label>내용</label>
                <textarea name="content" rows="10" cols="100"></textarea>
            </div>
            <div class="notice_insert_field">
                <label>이미지</label>
                <input type="file" name="image"/>
            </div>
            <div class="notice_insert_field">
                <input type="submit" value="작성완료" onClick="return noticeCheck()" />
            </div>
        </form>
    </div>
</div>

<%@ include file="/footer.jsp" %>
