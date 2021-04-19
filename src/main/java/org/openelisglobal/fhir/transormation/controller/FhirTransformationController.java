package org.openelisglobal.fhir.transormation.controller;

import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FhirTransformationController extends BaseController {
    @Autowired
    private SampleService sampleService;
    @Autowired
    private FhirTransformService fhirTransformService;

    @GetMapping("/OEToFhir")
    public void transformPersistMissingFhirObjects(@RequestParam(defaultValue = "false") Boolean checkAll,
            @RequestParam(defaultValue = "100") int batchSize)
            throws FhirLocalPersistingException {
        List<Sample> samples;
        if (checkAll) {
            samples = sampleService.getAll();
        } else {
            samples = sampleService.getAllMissingFhirUuid();
        }
        LogEvent.logDebug(this.getClass().getName(), "transformPersistMissingFhirObjects",
                "samples to convert: " + samples.size());

        List<String> sampleIds = new ArrayList<>();
        for (int i = 0; i < samples.size(); ++i) {
            sampleIds.add(samples.get(i).getId());
            if (i % batchSize == batchSize - 1 || i + 1 == samples.size()) {
                LogEvent.logDebug(this.getClass().getName(), "",
                        "persisting batch " + (i - batchSize + 1) + "-" + i + " of " + samples.size());
                try {
                fhirTransformService.transformPersistObjectsUnderSamples(sampleIds);
                } catch (FhirPersistanceException e) {
                    LogEvent.logError(e);
                    LogEvent.logError(this.getClass().getName(), "transformPersistMissingFhirObjects",
                            "error persisting batch " + (i - batchSize + 1) + "-" + i);
                }
                sampleIds = new ArrayList<>();
            }
        }
        LogEvent.logDebug(this.getClass().getName(), "transformPersistMissingFhirObjects", "finished all batches");
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
