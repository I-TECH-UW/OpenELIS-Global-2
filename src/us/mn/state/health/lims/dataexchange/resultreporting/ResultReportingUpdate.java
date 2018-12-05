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
package us.mn.state.health.lims.dataexchange.resultreporting;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.valueholder.Result;

public class ResultReportingUpdate implements IResultUpdate {

	@Override
	public void transactionalUpdate(IResultSaveService resultService) throws LIMSRuntimeException {
		//no-op
	}

	@Override
	public void postTransactionalCommitUpdate(IResultSaveService resultService) {
		ResultReportingCollator collator = new ResultReportingCollator();
		List<Result> updatedResults = new ArrayList<Result>();
		
		for (ResultSet resultSet : resultService.getNewResults()) {
			boolean success = collator.addResult(resultSet.result, resultSet.patient, false, false);
			if( success){
				updatedResults.add(resultSet.result);
			}
		}
		for (ResultSet resultSet : resultService.getModifiedResults()) {
			boolean success = collator.addResult(resultSet.result, resultSet.patient, true, false);
			if( success){
				updatedResults.add(resultSet.result);
			}
		}

		ResultReportingTransfer transfer = new ResultReportingTransfer();
		transfer.sendResults(collator.getResultReport(), updatedResults, ConfigurationProperties.getInstance().getPropertyValue(Property.resultReportingURL));
	}

}
