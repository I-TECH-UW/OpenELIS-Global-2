package org.openelisglobal.program.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Questionnaire;
import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.program.service.ProgramService;
import org.openelisglobal.program.valueholder.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramController extends BaseRestController {

    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private FhirUtil fhirUtil;
    @Autowired
    private ProgramService programService;

    @GetMapping(value = "/program/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EditProgramForm createProgram(@PathVariable String id) throws FhirLocalPersistingException {
        EditProgramForm form = new EditProgramForm();
        form.setProgram(programService.get(id));
        if (form.getProgram().getQuestionnaireUUID() != null) {
            form.setAdditionalOrderEntryQuestions(fhirUtil.getLocalFhirClient().read().resource(Questionnaire.class)
                    .withId(form.getProgram().getQuestionnaireUUID().toString()).execute());
        }
        return form;
    }

    @PostMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EditProgramForm createProgram(@RequestBody EditProgramForm form) throws FhirLocalPersistingException {
        Questionnaire questionnaire = form.getAdditionalOrderEntryQuestions();
        Program program = form.getProgram();
        if (!GenericValidator.isBlankOrNull(program.getId())) {
            program.setLastupdated(programService.get(program.getId()).getLastupdated());
        }
        if (program.getQuestionnaireUUID() == null) {
            program.setQuestionnaireUUID(UUID.randomUUID());
        }
        if (questionnaire == null) {
            questionnaire = new Questionnaire();
        }
        program = programService.save(program);
        questionnaire.setId(program.getQuestionnaireUUID().toString());
        fhirPersistanceService.updateFhirResourceInFhirStore(questionnaire);
        DisplayListService.getInstance().refreshList(ListType.PROGRAM);
        return form;
    }

    @GetMapping(value = "/program/{id}/questionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Questionnaire getAdditionalEntryQuestions(HttpServletRequest request, @PathVariable String id)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (programService.get(id).getQuestionnaireUUID() != null) {
            return fhirUtil.getLocalFhirClient().read().resource(Questionnaire.class)
                    .withId(programService.get(id).getQuestionnaireUUID().toString()).execute();
        }
        return null;

    }

}
