package org.openelisglobal.dataexchange.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.dataexchange.order.ElectronicOrderSortOrderCategoryConvertor;
import org.openelisglobal.dataexchange.order.form.ElectronicOrderViewForm;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.statusofsample.service.StatusOfSampleService;
import org.openelisglobal.statusofsample.valueholder.StatusOfSample;
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
public class ElectronicOrdersController extends BaseController {

    @Autowired
    StatusOfSampleService statusOfSampleService;
    @Autowired
    ElectronicOrderService electronicOrderService;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(ElectronicOrder.SortOrder.class,
                new ElectronicOrderSortOrderCategoryConvertor());
    }

    @RequestMapping(value = "/ElectronicOrders", method = RequestMethod.GET)
    public ModelAndView showElectronicOrders(HttpServletRequest request,
            @ModelAttribute("form") @Valid ElectronicOrderViewForm form, BindingResult result) {
        // if invalid request, default to basic values
        if (result.hasErrors()) {
            saveErrors(result);
            form.setSortOrder(ElectronicOrder.SortOrder.LAST_UPDATED);
            form.setPage(1);
        }

        List<ElectronicOrder> eOrders = electronicOrderService.getAllElectronicOrdersOrderedBy(form.getSortOrder());

        // correct for proper bounds
        int startIndex = (form.getPage() - 1) * 50;
        startIndex = startIndex > eOrders.size() ? 0 : startIndex;
        int endIndex = startIndex + 50 > eOrders.size() ? eOrders.size() : startIndex + 50;

        // set attributes for use in jsp
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("endIndex", endIndex);
        request.setAttribute("total", eOrders.size());

        // get section of list for display on current page
        eOrders = eOrders.subList(startIndex, endIndex);
        for (ElectronicOrder eOrder : eOrders) {
            StatusOfSample status = new StatusOfSample();
            status.setId(eOrder.getStatusId());
            statusOfSampleService.get(eOrder.getStatusId());
            eOrder.setStatus(status);
        }
        form.setEOrders(eOrders);

        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "electronicOrderViewDefinition";
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
