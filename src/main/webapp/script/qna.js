function onReplySubmit() {
	if (confirm("답변을 등록하시겠습니까?")) {
		var form = document.getElementById("qnaReplyForm");
		form.submit();
	}
}

function qnaPwdCheck(qseq, mode) {
	function postQnaPwdCheck(qseq, mode, pwd) {
		const xhr = new XMLHttpRequest();
		xhr.open("POST", "museum.do?command=qnaPwdCheck", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (xhr.readyState !== 4 || xhr.status !== 200) {
				return;
			}

			var response = JSON.parse(xhr.responseText);
			switch (response.code) {
				case 'success':
					location.href = response.url;
					break;

				case 'pwd_invalid':
					alert("잘못된 비밀번호입니다. 다시 입력해주세요.");
				// 의도적으로 break를 생략. 비밀번호를 다시 입력 받기 위해 prompt를 호출합니다.
				case 'pwd_request':
					var pwd = prompt(qseq + "번 QnA 글의 비밀번호를 입력하세요:");
					if (pwd !== null && pwd !== undefined && pwd !== "") {
						postQnaPwdCheck(qseq, mode, pwd);
					} else {
						alert("비밀번호 입력이 취소되었습니다.");
					}
					break;

				case 'not_found':
				case 'mode_invalid':
					alert("잘못된 접근입니다.");
					break;
			}
		};
		xhr.send("qseq=" + qseq + "&mode=" + mode + (pwd ? "&pwd=" + pwd : ""));
	}

	postQnaPwdCheck(qseq, mode);
}