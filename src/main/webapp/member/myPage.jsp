<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp"%>

<section>
	<article>
		<form action="museum.do?command=join" method="post" name="joinForm" class="joinForm">
			<h2>나의 정보</h2>

		<div class="middle">
			<div class="field">
				<span>*</span><label>이름</label> <input type="text" name="name" readonly value="${loginUser.name}">
			</div>
			<div class="field">
				<span>*</span>
				<label>아이디</label>
				<div>
					<input type="text" name="id" size="12" readonly value="${loginUser.id}"> 
					<input type="hidden" name="reid"> 
					
				</div>
			</div>
			<p style="font-size:13px;">아이디는 4자~12자 이내의 영문과 숫자로 공백 없이 입력하시면 됩니다. 영문 대소문자를 구분하지 않습니다.</p>
			<div class="field">	
				<span>*</span><label>비밀번호</label><input type="password" name="pwd" readonly value="${loginUser.pwd}">
			</div>
		
			<div class="field">
				<span>*</span><label>연락처</label> 
				<input type="text" name="phone" readonly value="${loginUser.phone}"> 
			</div>
			<div class="field">
				<span>*</span><label>이메일</label> <input type="text" name="email" readonly value="${loginUser.email}"/>

			</div>
		</div>
			<div class="btn">
				<input type="button" value="이전" onclick="location.href='museum.do?command=index'"> 
				<input type="button" value="수정" onClick="location.href='museum.do?command=editMemberForm'">
			</div>
		</form>
	</article>
</section>



<%@ include file="/footer.jsp"%>