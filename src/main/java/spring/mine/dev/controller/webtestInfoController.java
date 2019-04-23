package spring.mine.dev.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.dev.form.WebTestInfoForm;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Controller
public class webtestInfoController extends BaseController {

	@RequestMapping(value = "/webtestInfo", method = RequestMethod.GET)
	public ModelAndView showwebtestInfo(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WebTestInfoForm form = new WebTestInfoForm();
		String xmlWad = getWebTestXmlWad();

		PropertyUtils.setProperty(form, "xmlWad", xmlWad);

		return findForward(FWD_SUCCESS, form);
	}

	private String getWebTestXmlWad() {
		StringBuilder xmlBuilder = new StringBuilder();

		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append("<webTestInfo>");

		addUserInfo(xmlBuilder);
		// addUserLocked(xmlBuilder);
		addNumberOfPatients(xmlBuilder);
		addNumberOfSamples(xmlBuilder);

		xmlBuilder.append("</webTestInfo>");
		return xmlBuilder.toString();

	}

	private void addUserInfo(StringBuilder xmlBuilder) {
		LoginDAO loginDAO = new LoginDAOImpl();
		// Login user = loginDAO.getUserProfile("user");
		Login user = loginDAO.getUserProfile("webtest");
		if (user != null) {
			xmlBuilder.append("<webtestuser-id>");
			xmlBuilder.append(user.getSystemUserId() + "-" + user.getId());
			xmlBuilder.append("</webtestuser-id>");
			xmlBuilder.append("<webtestuser-passwd>");
			xmlBuilder.append(user.getPassword());
			xmlBuilder.append("</webtestuser-passwd>");
			xmlBuilder.append("<webtestuser-accountLocked>");
			xmlBuilder.append(user.getAccountLocked());
			xmlBuilder.append("</webtestuser-accountLocked>");
		} else {
			xmlBuilder.append("<webtestuser-id>no 'webtest' user</webtestuser-id>");
		}
	}

	private void addNumberOfPatients(StringBuilder xmlBuilder) {
		int count = new PatientDAOImpl().getTotalCount("Patient", Patient.class);

		xmlBuilder.append("<patient-count>");
		xmlBuilder.append(String.valueOf(count));
		xmlBuilder.append("</patient-count>");

	}

	private void addNumberOfSamples(StringBuilder xmlBuilder) {
		int count = new PatientDAOImpl().getTotalCount("Sample", Sample.class);

		xmlBuilder.append("<sample-count>");
		xmlBuilder.append(String.valueOf(count));
		xmlBuilder.append("</sample-count>");

	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "webTestInfoDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
