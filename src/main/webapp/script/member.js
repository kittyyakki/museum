function loginCheck(){
	if(document.loginForm.id.value==""){
		alert("아이디를 입력하세요");
		document.loginForm.id.focus();
		return false;
	}else if(document.loginForm.pwd.value==""){
		alert("패스워드를 입력하세요");
		document.loginForm.pwd.focus();
		return false;
	}else{
		return true;
	}
}
