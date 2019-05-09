package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;

@Service
public class DocumentTrackServiceImpl extends BaseObjectServiceImpl<DocumentTrack> implements DocumentTrackService {
  @Autowired
  protected DocumentTrackDAO baseObjectDAO;

  DocumentTrackServiceImpl() {
    super(DocumentTrack.class);
  }

  @Override
  protected DocumentTrackDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
