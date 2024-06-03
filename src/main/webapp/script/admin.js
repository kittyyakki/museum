function checkAll() {
	// select-all-box의 체크 상태를 가져옵니다.
	const selectAllBox = document.querySelector('.select-all-box');
	const isChecked = selectAllBox.checked;

	// 모든 체크박스를 가져옵니다.
	const checkBoxes = document.querySelectorAll('.check-box');

	// 모든 체크박스의 체크 상태를 select-all-box의 상태와 일치시킵니다.
	checkBoxes.forEach(checkBox => {
		checkBox.checked = isChecked;
	});
}

function grantAdmin() {
	const selectedMembers = [];
	const checkBoxes = document.querySelectorAll('.check-box:checked');

	checkBoxes.forEach(checkBox => {
		const memberRow = checkBox.closest('ul');
		const memberId = memberRow.querySelector('li:nth-child(2)').textContent.trim();
		selectedMembers.push(memberId);
	});

	if (selectedMembers.length === 0) {
		alert('Please select at least one member to grant admin rights.');
		return;
	}

	// 선택된 회원 ID들을 숨겨진 input 필드에 저장합니다.
	const memberIdsInput = document.grantAdminForm.memberIds;
	memberIdsInput.value = selectedMembers.join(',');

	// 폼을 제출합니다.
	document.grantAdminForm.submit();
}