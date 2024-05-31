function noticeCheck(){
	if(  document.insertNotice.pass.value==""){
		alert("비밀번호를 입력하세요");
		document.insertNotice.pass.focus();
		return false;
	}else if(  document.insertNotice.email.value==""){
		alert("이메일을 입력하세요");
		document.insertNotice.email.focus();
		return false;
	}else if(  document.insertNotice.title.value==""){
		alert("제목을 입력하세요");
		document.insertNotice.title.focus();
		return false;
	}else if(  document.insertNotice.content.value==""){
		alert("내용을 입력하세요");
		document.insertNotice.content.focus();
		return false;
	}else{
		return true;
	}
	
}

function updateNoticeCheck( pass ){
	if(  document.insertNotice.pass.value==""){
		alert("비밀번호를 입력하세요");
		document.insertNotice.pass.focus();
		return false;
	}else if(  document.insertNotice.email.value==""){
		alert("이메일을 입력하세요");
		document.insertNotice.email.focus();
		return false;
	}else if(  document.insertNotice.title.value==""){
		alert("제목을 입력하세요");
		document.insertNotice.title.focus();
		return false;
	}else if(  document.insertNotice.content.value==""){
		alert("내용을 입력하세요");
		document.insertNotice.content.focus();
		return false;
	}else if(  document.insertNotice.pass.value!=pass){
		alert("비밀번호가 일치하지 않습니다");
		document.insertNotice.content.focus();
		return false;
	}else{
		return true;
	}
}



function deleteNotice(pass, num){
	var inputpass = prompt('삭제에 필요한 비밀번호를 입력하세요', '');
	if(pass != inputpass) {
		alert('비밀번호가 일치하지 않습니다');
		return;
	}else{
		location.href='museum.do?command=deleteNotice&num=' + num;
	}
}



function replyCheck(){
	if( document.reply.content.value==""){
		alert("댓글을 입력하세요");
		document.reply.content.focus();
		return false;
	}
	return true;
}
