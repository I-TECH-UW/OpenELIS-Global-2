package org.openelisglobal.program.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hl7.fhir.r4.model.Questionnaire;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.fhir.springserialization.QuestionnaireDeserializer;
import org.openelisglobal.program.controller.EditProgramForm;
import org.openelisglobal.program.valueholder.Program;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.valueholder.TestSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

@Component
public class ProgramAutocreateService {

    @Value("${org.openelisglobal.program.autocreate:true}")
    private boolean autocreateOn;

    @Value("classpath*:programs/*.json")
    private Resource[] programResources;

    @Autowired
    private ProgramService programService;
    @Autowired
    private TestSectionService testSectionService;
    @Autowired
    private FhirPersistanceService fhirPersistanceService;

    @PostConstruct 
	@Transactional
    public void autocreateProgram() {
        if (autocreateOn) {
            for (Resource programResource : programResources) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(programResource.getInputStream()))) {
                    String contents = reader.lines().map(line -> line + "\n").collect(Collectors.joining());
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new Hibernate5Module());
                    mapper.setSerializationInclusion(Include.NON_NULL);

                    SimpleModule module = new SimpleModule();
                    module.addDeserializer(Questionnaire.class, new QuestionnaireDeserializer());
                    mapper.registerModule(module);
                    EditProgramForm form = mapper.readValue(contents, EditProgramForm.class); 
                  
                    Questionnaire questionnaire = form.getAdditionalOrderEntryQuestions();

                    Program program = form.getProgram();
                    program.setManuallyChanged(false);
                    if (!GenericValidator.isBlankOrNull(program.getCode())) {
                        Optional<Program> dbProgram = programService.getMatch("code", form.getProgram().getCode());
                        if (dbProgram.isPresent()) {
                            if (dbProgram.get().getManuallyChanged()) {
                                continue;
                            }
                            program.setId(dbProgram.get().getId());
                            program.setLastupdated(dbProgram.get().getLastupdated());
                        }
                    }
                    if (program.getQuestionnaireUUID() == null) {
                        program.setQuestionnaireUUID(UUID.randomUUID());
                    }
                    if (questionnaire == null) {
                        questionnaire = new Questionnaire();
                    }
                    if(StringUtils.isNotBlank(form.getTestSectionName())){
                        Optional<TestSection> testSection = testSectionService.getMatch("testSectionName", form.getTestSectionName());
                        if(testSection.isPresent()){
                            program.setTestSection(testSection.get());
                        } else {
                            program.setTestSection(null);
                        }
                    }
                    program = programService.save(program);
                    questionnaire.setId(program.getQuestionnaireUUID().toString());
                    fhirPersistanceService.updateFhirResourceInFhirStore(questionnaire);
                    DisplayListService.getInstance().refreshList(ListType.PROGRAM);
                    
                } catch (IOException | FhirLocalPersistingException e) {
                    LogEvent.logError(e);
                }
            }
        }
    }


}
