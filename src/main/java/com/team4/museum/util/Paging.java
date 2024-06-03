package com.team4.museum.util;

public class Paging {

	private int page = 1;
	private int totalCount;
	private int displayRow = 10;
	private int displayPage = 5;
	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int startNum;
	private int endNum;
	private int totalPage;

	private void calPaging() {
		endPage = ((int) (Math.ceil(page / (double) displayPage))) * displayPage;
		beginPage = endPage - (displayPage - 1);

		totalPage = (int) Math.ceil(totalCount / (double) displayRow);
		if (totalPage < endPage) {
			endPage = totalPage;
		}

		prev = page > 1;
		next = page < totalPage;

		startNum = (page - 1) * displayRow + 1;
		endNum = page * displayRow;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calPaging(); // 전체 레코드 갯수가 결정이 되면 나머지 멤버변수들이 계산됩니다
	}

	public int getDisplayRow() {
		return displayRow;
	}

	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}

	public int getDisplayPage() {
		return displayPage;
	}

	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int setTotalPage(int totalPage) {
		return totalPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

}
