<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<section class="artwork-write-form-container">
	<form method="post" name="artworkWriteForm" action="museum.do?command=artworkWriteForm" class="artwork-write-form">
		<div class="artwork-write-form-info">
			<ul>
				<li>
					<h2>예술품 등록</h2>
				</li>
				<li>
					<label>작가명</label>
					<input type="text" name="artist" >
					<input type="checkbox" name="unknownArtist" onclick="unknown()" id="unknownArtist" class="artwork-write-form-checkbox">
					<label for="unknownArtist">작자미상</label>
				</li>
				<li>
					<label>작품명</label>
					<input type="text" name="artname">
				</li>
				<li>
					<label>제작연도</label>
					<input type="text" name="year">
					<input type="checkbox" name="unknownYear" onclick="unknown()" id="unknownYear" class="artwork-write-form-checkbox">
					<label for="unknownYear">연도미상</label>
				</li>
				<li>
					<label>재료</label>
					<input type="text" name="material">
				</li>
				<li>
					<label>규격</label>
					<input type="text" name="size">
				</li>
				<li>
					<label>부문</label>
					<input type="text" name="category">
				</li>
				<li>
					<label>작품설명</label>
					<textarea rows="10	" cols="50" name="content">
				</textarea>
				</li>
				<li>
					<label>전시상태</label>
					<input type="radio" name="displayYn" value="Y" >
					공개
					<input type="radio" name="displayYn" value="N">
					비공개
				</li>
			</ul>
			<div>
				<img alt="artwork-img" src="">
			</div>
		</div>
		<div class="artwork-write-form-btn">
			<input type="button" value="등록" onclick="artworkUpdate()">
			<input type="button" value="취소">
		</div>
	</form>
</section>

<%@ include file="/footer.jsp"%>