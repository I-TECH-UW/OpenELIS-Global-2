package org.openelisglobal.sample.service;

import javax.servlet.http.HttpServletRequest;
import org.openelisglobal.sample.form.SampleTbEntryForm;

public interface TbSampleService {
    boolean persistTbData(SampleTbEntryForm form, HttpServletRequest request);

    void getTBFormData(SampleTbEntryForm form);
}
