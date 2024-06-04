<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
<jsp:param name="stylesheet" value="css/main.css"/>
<jsp:param name="script" value="script/jquery-3.7.1.min.js"/>
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
								dist = -1440 * imgNum;
								$('#main-center-imgs').animate({
									left : dist
								}, 1000);
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
						dist = -1440 * imgNum;
						$('#main-center-imgs').animate({
							left : dist
						}, 1000);
					}
					$('#main-center-remote div').removeClass('selected');
					$('#main-center-remote div').eq(imgNum)
							.addClass('selected');
				});
	});
</script>

<div id="main-center">
	<div id="main-center-imgs">
		<img src="main_images/main_image1.png"><img src="main_images/main_image2.jpg"><img src="main_images/main_image3.jpg"><img src="main_images/main_image4.jpg"><img src="main_images/main_image5.jpg"><img src="main_images/main_image6.jpg"><img src="main_images/main_image7.jpg"><img src="main_images/main_image8.jpg">
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









<%@ include file="footer.jsp"%>