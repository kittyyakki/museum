<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/joinForm.css" />
	<jsp:param name="script" value="script/join.js" />
</jsp:include>
<section>
	<article>
		<form action="museum.do?command=editMember" method="post" name="joinForm" class="joinForm">
			<h2>회원정보 수정</h2>

		<div class="middle">
			<div class="field">
				<span>*</span><label>이름</label> <input type="text" name="name" value="${loginUser.name}">
			</div>
			<div class="field">	
				<span>*</span>
				<label>아이디</label>
				<div>
					<input type="text"  class="edit-id" name="id" size="12" value="${loginUser.id}" readonly > 
				</div>
			</div>
			<p style="font-size:13px;">아이디는 4자~12자 이내의 영문과 숫자로 공백 없이 입력하시면 됩니다. 영문 대소문자를 구분하지 않습니다.</p>
			<div class="field">	
				<span>*</span><label>비밀번호</label> <input type="password" name="pwd" ">
			</div>
			<div class="field">
				<span>*</span><label>비밀번호 확인</label> <input type="password" name="pwdCheck" ">
			</div>

		
			<div class="field">
				<span>*</span><label>연락처</label> 
				<input type="text" name="phone" value="${loginUser.phone}"> 
			</div>
			<div class="field">
				<span>*</span><label>이메일</label> <input type="text" name="email" value="${loginUser.email}" >

			</div>
		</div>
			<div class="btn">
				<input type="button" value="이전" onclick="location.href='museum.do?command=myPage'"> 
				<input type="button" value="저장" onClick="go_editMember()">
			</div>
		</form>
	</article>
</section>

<%@ include file="/footer.jsp"%>