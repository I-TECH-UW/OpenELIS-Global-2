package spring.unused.resultlimits.form;

import java.util.Collection;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.resultlimits.form.ResultLimitsLink;

public class ResultLimitsForm extends BaseForm {
	private ResultLimitsLink limit;

	private Collection tests;

	private Collection resultTypes;

	private Collection genders;

	private Collection units;

	public ResultLimitsForm() {
		setFormName("resultLimitsForm");
	}

	public ResultLimitsLink getLimit() {
		return limit;
	}

	public void setLimit(ResultLimitsLink limit) {
		this.limit = limit;
	}

	public Collection getTests() {
		return tests;
	}

	public void setTests(Collection tests) {
		this.tests = tests;
	}

	public Collection getResultTypes() {
		return resultTypes;
	}

	public void setResultTypes(Collection resultTypes) {
		this.resultTypes = resultTypes;
	}

	public Collection getGenders() {
		return genders;
	}

	public void setGenders(Collection genders) {
		this.genders = genders;
	}

	public Collection getUnits() {
		return units;
	}

	public void setUnits(Collection units) {
		this.units = units;
	}
}
