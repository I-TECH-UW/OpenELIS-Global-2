package org.openelisglobal.dataexchange.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.order.ElectronicOrderSortOrderCategoryConvertor;
import org.openelisglobal.dataexchange.order.form.ElectronicOrderViewForm;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
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
public class ElectronicOrdersController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "searchValue", "page", "sortOrder",
            "excludedStatuses" };

    @Autowired
    private StatusOfSampleService statusOfSampleService;
    @Autowired
    private ElectronicOrderService electronicOrderService;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(ElectronicOrder.SortOrder.class,
                new ElectronicOrderSortOrderCategoryConvertor());
        webdataBinder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/ElectronicOrders", method = RequestMethod.GET)
    public ModelAndView showElectronicOrders(HttpServletRequest request,
            @ModelAttribute("form") @Valid ElectronicOrderViewForm form, BindingResult result) {
        // if invalid request, default to basic values
        if (result.hasErrors()) {
            saveErrors(result);
            form.setSortOrder(ElectronicOrder.SortOrder.STATUS_ID);
            form.setSearchValue("");
            form.setPage(1);
        }
        List<ElectronicOrder> eOrders;
        form.getExcludedStatuses().add(ExternalOrderStatus.Realized);
        form.getExcludedStatuses().add(ExternalOrderStatus.NonConforming);


        eOrders = electronicOrderService.getElectronicOrdersContainingValueExludedByOrderedBy(form.getSearchValue(),
                form.getExcludedStatuses(),
                form.getSortOrder());
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
            eOrder.setStatus(statusOfSampleService.get(eOrder.getStatusId()));
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
