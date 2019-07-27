package org.openelisglobal.typeofsample.form;

import java.util.Collection;

import org.openelisglobal.common.form.BaseForm;

public class TypeOfSampleTestForm extends BaseForm {
	private String id = "";

	private String sample = "";

	private String test = "";

	private Collection samples;

	private Collection tests;

	public TypeOfSampleTestForm() {
		setFormName("typeOfSampleTestForm");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Collection getSamples() {
		return samples;
	}

	public void setSamples(Collection samples) {
		this.samples = samples;
	}

	public Collection getTests() {
		return tests;
	}

	public void setTests(Collection tests) {
		this.tests = tests;
	}
}
