package org.openelisglobal.common.provider.query;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.servlet.validation.AjaxServlet;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.TestSection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonConformingEventSearchProvider extends BaseQueryProvider {

    private NCEventService ncEventService = SpringContext.getBean(NCEventService.class);;
    private TestSectionService testSectionService = SpringContext.getBean(TestSectionService.class);;

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String labNumber = request.getParameter("labNumber");
        String nceNumber = request.getParameter("nceNumber");
        String status = request.getParameter("status");

        String result = VALID;
        StringBuilder xml = new StringBuilder();


        Map<String, Object> searchParameters = new HashMap<>();
        searchParameters.put("status", status);
        List<NcEvent> searchResults = new ArrayList<>();
        if (!"".equalsIgnoreCase(labNumber)) {
            searchParameters.put("labOrderNumber", labNumber);
        } else if (!"".equalsIgnoreCase(nceNumber)) {
            searchParameters.put("nceNumber", nceNumber);
        }
        searchResults = ncEventService.getAllMatching(searchParameters);

        if (searchResults.size() == 0) {
           result = INVALID;
           xml.append("No results found for search criteria.");
        } else {
            formatResults(searchResults, xml);
        }
        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    public void formatResults(List<NcEvent> results, StringBuilder xml) {
        for (NcEvent nce: results) {
            String reportingUnit = "";
            if (nce.getReportingUnitId() != null) {
                TestSection testSection = testSectionService.getTestSectionById(nce.getReportingUnitId());
                reportingUnit = testSection.getTestSectionName();
            }
            xml.append("<nce><date>").append(nce.getDateOfEvent()).append("</date><ncenumber>");
            xml.append(nce.getNceNumber()).append("</ncenumber>").append("<unit>").append(reportingUnit).append("</unit>");
            xml.append("<color>").append(nce.getColorCode()).append("</color></nce>");
        }
    }

    @Override
    public void setServlet(AjaxServlet ajaxServlet) {
        this.ajaxServlet = ajaxServlet;
    }

    @Override
    public AjaxServlet getServlet() {
        return ajaxServlet;
    }
}
