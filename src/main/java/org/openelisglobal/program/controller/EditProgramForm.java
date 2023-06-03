package org.openelisglobal.program.controller;

import org.hl7.fhir.r4.model.Questionnaire;
import org.openelisglobal.program.valueholder.Program;

public class EditProgramForm {

    private Program program;

    private Questionnaire additionalOrderEntryQuestions;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Questionnaire getAdditionalOrderEntryQuestions() {
        return additionalOrderEntryQuestions;
    }

    public void setAdditionalOrderEntryQuestions(Questionnaire additionalOrderEntryQuestions) {
        this.additionalOrderEntryQuestions = additionalOrderEntryQuestions;
    }
}
