<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>
<section class="artwork-view">
	<div class="artwork-view-title">
		<span>${artwork.artist} / </span>
		<span>${artwork.name} / </span>
		<span>${artwork.year}</span>
	</div>
	<div class="artwork-view-img">
		<img alt="artwork-image" src="images/artwork/${artwork.savefilename}">
		<span>
			※ 예술품 이미지는 저작권법에 따라 복제뿐만 아니라 전송, 배포 등 어떠한 방식으로든 무단 이용할 수 없으며, 영리적인 목적으로 사용할 경우 원작자에게 별도의 동의를 받아야함을 알려드립니다.
		</span>
	</div>
	<div class="artwork-view-info">
		<ul>
			<li>
				<label>작가명</label>
				<span>${artwork.artist}</span>
			</li>
			<li>
				<label>작품명</label>
				<span>${artwork.name}</span>
			</li>
			<li>
				<label>작가명</label>
				<span></span>
			</li>
			<li>
				<label>작가명</label>
				<span></span>
			</li>
			<li>
				<label>작가명</label>
				<span></span>
			</li>
			<li>
				<label>작가명</label>
				<span></span>
			</li>
			<li>
				<label>작가명</label>
				<span></span>
			</li>
		</ul>
	</div>
</section>

<%@ include file="/footer.jsp" %>