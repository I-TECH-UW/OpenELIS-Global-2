package us.mn.state.health.lims.referral.valueholder;

import java.util.ArrayList;
import java.util.List;

import spring.service.test.TestService;
import spring.util.SpringContext;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.result.valueholder.Result;

public class ReferralSet {
	Referral referral;
	Note note;
	List<ReferralResult> updatableReferralResults = new ArrayList<>();
	private List<ReferralResult> existingReferralResults = new ArrayList<>();

	public List<ReferralResult> getExistingReferralResults() {
		return existingReferralResults;
	}

	public void setExistingReferralResults(List<ReferralResult> existingReferralResults) {
		this.existingReferralResults = existingReferralResults;
	}

	public ReferralResult getNextReferralResult() {
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
					TestService testTestService = SpringContext.getBean(TestService.class);
					testTestService.setTest(newTestId);

					referralResult.setTestId(newTestId);
					referralResult.setSysUserId(currentUserId);

					result.setResultType(testTestService.getResultType());
					result.setValue("");
					updatedReferralResult = referralResult;
					updatableReferralResults.add(referralResult);
				}
			}
		}
		existingReferralResults.remove(updatedReferralResult);
	}

	public void setNote(Note note) {
		this.note = note;

	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Note getNote() {
		return note;
	}

	public List<ReferralResult> getUpdatableReferralResults() {
		return updatableReferralResults;
	}

	public Referral getReferral() {
		return referral;
	}
}