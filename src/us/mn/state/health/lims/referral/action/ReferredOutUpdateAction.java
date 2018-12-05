/** The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) CIRG, University of Washington, Seattle WA. All Rights
 * Reserved. */
package us.mn.state.health.lims.referral.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.referral.action.beanitems.IReferralResultTest;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;
import us.mn.state.health.lims.referral.action.beanitems.ReferredTest;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.daoimpl.ReferralDAOImpl;
import us.mn.state.health.lims.referral.daoimpl.ReferralResultDAOImpl;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class ReferredOutUpdateAction extends BaseAction {

  private List<ReferralSet> referralSetList;
  private List<ReferralResult> removableReferralResults;
  private List<ReferralItem> modifiedItems;
  private List<ReferralItem> canceledItems;
  private Set<Sample> parentSamples;
  private List<Sample> modifiedSamples;
  private ActionMessages errors;

  private final ReferralDAO referralDAO = new ReferralDAOImpl();
  private final ReferralResultDAO referralResultDAO = new ReferralResultDAOImpl();
  private final OrganizationDAO organizationDAO = new OrganizationDAOImpl();
  private final ResultDAO resultDAO = new ResultDAOImpl();
  private final SampleDAO sampleDAO = new SampleDAOImpl();
  private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
  private final NoteDAO noteDAO = new NoteDAOImpl();
  private final TestResultDAO testResultDAO = new TestResultDAOImpl();

  private static final String RESULT_SUBJECT = "Result Note";
  private TestDAO testDAO = new TestDAOImpl();
  private SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();

  @Override
  protected String getPageSubtitleKey() {
    return "referral.out.manage";
  }

  @Override
  protected String getPageTitleKey() {
    return "referral.out.manage";
  }

  @SuppressWarnings("unchecked")
  @Override
  protected ActionForward performAction(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response) throws Exception {

    parentSamples = new HashSet<Sample>();
    modifiedSamples = new ArrayList<Sample>();
    errors = new ActionMessages();
    request.getSession().setAttribute(SAVE_DISABLED, TRUE);

    DynaActionForm dynaForm = (DynaActionForm) form;

    List<ReferralItem> referralItems = (List<ReferralItem>) PropertyUtils.getProperty(dynaForm,
            "referralItems");
    selectModifiedAndCanceledItems(referralItems);

    validateModifedItems();

    if (errors.size() > 0) {
      saveErrors(request, errors);
      request.setAttribute(Globals.ERROR_KEY, errors);

      return mapping.findForward(IActionConstants.FWD_VALIDATION_ERROR);
    }

    try {
      createReferralSets();
    } catch (LIMSRuntimeException e) {
      saveErrors(request, errors);
      request.setAttribute(Globals.ERROR_KEY, errors);

      return mapping.findForward(IActionConstants.FWD_VALIDATION_ERROR);
    }

    Transaction tx = HibernateUtil.getSession().beginTransaction();

    try {
      for (ReferralSet referralSet : referralSetList) {
        referralDAO.updateData(referralSet.referral);

        for (ReferralResult referralResult : referralSet.updatableReferralResults) {
          Result result = referralResult.getResult();
          if (result != null) {
            if (result.getId() == null) {
              resultDAO.insertData(result);
            } else {
              result.setSysUserId(currentUserId);
              resultDAO.updateData(result);
            }
          }

          if (referralResult.getId() == null) {
            referralResultDAO.insertData(referralResult);
          } else {
            referralResultDAO.updateData(referralResult);
          }
        }

        if (referralSet.note != null) {
          if (referralSet.note.getId() == null) {
            noteDAO.insertData(referralSet.note);
          } else {
            noteDAO.updateData(referralSet.note);
          }
        }
      }

      for (ReferralResult referralResult : removableReferralResults) {

        referralResult.setSysUserId(currentUserId);
        referralResultDAO.deleteData(referralResult);

        if (referralResult.getResult() != null && referralResult.getResult().getId() != null) {
          referralResult.getResult().setSysUserId(currentUserId);
          resultDAO.deleteData(referralResult.getResult());
        }
      }

      setStatusOfParentSamples();

      for (Sample sample : modifiedSamples) {
        sampleDAO.updateData(sample);
      }

      tx.commit();

    } catch (LIMSRuntimeException lre) {
      tx.rollback();

      ActionError error;
      if (lre.getException() instanceof StaleObjectStateException) {
        error = new ActionError("errors.OptimisticLockException", null, null);
      } else {
        lre.printStackTrace();
        error = new ActionError("error.system", null, null);
      }

      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
      saveErrors(request, errors);
      request.setAttribute(Globals.ERROR_KEY, errors);
      request.setAttribute(ALLOW_EDITS_KEY, "false");
      return mapping.findForward(FWD_FAIL);

    } finally {
      HibernateUtil.closeSession();
    }

    return mapping.findForward(IActionConstants.FWD_SUCCESS);
  }

  private void selectModifiedAndCanceledItems(List<ReferralItem> referralItems) {
    modifiedItems = new ArrayList<ReferralItem>();
    canceledItems = new ArrayList<ReferralItem>();

    for (ReferralItem item : referralItems) {
      if (item.isCanceled()) {
        canceledItems.add(item);
      } else if (item.isModified()) {
        modifiedItems.add(item);
      }
    }
  }

  private void validateModifedItems() {
    for (ReferralItem referralItem : modifiedItems) {
      validateModifedItem(referralItem);
    }
  }

  private void validateModifedItem(ReferralItem referralItem) {
    // if an institution has not been entered then there may not be a test
    if (!institutionEntered(referralItem) && testEntered(referralItem)) {
      ActionError error = new ActionError("error.referral.missingInstitution",
              referralItem.getAccessionNumber(), null);
      errors.add(ActionErrors.GLOBAL_MESSAGE, error);
    }

    // if a test has not been entered then there can not be a result or
    // report date
    if (!testEntered(referralItem)
            && (reportDateEntered(referralItem) || resultEntered(referralItem))) {
      ActionError error = new ActionError("error.referral.missingTest",
              referralItem.getAccessionNumber(), null);
      errors.add(ActionErrors.GLOBAL_MESSAGE, error);
    }

  }

  private boolean institutionEntered(ReferralItem referralItem) {
    return !"0".equals(referralItem.getReferredInstituteId());
  }

  private boolean testEntered(IReferralResultTest resultTest) {
    return !"0".equals(resultTest.getReferredTestId());
  }

  private boolean reportDateEntered(IReferralResultTest resultTest) {
    return !GenericValidator.isBlankOrNull(resultTest.getReferredReportDate());
  }

  private boolean resultEntered(IReferralResultTest resultTest) {
    return !(GenericValidator.isBlankOrNull(resultTest.getReferredResult())
            && "0".equals(resultTest.getReferredDictionaryResult()));
  }

  private void createReferralSets() throws LIMSRuntimeException {
    referralSetList = new ArrayList<ReferralSet>();
    removableReferralResults = new ArrayList<ReferralResult>();

    for (ReferralItem item : canceledItems) {
      referralSetList.add(createCanceledReferralSet(item));
    }

    for (ReferralItem item : modifiedItems) {
      referralSetList.add(createModifiedSet(item));
    }
  }

  private ReferralSet createCanceledReferralSet(ReferralItem item) {
    ReferralSet referralSet = new ReferralSet();

    Referral referral = referralDAO.getReferralById(item.getReferralId());

    referralSet.referral = referral;
    referral.setSysUserId(currentUserId);
    referral.setCanceled(true);

    setStatusForCanceledReferrals(referral);

    return referralSet;
  }

  @SuppressWarnings("unchecked")
  private void setStatusForCanceledReferrals(Referral referral) {

    Analysis analysis = referral.getAnalysis();
    analysis.setReferredOut(false);
    Sample sample = analysis.getSampleItem().getSample();
    parentSamples.add(sample);

  }

  private ReferralSet createModifiedSet(ReferralItem referralItem) throws LIMSRuntimeException {
    // place all existing referral results in list
    ReferralSet referralSet = new ReferralSet();
    referralSet.setExistingReferralResults(
            referralResultDAO.getReferralResultsForReferral(referralItem.getReferralId()));

    Referral referral = referralDAO.getReferralById(referralItem.getReferralId());
    referral.setCanceled(false);
    referral.setSysUserId(currentUserId);
    referral.setOrganization(
            organizationDAO.getOrganizationById(referralItem.getReferredInstituteId()));
    referral.setSentDate(
            DateUtil.convertStringDateToTruncatedTimestamp(referralItem.getReferredSendDate()));
    referral.setRequesterName(referralItem.getReferrer());
    referral.setReferralReasonId(referralItem.getReferralReasonId());
    referralSet.referral = referral;

    referralSet.note = new NoteService(referral.getAnalysis()).createSavableNote(
            NoteService.NoteType.INTERNAL, referralItem.getNote(), RESULT_SUBJECT, currentUserId);

    createReferralResults(referralItem, referralSet);

    if (referralItem.getAdditionalTests() != null) {
      for (ReferredTest existingAdditionalTest : referralItem.getAdditionalTests()) {
        if (!existingAdditionalTest.isRemove()) {
          // nothing to do if isRemove, because on insert we reused what we could
          // then deleted all old referralResults (see below).
          // removableReferralResults.add(getRemovableReferralableResults(existingAdditionalTest));
          createReferralResults(existingAdditionalTest, referralSet);
        }
      }
    }

    List<ReferredTest> newAdditionalTests = getNewTests(referralItem.getAdditionalTestsXMLWad());

    for (ReferredTest newReferralTest : newAdditionalTests) {
      newReferralTest.setReferralId(referralItem.getReferralId());
      createReferralResults(newReferralTest, referralSet);
    }

    // any leftovers get deleted
    this.removableReferralResults.addAll(referralSet.getExistingReferralResults());

    return referralSet;
  }

  /**
   * Reuse any existing referrableResults, placing the submitted results in
   * them. Then any remaining referral results are removable.
   ***/
  private void createReferralResults(IReferralResultTest referralItem, ReferralSet referralSet) {
    if (referralItem.getReferredTestIdShadow() != null
            && !referralItem.getReferredTestId().equals(referralItem.getReferredTestIdShadow())) {
      referralSet.updateTest(referralItem.getReferredTestIdShadow(),
              referralItem.getReferredTestId(), currentUserId);
    } else {
      String referredResultType = getReferredResultType(referralItem, null);
      if (TypeOfTestResultService.ResultType.isMultiSelectVariant(referredResultType)) {
        if (!GenericValidator.isBlankOrNull(referralItem.getMultiSelectResultValues())
                && !"{}".equals(referralItem.getMultiSelectResultValues())) {
          JSONParser parser = new JSONParser();
          try {
            JSONObject jsonResult = (JSONObject) parser
                    .parse(referralItem.getMultiSelectResultValues());
            for (Object key : jsonResult.keySet()) {
              String[] ids = ((String) jsonResult.get(key)).trim().split(",");
              // This will populate a result for each item in the multiselect referral result
              for (String id : ids) {
                ReferralResult referralResult = referralSet.getNextReferralResult();
                referralItem.setReferredDictionaryResult(id);  // move particular multi result into
                                                               // (single) dictionary result.
                fillReferralResultResult(referralItem, referralResult,
                        Integer.parseInt((String) key));
              }
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
        } else {
          ReferralResult referralResult = referralSet.getNextReferralResult();
          fillReferralResultResult(referralItem, referralResult, 0);
        }
      } else {
        ReferralResult referralResult = referralSet.getNextReferralResult();
        fillReferralResultResult(referralItem, referralResult, 0);
      }
    }
  }

  private void fillReferralResultResult(IReferralResultTest referralItem,
          ReferralResult referralResult, int grouping) {
    referralResult.setSysUserId(currentUserId);

    setReferredResultReportDate(referralItem, referralResult);
    setReferredResultTestId(referralItem, referralResult);
    referralResult.setReferralId(referralItem.getReferralId());
    Result result = referralResult.getResult();

    if (result == null && !GenericValidator.isBlankOrNull(referralItem.getReferredResultType())) {
      result = new Result();
    }

    if (result != null) {
      setResultValuesForReferralResult(referralItem, result, grouping);
      referralResult.setResult(result);
    }

  }

  /**
   * If the referredTest.referredResultType is "M" the particular value to
   * translate into the result should already be loaded in
   * referredTest.referredDictionaryResult
   * 
   */
  private void setResultValuesForReferralResult(IReferralResultTest referredTest, Result result,
          int grouping) {
    result.setSysUserId(currentUserId);
    result.setSortOrder("0");

    Test test = testDAO.getTestById(referredTest.getReferredTestId());
    Sample sample = referralDAO.getReferralById(referredTest.getReferralId()).getAnalysis()
            .getSampleItem().getSample();
    Patient patient = sampleHumanDAO.getPatientForSample(sample);
    ResultLimit limit = new ResultLimitService().getResultLimitForTestAndPatient(test, patient);
    result.setMinNormal(limit != null ? limit.getLowNormal() : 0.0);
    result.setMaxNormal(limit != null ? limit.getHighNormal() : 0.0);
    result.setGrouping(grouping);

    String referredResultType = getReferredResultType(referredTest, test);
    result.setResultType(referredResultType);
    if (TypeOfTestResultService.ResultType.isDictionaryVariant(referredResultType)) {
      String dicResult = referredTest.getReferredDictionaryResult();
      if (!(GenericValidator.isBlankOrNull(dicResult) || "0".equals(dicResult))) {
        result.setValue(dicResult);
      }
    } else {
      result.setValue(referredTest.getReferredResult());
    }
  }

  private String getReferredResultType(IReferralResultTest referredTest, Test test) {
    // N.B. this should not be corrected here. It should be done on load
    /*
     * referredTest.getReferredResultType() is not always accurate alpha-numeric and
     * numeric are not differentiated
     */

    String referredResultType = referredTest.getReferredResultType();

    if (!TypeOfTestResultService.ResultType.isDictionaryVariant(referredResultType)
            && test != null) {
      @SuppressWarnings("unchecked")
      List<TestResult> testResults = testResultDAO.getAllActiveTestResultsPerTest(test);

      if (!testResults.isEmpty()) {
        referredResultType = testResults.get(0).getTestResultType();
      }
    }

    return referredResultType;
  }

  private void setReferredResultTestId(IReferralResultTest referralTest,
          ReferralResult referralResult) {
    if (!"0".equals(referralTest.getReferredTestId())) {
      referralResult.setTestId(referralTest.getReferredTestId());
    }
  }

  private void setReferredResultReportDate(IReferralResultTest referralTest,
          ReferralResult referralResult) throws LIMSRuntimeException {

    if (!GenericValidator.isBlankOrNull(referralTest.getReferredReportDate())) {
      try {
        referralResult.setReferralReportDate(DateUtil
                .convertStringDateToTruncatedTimestamp(referralTest.getReferredReportDate()));
      } catch (LIMSRuntimeException e) {
        ActionError error = new ActionError("errors.date", referralTest.getReferredReportDate(),
                null);
        errors.add(ActionErrors.GLOBAL_MESSAGE, error);
        throw e;
      }
    }
  }

  @SuppressWarnings("unchecked")
  private List<ReferredTest> getNewTests(String xml) {
    List<ReferredTest> newTestList = new ArrayList<ReferredTest>();

    if (GenericValidator.isBlankOrNull(xml)) {
      return newTestList;
    }

    try {
      Document testsDom = DocumentHelper.parseText(xml);

      for (Iterator<Element> i = testsDom.getRootElement().elementIterator("test"); i.hasNext();) {
        Element testItem = i.next();

        String testId = testItem.attribute("testId").getValue();

        ReferredTest referralTest = new ReferredTest();
        referralTest.setReferredTestId(testId);
        referralTest.setReferredResultType(new TestService(testId).getResultType());
        referralTest.setReferredResult("");
        referralTest.setReferredDictionaryResult("");
        referralTest.setReferredMultiDictionaryResult("");
        referralTest.setReferredReportDate("");

        newTestList.add(referralTest);
      }
    } catch (DocumentException e) {
      e.printStackTrace();
      throw new LIMSRuntimeException(e);
    }

    return newTestList;
  }

  private void setStatusOfParentSamples() {
    for (Sample sample : parentSamples) {
      List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

      String finalizedId = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
      boolean allAnalysisFinished = true;

      if (analysisList != null) {
        for (Analysis childAnalysis : analysisList) {
          Referral referral = referralDAO.getReferralByAnalysisId(childAnalysis.getId());
          List<ReferralResult> referralResultList;

          if (referral == null || referral.getId() == null) {
            referralResultList = new ArrayList<ReferralResult>();
          } else {
            referralResultList = referralResultDAO.getReferralResultsForReferral(referral.getId());
          }

          if (referralResultList.isEmpty()) {
            if (!finalizedId.equals(childAnalysis.getStatusId())) {
              allAnalysisFinished = false;
              break;
            }
          } else {
            for (ReferralResult referralResult : referralResultList) {
              if (referralResult.getResult() == null
                      || GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
                if (!(referral.isCanceled() && finalizedId.equals(childAnalysis.getStatusId()))) {
                  allAnalysisFinished = false;
                  break;
                }
              }
            }
          }
        }
      }

      if (allAnalysisFinished) {
        sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Finished));
        sample.setSysUserId(currentUserId);
        modifiedSamples.add(sample);
      }
    }
  }

  static class ReferralSet {
    Referral referral;
    Note note;
    List<ReferralResult> updatableReferralResults = new ArrayList<ReferralResult>();
    private List<ReferralResult> existingReferralResults = new ArrayList<ReferralResult>();

    public List<ReferralResult> getExistingReferralResults() {
      return existingReferralResults;
    }

    public void setExistingReferralResults(List<ReferralResult> existingReferralResults) {
      this.existingReferralResults = existingReferralResults;
    }

    ReferralResult getNextReferralResult() {
      ReferralResult referralResult = existingReferralResults.isEmpty() ? new ReferralResult()
              : existingReferralResults.remove(0);
      updatableReferralResults.add(referralResult);

      return referralResult;
    }

    public void updateTest(String oldTestId, String newTestId, String currentUserId) {
      ReferralResult updatedReferralResult = null;
      for (ReferralResult referralResult : existingReferralResults) {
        if (referralResult.getTestId().equals(oldTestId)) {
          Result result = referralResult.getResult();
          result.setSysUserId(currentUserId);
          if (updatedReferralResult == null) {
            referralResult.setTestId(newTestId);
            referralResult.setSysUserId(currentUserId);
            result.setResultType(new TestService(newTestId).getResultType());
            result.setValue("");
            updatedReferralResult = referralResult;
            updatableReferralResults.add(referralResult);
          }
        }
      }
      existingReferralResults.remove(updatedReferralResult);
    }
  }
}
