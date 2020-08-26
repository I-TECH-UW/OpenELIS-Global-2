package org.openelisglobal.config;

import java.net.URI;

import org.openelisglobal.common.propertyeditor.CaseInsensitiveEnumPropertyEditor;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.ProgrammedConnection;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.beans.propertyeditors.URIEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerSetup {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(false);
        binder.registerCustomEditor(String.class, stringTrimmer);
        binder.registerCustomEditor(URI.class, new URIEditor(false));
        binder.registerCustomEditor(AuthType.class, new CaseInsensitiveEnumPropertyEditor<>(AuthType.class));
        binder.registerCustomEditor(ProgrammedConnection.class,
                new CaseInsensitiveEnumPropertyEditor<>(ProgrammedConnection.class));
    }

}
