package org.openelisglobal.reportconfiguration.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.valueholder.Menu;
import org.openelisglobal.reportconfiguration.dao.ReportDAO;
import org.openelisglobal.reportconfiguration.form.ReportConfigurationForm;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl extends BaseObjectServiceImpl<Report, String> implements ReportService {

    @Autowired
    protected ReportDAO baseObjectDAO;
    @Autowired
    private MenuService menuService;

    ReportServiceImpl() {
        super(Report.class);
    }

    @Override
    public boolean updateReport(ReportConfigurationForm form, String currentUserId) {
        try {
            Report report = baseObjectDAO.get(form.getCurrentReport().getId()).get();
            List<Integer> idOrder = Arrays.asList(form.getIdOrder().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
            if (report != null) {
                report.setName(form.getCurrentReport().getName());
                report.setCategory(form.getCurrentReport().getCategory());
                report.setType(form.getCurrentReport().getType());
                report.setIsVisible(form.getCurrentReport().getIsVisible());
                report.setSortOrder(idOrder.indexOf(Integer.valueOf(form.getCurrentReport().getId())));
                baseObjectDAO.update(report);
            }
            for (int i = 0; i < idOrder.size(); i++) {
                Report r = baseObjectDAO.get(idOrder.get(i) + "").get();
                if (r != null) {
                    Menu menu = menuService.getMenuByElementId(r.getMenuElementId());
                    menu.setIsActive(r.getIsVisible() ? true : false);
                    menu.setPresentationOrder(i);
                    menuService.save(menu);
                    r.setSortOrder(i);
                    baseObjectDAO.update(r);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected ReportDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
