package com.target.trak.system.web.builders.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.target.trak.system.web.builders.PaginationBuilder;
import com.target.trak.system.web.views.Page;
import com.target.trak.system.web.views.PaginationBean;

public class PaginationBuilderImpl implements PaginationBuilder {

	private static final int NUMBER_OF_ROWS_SHOWN = 10;

	private static final int FIRST_PAGE = 0;

	private static final String ACTIVE_CSS = "active";

	private static final String DISABLED_CSS = "disabled";

	private Properties pagingProperties;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public PaginationBean buildPaginationBean(final int requestedPage, final int totalSize, final String paginationLink) {
		PaginationBean pagingBean = new PaginationBean();
		int numberOfPages = getTotalNumberOfPages(totalSize);
		pagingBean.setTotalNumberPages(numberOfPages);

		if (numberOfPages > 1) {
			pagingBean.setCurrentPage(requestedPage);
			List<Page> pages = buildListOfPages(requestedPage, numberOfPages, totalSize, paginationLink);
			Collections.sort(pages);
			pagingBean.setPagesDisplayed(pages);

		} else {
			logger.info("There is only one page of data");
		}
		return pagingBean;
	}

	private List<Page> buildListOfPages(final int requestedPage, final int numberOfPages, final int totalSize, final String paginationLink) {
		List<Page> pages = new ArrayList<Page>();
		int pageIndex = 1;
		pages.add(buildPreviousPage(requestedPage, totalSize, paginationLink));

		int itemIndex = 2;
		Page page = null;
		while (pageIndex <= numberOfPages) {
			page = buildPage(requestedPage, pageIndex, totalSize, paginationLink);
			page.setOrder(itemIndex);
			pages.add(page);
			pageIndex++;
			itemIndex++;
		}

		Page nextPage = buildNextPage(requestedPage, totalSize, numberOfPages, paginationLink);
		nextPage.setOrder(itemIndex);
		pages.add(nextPage);
		return pages;
	}

	private Page buildPreviousPage(final int requestedPage, final int totalSize, final String paginationLink) {
		Page previousPage = new Page();
		previousPage.setPageText("<< Previous");
		previousPage.setOrder(1);
		previousPage.setPageNumber(0);
		if (requestedPage == FIRST_PAGE) {
			previousPage.setCssClass(DISABLED_CSS);
			previousPage.setLink("#");
		} else {
			previousPage.setCssClass("");
			previousPage.setLink(buildPaginationLink((requestedPage - 1), totalSize, paginationLink));
		}
		return previousPage;
	}

	private Page buildNextPage(final int requestedPage, final int totalSize, final int numberOfPages, final String paginationLink) {
		Page previousPage = new Page();
		previousPage.setPageText("Next >>");
		if (requestedPage == numberOfPages) {
			previousPage.setPageNumber(totalSize + 1);
			previousPage.setCssClass(DISABLED_CSS);
			previousPage.setLink("#");
		} else {
			previousPage.setPageNumber(requestedPage + 1);
			previousPage.setCssClass("");
			previousPage.setLink(buildPaginationLink((requestedPage + 1), totalSize, paginationLink));
		}
		return previousPage;
	}

	private Page buildPage(final int requestedPage, int pageNumber, final int totalSize, final String paginationLink) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page.setPageText(String.valueOf(pageNumber));
		page.setCssClass(requestedPage == pageNumber ? ACTIVE_CSS : "");
		page.setLink(buildPaginationLink(pageNumber, totalSize, paginationLink));
		return page;
	}

	private String buildPaginationLink(int page, int totalSize, final String paginationLink) {
		StringBuilder bldr = new StringBuilder();
		bldr.append("/target-trak/");
		bldr.append(paginationLink);
		bldr.append("?page=").append(page).append("&start=").append(getStartNumber(totalSize, page)).append("&end=").append(getEndNumber(totalSize, page));
		return bldr.toString();
	}

	private int getStartNumber(int totalSize, int pageRequested) {
		int start = FIRST_PAGE;
		if (pageRequested == FIRST_PAGE) {
			start = FIRST_PAGE;
		} else {
			start = ((pageRequested - 1) * NUMBER_OF_ROWS_SHOWN) + 1;
		}
		return start;
	}

	private int getEndNumber(int totalSize, int pageRequested) {
		// int start = getStartNumber(totalSize, pageRequested);
		// int end = (start + NUMBER_OF_ROWS_SHOWN) < totalSize ? ((start +
		// NUMBER_OF_ROWS_SHOWN) - 1) : totalSize;
		return NUMBER_OF_ROWS_SHOWN;
	}

	private int getTotalNumberOfPages(final int totalSize) {
		String rowsProperty = pagingProperties.getProperty("number.of.rows");
		int numRowsShown = rowsProperty == null ? NUMBER_OF_ROWS_SHOWN : Integer.parseInt(rowsProperty);
		int numberOfPages = ((int) totalSize / numRowsShown);

		if ((totalSize % numRowsShown) != 0) {
			numberOfPages++;
		}
		logger.info("Total Size: " + totalSize + " Num Rows Shown: " + numRowsShown + " Num Pages: " + numberOfPages);

		return numberOfPages;
	}

	public void setPagingProperties(Properties pagingProperties) {
		this.pagingProperties = pagingProperties;
	}
}