<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp">
<jsp:param name="stylesheet" value="NewFile.css"/>
<jsp:param name="script" value="script/jquery-3.7.1.min.js"/>
</jsp:include> 
<script type="text/javascript">
var imgNum = 0;
var timer;
var dist;
$(function(){
    $('#main-center').hover(
        function(){
            window.clearInterval(timer);
        },
        function(){ 
            timer = window.setInterval(function(){
                imgNum++;
                if(imgNum>8) imgNum=0;
                dist=-1440 * imgNum;
                $('#main-center-imgs').animate({left:dist}, 4000);
            });
        }
    );
},1);
</script>

<div id="main-center">
	<div id="main-center-imgs">
		<img src="main_images/main_image1.png"><img src="main_images/main_image2.jpg"><img src="main_images/main_image3.jpg"><img src="main_images/main_image4.jpg"><img src="main_images/main_image5.jpg"><img src="main_images/main_image6.jpg"><img src="main_images/main_image7.jpg"><img src="main_images/main_image8.jpg">
	</div>
</div>









<%@ include file="footer.jsp"%>