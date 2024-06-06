<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.team4.museum.util.NoticeCategory"%>
<%@ page import="com.team4.museum.util.ArtworkCategory"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Museum</title>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable-gov-dynamic-subset.min.css" />
<link rel="stylesheet" href="css/reset01.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<c:forEach items="${paramValues.stylesheet}" var="css">
    <link rel="stylesheet" href="${css}">
</c:forEach>

<!-- JavaScript -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.drawsvg/1/jquery.drawsvg.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<script src="https://unpkg.com/aos@2.2/dist/aos.js"></script>
<script src="http://cdn.jsdelivr.net/vivus/0.2.1/vivus.min.js"></script>
<script src="script/header.js"></script>
<script src="script/ajax.js"></script>

<!-- Load other scripts -->
<c:forEach items="${paramValues.script}" var="js">
    <script src="${js}"></script>
</c:forEach>

</head>
<body>
	<header>
		<div class="header_util">
			<ul class="util-link">
				<li><a href="https://www.museum.go.kr/site/child/home" target="_blank">어린이박물관</a></li>
				<li><a href="https://www.museum.go.kr/curator" target="_blank">학예사자격증</a></li>
				<li><a href="http://webzine.museum.go.kr" target="_blank">박물관신문</a></li>
				<li><a class="lang-select" href="javascript:;"> Language </a></li>
				<li><a href="https://blog.naver.com/100museum" target="_blank" title="국립중앙박물관 네이버 블로그 새 창으로 열림" class="naver"> <img src="images/header/ico_header_item1.png" alt="블로그">
				</a></li>
				<li><a href="https://twitter.com/The_NMK" target="_blank" title="국립중앙박물관 X 새 창으로 열림" class="twitter"> <img src="images/header/ico_header_item2.png" alt="X">
				</a></li>
				<li><a href="https://www.facebook.com/NationalMuseumofKorea/" target="_blank" title="국립중앙박물관 페이스북 새 창으로 열림" class="facebook"> <img src="images/header/ico_header_item3.png" alt="페이스북">
				</a></li>
				<li><a href="https://www.instagram.com/nationalmuseumofkorea/" target="_blank" title="국립중앙박물관 인스타그램 새 창으로 열림" class="instagram"> <img src="images/header/ico_header_item4.png" alt="인스타그램">
				</a></li>
				<li><a href="https://www.youtube.com/user/koreanmuseum" target="_blank" title="국립중앙박물관 유튜브 새 창으로 열림" class="youtube"> <img src="images/header/ico_header_item5.png" alt="유튜브">
				</a></li>
				<li><a href="https://audioclip.naver.com/channels/2320" target="_blank" title="국립중앙박물관 네이버 오디오클립 새 창으로 열림" class="audioclip"> <img src="images/header/ico_header_item6.png" alt="오디오클립">
				</a></li>
			</ul>
		</div>
		<nav>
			<h1 data-aos="fade-in" data-aos-offset="200" data-aos-easing="ease-in">
				<svg id="header_logo_v" width="300" height="28.714" viewBox="0 0 200 30" xmlns="http://www.w3.org/2000/svg">
					<g stroke-linecap="round" fill-rule="evenodd" font-size="9pt" stroke="#000" stroke-width="0.2mm" fill="#000" style="stroke:#000;stroke-width:0.2mm;fill:#000">
					<path class="logo01" id="logo_outline01" data-duration="300" stroke="#000" fill="#000" fill-opacity="0" d="M 24.37 18.628 L 24.37 28.882 L 21.67 28.882 L 21.67 20.85 L 2.871 20.85 L 2.871 18.628 L 24.37 18.628 Z M 27.822 10.835 L 27.822 13.022 L 0 13.022 L 0 10.835 L 27.822 10.835 Z M 23.276 0 L 23.276 2.188 L 3.521 2.188 L 3.521 0 L 23.276 0 Z M 23.174 11.963 L 20.542 11.621 Q 20.986 9.707 21.191 8.032 A 48.562 48.562 0 0 0 21.324 6.836 Q 21.381 6.245 21.415 5.718 A 25.81 25.81 0 0 0 21.448 5.11 A 54.543 54.543 0 0 0 21.479 4.114 Q 21.489 3.674 21.494 3.193 A 96.813 96.813 0 0 0 21.499 2.187 L 21.499 0 L 24.165 0 L 24.165 2.188 Q 24.165 4.819 23.994 6.99 Q 23.847 8.855 23.347 11.187 A 48.515 48.515 0 0 1 23.174 11.963 Z M 15.176 12.305 L 15.176 19.551 L 12.51 19.551 L 12.51 12.305 L 15.176 12.305 Z" vector-effect="non-scaling-stroke"  />
					<path class="logo02" id="logo_outline02" data-duration="100" stroke="#000" fill="#000" fill-opacity="0" d="M 13.877 1.47 L 13.877 9.673 L 2.666 9.673 L 2.666 15.278 L 0.068 15.278 L 0.068 7.554 L 11.211 7.554 L 11.211 3.691 L 0 3.691 L 0 1.47 L 13.877 1.47 Z M 6.494 18.218 L 6.494 21.841 L 20.884 21.841 L 20.884 18.218 L 23.55 18.218 L 23.55 29.941 L 3.794 29.941 L 3.794 18.218 L 6.494 18.218 Z M 17.09 12.852 L 17.397 15.073 A 78.683 78.683 0 0 1 12.21 15.701 A 69.31 69.31 0 0 1 10.544 15.825 Q 7.246 16.03 2.529 16.064 L 0.068 16.064 L 0.068 13.809 L 2.529 13.809 A 387.175 387.175 0 0 0 4.784 13.778 Q 6.554 13.749 7.964 13.706 A 74.871 74.871 0 0 0 12.476 13.433 A 69 69 0 0 0 17.09 12.852 Z M 23.55 0 L 23.55 16.68 L 20.884 16.68 L 20.884 0 L 23.55 0 Z M 6.494 27.754 L 20.884 27.754 L 20.884 23.994 L 6.494 23.994 L 6.494 27.754 Z" vector-effect="non-scaling-stroke" />	
					<path class="logo03" id="logo_outline03" data-duration="100" stroke="#000" fill="#000" fill-opacity="0"
						d="M 23.687 0 L 23.687 30.386 L 21.021 30.386 L 21.021 0 L 23.687 0 Z M 11.412 9.904 A 8.925 8.925 0 0 0 9.194 9.639 A 9.665 9.665 0 0 0 8.147 9.694 A 7.767 7.767 0 0 0 5.349 10.527 A 6.616 6.616 0 0 0 2.7 13.005 A 6.492 6.492 0 0 0 2.089 14.34 A 7.262 7.262 0 0 0 1.743 16.611 Q 1.743 18.628 2.7 20.217 A 6.616 6.616 0 0 0 5.349 22.695 A 7.462 7.462 0 0 0 6.977 23.318 A 8.925 8.925 0 0 0 9.194 23.584 A 9.665 9.665 0 0 0 10.242 23.528 A 7.767 7.767 0 0 0 13.04 22.695 A 6.616 6.616 0 0 0 15.688 20.217 A 6.492 6.492 0 0 0 16.299 18.883 A 7.262 7.262 0 0 0 16.646 16.611 Q 16.646 14.595 15.688 13.005 A 6.616 6.616 0 0 0 13.04 10.527 A 7.462 7.462 0 0 0 11.412 9.904 Z M 18.081 4.888 L 18.081 7.109 L 0 7.109 L 0 4.888 L 18.081 4.888 Z M 9.194 11.895 A 5.64 5.64 0 0 0 7.71 12.084 A 4.834 4.834 0 0 0 6.682 12.493 A 4.403 4.403 0 0 0 4.939 14.167 Q 4.307 15.244 4.307 16.611 A 5.033 5.033 0 0 0 4.501 18.033 A 4.328 4.328 0 0 0 4.939 19.038 A 4.439 4.439 0 0 0 6.682 20.696 A 5.023 5.023 0 0 0 8.595 21.265 A 6.154 6.154 0 0 0 9.194 21.294 A 5.64 5.64 0 0 0 10.679 21.105 A 4.834 4.834 0 0 0 11.707 20.696 A 4.439 4.439 0 0 0 13.45 19.038 A 4.495 4.495 0 0 0 14.06 17.113 A 5.565 5.565 0 0 0 14.082 16.611 A 5.055 5.055 0 0 0 13.865 15.109 A 4.452 4.452 0 0 0 13.45 14.167 A 4.403 4.403 0 0 0 11.707 12.493 A 5.023 5.023 0 0 0 9.794 11.923 A 6.154 6.154 0 0 0 9.194 11.895 Z M 10.493 0.376 L 10.493 5.913 L 7.827 5.913 L 7.827 0.376 L 10.493 0.376 Z M 28.779 12.544 L 28.779 14.766 L 23.071 14.766 L 23.071 12.544 L 28.779 12.544 Z"
						vector-effect="non-scaling-stroke" />	
					<path class="logo04" id="logo_outline04" data-duration="100" stroke="#000" fill="#000" fill-opacity="0"
						d="M 23.926 0 L 23.926 30.454 L 21.226 30.454 L 21.226 0 L 23.926 0 Z M 11.775 3.674 A 6.532 6.532 0 0 0 7.759 2.358 A 6.532 6.532 0 0 0 3.743 3.674 A 7.712 7.712 0 0 0 2.079 5.391 A 10.03 10.03 0 0 0 0.991 7.383 A 12.574 12.574 0 0 0 0.211 10.191 A 17.261 17.261 0 0 0 0 12.954 A 17.308 17.308 0 0 0 0.204 15.673 A 12.602 12.602 0 0 0 0.991 18.525 A 10.358 10.358 0 0 0 1.774 20.057 A 7.757 7.757 0 0 0 3.743 22.217 A 6.59 6.59 0 0 0 7.759 23.516 A 6.59 6.59 0 0 0 11.775 22.217 A 7.593 7.593 0 0 0 13.47 20.469 A 9.971 9.971 0 0 0 14.527 18.525 A 12.574 12.574 0 0 0 15.307 15.717 A 17.261 17.261 0 0 0 15.518 12.954 A 17.308 17.308 0 0 0 15.314 10.235 A 12.602 12.602 0 0 0 14.527 7.383 A 10.467 10.467 0 0 0 13.754 5.865 A 7.897 7.897 0 0 0 11.775 3.674 Z M 7.759 4.751 Q 6.255 4.751 5.076 5.776 Q 3.929 6.773 3.284 8.545 A 9.797 9.797 0 0 0 3.247 8.647 A 10.924 10.924 0 0 0 2.76 10.647 Q 2.598 11.724 2.598 12.954 A 15.441 15.441 0 0 0 2.741 15.105 A 11.249 11.249 0 0 0 3.247 17.244 A 8.252 8.252 0 0 0 3.856 18.604 Q 4.291 19.37 4.86 19.919 A 4.919 4.919 0 0 0 5.076 20.115 Q 6.255 21.123 7.759 21.123 A 4.014 4.014 0 0 0 10.442 20.115 A 5.482 5.482 0 0 0 11.545 18.802 Q 11.914 18.203 12.193 17.459 A 9.683 9.683 0 0 0 12.271 17.244 A 11.249 11.249 0 0 0 12.777 15.105 A 15.441 15.441 0 0 0 12.92 12.954 A 15.849 15.849 0 0 0 12.796 10.919 Q 12.656 9.84 12.359 8.91 A 9.564 9.564 0 0 0 12.271 8.647 A 8.439 8.439 0 0 0 11.658 7.283 Q 11.149 6.385 10.459 5.776 Q 9.297 4.751 7.759 4.751 Z"
						vector-effect="non-scaling-stroke" />	
					<path class="logo05" id="logo_outline05" data-duration="100" stroke="#000" fill="#000" fill-opacity="0" d="M 14.048 3.008 L 14.048 22.764 L 0 22.764 L 0 3.008 L 14.048 3.008 Z M 23.276 0 L 23.276 30.454 L 20.576 30.454 L 20.576 0 L 23.276 0 Z M 2.632 5.161 L 2.632 20.576 L 11.416 20.576 L 11.416 5.161 L 2.632 5.161 Z" vector-effect="non-scaling-stroke" />
					<path class="logo06" id="logo_outline06" data-duration="100" stroke="#000" fill="#000" fill-opacity="0"
						d="M 24.131 17.773 L 24.131 24.678 L 6.084 24.678 L 6.084 28.369 L 3.418 28.369 L 3.418 22.661 L 21.499 22.661 L 21.499 19.893 L 3.35 19.893 L 3.35 17.773 L 24.131 17.773 Z M 27.686 12.544 L 27.686 14.731 L 0 14.731 L 0 12.544 L 27.686 12.544 Z M 25.054 27.754 L 25.054 29.873 L 3.418 29.873 L 3.418 27.754 L 25.054 27.754 Z M 26.182 8.511 L 25.225 10.562 A 22.038 22.038 0 0 1 19.192 8.853 Q 16.372 7.588 14.646 5.605 A 7.104 7.104 0 0 1 13.325 3.434 A 6.415 6.415 0 0 1 12.92 1.162 L 12.92 0 L 15.244 0 L 15.244 1.162 Q 15.244 2.974 16.799 4.529 Q 18.354 6.084 20.867 7.126 Q 23.379 8.169 26.182 8.511 Z M 2.461 10.562 L 1.538 8.511 Q 4.341 8.169 6.853 7.126 A 14.886 14.886 0 0 0 8.933 6.076 Q 10.07 5.379 10.92 4.529 A 6.107 6.107 0 0 0 11.837 3.378 A 4.218 4.218 0 0 0 12.476 1.162 L 12.476 0 L 14.766 0 L 14.766 1.162 A 6.538 6.538 0 0 1 13.223 5.387 A 8.35 8.35 0 0 1 13.04 5.605 A 11.794 11.794 0 0 1 10.455 7.803 A 15.324 15.324 0 0 1 8.494 8.853 A 22.038 22.038 0 0 1 2.461 10.562 Z M 15.142 14.116 L 15.142 18.799 L 12.476 18.799 L 12.476 14.116 L 15.142 14.116 Z"
						vector-effect="non-scaling-stroke" />
					<path class="logo07" id="logo_outline07" data-duration="100" stroke="#000" fill="#000" fill-opacity="0"
						d="M 23.994 0 L 23.994 22.866 L 21.26 22.866 L 21.26 0 L 23.994 0 Z M 24.985 27.48 L 24.985 29.736 L 4.683 29.736 L 4.683 27.48 L 24.985 27.48 Z M 19.209 14.8 L 19.38 16.782 A 74.627 74.627 0 0 1 13.855 17.499 A 88.795 88.795 0 0 1 10.322 17.756 A 183.762 183.762 0 0 1 2.863 17.997 A 207.16 207.16 0 0 1 0.273 18.013 L 0 15.791 Q 5.623 15.758 10.142 15.581 A 215.995 215.995 0 0 0 10.442 15.569 Q 15.073 15.381 19.209 14.8 Z M 15.449 2.358 L 15.449 4.614 L 1.88 4.614 L 1.88 2.358 L 15.449 2.358 Z M 16.235 12.954 L 13.535 12.749 Q 14.082 10.083 14.219 8.203 A 61.823 61.823 0 0 0 14.327 6.27 Q 14.369 5.249 14.387 4.116 A 100.739 100.739 0 0 0 14.39 3.965 L 14.39 2.358 L 17.021 2.358 L 17.021 3.965 A 115.624 115.624 0 0 1 16.975 6.043 Q 16.935 7.312 16.868 8.408 Q 16.776 9.9 16.454 11.772 A 49.004 49.004 0 0 1 16.235 12.954 Z M 9.194 9.092 L 9.194 16.714 L 6.528 16.714 L 6.528 9.092 L 9.194 9.092 Z M 7.383 20.815 L 7.383 28.198 L 4.683 28.198 L 4.683 20.815 L 7.383 20.815 Z M 28.438 9.912 L 28.438 12.202 L 22.764 12.202 L 22.764 9.912 L 28.438 9.912 Z"
						vector-effect="non-scaling-stroke" />
							</g>
						</svg>
			</h1>
			<div class="header_gnb">
				<div>
					<a href="museum.do?command=artworkList">예술품</a>
					<div class="header_gnb_list_containner">
						<div>
							<a href="museum.do?command=artworkList">전체</a> <a href="museum.do?command=noticeList">회화</a> <a href="museum.do?command=galleryList">드로잉</a> <a href="museum.do?command=qnaList">판화</a> <a href="museum.do?command=myPage">조각/설치</a> <a href="museum.do?command=myPage">사진</a> <a href="museum.do?command=myPage">공예</a> <a href="museum.do?command=myPage">디자인</a> <a href="museum.do?command=myPage">서예</a>
						</div>
					</div>
					<a href="museum.do?command=noticeList">소식지</a> <a href="museum.do?command=galleryList">이용자 갤러리</a> <a href="museum.do?command=qnaList">고객센터</a> <a href="museum.do?command=myPage">마이페이지</a>
				</div>
				<div class="header_search">
					<div>
						<input type="text" placeholder="검색어를 입력하세요">
					</div>
					<div>
						<a class="btn_icon" href="#" title="검색"> <img src="images/header/btn_search_open.png" alt="검색">
						</a>
					</div>
				</div>
			</div>
			<div class="login_join_box">
				<c:choose>
					<c:when test="${empty loginUser}">
						<a href="museum.do?command=loginForm&returnUrl=${urlPath}">로그인</a>
						<a href="museum.do?command=joinForm&returnUrl=${urlPath}">회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="#">${loginUser.name}(${loginUser.id})</a>
						<c:if test="${isAdmin}">
							<a href="museum.do?command=admin">관리자 페이지</a>
						</c:if>
						<span onclick="ajax({command:'logout',returnUrl:'${urlPath}'})">로그아웃</span>
					</c:otherwise>
				</c:choose>
			</div>
			<%-- 			<div class="artwork sub-menu">
				<!-- request.setAttribute("kindList", ArtworkKind.values()); -->
				<c:forEach items="${ArtworkCategory.values()}" var="category">
					<a href="museum.do?command=artwork&category=${category.name()}">${category.name()}</a>
				</c:forEach>
			</div>
			<div class="notice sub-menu">
				<c:forEach items="${NoticeCategory.values()}" var="category">
					<a href="museum.do?command=notice&category=${category.name()}">${category.name()}</a>
				</c:forEach>
			</div>  --%>
		</nav>
	</header>
	<script>
		AOS.init({
			once : true
		});
		$(window).on('load', function() {
			AOS.refresh();
		});
	</script>
	<div class="content-wrap">