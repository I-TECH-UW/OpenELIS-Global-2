package us.mn.state.health.lims.barcode.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

/**
 * Action class for saving bar code configuration. Navigated to on "save" by
 * BarcodeConfiguration.do
 * 
 * @author Caleb
 *
 */
public class BarcodeConfigurationSaveAction extends BaseAction {

  @Override
  protected ActionForward performAction(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response) throws Exception {

    String forward = FWD_SUCCESS;

    ActionMessages errors = validate(request);
    if (!errors.isEmpty()) {
      saveErrors(request, errors);
      request.setAttribute(IActionConstants.FWD_SUCCESS, false);
      forward = FWD_FAIL;
      return mapping.findForward(forward);
    }

    BaseActionForm dynaForm = (BaseActionForm) form;
    dynaForm.initialize(mapping);
    updateLabelSizing(errors, request.getParameter("heightOrderLabels"),
            request.getParameter("widthOrderLabels"), request.getParameter("heightSpecimenLabels"),
            request.getParameter("widthSpecimenLabels"));
    updateFields(errors, request.getParameter("collectionDateCheck"),
            request.getParameter("patientSexCheck"), request.getParameter("testsCheck"));
    updateLabelMaximums(errors, request.getParameter("numOrderLabels"),
            request.getParameter("numSpecimenLabels"));
    if (errors.isEmpty()) {
      request.setAttribute(IActionConstants.FWD_SUCCESS, true);
    } else {
      saveErrors(request, errors);
      request.setAttribute(IActionConstants.FWD_SUCCESS, false);
      forward = FWD_FAIL;
      return mapping.findForward(forward);
    }

    return mapping.findForward(forward);
  }

  private ActionMessages validate(HttpServletRequest request) {
    ActionMessages errors = new ActionMessages();

    // check dimensions
    if (!GenericValidator.isFloat(request.getParameter("heightOrderLabels"))) {
      ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Order Height",
              null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isFloat(request.getParameter("widthOrderLabels"))) {
      ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Order Width",
              null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isFloat(request.getParameter("heightSpecimenLabels"))) {
      ActionError error = new ActionError("barcode.config.error.dimension.invalid",
              "Specimen Height", null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isFloat(request.getParameter("widthSpecimenLabels"))) {
      ActionError error = new ActionError("barcode.config.error.dimension.invalid",
              "Specimen Width", null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    // check number of labels
    if (!GenericValidator.isInt(request.getParameter("numOrderLabels"))) {
      ActionError error = new ActionError("barcode.config.error.number.invalid", "Order", null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isInt(request.getParameter("numSpecimenLabels"))) {
      ActionError error = new ActionError("barcode.config.error.number.invalid", "Specimen", null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    // check optional fields
    if (!GenericValidator.isBool(request.getParameter("collectionDateCheck"))
            && !GenericValidator.isBlankOrNull(request.getParameter("collectionDateCheck"))) {
      ActionError error = new ActionError("barcode.config.error.field.invalid", "Collection Date",
              null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isBool(request.getParameter("patientSexCheck"))
            && !GenericValidator.isBlankOrNull(request.getParameter("patientSexCheck"))) {
      ActionError error = new ActionError("barcode.config.error.field.invalid", "Patient Sex",
              null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isBool(request.getParameter("testsCheck"))
            && !GenericValidator.isBlankOrNull(request.getParameter("testsCheck"))) {
      ActionError error = new ActionError("barcode.config.error.field.invalid", "Tests", null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    return errors;
  }

  /**
   * Update the values for label sizing in the database
   * @param errors      for error tracking on inserts
   * @param orderHeight New height for order labels
   * @param orderWidth  New width for order labels
   * @param specHeight  New height for specimen labels
   * @param specWidth   New width for specimen labels
   */
  private void updateLabelSizing(ActionMessages errors, String orderHeight, String orderWidth,
          String specHeight, String specWidth) {

    setValue(errors, "heightOrderLabels", orderHeight, "text");
    setValue(errors, "widthOrderLabels", orderWidth, "text");
    setValue(errors, "heightSpecimenLabels", specHeight, "text");
    setValue(errors, "widthSpecimenLabels", specWidth, "text");
  }

  /**
   * Update the values for label print maximums in the database
   * @param errors    For error tracking on inserts
   * @param numOrder  New maximum print value for order labels
   * @param numSpec   New maximum print value for specimen labels
   */
  private void updateLabelMaximums(ActionMessages errors, String numOrder, String numSpec) {
    setValue(errors, "numOrderLabels", numOrder, "text");
    setValue(errors, "numSpecimenLabels", numSpec, "text");
  }

  /**
   * Update the values for optional fields for the specimen labels
   * @param errors              For error tracking on inserts
   * @param collectionDateCheck New value for whether to use collection date
   * @param patientSexCheck     New value for whether to use patient sex
   * @param testsCheck          New value for whether to use tests
   */
  private void updateFields(ActionMessages errors, String collectionDateCheck,
          String patientSexCheck, String testsCheck) {

    collectionDateCheck = null == collectionDateCheck ? "false" : collectionDateCheck;
    patientSexCheck = null == patientSexCheck ? "false" : patientSexCheck;
    testsCheck = null == testsCheck ? "false" : testsCheck;
    setValue(errors, "collectionDateCheck", collectionDateCheck, "boolean");
    setValue(errors, "patientSexCheck", patientSexCheck, "boolean");
    setValue(errors, "testsCheck", testsCheck, "boolean");
  }

  /**
   * Persist a bar code configuration value in the database under site_information
   * @param errors    For error tracking on inserts
   * @param name      The name in the database
   * @param value     The new value to save
   * @param valueType The type of the value to save
   */
  private void setValue(ActionMessages errors, String name, String value, String valueType) {
    SiteInformation siteInformation;
    SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

    siteInformation = siteInformationDAO.getSiteInformationByName(name);
    Transaction tx = HibernateUtil.getSession().beginTransaction();
    try {
      if (siteInformation == null) {
        siteInformation = new SiteInformation();
        siteInformation.setName(name);
        siteInformation.setValue(value);
        siteInformation.setValueType(valueType);
        siteInformation.setSysUserId(currentUserId);
        siteInformationDAO.insertData(siteInformation);
      } else {
        siteInformation.setValue(value);
        siteInformation.setSysUserId(currentUserId);
        siteInformationDAO.updateData(siteInformation);
      }
      tx.commit();
    } catch (LIMSRuntimeException lre) {
      tx.rollback();
      ActionError error = new ActionError("barcode.config.error.insert", name, null);
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    } finally {
      HibernateUtil.closeSession();
    }
    ConfigurationProperties.forceReload();
  }

  @Override
  protected String getPageTitleKey() {
    return "barcodeconfiguration.browse.title";
  }

  @Override
  protected String getPageSubtitleKey() {
    return "barcodeconfiguration.browse.title";
  }

}
