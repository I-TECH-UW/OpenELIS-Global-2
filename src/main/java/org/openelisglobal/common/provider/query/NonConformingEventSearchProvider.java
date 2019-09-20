package org.openelisglobal.common.provider.query;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.servlet.validation.AjaxServlet;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NonConformingEventSearchProvider extends BaseQueryProvider {

    private NCEventService ncEventService = SpringContext.getBean(NCEventService.class);;

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String labNumber = request.getParameter("labNumber");
        String nceNumber = request.getParameter("nceNumber");

        String result = VALID;
        StringBuilder xml = new StringBuilder();


        List<NcEvent> searchResults = new ArrayList<>();
        if (!"".equalsIgnoreCase(labNumber)) {
            searchResults = ncEventService.getAllMatching("labNumber", labNumber);
        } else if (!"".equalsIgnoreCase(nceNumber)) {
            searchResults = ncEventService.getAllMatching("nceNumber", nceNumber);
        }


        if (searchResults.size() == 0) {
           result = INVALID;
           xml.append("No results found for search criteria.");
        } else {
            formatResults(searchResults, xml);
            xml.append("<nce><date>12/02/2019</date><ncenumber>45</ncenumber><unit>Haematology</unit></nce>");
        }

        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    public void formatResults(List<NcEvent> results, StringBuilder xml) {
        // TODO: Fix unit.
        for (NcEvent nce: results) {
            xml.append("<nce><date>").append(nce.getDateOfEvent()).append("</date><ncenumber>");
            xml.append(nce.getNceNumber()).append("</ncenumber>").append("<unit>Haematology").append("</unit></nce>");
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
