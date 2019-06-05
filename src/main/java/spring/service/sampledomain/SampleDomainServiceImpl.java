package spring.service.sampledomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampledomain.dao.SampleDomainDAO;
import us.mn.state.health.lims.sampledomain.valueholder.SampleDomain;

@Service
public class SampleDomainServiceImpl extends BaseObjectServiceImpl<SampleDomain, String>
		implements SampleDomainService {
	@Autowired
	protected SampleDomainDAO baseObjectDAO;

	SampleDomainServiceImpl() {
		super(SampleDomain.class);
	}

	@Override
	protected SampleDomainDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
