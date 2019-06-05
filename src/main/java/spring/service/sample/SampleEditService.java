package spring.service.sample;

import javax.servlet.http.HttpServletRequest;

import spring.mine.sample.form.SampleEditForm;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface SampleEditService {

	void editSample(SampleEditForm form, HttpServletRequest request, Sample updatedSample, boolean sampleChanged,
			String sysUserId);

}
