package org.openelisglobal.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.validation.annotations.ValidName;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    public enum NameType {
        USERNAME, FIRST_NAME, LAST_NAME, FULL_NAME
    }

    private static final SiteInformation DEFAULT_SITE_INFORATION = new SiteInformation();
    private static final String DEFAULT_REGEX = "0-9a-z .'_@-";

    private NameType nameType;

    SiteInformationService siteInformationService = SpringContext.getBean(SiteInformationService.class);

    private String escapeRegexChars(String regex) {
        // TODO Auto-generated method stub
        return regex;
    }

    @Override
    public void initialize(ValidName constraint) {
        nameType = constraint.nameType();
        DEFAULT_SITE_INFORATION.setValue(DEFAULT_REGEX);
    }

    private String getRegex(NameType nameType) {
        switch (nameType) {
        case FIRST_NAME:
            return "(?i)^[" + escapeRegexChars(siteInformationService.getMatch("name", "firstNameCharset")
                    .orElse(DEFAULT_SITE_INFORATION).getValue()) + "]*$";
        case LAST_NAME:
            return "(?i)^[" + escapeRegexChars(
                    siteInformationService.getMatch("name", "lastNameCharset").orElse(DEFAULT_SITE_INFORATION)
                            .getValue())
                    + "]*$";
        case FULL_NAME:
            return "(?i)^["
                    + escapeRegexChars(siteInformationService.getMatch("name", "firstNameCharset")
                            .orElse(new SiteInformation()).getValue())
                    + "]*([ ]*[" + escapeRegexChars(siteInformationService.getMatch("name", "lastNameCharset")
                            .orElse(DEFAULT_SITE_INFORATION).getValue())
                    + "])?$";
        case USERNAME:
            return "(?i)^[" + escapeRegexChars(
                    siteInformationService.getMatch("name", "userNameCharset").orElse(DEFAULT_SITE_INFORATION)
                            .getValue())
                    + "]*$";

        default:
            return null;
        }

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (org.apache.commons.validator.GenericValidator.isBlankOrNull(value)) {
            return true;
        }

        switch (nameType) {
        case LAST_NAME:
            return "UNKNOWN_".equals(value) || value.matches(getRegex(nameType));
        case FIRST_NAME:
        case FULL_NAME:
        case USERNAME:
            return value.matches(getRegex(nameType));

        }
        return false;

    }

}