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
package us.mn.state.health.lims.referral.dao;

import java.util.List;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;


public interface ReferralResultDAO{

	public boolean insertData(ReferralResult referralResult) throws LIMSRuntimeException;

	public ReferralResult getReferralResultById(String referralResultId) throws LIMSRuntimeException;

	public List<ReferralResult> getReferralResultsForReferral(String referralId) throws LIMSRuntimeException;

	public void updateData(ReferralResult referralResult) throws LIMSRuntimeException;

	public void deleteData(ReferralResult referralResult) throws LIMSRuntimeException;

	public List<ReferralResult> getReferralsByResultId(String resultId) throws LIMSRuntimeException;
}
