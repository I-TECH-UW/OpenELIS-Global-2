package us.mn.state.health.lims.barcode.action;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;

public class BarcodeConfigurationAction extends BaseAction {

  @Override
  protected ActionForward performAction(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response) throws Exception {

    String forward = FWD_SUCCESS;

    BaseActionForm dynaForm = (BaseActionForm) form;
    dynaForm.initialize(mapping);
    setFields(dynaForm);

    request.getSession().setAttribute(SAVE_DISABLED, "false");
    return mapping.findForward(forward);
  }

  /**
   * Set the form fields with those values stored in the database
   * @param dynaForm    The form to populate
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   */
  private void setFields(BaseActionForm dynaForm)
          throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    
    // get the dimension values
    String heightOrderLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.ORDER_BARCODE_HEIGHT);
    String widthOrderLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.ORDER_BARCODE_WIDTH);
    String heightSpecimenLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_BARCODE_HEIGHT);
    String widthSpecimenLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_BARCODE_WIDTH);
    // set the dimension values
    PropertyUtils.setProperty(dynaForm, "heightOrderLabels", heightOrderLabels);
    PropertyUtils.setProperty(dynaForm, "widthOrderLabels", widthOrderLabels);
    PropertyUtils.setProperty(dynaForm, "heightSpecimenLabels", heightSpecimenLabels);
    PropertyUtils.setProperty(dynaForm, "widthSpecimenLabels", widthSpecimenLabels);

    // get the maximum print values
    String numOrderLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.MAX_ORDER_PRINTED);
    String numSpecimenLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.MAX_SPECIMEN_PRINTED);
    String numAliquotLabels = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.MAX_ALIQUOT_PRINTED);
    //set the maximum print values
    PropertyUtils.setProperty(dynaForm, "numOrderLabels", numOrderLabels);
    PropertyUtils.setProperty(dynaForm, "numSpecimenLabels", numSpecimenLabels);
    PropertyUtils.setProperty(dynaForm, "numAliquotLabels", numAliquotLabels);

    // get the optional specimen values
    String collectionDateCheck = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_DATE);
    String testsCheck = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_TESTS);
    String patientSexCheck = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_SEX);
    // set the optional specimen values
    PropertyUtils.setProperty(dynaForm, "collectionDateCheck", collectionDateCheck);
    PropertyUtils.setProperty(dynaForm, "testsCheck", testsCheck);
    PropertyUtils.setProperty(dynaForm, "patientSexCheck", patientSexCheck);
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
