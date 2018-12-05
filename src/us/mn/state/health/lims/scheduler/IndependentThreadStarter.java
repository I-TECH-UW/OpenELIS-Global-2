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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.scheduler;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.scheduler.independentthreads.MalariaResultExporter;
import us.mn.state.health.lims.scheduler.independentthreads.ResultExporter;
import us.mn.state.health.lims.scheduler.independentthreads.TestUsageBacklog;

public class IndependentThreadStarter {
	
	private ResultExporter resultExporter;
	private MalariaResultExporter malariaResultExporter;
	
	public void startThreads(){
		String reportInterval = ConfigurationProperties.getInstance().getPropertyValue(Property.resultsResendTime);
		if( !GenericValidator.isBlankOrNull(reportInterval)){
			Long period = 30L;
			
			try {
				period = Long.parseLong(reportInterval);	
			} catch (NumberFormatException e) {
				LogEvent.logError("IndependentThreadStarter","start", "Unable to parse " + reportInterval);
			}
			
			resultExporter = new ResultExporter(period);
			resultExporter.start();		
			malariaResultExporter = new MalariaResultExporter(period);
			malariaResultExporter.start();		
			new TestUsageBacklog().start();
		}

	}

	public void stopThreads(){
		if( resultExporter != null){
			resultExporter.stopExports();
		}
	}


}
