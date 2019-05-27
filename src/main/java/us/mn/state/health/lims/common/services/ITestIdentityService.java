package us.mn.state.health.lims.common.services;

import us.mn.state.health.lims.test.valueholder.Test;

public interface ITestIdentityService {

	public abstract boolean doesTestExist(String name);

	public abstract boolean doesActiveTestExist(String name);

	public abstract boolean doesPanelExist(String name);

	public abstract boolean doesTestExistForLoinc(String loincCode);

	public abstract boolean doesActiveTestExistForLoinc(String loincCode);

	public abstract boolean isTestNumericViralLoad(String testId);

	public abstract boolean isTestNumericViralLoad(Test test);

}