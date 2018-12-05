/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.paging;

import java.io.Serializable;
import java.util.List;

import us.mn.state.health.lims.common.util.IdValuePair;


public class PagingBean implements Serializable{
	static final long serialVersionUID = 1L;
	private String totalPages;
	private String currentPage;
	private List<IdValuePair> searchTermToPage;

	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	public String getTotalPages() {
		return totalPages;
	}
	public void setSearchTermToPage(List<IdValuePair> searchTermToPage) {
		this.searchTermToPage = searchTermToPage;
	}
	public List<IdValuePair> getSearchTermToPage() {
		return searchTermToPage;
	}

}
