<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 비밀번호 확인 ${qnaPwdResult}</title>
<script src="script/qna.js"></script>
<script type="text/javascript">
	window.onload = function() {
		onQnaPwdResult(${qnaPwdResult == null ? 'null' : qnaPwdResult}, ${qseq});
	}
</script>
</head>
<body style="display: none;">
	<form id="qnaPwdCheckForm" method="post" action="museum.do?command=qnaPwdCheck&qseq=${qseq}">
		<input type="hidden" name="qseq" value="${qseq}">
		<input type="hidden" name="pwd">
	</form>
</body>
</html>