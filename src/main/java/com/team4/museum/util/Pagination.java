package com.team4.museum.util;

import jakarta.servlet.http.HttpServletRequest;

public class Pagination {

	private int itemCount;
	private int itemsPerPage = 10;
	private int pageRange = 5;
	private int currentPage;
	private String urlTemplate = "museum.do?command=example&page=%d";

	/**
	 * 페이지네이션에 표시할 아이템의 총 갯수를 반환합니다.
	 * 
	 * @return 아이템의 총 갯수
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * 페이지네이션에 표시할 아이템의 총 갯수를 설정합니다.
	 * 
	 * @param itemCount 아이템의 총 갯수
	 * @return Pagination 객체
	 */
	public Pagination setItemCount(int itemCount) {
		this.itemCount = itemCount;
		return this;
	}

	/**
	 * 한 페이지당 표시할 아이템의 갯수를 반환합니다.
	 * 
	 * @return 아이템의 갯수
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * 한 페이지당 표시할 아이템의 갯수를 설정합니다.
	 * 
	 * @param itemsPerPage 아이템의 갯수
	 * @return Pagination 객체
	 */
	public Pagination setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
		return this;
	}

	/**
	 * 표시될 페이지 범위를 반환합니다.
	 * 
	 * @return 페이지 범위
	 */
	public int getPageRange() {
		return pageRange;
	}

	/**
	 * 표시될 페이지 범위를 설정합니다.
	 * 
	 * 이전/다음과 ...을 제외한 수입니다. e.g. 5일 때 [이전 1 ... 4 5 6 7 8 ... 99 다음]
	 * 
	 * @param pageRange 페이지 범위
	 * @return Pagination 객체
	 */
	public Pagination setPageRange(int pageRange) {
		this.pageRange = pageRange;
		return this;
	}

	/**
	 * 현재 페이지를 반환합니다.
	 * 
	 * @return 현재 페이지
	 */
	public int getCurrentPage() {
		return fitPage(currentPage);
	}

	/**
	 * 현재 페이지를 설정합니다.
	 * 
	 * @param currentPage 현재 페이지
	 * @return Pagination 객체
	 */
	public Pagination setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	/**
	 * 현재 페이지의 URL을 반환합니다.
	 * 
	 * @return 현재 페이지의 URL
	 */
	public String getUrl() {
		return getUrl(getCurrentPage());
	}

	/**
	 * 특정 페이지의 URL을 반환합니다. 현재 페이지일 경우 #을 반환합니다.
	 * 
	 * @param page 페이지 번호
	 * @return 페이지의 URL
	 */
	public String getUrl(int page) {
		return currentPage == page ? "#" : String.format(urlTemplate, page);
	}

	/**
	 * 페이지의 URL 템플릿을 설정합니다.
	 * 
	 * 
	 * @return Pagination 객체
	 */
	public Pagination setUrlTemplate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
		return this;
	}

	/**
	 * 범위를 벗어나지 않는 페이지 번호를 반환합니다.
	 * 
	 * @return 페이지 번호
	 */
	private int fitPage(int page) {
		return Math.min(getMaxPage(), Math.max(1, page));
	}

	/**
	 * 페이지의 총 갯수를 반환합니다.
	 * 
	 * @return 페이지의 총 갯수
	 */
	public int getMaxPage() {
		return (int) Math.ceil((double) itemCount / itemsPerPage);
	}

	/**
	 * SQL을 위한 개수 제한을 반환합니다.
	 * 
	 * @return 아이템 시작 인덱스
	 */
	public int getLimit() {
		return itemsPerPage;
	}

	/**
	 * SQL을 위한 오프셋을 반환합니다.
	 * 
	 * @return 아이템 오프셋
	 */
	public int getOffset() {
		return Math.max(0, getCurrentPage() - 1) * itemsPerPage;
	}

	/**
	 * 페이지 범위의 시작을 반환합니다.
	 * 
	 * (현재 페이지 - 페이지 범위 / 2) 혹은 (최대 페이지 - 페이지 범위 - 1) 중 작은 값을 반환합니다.
	 * 
	 * @return 페이지 범위의 시작
	 */
	public int getBegin() {
		return fitPage(Math.min(currentPage - pageRange / 2, getMaxPage() - pageRange - 1));
	}

	/**
	 * 페이지 범위의 끝을 반환합니다.
	 * 
	 * (현재 페이지 + 페이지 범위 / 2) 혹은 (페이지 범위 + 2) 중 큰 값을 반환합니다.
	 * 
	 * @return 페이지 범위의 끝
	 */
	public int getEnd() {
		return fitPage(Math.max(currentPage + pageRange / 2, pageRange + 2));
	}

	/**
	 * 페이지가 숨겨질 수 있는지 여부를 반환합니다.
	 * 
	 * 
	 * 
	 * @param page
	 * @return 숨겨질 수 있는 경우 true
	 */
	public boolean isHidable(int page) {
		if (getMaxPage() < pageRange + 2) { // 말 줄임표가 없는 경우 숨김 처리 불필요
			return false;
		}

		// 앞 말줄임표가 필요한 경우, 페이지 범위의 시작을 숨기고, 아니면 페이지 범위의 끝의 이전 페이지를 숨김
		// 뒷 말줄임표가 필요한 경우, 페이지 범위의 끝을 숨기고, 아니면 페이지 범위의 시작의 다음 페이지를 숨김
		// 결과적으로 총 2개의 페이지가 숨겨짐
		return page == (needPrevSkip() ? getBegin() : getEnd() - 1)
				|| page == (needNextSkip() ? getEnd() : getBegin() + 1);
	}

	/**
	 * 페이지 범위의 시작의 이전 페이지를 반환합니다.
	 * 
	 * @return 이전 페이지
	 */
	public int getPrevPage() {
		return fitPage(getBegin() - 1);
	}

	/**
	 * 페이지 범위의 끝의 다음 페이지를 반환합니다.
	 * 
	 * @return 다음 페이지
	 */
	public int getNextPage() {
		return fitPage(getEnd() + 1);
	}

	/**
	 * 첫 페이지(1) 이동 링크의 필요 여부를 반환합니다.
	 * 
	 * @return 페이지 범위의 시작이 1보다 큰 경우 true
	 */
	public boolean needFirstLink() {
		return getBegin() > 1;
	}

	/**
	 * 마지막 페이지 이동 링크의 필요 여부를 반환합니다.
	 * 
	 * @return 페이지 범위의 끝이 총 페이지보다 작은 경우 true
	 */
	public boolean needLastLink() {
		return getEnd() < getMaxPage();
	}

	/**
	 * 앞 말줄임표의 필요 여부를 반환합니다.
	 * 
	 * @return 페이지 범위의 시작이 2보다 큰 경우 true
	 */
	public boolean needPrevSkip() {
		return getBegin() > 2;
	}

	/**
	 * 뒷 말줄임표의 필요 여부를 반환합니다.
	 * 
	 * @return 페이지 범위의 끝이 총 (페이지 - 1)보다 작은 경우 true
	 */
	public boolean needNextSkip() {
		return getEnd() < getMaxPage() - 1;
	}

	/**
	 * 페이지네이션 정보를 요청에서 가져와서 Pagination 객체를 생성합니다.
	 * 
	 * @param request 요청
	 * @return Pagination 객체
	 */
	public static Pagination fromRequest(HttpServletRequest request) {
		int page = 1;

		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}

		return new Pagination().setCurrentPage(page);
	}

}
