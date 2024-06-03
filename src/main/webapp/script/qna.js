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
					location.href = response.data;
					break;

				case 'failure':
					alert(response.data);
					break;

				case 'pwd_request':
					var pwd = prompt(qseq + "번 QnA 글의 비밀번호를 입력하세요:");
					if (pwd !== null && pwd !== undefined && pwd !== "") {
						postQnaPwdCheck(qseq, mode, pwd);
					} else {
						alert("비밀번호 입력이 취소되었습니다.");
					}
					break;

				default:
					alert("알 수 없는 오류가 발생했습니다.");
					break;
			}
		};
		xhr.send("qseq=" + qseq + "&mode=" + mode + (pwd ? "&pwd=" + pwd : ""));
	}

	postQnaPwdCheck(qseq, mode);
}