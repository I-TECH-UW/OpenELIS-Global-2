package org.openelisglobal.result.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.ResultSaveService;
import org.openelisglobal.common.services.StatusService.OrderStatus;
import org.openelisglobal.common.services.registration.interfaces.IResultUpdate;
import org.openelisglobal.dataexchange.orderresult.OrderResponseWorker.Event;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.referral.service.ReferralResultService;
import org.openelisglobal.referral.service.ReferralService;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralResult;
import org.openelisglobal.result.action.util.ResultSet;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.testreflex.action.util.TestReflexBean;
import org.openelisglobal.testreflex.action.util.TestReflexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogbookPersistServiceImpl implements LogbookResultsPersistService {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private ResultSignatureService resultSigService;
    @Autowired
    private ResultInventoryService resultInventoryService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private ReferralService referralService;
    @Autowired
    private ReferralResultService referralResultService;

    @Override
    @Transactional
    public void persistDataSet(ResultsUpdateDataSet actionDataSet, List<IResultUpdate> updaters, String sysUserId) {
        for (Note note : actionDataSet.getNoteList()) {
            noteService.insert(note);
        }

        List<org.openelisglobal.result.valueholder.Result> checkResult = null;
        Analysis checkAnalysis = null;
        SampleItem checkSampleItem = null;
        Sample checkSample = null;
        for (ResultSet resultSet : actionDataSet.getNewResults()) {
            resultSet.result.setResultEvent(Event.PRELIMINARY_RESULT);
            resultSet.result.setFhirUuid(UUID.randomUUID());
            String resultId = resultService.insert(resultSet.result);
            
            checkAnalysis = resultSet.result.getAnalysis();
            checkSampleItem = checkAnalysis.getSampleItem();
            checkSample = checkSampleItem.getSample();
//            System.out.println(">>>: " + 
//                    checkAnalysis.getId() + " " + 
//                    checkSampleItem.getId() + " " +
//                    checkSample.getId() + " " +
//                    checkSample.getAccessionNumber());

            checkResult = resultService.getResultsForTestAndSample(checkSample.getId(), checkAnalysis.getTest().getId());
            if (checkResult.size() == 0 ) {
                resultService.insert(resultSet.result);
            } else {
                continue;
            }
            
            if (resultSet.signature != null) {
                resultSet.signature.setResultId(resultSet.result.getId());
                resultSigService.insert(resultSet.signature);
            }

            if (resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null) {
                resultSet.testKit.setResultId(resultSet.result.getId());
                resultInventoryService.insert(resultSet.testKit);
            }
            resultSet.result.setId(resultId);
        }

        for (Referral referral : actionDataSet.getSavableReferrals()) {
            if (referral != null) {
                saveReferralsWithRequiredObjects(referral, sysUserId);
            }
        }

        for (ResultSet resultSet : actionDataSet.getModifiedResults()) {
            resultSet.result.setResultEvent(Event.RESULT);
            resultService.update(resultSet.result);

            if (resultSet.signature != null) {
                resultSet.signature.setResultId(resultSet.result.getId());
                if (resultSet.alwaysInsertSignature) {
                    resultSigService.insert(resultSet.signature);
                } else {
                    resultSigService.update(resultSet.signature);
                }
            }

            if (resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null) {
                resultSet.testKit.setResultId(resultSet.result.getId());
                if (resultSet.testKit.getId() == null) {
                    resultInventoryService.insert(resultSet.testKit);
                } else {
                    resultInventoryService.update(resultSet.testKit);
                }
            }
        }

        for (Analysis analysis : actionDataSet.getModifiedAnalysis()) {
            analysisService.update(analysis);
        }

        ResultSaveService.removeDeletedResultsInTransaction(actionDataSet.getDeletableResults(), sysUserId);

        setTestReflexes(actionDataSet, sysUserId);

        setSampleStatus(actionDataSet, sysUserId);

        for (IResultUpdate updater : updaters) {
            updater.transactionalUpdate(actionDataSet);
        }
    }

    private void saveReferralsWithRequiredObjects(Referral referral, String sysUserId) {
        if (referral.getId() != null) {
            referralService.update(referral);
        } else {
            referralService.insert(referral);
            ReferralResult referralResult = new ReferralResult();
            referralResult.setReferralId(referral.getId());
            referralResult.setSysUserId(sysUserId);
            referralResultService.insert(referralResult);
        }
    }

    protected void setTestReflexes(ResultsUpdateDataSet actionDataSet, String sysUserId) {
        TestReflexUtil testReflexUtil = new TestReflexUtil();
        testReflexUtil.addNewTestsToDBForReflexTests(convertToTestReflexBeanList(actionDataSet.getNewResults()),
                sysUserId);
        testReflexUtil.updateModifiedReflexes(convertToTestReflexBeanList(actionDataSet.getModifiedResults()),
                sysUserId);
    }

    private List<TestReflexBean> convertToTestReflexBeanList(List<ResultSet> resultSetList) {
        List<TestReflexBean> reflexBeanList = new ArrayList<>();

        for (ResultSet resultSet : resultSetList) {
            TestReflexBean reflex = new TestReflexBean();
            reflex.setPatient(resultSet.patient);

            if (resultSet.triggersToSelectedReflexesMap.size() > 0 && resultSet.multipleResultsForAnalysis) {
                for (String trigger : resultSet.triggersToSelectedReflexesMap.keySet()) {
                    if (trigger.equals(resultSet.result.getValue())) {
                        HashMap<String, List<String>> reducedMap = new HashMap<>(1);
                        reducedMap.put(trigger, resultSet.triggersToSelectedReflexesMap.get(trigger));
                        reflex.setTriggersToSelectedReflexesMap(reducedMap);
                    }
                }
                if (reflex.getTriggersToSelectedReflexesMap() == null) {
                    reflex.setTriggersToSelectedReflexesMap(new HashMap<String, List<String>>());
                }
            } else {
                reflex.setTriggersToSelectedReflexesMap(resultSet.triggersToSelectedReflexesMap);
            }

            reflex.setResult(resultSet.result);
            reflex.setSample(resultSet.sample);
            reflexBeanList.add(reflex);
        }

        return reflexBeanList;
    }

    private void setSampleStatus(ResultsUpdateDataSet actionDataSet, String sysUserId) {
        Set<Sample> sampleSet = new HashSet<>();

        for (ResultSet resultSet : actionDataSet.getNewResults()) {
            sampleSet.add(resultSet.sample);
        }

        String sampleTestingStartedId = SpringContext.getBean(IStatusService.class).getStatusID(OrderStatus.Started);
        String sampleNonConformingId = SpringContext.getBean(IStatusService.class).getStatusID(OrderStatus.NonConforming_depricated);

        for (Sample sample : sampleSet) {
            if (!(sample.getStatusId().equals(sampleNonConformingId)
                    || sample.getStatusId().equals(sampleTestingStartedId))) {
                Sample newSample = sampleService.get(sample.getId());

                newSample.setStatusId(sampleTestingStartedId);
                newSample.setSysUserId(sysUserId);
                sampleService.update(newSample);
            }
        }
    }

}
