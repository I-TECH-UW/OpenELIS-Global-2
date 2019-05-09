package spring.service.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;

@Service
public class CronSchedulerServiceImpl extends BaseObjectServiceImpl<CronScheduler> implements CronSchedulerService {
  @Autowired
  protected CronSchedulerDAO baseObjectDAO;

  CronSchedulerServiceImpl() {
    super(CronScheduler.class);
  }

  @Override
  protected CronSchedulerDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
