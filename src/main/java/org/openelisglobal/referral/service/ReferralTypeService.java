package org.openelisglobal.referral.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.referral.valueholder.ReferralType;

public interface ReferralTypeService extends BaseObjectService<ReferralType, String> {
  ReferralType getReferralTypeByName(String name);
}
