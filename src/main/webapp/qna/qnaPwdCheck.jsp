<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 비밀번호 확인 ${qnaPwdResult}</title>
<c:choose>
	<c:when test="${empty qnaPwdResult}">
		<!-- qnaPwdResult가 없을 경우 -->
		<script type="text/javascript">
			alert("잘못된 접근입니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${qnaPwdResult == 0}">
		<!-- RESULT_SUCCESS -->
		<script type="text/javascript">
			opener.document.location.href = "museum.do?command=qnaView&qseq=${qseq}";
			self.close();
		</script>
	</c:when>
	<c:when test="${qnaPwdResult == 1}">
		<!-- RESULT_REQUEST_PWD -->
		<script type="text/javascript">
			window.onload = function() {
				var form = document.getElementById("qnaPwdCheckForm");
				var pwd = self.prompt("비밀번호를 입력하세요:");
				if (pwd !== null && pwd !== undefined && pwd !== "") {
					form.pwd.value = pwd;
					form.submit();
				} else {
					self.alert("비밀번호 입력이 취소되었습니다.");
					self.close();
				}
			};
		</script>
	</c:when>
	<c:when test="${qnaPwdResult == 2}">
		<!-- RESULT_NOT_FOUND -->
		<script type="text/javascript">
			self.alert("잘못된 접근입니다.");
			self.close();
		</script>
	</c:when>
	<c:when test="${qnaPwdResult == 3}">
		<!-- RESULT_PWD_WRONG -->
		<script type="text/javascript">
			self.alert("잘못된 비밀번호입니다. 다시 입력해주세요.");
			self.close();
		</script>
	</c:when>
</c:choose>
</head>
<body style="display: none;">
	<form id="qnaPwdCheckForm" method="post" action="museum.do?command=qnaPwdCheck&qseq=${qseq}">
		<input type="hidden" name="qseq" value="${qseq}">
		<input type="hidden" name="pwd">
	</form>
</body>
</html>