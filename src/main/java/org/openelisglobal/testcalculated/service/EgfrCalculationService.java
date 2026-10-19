package org.openelisglobal.testcalculated.service;

import java.util.Collection;
import org.openelisglobal.result.valueholder.Result;

/**
 * Fills an ordered eGFR (LOINC 98979-8) from the creatinine result on the same
 * order with the race-free CKD-EPI 2021 equation.
 */
public interface EgfrCalculationService {

    /** Creatinine [Mass/volume] in Serum or Plasma. */
    String CREATININE_MASS_LOINC = "2160-0";
    /** Creatinine [Moles/volume] in Serum or Plasma. */
    String CREATININE_MOLES_LOINC = "14682-9";
    /**
     * Glomerular filtration rate/1.73 sq M.predicted [Volume Rate/Area] in Serum,
     * Plasma or Blood by Creatinine-based formula (CKD-EPI 2021).
     */
    String EGFR_CKD_EPI_2021_LOINC = "98979-8";

    /**
     * Call after results are saved, in the same transaction. For every creatinine
     * among {@code savedResults} whose order also carries an eGFR test, the eGFR is
     * calculated, recalculated, cleared or left alone (see
     * {@link org.openelisglobal.testcalculated.egfr.EgfrDecision}). An eGFR among
     * {@code savedResults} is one entered by hand or sent by an analyzer, and is
     * from then on never overwritten.
     */
    void updateEgfrForSavedResults(Collection<Result> savedResults, String sysUserId);
}
