package org.openelisglobal.systemuser.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.resultvalidation.bean.AnalysisItem;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.userrole.valueholder.UserLabUnitRoles;

public interface UserService {

    void updateLoginUser(LoginUser loginUser, boolean loginUserNew, SystemUser systemUser, boolean systemUserNew,
            List<String> selectedRoles, String loggedOnUserId);

    void saveUserLabUnitRoles(SystemUser systemUser, Map<String, Set<String>> selectedLabUnitRolesMap,
            String loggedOnUserId);

    UserLabUnitRoles getUserLabUnitRoles(String systemUserId);

    List<UserLabUnitRoles> getAllUserLabUnitRoles();

    List<IdValuePair> getUserTestSections(String systemUserId, String roleId);

    /**
     * OGC-189 (M2): the lab units to offer in a <em>viewer</em> control — a filter
     * over work the user is looking at (Results, Workplan, by-unit reports,
     * history), as opposed to a <em>chooser</em> ("assign this to a lab unit"),
     * which must keep using {@link #getUserTestSections}.
     *
     * <p>
     * Viewers show {@code isActive OR hasContent}: {@link #getUserTestSections}
     * returns active units only, so a unit deactivated with analyses still in
     * flight would drop off the worklists and strand them. This adds those units
     * back, and only those — a deactivated unit with nothing left in it stays
     * hidden, so the list cleans itself up as work completes.
     *
     * <p>
     * Authorization is unchanged: the user's own lab-unit roles still gate the
     * result, so this can never widen what someone may see.
     */
    List<IdValuePair> getUserViewerTestSections(String systemUserId, String roleId);

    List<IdValuePair> getUserSampleTypes(String systemUserId, String userRole);

    List<IdValuePair> getAllDisplayUserTestsByLabUnit(String SystemUserId, String roleName);

    List<AnalysisItem> filterAnalysisResultsByLabUnitRoles(String SystemUserId, List<AnalysisItem> results,
            String roleName);

    List<Analysis> filterAnalysesByLabUnitRoles(String SystemUserId, List<Analysis> results, String roleName);

    List<TestResultItem> filterResultsByLabUnitRoles(String SystemUserId, List<TestResultItem> results,
            String roleName);

    List<IdValuePair> getUserPrograms(String systemUserId, String userRole);

    List<IdValuePair> getUserSampleTypes(String systemUserId, String roleName, String testSectionName);
}
