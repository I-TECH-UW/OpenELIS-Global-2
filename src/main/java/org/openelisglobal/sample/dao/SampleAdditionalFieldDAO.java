package org.openelisglobal.sample.dao;

import java.util.List;
import java.util.Optional;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.sample.valueholder.SampleAdditionalField;
import org.openelisglobal.sample.valueholder.SampleAdditionalField.SampleAdditionalFieldId;

public interface SampleAdditionalFieldDAO extends BaseDAO<SampleAdditionalField, SampleAdditionalFieldId> {

    List<SampleAdditionalField> getAllForSample(String sampleId);

    Optional<SampleAdditionalField> getFieldForSample(String fieldName, String sampleId);

}
