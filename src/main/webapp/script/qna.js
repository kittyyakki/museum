
function qnaPwdCheck(qseq) {
	window.open(
		"museum.do?command=qnaPwdCheck&qseq=" + qseq,
		"QnA 비밀번호 확인",
		"toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no"
	);
}

function onQnaPwdUndefined() {
	opener.alert("해당 QnA 글이 존재하지 않습니다.");
	self.close();
}

function onQnaPwdSuccess(qseq) {
	opener.open("museum.do?command=qnaView&qseq=" + qseq, "_self");
}

function onQnaPwdFailed(qseq) {
	opener.alert("비밀번호가 일치하지 않습니다.");
}
