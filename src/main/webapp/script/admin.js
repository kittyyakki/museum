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

function selectCheckedList(url){
	const selectedMembers = [];
	const checkBoxes = document.querySelectorAll('.check-box:checked');

	checkBoxes.forEach(checkBox => {
		const memberRow = checkBox.closest('ul');
		const memberId = memberRow.querySelector('li:nth-child(2)>span:first-child').textContent.trim();
		selectedMembers.push(memberId);
	});
	
	if (selectedMembers.length === 0) {
		alert('한 개 이상 선택하세요');
		return;
	}

	// 선택된 회원 ID들을 숨겨진 input 필드에 저장합니다.
	const memberIdsInput = document.grantAdminForm.memberIds;
	memberIdsInput.value = selectedMembers.join(',');

	// 폼을 제출합니다.
	document.grantAdminForm.action = url;
	document.grantAdminForm.submit();
}

function adminRightsAction(act) {
	document.grantAdminForm.action.value = act;
	selectCheckedList("museum.do?command=grantAdminRights");
}

function deleteMember(){
	let ans = confirm("정말 삭제하시겠습니까?");
	if(ans){
		selectCheckedList("museum.do?command=adminDeleteMember");
	}else{
		return;
	}
}

function go_check(event) {
    // 클릭한 요소가 체크박스가 아닌 경우에만 체크박스를 체크/체크 해제
    if (!event.target.classList.contains('check-box') || !event.target.classList.contains('artwork-name')) {
        let checkbox = event.currentTarget.querySelector('.check-box');
        checkbox.checked = !checkbox.checked;
    }
}