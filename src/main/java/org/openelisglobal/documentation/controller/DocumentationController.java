package org.openelisglobal.documentation.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DocumentationController {

    @GetMapping(value = "/docs/UserManual", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getUserManual() throws IOException {
        Locale locale = LocaleContextHolder.getLocale();
        String filename = "";
        if (locale.getLanguage().equals("en")) {
            filename = "/static/documentation/OEGlobal_UserManual_en.pdf";
        } else {
            filename = "/static/documentation/CI_Regional_fr.pdf";
        }
        InputStream in = getClass().getResourceAsStream(filename);
        return IOUtils.toByteArray(in);
    }

}
