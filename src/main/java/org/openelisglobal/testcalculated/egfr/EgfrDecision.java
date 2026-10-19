package org.openelisglobal.testcalculated.egfr;

import java.util.Locale;

/**
 * What to do with an ordered eGFR when its creatinine result is saved. Pure
 * logic, no database access.
 *
 * <ul>
 * <li>An eGFR the calculation did not write (entered by hand, or sent by an
 * analyzer) is never replaced or cleared.</li>
 * <li>A validated eGFR is never changed.</li>
 * <li>A calculated eGFR follows its creatinine: it is recalculated when the
 * creatinine changes, and cleared when it can no longer be calculated.</li>
 * <li>When no eGFR is calculated, a note says why.</li>
 * </ul>
 */
public final class EgfrDecision {

    public enum Action {
        NONE, WRITE, CLEAR
    }

    private static final String METHOD_NOTE = CkdEpi2021.METHOD + " (race-free)";

    private final Action action;
    private final String value;
    private final String note;

    private EgfrDecision(Action action, String value, String note) {
        this.action = action;
        this.value = value;
        this.note = note;
    }

    public Action getAction() {
        return action;
    }

    /** The eGFR to write, for {@link Action#WRITE}. */
    public String getValue() {
        return value;
    }

    /** Internal note for the eGFR test, or null when there is nothing to say. */
    public String getNote() {
        return note;
    }

    /**
     * @param outcome         the calculation for the creatinine as just saved
     * @param creatinineValue the creatinine result, for the note
     * @param creatinineUnit  the creatinine unit, for the note
     * @param currentEgfr     the eGFR result currently stored, null or blank if
     *                        none
     * @param calculatedEgfr  true if the stored eGFR was written by this
     *                        calculation, false if it was entered by hand or by an
     *                        analyzer
     * @param egfrFinalized   true if the eGFR test is already validated
     */
    public static EgfrDecision decide(CkdEpi2021.Outcome outcome, String creatinineValue, String creatinineUnit,
            String currentEgfr, boolean calculatedEgfr, boolean egfrFinalized) {
        boolean hasValue = currentEgfr != null && !currentEgfr.isBlank();
        String current = hasValue ? currentEgfr.trim() : null;
        String calculated = outcome.isCalculated() ? String.valueOf(outcome.getEgfr()) : null;
        boolean differs = calculated != null && !calculated.equals(current);

        if (egfrFinalized) {
            return new EgfrDecision(Action.NONE, null, differs
                    ? "eGFR is already validated and was not recalculated. " + METHOD_NOTE + " now gives " + calculated
                            + " " + CkdEpi2021.UNIT + "."
                    : null);
        }
        if (hasValue && !calculatedEgfr) {
            return new EgfrDecision(
                    Action.NONE, null, differs
                            ? "eGFR " + current + " was entered by hand and was not replaced. "
                                    + detail(outcome, creatinineValue, creatinineUnit)
                            : null);
        }
        if (!outcome.isCalculated()) {
            String reason = outcome.getSkipReason().getText();
            if (hasValue) {
                return new EgfrDecision(Action.CLEAR, null, "Calculated eGFR " + current + " removed: " + reason + ".");
            }
            // an empty creatinine (not entered yet, or cleared) needs no explanation
            boolean worthNoting = outcome.getSkipReason() != CkdEpi2021.SkipReason.NO_CREATININE_VALUE;
            return new EgfrDecision(Action.NONE, null,
                    worthNoting ? "eGFR not calculated (" + CkdEpi2021.METHOD + "): " + reason + "." : null);
        }
        if (!differs) {
            return new EgfrDecision(Action.NONE, null, null);
        }
        return new EgfrDecision(Action.WRITE, calculated, detail(outcome, creatinineValue, creatinineUnit));
    }

    /**
     * "Calculated with CKD-EPI 2021 (race-free) from creatinine 0.62 mg/dL, female,
     * 27 years at collection: 125 mL/min/1.73 m2."
     */
    static String detail(CkdEpi2021.Outcome outcome, String creatinineValue, String creatinineUnit) {
        String creatinine = (creatinineValue == null ? "?" : creatinineValue.trim())
                + (creatinineUnit == null ? "" : " " + creatinineUnit.trim());
        if (CkdEpi2021.isUmolL(creatinineUnit)) {
            creatinine += String.format(Locale.ROOT, " (%.2f mg/dL)", outcome.getCreatinineMgDl());
        }
        return "Calculated with " + METHOD_NOTE + " from creatinine " + creatinine + ", "
                + (outcome.isFemale() ? "female" : "male") + ", " + outcome.getAge() + " years at collection: "
                + outcome.getEgfr() + " " + CkdEpi2021.UNIT + ".";
    }
}
