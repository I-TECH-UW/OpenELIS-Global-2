package spring.mine.result.controller;

import org.apache.commons.validator.GenericValidator;

import spring.mine.common.controller.BaseController;
import spring.mine.internationalization.MessageUtil;

public abstract class LogbookResultsBaseController extends BaseController {

	private String titleKey = "";

	@Override
	protected String getPageTitleKey() {
		return titleKey;
	}

	@Override
	protected String getPageSubtitleKey() {
		return titleKey;
	}

	@Override
	protected String getMessageForKey(String messageKey) throws Exception {
		return MessageUtil.getMessage("results.title", messageKey);
	}

	protected void setRequestType(String section) {
		if (!GenericValidator.isBlankOrNull(section)) {
			titleKey = section;
		}
	}

}
