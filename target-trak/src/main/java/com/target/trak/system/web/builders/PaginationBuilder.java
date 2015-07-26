package com.target.trak.system.web.builders;

import com.target.trak.system.web.views.PaginationBean;

public interface PaginationBuilder {

	public static final int FIRST_PAGE = 1;
	
	public PaginationBean buildPaginationBean(final int requestedPage, final int totalSize, final String paginationLink);
}
