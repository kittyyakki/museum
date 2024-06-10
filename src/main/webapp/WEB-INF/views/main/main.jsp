<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<div class="notice-area">
		<div class="notice_content">
			<div class="notice_tabBtn">
				<button type="button" class="btn  selected" title="선택됨" onClick="location.href='museum.do?command=noticeList&category=공지사항'">공지사항</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=이벤트'">이벤트</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=매거진'">매거진</button>
				<button type="button" class="btn " onClick="location.href='museum.do?command=noticeList&category=신문'">신문</button>
			</div>
			<div class="notice_inner">
					<!-- <ul>
						<li> --><c:forEach items="${noticeList}" var="n">
								<div class="row">
									<div class="col col_title">
										<a href="museum.do?command=noticeView&nseq=${n.nseq}"> ${n.content} </a>
									</div>
								</div>
							</c:forEach><!-- </li>
					</ul> -->
				</div>
			</div>
		</div>
		<div class="notice_banner">
			<div class="swiper-container notice-swiper-container swiper-container-horizontal">
				<div class="swiper-wrapper" style="transform: translate3d(-1390px, 0px, 0px); transition-duration: 0ms;">
					<div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="3" style="width: 694px; margin-right: 1px;">
						<a alt="스마트폰 모바일 전시안내 무료 서비스
스마트폰 앱을 다운로드 후, 실행하시면
전시안내 정보를 제공받으실 수 있습니다." href="/site/main/content/exhibition_guide_link?tab=NMK_APP_INFO" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/650792.jpg);" tabindex="-1">스마트폰 전시안내 앱 서비스</a>
					</div>

					<div class="swiper-slide swiper-slide-prev" data-swiper-slide-index="0" style="width: 694px; margin-right: 1px;">
						<a alt="비상진료에 따른 병,의원 이용안내
경증일 때는 지역의 병, 의원으로 가셔야 합니다
상급종합병원에서 경증 환자는 지역 병,의원으로 재의뢰 되실 수 있습니다.
문 여는 의료기관 확인
응급의료포털(www.e-gen.or.kr)
복지부 시도 보건소, 건보공단, 심평원 홈페이지" href="https://www.e-gen.or.kr" target="_blank" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/1869687.png);" tabindex="-1">비상진료에 따른 병의원 이용안내</a>
					</div>

					<div class="swiper-slide swiper-slide-active" data-swiper-slide-index="1" style="width: 694px; margin-right: 1px;">
						<a alt="박물관이 더 쉽고 편해지는
모두를 위한 관람 안내
장애인, 고령자, 어린이를 비롯해 더욱 많은 분들이 박물관을 편하게 관람할 수 있도록 돋습니다." href="/site/main/content/accessibility" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/1762782.jpg);" tabindex="0">모두를 위한 관람 안내</a>
					</div>

					<div class="swiper-slide swiper-slide-next" data-swiper-slide-index="2" style="width: 694px; margin-right: 1px;">
						<a alt="Children's Museum
어린이박물관" href="/site/child/home" target="_blank" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/121.jpg);" tabindex="-1">어린이박물관 홈페이지 바로가기</a>
					</div>

					<div class="swiper-slide" data-swiper-slide-index="3" style="width: 694px; margin-right: 1px;">
						<a alt="스마트폰 모바일 전시안내 무료 서비스
스마트폰 앱을 다운로드 후, 실행하시면
전시안내 정보를 제공받으실 수 있습니다." href="/site/main/content/exhibition_guide_link?tab=NMK_APP_INFO" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/650792.jpg);" tabindex="-1">스마트폰 전시안내 앱 서비스</a>
					</div>

					<div class="swiper-slide swiper-slide-duplicate swiper-slide-duplicate-prev" data-swiper-slide-index="0" style="width: 694px; margin-right: 1px;">
						<a alt="비상진료에 따른 병,의원 이용안내
경증일 때는 지역의 병, 의원으로 가셔야 합니다
상급종합병원에서 경증 환자는 지역 병,의원으로 재의뢰 되실 수 있습니다.
문 여는 의료기관 확인
응급의료포털(www.e-gen.or.kr)
복지부 시도 보건소, 건보공단, 심평원 홈페이지" href="https://www.e-gen.or.kr" target="_blank" class="access m_off" style="background-image: url(/uploadfile/ecms/banner/1869687.png);" tabindex="-1">비상진료에 따른 병의원 이용안내</a>
					</div>
				</div>
				<div class="notice-pagination">
					<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
						<a href="javascript:void(0);" class="swiper-pagination-bullet">1</a><a href="javascript:void(0);" class="swiper-pagination-bullet swiper-pagination-bullet-active">2</a><a href="javascript:void(0);" class="swiper-pagination-bullet">3</a><a href="javascript:void(0);" class="swiper-pagination-bullet">4</a>
					</div>
					<div class="controller">
						<a href="javascript:;" class="btn-pause">슬라이더 정지</a>
					</div>
				</div>
			</div>
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