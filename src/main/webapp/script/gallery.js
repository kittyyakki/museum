function go_save(){
	var theForm = document.galleryForm;
	if(theForm.gname.value==""){
		alert("갤러리명을 입력하세요");
		theForm.gname.focus();
	}else if(theForm.ginfo.value==""){
		alert("갤러리 설명을 입력하세요");
		theForm.ginfo.focus();
	}else if(theForm.image.value==""){
		alert("이미지를 첨부해주세요");
		theForm.image.focus();
	}else{
		theForm.action = "museum.do?command=galleryWrite";
		theForm.submit();
	}
}

