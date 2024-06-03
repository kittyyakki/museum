<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp" %>

<section class="admin-list">
        <form action="museum.do?command=grantAdminRights" method="post" name="grantAdminForm">
            <div class="admin-list-btn">
                <input type="hidden" name="memberIds" value="">
                <input type="button" value="관리자 권한 부여" onclick="grantAdmin()">
                <input type="button" value="회원 삭제" onclick="">
            </div>
            <ul class="admin-list-header">
                <li>
                    <input type="checkbox" onclick="checkAll()" class="select-all-box">
                </li>
                <li>ID</li>
                <li>이름</li>
                <li>Email</li>
                <li>가입일</li>
                <li>전화번호</li>
            </ul>
            <c:forEach items="${memberList}" var="mvo">
                <ul>
                    <li>
                        <input type="checkbox" class="check-box">
                    </li>
                    <li>${mvo.id}
                        <c:if test="${mvo.adminyn == 'Y'}"><span style="color:red;">[admin]</span></c:if>
                    </li>
                    <li>${mvo.name}</li>
                    <li>${mvo.email}</li>
                    <li>${mvo.indate}</li>
                    <li>${mvo.phone}</li>
                </ul>
            </c:forEach>
        </form>
    </section>


<%@ include file="/util/pagination.jsp"%>
<%@ include file="/footer.jsp" %>
