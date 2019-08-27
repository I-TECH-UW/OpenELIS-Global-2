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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.common.provider.validation;

import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;

public class PasswordValidationFactory {

    private static final String MINN_SITE = "MINN";
    private static final String HAITI_SITE = "HAITI";
    private static final String CDI_SITE = "CDI";

    public static ILoginPasswordValidation getPasswordValidator() {

        String passwordRequirementSite = ConfigurationProperties.getInstance()
                .getPropertyValueUpperCase(Property.PasswordRequirments);
        ILoginPasswordValidation validator = new MinnPasswordValidation();

        if (MINN_SITE.equals(passwordRequirementSite)) {
            // no-op Minnesota is the default
        } else if (HAITI_SITE.equals(passwordRequirementSite)) {
            validator = new HaitiPasswordValidation();
        } else if (CDI_SITE.equals(passwordRequirementSite)) {
            validator = new CDIPasswordValidation();
        }

        return validator;
    }
}
