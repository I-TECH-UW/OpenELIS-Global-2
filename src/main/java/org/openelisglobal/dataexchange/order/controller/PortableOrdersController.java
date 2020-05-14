package org.openelisglobal.dataexchange.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.dataexchange.order.PortableOrderSortOrderCategoryConvertor;
import org.openelisglobal.dataexchange.order.form.PortableOrderViewForm;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.service.order.PortableOrderService;
import org.openelisglobal.statusofsample.service.StatusOfSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortableOrdersController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] {};

    @Autowired
    private StatusOfSampleService statusOfSampleService;
    @Autowired
    private PortableOrderService portableOrderService;
    @Autowired
    private FhirTransformService fhirTransformService;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(PortableOrder.SortOrder.class,
                new PortableOrderSortOrderCategoryConvertor());
        webdataBinder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/PortableOrders", method = RequestMethod.GET)
    public ModelAndView showPortableOrders(HttpServletRequest request,
            @ModelAttribute("form") @Valid PortableOrderViewForm form, BindingResult result) {
        // if invalid request, default to basic values
        if (result.hasErrors()) {
            saveErrors(result);
            form.setSortOrder(PortableOrder.SortOrder.LAST_UPDATED);
            form.setPage(1);
        }

        List<PortableOrder> pOrders = portableOrderService.getAllPortableOrdersOrderedBy(form.getSortOrder());

        // correct for proper bounds
        int startIndex = (form.getPage() - 1) * 50;
        startIndex = startIndex > pOrders.size() ? 0 : startIndex;
        int endIndex = startIndex + 50 > pOrders.size() ? pOrders.size() : startIndex + 50;

        // set attributes for use in jsp
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("endIndex", endIndex);
        request.setAttribute("total", pOrders.size());

        // get section of list for display on current page
        pOrders = pOrders.subList(startIndex, endIndex);
        for (PortableOrder pOrder : pOrders) {
            pOrder.setStatus(statusOfSampleService.get(pOrder.getStatusId()));
            pOrder.setData(fhirTransformService.CreateFhirFromOESample(pOrder));
        }
        form.setEOrders(pOrders);

        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "portableOrderViewDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "eorder.browse.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "eorder.browse.title";
    }
}
