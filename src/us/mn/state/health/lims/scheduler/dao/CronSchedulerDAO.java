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
package us.mn.state.health.lims.scheduler.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;

public interface CronSchedulerDAO extends BaseDAO {
	public List<CronScheduler> getAllCronSchedules() throws LIMSRuntimeException;
	public CronScheduler getCronScheduleByJobName(String jobName) throws LIMSRuntimeException;
	public void insert(CronScheduler cronScheduler) throws LIMSRuntimeException;
	public void update(CronScheduler cronScheduler) throws LIMSRuntimeException;
	public CronScheduler getCronScheduleById(String schedulerId) throws LIMSRuntimeException;
}
