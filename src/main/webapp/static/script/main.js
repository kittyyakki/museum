$(function() {
	var imgNum = 0;
	var timer;
	var state = false;
	$('#main-center-remote div').click(
		function() {
			var idx = $(this).index();
			var dist;
			if (idx == 8) { // 자동 버튼
				if (state == false) { // 자동동작 멈춤상태
					state = true;
					$('#main-center-remote div:eq(8)').html('||');
					timer = window.setInterval(function() {
						imgNum++;
						if (imgNum > 7)
							imgNum = 0;
						dist = -100 * imgNum; // 너비 비율을 100%로 설정
						$('#main-center-imgs').animate({
							left: dist + '%'
						}, 1000);
						$('#main-center-remote div').removeClass('selected');
						$('#main-center-remote div').eq(imgNum).addClass('selected');
					}, 3500);
				} else { // 자동동작 상태
					state = false;
					$('#main-center-remote div:eq(8)').html('▶');
					window.clearInterval(timer);
				}
			} else {
				imgNum = idx;
				dist = -100 * imgNum; // 너비 비율을 100%로 설정
				$('#main-center-imgs').animate({
					left: dist + '%'
				}, 1000);
			}
			$('#main-center-remote div').removeClass('selected');
			$('#main-center-remote div').eq(imgNum).addClass('selected');
		});
});


$(function() {
	var imgNum = 0;
	var timer;
	var state = false;
	$('#main-center-remote02 div').click(
		function() {
			var idx = $(this).index();
			var dist;
			if (idx == 4) { // 자동 버튼
				if (state == false) { // 자동동작 멈춤상태
					state = true;
					$('#main-center-remote02 div:eq(4)').html('||');
					timer = window.setInterval(function() {
						imgNum++;
						if (imgNum > 3)
							imgNum = 0;
						dist = -100 * imgNum; // 너비 비율을 100%로 설정
						$('#main-center-imgs02').animate({
							left: dist + '%'
						}, 1000);
						$('#main-center-remote02 div').removeClass('selected');
						$('#main-center-remote02 div').eq(imgNum).addClass('selected');
					}, 3500);
				} else { // 자동동작 상태
					state = false;
					$('#main-center-remote02 div:eq(4)').html('▶');
					window.clearInterval(timer);
				}
			} else {
				imgNum = idx;
				dist = -100 * imgNum; // 너비 비율을 100%로 설정
				$('#main-center-imgs02').animate({
					left: dist + '%'
				}, 1000);
			}
			$('#main-center-remote02 div').removeClass('selected');
			$('#main-center-remote02 div').eq(imgNum).addClass('selected');
		});
});



let container = document.querySelector('.image-list');
container.id = 'container1';

let clone = container.cloneNode(true)

clone.id = 'container2';

document.querySelector('.container-box').appendChild(clone);

document.querySelector('#container1').style.left = '0px';
document.querySelector('#container2').style.left = document.querySelector('.image-list').offsetWidth + 'px';


container.classList.add('original');
clone.classList.add('clone');



