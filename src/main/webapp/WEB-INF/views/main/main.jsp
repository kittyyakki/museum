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
				<button type="button" class="btn  selected" title="선택됨" onclick="openCity(event, 'tab_1', '알림')">알림</button>
				<button type="button" class="btn " onclick="openCity(event, 'tab_2', '고시/공고')">고시/공고</button>
				<button type="button" class="btn " onclick="openCity(event, 'tab_3', '채용 안내')">채용 안내</button>
				<button type="button" class="btn " onclick="openCity(event, 'tab_4', '보도자료')">보도자료</button>
				<h4 style="display: inline;">알림</h4>
				<a href="/site/main/archive/united/category/128" class="tab_1 more_txt"><i class="more">알림 더보기</i></a> <a href="/site/main/announce/list" class="tab_2 more_txt" style="display: none"><i class="more">고시/공고 더보기</i></a> <a href="/site/main/archive/post/category/category_54" class="tab_3 more_txt" style="display: none"><i class="more">채용 안내 더보기</i></a> <a href="/site/main/archive/post/category/category_93" class="tab_4 more_txt" style="display: none"><i class="more">보도자료 더보기</i></a>
			</div>
			<div class="notice_inner">
				<div class="notice_tab" id="tab_1" style="display: block;" title="알림 탭">
					<ul>


						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21118">구석기실, 신석기실, 청동기ㆍ고조선실, 부여ㆍ삼한실, 고구려실 휴실 알림(7.1.~12.16.)</a>
							</div> <span>2024.06.03</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21130">2024년 예비 자원봉사자 합격자 공고</a>
							</div> <span>2024.06.07</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21124">대표 홈페이지 소장품 검색 기능 제한 안내</a>
							</div> <span>2024.06.04</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21119">분실물 정보 안내</a>
							</div> <span>2024.06.03</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21117">국립중앙박물관 전시안내 앱 오류찾기 이벤트 결과 발표</a>
							</div> <span>2024.06.03</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/united/21114">6월 도서관 휴관일 안내</a>
							</div> <span>2024.05.31</span>
						</li>


					</ul>
				</div>
				<div class="notice_tab" id="tab_2" style="display: none;" title="고시/공고 탭">
					<ul>


						<li>
							<div class="tit">
								<a href="/site/main/announce/view/2026439">2024년도 문화체육관광부 청년인턴 채용(2차) 국립공주박물관 최종 합격자 공고</a>
							</div> <span>2024.05.23</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/announce/view/1967907">2024년 상반기 학예사 자격증 및 경력인정대상기관 심의 결과</a>
							</div> <span>2024.04.19</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/announce/view/1917088">국립중앙박물관 특별전 도록 『동아시아의 칠기(가제)』 위탁 출판 공고</a>
							</div> <span>2024.03.21</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/announce/view/1903107">국립중앙박물관 특별전 도록 『북미 원주민의 역사와 문화(가제)』 위탁 출판 공고</a>
							</div> <span>2024.03.12</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/announce/view/1797367">2024년 박물관ㆍ미술관 학예사 자격증 및 경력인정대상기관 심사 일정 공고</a>
							</div> <span>2024.01.15</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/announce/view/1797366">2024년 상반기 박물관ㆍ미술관 학예사 자격증 및 경력인정대상기관 심사계획 공고</a>
							</div> <span>2024.01.15</span>
						</li>

					</ul>
				</div>
				<div class="notice_tab" id="tab_3" style="display: none;" title="채용 안내 탭">
					<ul>


						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21138">국립광주박물관 공무직 근로자(기계) 서류전형 합격자 및 면접시험 공고</a>
							</div> <span>2024.06.10</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21132">국립청주박물관 공무직(미화) 채용 공고</a>
							</div> <span>2024.06.07</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21131">2024년 국립대구박물관 학예연구실 공무직(소장품 DB 구축 및 공개 활용사업) 근로자 채용 공고</a>
							</div> <span>2024.06.07</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21113">국립중앙박물관 미래전략담당관 공무직 근로자 서류전형 합격자 및 면접시험 안내</a>
							</div> <span>2024.05.31</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21110">국립중앙박물관 유물관리부 기간제근로자(학예보조_휴직대체인력) 채용 공고</a>
							</div> <span>2024.05.29</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21106">국립청주박물관 공무직(기계) 채용 최종합격자 공고</a>
							</div> <span>2024.05.28</span>
						</li>

					</ul>
				</div>
				<div class="notice_tab" id="tab_4" style="display: none;" title="보도자료 탭">
					<ul>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21136">[국립김해박물관] 《로컬 뮤직 콘서트 '올옷'》 개최</a>
							</div> <span>2024.06.10</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21129">[국리대구박물관] 국립대구박물관, 대구대학교와 패션컬렉션 행사 개최</a>
							</div> <span>2024.06.05</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21128">[국립광주박물관]국립광주박물관과 호남고고학회 업무협약 체결</a>
							</div> <span>2024.06.05</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21127">[국립김해박물관] 가야加耶와 남조南朝 - 해상왕국 가야의 새로운 도약</a>
							</div> <span>2024.06.05</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21126">[국립제주박물관] 어느 수집가의 초대-고 이건희 회장 기증 국립제주박물관 특별전 개최</a>
							</div> <span>2024.06.05</span>
						</li>

						<li>
							<div class="tit">
								<a href="/site/main/archive/post/article_21125">[국립전주박물관] 국립전주박물관 「도깨비가 준 선물」 어린이 공연</a>
							</div> <span>2024.06.05</span>
						</li>

					</ul>
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