package spring.service.referral;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analysis.AnalysisService;
import spring.service.note.NoteService;
import spring.service.result.ResultService;
import spring.service.sample.SampleService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.referral.valueholder.ReferralSet;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
public class ReferralSetServiceImpl implements ReferralSetService {

	@Autowired
	private ReferralService referralService;
	@Autowired
	private ReferralResultService referralResultService;
	@Autowired
	private ResultService resultService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private NoteService noteService;

	@Transactional
	@Override
	public void updateRefreralSets(List<ReferralSet> referralSetList, List<Sample> modifiedSamples,
			Set<Sample> parentSamples, List<ReferralResult> removableReferralResults, String sysUserId) {
		for (ReferralSet referralSet : referralSetList) {
			referralService.update(referralSet.getReferral());

			for (ReferralResult referralResult : referralSet.getUpdatableReferralResults()) {
				Result rResult = referralResult.getResult();
				if (rResult != null) {
					if (rResult.getId() == null) {
						resultService.insert(rResult);
					} else {
						rResult.setSysUserId(sysUserId);
						resultService.update(rResult);
					}
				}

				if (referralResult.getId() == null) {
					referralResultService.insert(referralResult);
				} else {
					referralResultService.update(referralResult);
				}
			}

			if (referralSet.getNote() != null) {
				if (referralSet.getNote().getId() == null) {
					noteService.insert(referralSet.getNote());
				} else {
					noteService.update(referralSet.getNote());
				}
			}
		}

		for (ReferralResult referralResult : removableReferralResults) {

			referralResult.setSysUserId(sysUserId);
			referralResultService.delete(referralResult);

			if (referralResult.getResult() != null && referralResult.getResult().getId() != null) {
				referralResult.getResult().setSysUserId(sysUserId);
				resultService.delete(referralResult.getResult());
			}
		}

		setStatusOfParentSamples(modifiedSamples, parentSamples, sysUserId);

		for (Sample sample : modifiedSamples) {
			sampleService.update(sample);
		}

	}

	private void setStatusOfParentSamples(List<Sample> modifiedSamples, Set<Sample> parentSamples, String sysUserId) {
		for (Sample sample : parentSamples) {
			List<Analysis> analysisList = analysisService.getAnalysesBySampleId(sample.getId());

			String finalizedId = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
			boolean allAnalysisFinished = true;

			if (analysisList != null) {
				for (Analysis childAnalysis : analysisList) {
					Referral referral = referralService.getReferralByAnalysisId(childAnalysis.getId());
					List<ReferralResult> referralResultList;

					if (referral == null || referral.getId() == null) {
						referralResultList = new ArrayList<>();
					} else {
						referralResultList = referralResultService.getReferralResultsForReferral(referral.getId());
					}

					if (referralResultList.isEmpty()) {
						if (!finalizedId.equals(childAnalysis.getStatusId())) {
							allAnalysisFinished = false;
							break;
						}
					} else {
						for (ReferralResult referralResult : referralResultList) {
							if (referralResult.getResult() == null
									|| GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
								if (!(referral.isCanceled() && finalizedId.equals(childAnalysis.getStatusId()))) {
									allAnalysisFinished = false;
									break;
								}
							}
						}
					}
				}
			}

			if (allAnalysisFinished) {
				sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Finished));
				sample.setSysUserId(sysUserId);
				modifiedSamples.add(sample);
			}
		}
	}

}
