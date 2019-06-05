package spring.service.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.label.dao.LabelDAO;
import us.mn.state.health.lims.label.valueholder.Label;

@Service
public class LabelServiceImpl extends BaseObjectServiceImpl<Label, String> implements LabelService {
	@Autowired
	protected LabelDAO baseObjectDAO;

	LabelServiceImpl() {
		super(Label.class);
	}

	@Override
	protected LabelDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
