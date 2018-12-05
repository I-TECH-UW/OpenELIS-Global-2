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
*/
package us.mn.state.health.lims.reports.send.sample.valueholder.influenza;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 * @author diane benz 
 *  bugzilla 2393
 */
public class MessageXmit extends BaseObject {

	private SampleXmit sample;
	
	private MNPHLXmit mnphl;

	public SampleXmit getSample() {
		return sample;
	}

	public void setSample(SampleXmit sample) {
		this.sample = sample;
	}

	public MNPHLXmit getMnphl() {
		return mnphl;
	}

	public void setMnphl(MNPHLXmit mnphl) {
		this.mnphl = mnphl;
	}



}