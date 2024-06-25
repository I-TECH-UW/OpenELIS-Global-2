package org.openelisglobal.view;

import javax.servlet.http.HttpServletRequest;

public interface PageBuilderService {

    String setupJSPPage(String view, HttpServletRequest request) throws ViewConfigurationException;
}
