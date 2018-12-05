package us.mn.state.health.lims.common.util;

import org.apache.commons.validator.GenericValidator;

public class XMLUtil {

	public static void appendKeyValue(String key, String value, StringBuilder xml) {

		if (!GenericValidator.isBlankOrNull(value)) {
			value = value.trim();

			if (value.length() > 0) {
				xml.append("<");
				xml.append(key);
				xml.append(">");
				xml.append(value);
				xml.append("</");
				xml.append(key);
				xml.append(">");
			}
		}
	}
	
	public static void appendKeyValueAttribute(String key, String value, StringBuilder xml) {

		if (!GenericValidator.isBlankOrNull(value)) {
			value = value.trim();

			if (value.length() > 0) {
				xml.append(key);
				xml.append("=\"");
				xml.append(value);
				xml.append("\" ");
			}
		}
	}
}
