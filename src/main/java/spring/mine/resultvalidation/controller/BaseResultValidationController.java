package spring.mine.resultvalidation.controller;

import org.apache.commons.validator.GenericValidator;

import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.util.StringUtil;

public abstract class BaseResultValidationController extends BaseController {

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
		return StringUtil.getMessageForKey("validation.title", messageKey);
	}

	protected void setRequestType(String section) {
		if (!GenericValidator.isBlankOrNull(section)) {
			titleKey = section;
		}
	}

}
