package spring.mine.analyzerimport.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;

@Component
public class AnalyzerTestMappingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AnalyzerTestMappingValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AnalyzerTestMapping analyzerTestMapping = (AnalyzerTestMapping) target;

		ValidationHelper.validateIdField(analyzerTestMapping.getAnalyzerId(), "analyzerId", errors, true);

		ValidationHelper.validateFieldAndCharset(analyzerTestMapping.getAnalyzerTestName(), "analyzerTestName", errors,
				true, 30, " a-zA-Z0-9àâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ\\\\\\-%#\\(\\)\\^_");

		ValidationHelper.validateIdField(analyzerTestMapping.getTestId(), "testId", errors, true);

	}

	public void preInsertValidate(AnalyzerTestMapping analyzerTestMapping, Errors errors) {
		validate(analyzerTestMapping, errors);

		AnalyzerTestMappingDAO mappingDAO = new AnalyzerTestMappingDAOImpl();
		List<AnalyzerTestMapping> testMappingList = mappingDAO.getAllAnalyzerTestMappings();
		for (AnalyzerTestMapping testMapping : testMappingList) {
			if (analyzerTestMapping.getAnalyzerId().equals(testMapping.getAnalyzerId())
					&& analyzerTestMapping.getAnalyzerTestName().equals(testMapping.getAnalyzerTestName())) {
				errors.reject("error.analyzer.test.name.duplicate");
			}
		}
	}

	public void preUpdateValidate(AnalyzerTestMapping analyzerTestMapping, Errors errors) {
		validate(analyzerTestMapping, errors);

		ValidationHelper.validateIdField(analyzerTestMapping.getId(), "id", errors, true);

	}

}
