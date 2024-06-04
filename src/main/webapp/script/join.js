function go_save(){
	if ( document.joinForm.id.value == "") {
		alert("아이디를 입력하여 주세요."); 
		document.joinForm.id.focus();
	}else if( document.joinForm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");	    
	    document.joinForm.pwd.focus();
	} else if( document.joinForm.pwd.value != document.joinForm.pwdCheck.value) {
	    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");	    
	    document.joinForm.pwd.focus();
	} else if( document.joinForm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.joinForm.name.focus();
	} else if( document.joinForm.phone.value == "") {
	    alert("전화번호를 입력해 주세요.");	   
	    document.joinForm.phone.focus();
	}else if( document.joinForm.email.value == "") {
	    alert("이메일을 입력해 주세요.");	   
	    document.joinForm.email.focus();
	} else{
	    document.joinForm.submit();
	}
	
	}
	
function idcheck(){
	if( document.joinForm.id.value==""){
		alert("아이디를 입력하고 중복체크를 진행하세요" );
		documnet.joinForm.id.focus();
		return;
	}
	var url = "museum.do?command=idcheckForm&id=" + document.joinForm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	
	window.open(url, "idcheck", opt);
}
	
function idok( id ){
	opener.joinForm.id.value = id;
	opener.joinForm.reid.value =id;
	self.close();
}	

function go_editMember(){
	if( document.joinForm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");	    
	    document.joinForm.pwd.focus();
	} else if( document.joinForm.pwd.value != document.joinForm.pwdCheck.value) {
	    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");	    
	    document.joinForm.pwd.focus();
	} else if( document.joinForm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.joinForm.name.focus();
	} else if( document.joinForm.phone.value == "") {
	    alert("전화번호를 입력해 주세요.");	   
	    document.joinForm.phone.focus();
	}else if( document.joinForm.email.value == "") {
	    alert("이메일을 입력해 주세요.");	   
	    document.joinForm.email.focus();
	} else{
	    alert("회원정보 수정을 완료했습니다.");
	    document.joinForm.submit();
	}
	
}	

