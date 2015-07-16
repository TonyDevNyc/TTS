package com.target.trak.system.web.views;

import java.util.List;

public class PaginationBean {

	private List<Page> pagesDisplayed;
	private int currentPage;
	private int totalNumberPages;

	public List<Page> getPagesDisplayed() {
		return pagesDisplayed;
	}

	public void setPagesDisplayed(List<Page> pagesDisplayed) {
		this.pagesDisplayed = pagesDisplayed;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalNumberPages() {
		return totalNumberPages;
	}

	public void setTotalNumberPages(int totalNumberPages) {
		this.totalNumberPages = totalNumberPages;
	}
}
