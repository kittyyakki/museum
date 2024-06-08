<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/joinForm.css" />
	<jsp:param name="script" value="script/join.js" />
</jsp:include>
<section>
	<article>
		<form name="joinForm" class="joinForm" method="post" action="museum.do?command=join&returnUrl=${returnUrl}" onsubmit="ajaxSubmit(event)">
			<h2>회원가입</h2>
			<div class="middle">
				<div class="field">
					<span>*</span>
					<label>이름</label>
					<input type="text" name="name">
				</div>
				<div class="field">
					<span>*</span>
					<label>아이디</label>
					<div>
						<input type="text" name="id" size="12">
						<input type="hidden" name="reid">
						<input type="button" value="중복 체크" onclick="ajax({command:'idCheck',id:document.joinForm.id.value})">
					</div>
				</div>
				<p style="font-size: 13px;">아이디는 4자~12자 이내의 영문과 숫자로 공백 없이 입력하시면 됩니다. 영문 대소문자를 구분하지 않습니다.</p>
				<div class="field">
					<span>*</span>
					<label>비밀번호</label>
					<input type="password" name="pwd">
				</div>
				<div class="field">
					<span>*</span>
					<label>비밀번호 확인</label>
					<input type="password" name="pwdCheck">
				</div>
				<div class="field">
					<span>*</span>
					<label>연락처</label>
					<input type="tel" name="phone">
				</div>
				<div class="field">
					<span>*</span>
					<label>이메일</label>
					<input type="email" name="email" />
				</div>
			</div>
			<div class="btn">
				<input type="button" value="이전" onclick="history.back();">
				<input type="submit" value="가입">
			</div>
		</form>
	</article>
</section>
<%@ include file="/footer.jsp"%>