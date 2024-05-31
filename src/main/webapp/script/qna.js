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
