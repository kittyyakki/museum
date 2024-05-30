<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header.jsp"%>

<section>
	<article class="login_Form">
		<div class="login_Form_inner">
			<form method="post" action="museum.do?command=login" name="loginForm">
				<div class="login_icon_box">
					<div>
						<img src="images/ico_login_img.png" style="width: 80px; height: 80px;">
					</div>
					<h2>LogIn</h2>
				</div>
				<div class="field">
					<label>아이디</label> <span> <input name="id" type="text" placeholder="아이디" required>
					</span>
				</div>
				<div class="field">
					<label>비밀번호</label> <span> <input name="pwd" type="password" placeholder="비밀번호" required>
					</span>
				</div>
				<div class="btn">
					<input type="submit" value="로그인" onclick="return loginCheck()" required> <input type="button" value="회원가입" onclick="" required> <input type="button" value="아이디 찾기" onclick="" required>
				</div>
				<div style="font-size: 80%; font-weight: bold">${message}</div>
			</form>
		</div>
	</article>
	</div>
</section>







<%@ include file="/footer.jsp"%>