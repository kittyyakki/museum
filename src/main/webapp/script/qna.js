function onReplySubmit() {
	if (confirm("답변을 등록하시겠습니까?")) {
		var form = document.getElementById("qnaReplyForm");
		form.submit();
	}
}

function qnaPwdCheck(qseq) {
	var form = document.getElementById("qnaPwdCheckForm");
	form.qseq.value = qseq;
	form.pwd.value = '';
	form.submit();
}

function handleQnaPwdCheckResult(result, qseq) {
	switch (result) {
		case 'success':
			document.location.href = "museum.do?command=qnaView&qseq=" + qseq;
			return;

		case 'notFound':
			alert("잘못된 접근입니다.");
			return;

		case 'requestPwd':
			var form = document.getElementById("qnaPwdCheckForm");
			var pwd = self.prompt(qseq + "번 QnA 글의 비밀번호를 입력하세요:");
			if (pwd !== null && pwd !== undefined && pwd !== "") {
				form.qseq.value = qseq;
				form.pwd.value = pwd;
				form.submit();
			} else {
				self.alert("비밀번호 입력이 취소되었습니다.");
			}
			return;

		case 'pwdWrong':
			alert("잘못된 비밀번호입니다. 다시 입력해주세요.");
			return;
	}
}