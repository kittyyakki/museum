function go_save(){
	let theForm = document.galleryWriteForm;
	if(theForm.title.value==""){
		alert("갤러리명을 입력하세요");
		theForm.title.focus();
	}else if(theForm.content.value==""){
		alert("갤러리 설명을 입력하세요");
		theForm.content.focus();
	}else if(theForm.image.value==""){
		alert("이미지를 첨부해주세요");
		theForm.image.focus();
	}else{
		theForm.submit();
	}
}

