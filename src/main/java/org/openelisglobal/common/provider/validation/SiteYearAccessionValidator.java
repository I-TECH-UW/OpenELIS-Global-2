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
package org.openelisglobal.common.provider.validation;

import java.util.HashSet;
import java.util.Set;

import org.openelisglobal.common.util.ConfigurationProperties;

public class SiteYearAccessionValidator extends BaseSiteYearAccessionValidator implements IAccessionNumberGenerator {

    private static Set<String> REQUESTED_NUMBERS = new HashSet<>();

    @Override
    public int getMaxAccessionLength() {
        return getSiteEndIndex() + 15;
    }

    @Override
    public int getMinAccessionLength() {
//      return getSiteEndIndex() + 7;
        return getSiteEndIndex() + 15;
    }

    @Override
    protected int getIncrementStartIndex() {
        return getSiteEndIndex() + 2;
    }

    @Override
    protected int getSiteEndIndex() {
        return getPrefix().length();
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
    public int getInvarientLength() {
        return getSiteEndIndex();
    }

    @Override
    public int getChangeableLength() {
        return getMaxAccessionLength() - getInvarientLength();
    }

    @Override
    public String getPrefix() {
        return ConfigurationProperties.getInstance()
                .getPropertyValue(ConfigurationProperties.Property.ACCESSION_NUMBER_PREFIX);
    }

    @Override
    protected Set<String> getReservedNumbers() {
        return REQUESTED_NUMBERS;
    }

    @Override
    protected String getOverrideStartingAt() {
        return null;
    }

}
