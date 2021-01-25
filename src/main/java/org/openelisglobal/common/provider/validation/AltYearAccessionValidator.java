package org.openelisglobal.common.provider.validation;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.internationalization.MessageUtil;

public class AltYearAccessionValidator extends BaseSiteYearAccessionValidator implements IAccessionNumberGenerator {

    private static Set<String> defaultReservedNumbers = new HashSet<>();
    // used when you want to override the default in-memory reservation
    private Set<String> localReservedNumbers = new HashSet<>();
    private String startingAt;

    // input parameter is not used in this case
    @Override
    public String getNextAvailableAccessionNumber(String nullPrefix, boolean globallyReserve) {

        String nextAccessionNumber;

        String startingAt = getOverrideStartingAt();

        Set<String> reservedNumbers = defaultReservedNumbers;
        if (!GenericValidator.isBlankOrNull(startingAt)) {
            reservedNumbers = localReservedNumbers;
            nextAccessionNumber = startingAt;
        } else {
            String curLargestAccessionNumber = sampleService.getLargestAccessionNumberMatchingPattern(getPrefix(),
                    getMaxAccessionLength());
            if (curLargestAccessionNumber == null) {
                if (reservedNumbers.isEmpty()) {
                    nextAccessionNumber = createFirstAccessionNumber(null);
                } else {
                    nextAccessionNumber = reservedNumbers.iterator().next();
                }
            } else {
                nextAccessionNumber = incrementAccessionNumber(curLargestAccessionNumber);
            }
        }

        while (reservedNumbers.contains(nextAccessionNumber)) {
            nextAccessionNumber = incrementAccessionNumber(nextAccessionNumber);
        }

        if (globallyReserve) {
            reservedNumbers.add(nextAccessionNumber);
            if (!GenericValidator.isBlankOrNull(startingAt)) {
                defaultReservedNumbers.add(nextAccessionNumber);
            }
        }

        return nextAccessionNumber;
    }

    @Override
    public String getInvalidMessage(ValidationResults results) {
        String suggestedAccessionNumber = getNextAvailableAccessionNumber(null, false);

        return MessageUtil.getMessage("sample.entry.invalid.accession.number.suggestion") + " "
                + suggestedAccessionNumber;

    }

    @Override
    public int getInvarientLength() {
        return getSiteEndIndex();
    }

    @Override
    public String getPrefix() {
        return ConfigurationProperties.getInstance().getPropertyValue(Property.ALT_ACCESSION_PREFIX);
    }

    @Override
    protected int getIncrementStartIndex() {
        return getSiteEndIndex() + 2;
    }

    @Override
    protected int getYearEndIndex() {
        return getSiteEndIndex() + 2;
    }

    @Override
    protected int getYearStartIndex() {
        return getSiteEndIndex();
    }

    @Override
    protected int getSiteEndIndex() {
        return getPrefix().length();
    }

    @Override
    public int getMaxAccessionLength() {
        return getSiteEndIndex() + 15;
    }

    @Override
    public int getMinAccessionLength() {
        return getMaxAccessionLength();
    }

    @Override
    public int getChangeableLength() {
        return getMaxAccessionLength() - getInvarientLength();
    }

    @Override
    protected Set<String> getReservedNumbers() {
        return defaultReservedNumbers;
    }

    @Override
    public String getOverrideStartingAt() {
        return startingAt;
    }

    public void setOverrideStartingAt(String startingAt) {
        this.startingAt = startingAt;
    }

}
