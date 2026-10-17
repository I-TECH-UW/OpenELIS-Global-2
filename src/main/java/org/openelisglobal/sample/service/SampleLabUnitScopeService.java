package org.openelisglobal.sample.service;

import java.util.Set;
import org.openelisglobal.sample.valueholder.Sample;

/**
 * The lab-unit scope an order-reading surface applies for one user: the same
 * rule the home dashboard counts with. A global administrator, a user mapped to
 * {@code AllLabUnits} and a user whose section comes from the login lab-unit
 * constraint read the whole lab; everyone else reads the orders that hold an
 * analysis in one of their lab units (child units included) or that they
 * entered themselves.
 */
public interface SampleLabUnitScopeService {

    /**
     * The test section ids the user is restricted to, expanded to child sections.
     * Empty when the user reads the whole lab.
     */
    Set<String> restrictedSectionIds(String sysUserId);

    /** True when the user may read this order under the scope above. */
    boolean isVisible(String sysUserId, Sample sample);

    /**
     * True when the order holds an analysis in one of the sections, or none at all.
     */
    boolean belongsToSections(Sample sample, Set<String> sectionIds);
}
