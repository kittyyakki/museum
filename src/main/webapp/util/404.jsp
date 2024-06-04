<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="random" class="java.util.Random" scope="application" />
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/404.css" />
</jsp:include>
<main class="not-found bg-${random.nextInt(7)}">
	<p>요청하신 페이지를 찾을 수 없습니다</p>
</main>
<%@ include file="/footer.jsp"%>