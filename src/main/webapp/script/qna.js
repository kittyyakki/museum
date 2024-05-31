function onReplySubmit() {
	if (confirm("답변을 등록하시겠습니까?")) {
		var form = document.getElementById("qnaReplyForm");
		form.submit();
	}
}

function qnaPwdCheck(qseq) {
	window.open(
		"museum.do?command=qnaPwdCheck&qseq=" + qseq,
		"qnaPwdCheck",
		"width=9999999, height=500, right=0, top=1, scrollbars=no, resizable=no"
	);
}

function onQnaPwdResult(result, qseq) {
	switch (result) {
		case 0: // RESULT_SUCCESS
			opener.document.location.href = "museum.do?command=qnaView&qseq=" + qseq;
			window.close();
			return;

		case 1: // RESULT_REQUEST_PWD
			var form = document.getElementById("qnaPwdCheckForm");
			var pwd = self.prompt("비밀번호를 입력하세요:");
			if (pwd !== null && pwd !== undefined && pwd !== "") {
				form.pwd.value = pwd;
				form.submit();
			} else {
				self.alert("비밀번호 입력이 취소되었습니다.");
				self.close();
			}
			return;

		case 2: // RESULT_NOT_FOUND
			alert("잘못된 접근입니다.");
			window.close();
			return;

		case 3: // RESULT_PWD_WRONG
			alert("잘못된 비밀번호입니다. 다시 입력해주세요.");
			window.close();
			return;

		default: // result가 null이거나, 0, 1, 2, 3이 아닌 경우
			alert("잘못된 접근입니다.");
			window.close();
	}
}