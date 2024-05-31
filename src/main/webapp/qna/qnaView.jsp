<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/header.jsp" />
<c:if test="${empty qnaContent or empty qvo}">
	<script type="text/javascript">
	<!-- qnaContent 혹은 qvo 값이 존재하지 않을 때 -->
		alert("잘못된 접근입니다.");
		history.back();
	</script>
</c:if>
<section class="qna-view">
	<h1>${qvo.title}</h1>
	<div>
		<c:out value="${qnaContent}" />
	</div>
</section>
<%@ include file="/footer.jsp"%>