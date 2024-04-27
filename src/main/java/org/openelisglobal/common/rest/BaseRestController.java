package org.openelisglobal.common.rest;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseRestController implements IActionConstants {

    private final Logger log = LoggerFactory.getLogger(BaseRestController.class);

    protected String getSysUserId(HttpServletRequest request) {
        UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
        if (usd == null) {
            usd = (UserSessionData) request.getAttribute(USER_SESSION_DATA);
            if (usd == null) {
                return null;
            }
        }
        return String.valueOf(usd.getSystemUserId());
    }

    protected ResponseEntity<String> convertFormToJsonAndAddToResponse(BaseForm form) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String json = objectMapper.writeValueAsString(form);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            log.error("Error converting form to JSON: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting form to JSON");
        }
    }

    protected static String errorsToJson(Errors errors) throws IOException {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = jsonConverter.getObjectMapper();
        Map<String, String> errorMap = new HashMap<>();

        for (ObjectError error : errors.getAllErrors()) {
            errorMap.put(error.getObjectName() + "." + error.getCode(), error.getDefaultMessage());
        }
        return mapper.writeValueAsString(errorMap);

    }
    
}
