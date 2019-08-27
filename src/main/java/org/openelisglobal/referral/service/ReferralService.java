package org.openelisglobal.referral.service;

import java.sql.Date;
import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.referral.valueholder.Referral;

public interface ReferralService extends BaseObjectService<Referral, String> {

    Referral getReferralById(String referralId);

    Referral getReferralByAnalysisId(String analysisId);

    List<Referral> getAllReferralsBySampleId(String id);

    List<Referral> getAllUncanceledOpenReferrals();

    List<Referral> getAllReferralsByOrganization(String organizationId, Date lowDate, Date highDate);
}
