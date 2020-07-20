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

    private String FIRST_NAME_REGEX;
    private String LAST_NAME_REGEX;
    private String FULL_NAME_REGEX;
    private String USERNAME_REGEX;

    private NameType nameType;

    SiteInformationService siteInformationService = SpringContext.getBean(SiteInformationService.class);

    private String escapeRegexChars(String regex) {
        // TODO Auto-generated method stub
        return regex;
    }

    @Override
    public void initialize(ValidName constraint) {
        nameType = constraint.nameType();

        String firstNameRegex = siteInformationService.getMatch("name", "firstNameCharset")
                .orElse(new SiteInformation()).getValue();
        firstNameRegex = escapeRegexChars(firstNameRegex);
        FIRST_NAME_REGEX = "(?i)^[" + firstNameRegex + "]*$";

        String lastNameRegex = siteInformationService.getMatch("name", "lastNameCharset").orElse(new SiteInformation())
                .getValue();
        lastNameRegex = escapeRegexChars(lastNameRegex);
        LAST_NAME_REGEX = "(?i)^[" + lastNameRegex + "]*$";

        FULL_NAME_REGEX = "(?i)^[" + firstNameRegex + "]*([ ]*[" + lastNameRegex + "])?$";

        String usernameRegex = siteInformationService.getMatch("name", "userNameCharset").orElse(new SiteInformation())
                .getValue();
        usernameRegex = escapeRegexChars(usernameRegex);
        USERNAME_REGEX = "(?i)^[" + usernameRegex + "]*$";

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (org.apache.commons.validator.GenericValidator.isBlankOrNull(value)) {
            return true;
        }

        switch (nameType) {
        case FIRST_NAME:
            return value.matches(FIRST_NAME_REGEX);
        case LAST_NAME:
            return "UNKNOWN_".equals(value) || value.matches(LAST_NAME_REGEX);
        case FULL_NAME:
            return value.matches(FULL_NAME_REGEX);
        case USERNAME:
            return value.matches(USERNAME_REGEX);

        }
        if (nameType == NameType.FIRST_NAME) {
        } else if (nameType == NameType.LAST_NAME) {
            return value.matches(LAST_NAME_REGEX);
        } else if (nameType == NameType.FULL_NAME) {
            return value.matches(FULL_NAME_REGEX);
        }

        return false;

    }
}