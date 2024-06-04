<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/notice.css" />
	<jsp:param name="script" value="script/notice.js" />
</jsp:include>


<script type="text/javascript">
alert("게시물 삭제가 완료되었습니다");
location.href='museum.do?command=notice';
</script>





<%@ include file="/footer.jsp" %>
