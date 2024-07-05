package org.openelisglobal.common.management.controller.rest;

import javax.servlet.http.HttpServletRequest;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.management.form.MethodManagementForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/rest")
public class MethodManagementRestController extends BaseController {
    @RequestMapping(value = "/MethodManagement", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity< MethodManagementForm> showMethodManagement(HttpServletRequest request) {
        MethodManagementForm form = new MethodManagementForm();

        return ResponseEntity.ok(form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "methodManagementDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        return null;
    }
}
