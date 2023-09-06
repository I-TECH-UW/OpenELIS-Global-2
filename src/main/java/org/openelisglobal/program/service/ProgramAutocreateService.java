package org.openelisglobal.program.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.fhir.springserialization.QuestionnaireDeserializer;
import org.openelisglobal.fhir.springserialization.QuestionnaireResponseDeserializer;
import org.openelisglobal.fhir.springserialization.QuestionnaireResponseSerializer;
import org.openelisglobal.fhir.springserialization.QuestionnaireSerializer;
import org.openelisglobal.program.controller.EditProgramForm;
import org.openelisglobal.program.valueholder.Program;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.valueholder.TestSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
                    if (!GenericValidator.isBlankOrNull(program.getCode())) {
                        Optional<Program> dbProgram = programService.getMatch("code", form.getProgram().getCode());
                        if (dbProgram.isPresent()) {
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
                    if(form.getProgram().getTestSection() != null && StringUtils.isNotBlank(form.getProgram().getTestSection().getTestSectionName())){
                        Optional<TestSection> testSection = testSectionService.getMatch("testSectionName", form.getProgram().getTestSection().getTestSectionName());
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
                    
                    // while ((line = reader.readLine()) != null) {
                    //     String[] lines = line.split("	");
                        // System.out.print("\n" + //
                        //         "\t\t<insert schemaName=\"clinlims\" tableName=\"localization\">\n" + //
                        //         "\t\t\t<column name=\"id\" valueSequenceNext=\"localization_seq\" />\n" + //
                        //         "\t\t\t<column name=\"lastupdated\" valueComputed=\"${now}\" />\n" + //
                        //         "\t\t\t<column name=\"description\" value=\"sampleType name\" />\n" + //
                        //         "\t\t\t<column name=\"english\" value=\"" + lines[1] + "\" />\n" + //
                        //         "\t\t\t<column name=\"french\" value=\"" + lines[1] + "\"/>\n" + //
                        //         "\t\t</insert>");

                        // System.out.print("\n" + //
                        //         "\t\t<insert schemaName=\"clinlims\" tableName=\"type_of_sample\">\n" + //
                        //         "\t\t\t<column name=\"id\" valueSequenceNext=\"type_of_sample_seq\"/>\n" + //
                        //         "\t\t\t<column name=\"description\" value=\"" + lines[1] + " sample Type\"/> \n" + //
                        //         "\t\t\t<column name=\"lastupdated\" valueDate=\"now()\" /> \n" + //
                        //         "\t\t\t<column name=\"domain\" value=\"H\" />  \n" + //
                        //         "\t\t\t<column name=\"local_abbrev\" value=\"" + lines[0] + "\" />\n" + //
                        //         "\t\t\t<column name=\"display_key\" value=\"sample.type." + lines[0] + "sampleType\"/>\n" + //
                        //         "\t\t\t<column name=\"name_localization_id\" valueComputed=\"(select id from localization where english = '" + lines[1] + "' and description = 'sampleType name' limit 1)\"/>\n" + //
                        //         "\t\t</insert>");

                        // System.out.print("\n" + //
                        //         "\t\t<insert schemaName=\"clinlims\" tableName=\"sampletype_test\">\n" + //
                        //         "\t\t\t<column name=\"id\" valueSequenceNext=\"sample_type_test_seq\"/>\n" + //
                        //         "\t\t\t<column name=\"sample_type_id\" valueNumeric=\"(select id from clinlims.type_of_sample where local_abbrev = '" + lines[0] + "' )\"/> \n" + //
                        //         "\t\t\t<column name=\"test_id\" valueNumeric=\"(select id from clinlims.test where description = 'Histopathology examination' )\" /> \n" + //
                        //         "\t\t</insert>");

                        // System.out.print("{\"valueCoding\": {\"system\": \"http://openelis-global.org/pathSpecimenNature\",\"code\": \"");
                        // System.out.print(lines[0]);
                        // System.out.print("\", \"display\": \"");
                        // System.out.print(lines[1]);
                        // System.out.print("\"}},");
                //     } 
                // }
            } catch (IOException | FhirLocalPersistingException e) {
                LogEvent.logError(e);
            }
        }
    }
}


}
