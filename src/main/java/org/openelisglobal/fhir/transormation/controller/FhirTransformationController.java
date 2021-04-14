package org.openelisglobal.fhir.transormation.controller;

import java.util.List;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FhirTransformationController extends BaseController {
    @Autowired
    private SampleService sampleService;
    @Autowired
    private FhirTransformService fhirTransformService;

    @PostMapping("/OEToFhir")
    public void transformPersistMissingFhirObjects(@RequestParam(defaultValue = "false") Boolean checkAll)
            throws FhirLocalPersistingException {
        List<Sample> samples;
        if (checkAll) {
            samples = sampleService.getAll();
        } else {
            samples = sampleService.getAllMissingFhirUuid();
        }
        for (Sample sample : samples) {
            fhirTransformService.transformPersistObjectsUnderSample(sample.getId());
        }
    }

    @Override
    protected String findLocalForward(String forward) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getPageTitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

}
