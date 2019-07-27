package org.openelisglobal.samplebatchentry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.SampleOrderService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.sample.action.BaseSampleEntryAction;
import org.openelisglobal.sample.form.ProjectData;

public class SampleBatchEntrySetupAction extends BaseSampleEntryAction {

  @Override
  protected ActionForward performAction(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    String forward = "success";

    request.getSession().setAttribute(IActionConstants.NEXT_DISABLED, IActionConstants.TRUE);
    BaseActionForm dynaForm = (BaseActionForm) form;
    dynaForm.initialize(mapping);

    SampleOrderService sampleOrderService = new SampleOrderService();
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems",
            sampleOrderService.getSampleOrderItem());
    PropertyUtils.setProperty(dynaForm, "sampleTypes",
            DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
    PropertyUtils.setProperty(dynaForm, "testSectionList",
            DisplayListService.getInstance().getList(ListType.TEST_SECTION));
    PropertyUtils.setProperty(dynaForm, "currentDate", DateUtil.getCurrentDateAsText());
    PropertyUtils.setProperty(dynaForm, "currentTime", DateUtil.getCurrentTimeAsText());
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems.receivedTime",
            DateUtil.getCurrentTimeAsText());
    PropertyUtils.setProperty(dynaForm, "ProjectDataVL", new ProjectData());
    PropertyUtils.setProperty(dynaForm, "ProjectDataEID", new ProjectData());
    
    addProjectList(dynaForm);

    if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
      PropertyUtils.setProperty(dynaForm, "initialSampleConditionList",
              DisplayListService.getInstance().getList(ListType.INITIAL_SAMPLE_CONDITION));
    }

    return mapping.findForward(forward);
  }

  @Override
  protected String getPageTitleKey() {
    return "sample.batchentry.setup.title";
  }

  @Override
  protected String getPageSubtitleKey() {
    return "sample.batchentry.setup.title";
  }
}
