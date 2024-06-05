<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
	<jsp:param name="stylesheet" value="css/main.css" />
	<jsp:param name="script" value="script/jquery-3.7.1.min.js" />
</jsp:include>
<script type="text/javascript">
	$(function() {
		var imgNum = 0
		var timer;
		var state = false;
		$('#main-center-remote div').click(
				function() {
					var idx = $(this).index();
					var dist;
					if (idx == 8) { // 자동 버튼
						if (state == false) { // 자동동작 멈춤상태
							state = true;
							$('#main-center-remote div:eq(8)').html('■')
							timer = window.setInterval(function() {
								imgNum++;
								if (imgNum > 7)
									imgNum = 0;
								dist = -100 * imgNum;
								$('#main-center-imgs').css('left', dist + '%');
								$('#main-center-remote div').removeClass(
										'selected');
								$('#main-center-remote div').eq(imgNum)
										.addClass('selected');
							}, 3500);
						} else { // 자동동작 상태
							state = false;
							$('#main-center-remote div:eq(8)').html('▶');
							window.clearInterval(timer);
						}
					} else {
						imgNum = idx;
						dist = -100 * imgNum;
						$('#main-center-imgs').css('left', dist + '%');
					}
					$('#main-center-remote div').removeClass('selected');
					$('#main-center-remote div').eq(imgNum)
							.addClass('selected');
				});
	});
</script>

<div id="main-center">
	<div class="main_inner_center">
		<div id="main-center-imgs">
			<img src="main_images/main_image1.png"> <img src="main_images/main_image2.jpg"> <img src="main_images/main_image3.jpg"> <img src="main_images/main_image4.jpg"> <img src="main_images/main_image5.jpg"> <img src="main_images/main_image6.jpg"> <img src="main_images/main_image7.jpg"> <img src="main_images/main_image8.jpg">
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
	</div>
	</div>
	<div class="main-section_text">
			<ul class="main-info-area">
				<li class="main_info"><strong class="main_info-tit"><i></i> 관람시간</strong>
					<div class="main_info-txt info-time">
						<ul>
							<li><strong>월/화/목/금/일</strong> <span>10:00 ~ 18:00</span></li>
							<li><strong>수/토</strong> <span>10:00 ~ 21:00</span></li>
							<li><span>&nbsp;&nbsp;&nbsp;* 입장 마감은 폐관30분 전까지</span></li>
						</ul>
					</div></li>
				<br>
				<li class="main_admission"><strong class="main_info-tit01"><i></i>
						관람료</strong>
					<div class="main_info-txt info-admission">
						<ul>
							<li><strong>무료</strong> <span>특별전시는 유료</span></li>
						</ul>
					</div></li>
			</ul>

			<div class="main_info info-link">
				<ul>
					<li><a href="/site/main/content/subway" class="main_btn btn-over">오시는길</a>
					</li>
					<li><a href="/site/main/content/tour_reservation" class="main_btn btn-over">예약하기</a></li>
				</ul>
			</div>
		</div>




<%@ include file="footer.jsp"%>
