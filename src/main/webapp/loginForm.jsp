<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="header.jsp"%>

<section>
	<article>
		<form method="post" action="museum.do?command=login" name="loginForm">
			<h2>LogIn</h2>
			<div class="field">
				<label>User ID</label>
				<input name="id" type="text">
			</div>
			<div class="field">
				<label>Password</label>
				<input name="pwd" type="password">
			</div>
			<div class="btn">
				<input type="submit" value="LOGIN" onclick="loginCheck()">
				<input type="button" value="JOIN" onclick="">
				<input type="button" value="FIND ID" onclick="">
			</div>
			<div style="font-size: 80%; font-weight: bold">${message}</div>
		</form>
	</article>
	</div>
</section>


<%@ include file="footer.jsp"%>