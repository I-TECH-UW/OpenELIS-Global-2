package spring.service.qaevent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.QaEventDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;

@Service
public class QaEventServiceImpl extends BaseObjectServiceImpl<QaEvent> implements QaEventService {
	@Autowired
	protected QaEventDAO baseObjectDAO;

	QaEventServiceImpl() {
		super(QaEvent.class);
	}

	@Override
	protected QaEventDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(QaEvent qaEvent) {
        getBaseObjectDAO().getData(qaEvent);

	}

	@Override
	public void deleteData(List qaEvents) {
        getBaseObjectDAO().deleteData(qaEvents);

	}

	@Override
	public void updateData(QaEvent qaEvent) {
        getBaseObjectDAO().updateData(qaEvent);

	}

	@Override
	public boolean insertData(QaEvent qaEvent) {
        return getBaseObjectDAO().insertData(qaEvent);
	}

	@Override
	public QaEvent getQaEventByName(QaEvent qaEvent) {
        return getBaseObjectDAO().getQaEventByName(qaEvent);
	}

	@Override
	public List getQaEvents(String filter) {
        return getBaseObjectDAO().getQaEvents(filter);
	}

	@Override
	public List getAllQaEvents() {
        return getBaseObjectDAO().getAllQaEvents();
	}

	@Override
	public Integer getTotalQaEventCount() {
        return getBaseObjectDAO().getTotalQaEventCount();
	}

	@Override
	public List getPageOfQaEvents(int startingRecNo) {
        return getBaseObjectDAO().getPageOfQaEvents(startingRecNo);
	}

	@Override
	public List getNextQaEventRecord(String id) {
        return getBaseObjectDAO().getNextQaEventRecord(id);
	}

	@Override
	public List getPreviousQaEventRecord(String id) {
        return getBaseObjectDAO().getPreviousQaEventRecord(id);
	}
}
