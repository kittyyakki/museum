<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/login.css" />
</jsp:include>
<section>
	<article class="login_Form">
		<div class="login_Form_inner">
			<form name="loginForm" method="post" action="museum.do?command=login&returnUrl=${returnUrl}" onsubmit="ajaxForm(this); return false;">
				<div class="login_icon_box">
					<div>
						<img src="images/ico_login_img.png" style="width: 80px; height: 80px;">
					</div>
					<h2>LogIn</h2>
				</div>
				<div class="field">
					<label>
						아이디
						<input name="id" type="text" placeholder="아이디" required>
					</label>
				</div>
				<div class="field">
					<label>비밀번호</label>
					<span> <input name="pwd" type="password" placeholder="비밀번호" required>
					</span>
				</div>
				<div class="btn">
					<input type="submit" value="로그인" required>
					<input type="button" value="회원가입" onclick="location.href='museum.do?command=joinForm'" required>
					<input type="button" value="아이디 찾기" onclick="" required>
				</div>
			</form>
		</div>
	</article>
	</div>
</section>
<%@ include file="/footer.jsp"%>