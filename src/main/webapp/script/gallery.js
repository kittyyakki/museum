function go_save() {
	let form = document.galleryWriteForm;
	if (form.title.value == "") {
		alert("갤러리명을 입력하세요");
		form.title.focus();
	} else if (form.content.value == "") {
		alert("갤러리 설명을 입력하세요");
		form.content.focus();
	} else if (form.image.value == "") {
		alert("이미지를 첨부해주세요");
		form.image.focus();
	} else {
		form.submit();
	}
}

function go_gallery() {
	let form = document.galleryList;
	if (form.id.value == "") {
		alert("로그인이 필요합니다 로그인 후 이용해주세요");
		form.id.focus();
	} else {
		form.action = 'museum.do?command=galleryWrite';
		form.submit();
	}
}