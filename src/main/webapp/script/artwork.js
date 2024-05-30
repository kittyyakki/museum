function go_search_artwork(){
	let inputText = document.searchForm.searchWord.value;
	if(inputText === ""){
		alert("검색어를 입력하세요");
		return false;
	}else{
		return true;
	}
}