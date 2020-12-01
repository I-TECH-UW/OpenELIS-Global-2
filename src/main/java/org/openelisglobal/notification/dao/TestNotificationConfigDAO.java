package org.openelisglobal.notification.dao;

import java.util.Optional;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.notification.valueholder.TestNotificationConfig;

public interface TestNotificationConfigDAO extends BaseDAO<TestNotificationConfig, Integer> {

    Optional<TestNotificationConfig> getTestNotificationConfigForTestId(String testId);

}
