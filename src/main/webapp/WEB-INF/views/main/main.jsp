<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/header.jsp">
	<jsp:param name="stylesheet" value="static/stylesheet/main.css" />
	<jsp:param name="script" value="static/script/main.js" />
</jsp:include>
<!------------------------------------------- 메인 상단 옆으로 넘어가는 슬라이드 -------------------------------------------->
<div data-aos="fade-up" data-aos-offset="200" data-aos-delay="50" data-aos-duration="500" data-aos-easing="ease-in" id="main-center">
	<div class="main_inner_center">
		<div id="main-center-imgs">
			<img src="static/image/main/main_image1.png"> <img src="static/image/main/main_image2.jpg"> <img src="static/image/main/main_image3.jpg"> <img src="static/image/main/main_image4.jpg"> <img src="static/image/main/main_image5.jpg"> <img src="static/image/main/main_image6.jpg"> <img src="static/image/main/main_image7.jpg"> <img src="static/image/main/main_image8.jpg">
		</div>
	</div>
</div>
<div id="main-center-remote">
	<div class="selected"></div>
	<div></div>
	<div></div>
	<div></div>
	<div></div>
	<div></div>
	<div></div>
	<div></div>
	<div>▶</div>
</div>
<div class="main-section_text">
	<ul class="main-info-area">
		<li class="main_info"><strong class="main_info-tit">관람시간</strong>
			<div class="main_info-txt info-time">
				<ul>
					<li><strong>월/화/목/금/일</strong> <span>10:00 ~ 18:00&nbsp;&nbsp;&nbsp;</span></li>
					<li><strong>수/토</strong> <span>10:00 ~ 21:00</span></li>
					<li><span>&nbsp;&nbsp;&nbsp;* 입장 마감은 폐관30분 전까지</span></li>
				</ul>
			</div></li>
		<li class="main_admission"><strong class="main_info-tit01"> 관람료</strong>
			<div class="main_info-txt info-admission">
				<ul>
					<li><strong>무료</strong> <span>특별전시는 유료</span></li>
				</ul>
			</div></li>
	</ul>
	<div class="main_info info-link">
		<ul>
			<li><a href="#" class="main_btn btn-over">오시는길</a></li>
			<li><a href="#" class="main_btn btn-over">예약하기</a></li>
		</ul>
	</div>
</div>

<!-- -- 알립니다 -- -->
<div class="main_notice_wrap_alarm">
	<h3 class="notice-tit">
		<i></i><a href="/site/main/archive/united/category/128">알립니다</a>
	</h3>
	<div class="main_notice_inner">
	<div class="notice-area">
		<div class="notice_content">
			<div class="notice_tabBtn">
				<button type="button" class="btn  selected" title="선택됨" onClick="location.href='museum.do?command=noticeList&category=공지사항'">공지사항</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=이벤트'">이벤트</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=매거진'">매거진</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=신문'">신문</button>
			</div>
			<div class="notice_inner">
				<ul>

					<c:forEach items="${noticeList}" var="n">
						<div class="main_notice_inner_row">
							<div class="col_col_title">
								<a href="museum.do?command=noticeView&nseq=${n.nseq}"> ${n.content} </a>
							</div>
							<div class="col_col_date">
								<fmt:formatDate value="${n.writedate}" pattern="yyyy-MM-dd" />
							</div>
						</div>
					</c:forEach>

				</ul>
			</div>
		</div>
	</div>
	<div data-aos="fade-up" data-aos-offset="200" data-aos-delay="50" data-aos-duration="500" data-aos-easing="ease-in" id="main-center_slide02">
	<div class="main_inner_center02">
		<div id="main-center-imgs02">
			<img src="static/image/main/side_img01.jpg"> 
			<img src="static/image/main/side_img02.jpg"> 
			<img src="static/image/main/side_img03.jpg"> 
			<img src="static/image/main/side_img04.jpg"> 
		</div>
	</div>
</div>
<div id="main-center-remote02">
	<div class="selected"></div>
	<div></div>
	<div></div>
	<div></div>
	<div>▶</div>
</div>
	</div>
	</div>
<!----------------------------------------- 좌우로 이동하는 슬라이드 --------------------------------------->
<div data-aos="fade-up" data-aos-offset="200" data-aos-delay="50" data-aos-duration="500" data-aos-easing="ease-in" class="main-middle-container">
	<div class="container-box">
		<div class="image-list" id="container1">
			<div class="imagelist">
				<img src="static/image/main/main_image1.png">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image2.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image3.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image4.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image5.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image6.jpg">
			</div>
		</div>
		<div class="image-list" id="container2">
			<div class="imagelist">
				<img src="static/image/main/main_image1.png">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image2.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image3.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image4.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image5.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image6.jpg">
			</div>
		</div>
	</div>
	<div class="container-box">
		<div class="image-list" id="container3">
			<div class="imagelist">
				<img src="static/image/main/main_image1.png">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image2.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image3.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image4.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image5.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image6.jpg">
			</div>
		</div>
		<div class="image-list" id="container4">
			<div class="imagelist">
				<img src="static/image/main/main_image1.png">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image2.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image3.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image4.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image5.jpg">
			</div>
			<div class="imagelist">
				<img src="static/image/main/main_image6.jpg">
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />