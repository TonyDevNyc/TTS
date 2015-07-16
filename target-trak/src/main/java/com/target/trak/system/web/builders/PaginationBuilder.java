package com.target.trak.system.web.builders;

import com.target.trak.system.web.views.PaginationBean;

public interface PaginationBuilder {

	public PaginationBean buildPaginationBean(final int requestedPage, final int totalSize);
}
