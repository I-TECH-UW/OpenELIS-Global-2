package org.openelisglobal.referral.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.referral.dao.ReferralDAO;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReferralServiceImpl extends BaseObjectServiceImpl<Referral, String> implements ReferralService {
    @Autowired
    protected ReferralDAO baseObjectDAO;

    ReferralServiceImpl() {
        super(Referral.class);
    }

    @Override
    protected ReferralDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Referral getReferralByAnalysisId(String id) {
        return getMatch("analysis.id", id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Referral> getUncanceledOpenReferrals() {
        return getBaseObjectDAO().getReferralsByStatus(
                Arrays.asList(ReferralStatus.CREATED, ReferralStatus.SENT, ReferralStatus.RECEIVED));
    }

    @Override
    @Transactional(readOnly = true)
    public Referral getReferralById(String referralId) {
        return getBaseObjectDAO().getReferralById(referralId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Referral> getReferralsBySampleId(String id) {
        return getBaseObjectDAO().getAllReferralsBySampleId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Referral> getReferralsByOrganization(String organizationId, Date lowDate, Date highDate) {
        return getBaseObjectDAO().getAllReferralsByOrganization(organizationId, lowDate, highDate);
    }

    @Override
    public List<Referral> getSentReferrals() {
        return getBaseObjectDAO().getReferralsByStatus(Arrays.asList(ReferralStatus.SENT));
    }

    @Override
    public List<UUID> getSentReferralUuids() {
        return getBaseObjectDAO().getReferralsByStatus(Arrays.asList(ReferralStatus.SENT)).stream()
                .map(e -> e.getFhirUuid()).filter(e -> e != null).collect(Collectors.toList());
    }
}
