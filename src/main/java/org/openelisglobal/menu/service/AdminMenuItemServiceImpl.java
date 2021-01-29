package org.openelisglobal.menu.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.openelisglobal.common.formfields.AdminFormFields;
import org.openelisglobal.common.formfields.AdminFormFields.Field;
import org.openelisglobal.common.util.ConfigurationListener;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.menu.valueholder.AdminMenuItem;
import org.springframework.stereotype.Service;

@Service
public class AdminMenuItemServiceImpl implements AdminMenuItemService, ConfigurationListener {

    List<AdminMenuItem> adminMenuItems;

    @PostConstruct
    public void createActiveList() {
        adminMenuItems = new ArrayList<>();
        String permissionBase = SystemConfiguration.getInstance().getPermissionAgent();
        AdminFormFields adminFields = AdminFormFields.getInstance();
        AdminMenuItem curItem;

        if ("true".equals(ConfigurationProperties.getInstance()
                .getPropertyValueLowerCase(ConfigurationProperties.Property.TrainingInstallation))) {
            curItem = new AdminMenuItem();
            curItem.setPath("/DatabaseCleaningRequest.do");
            curItem.setMessageKey("database.clean");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.AnalyzerTestNameMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/AnalyzerTestNameMenu.do");
            curItem.setMessageKey("analyzerTestName.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.DictionaryMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/DictionaryMenu.do");
            curItem.setMessageKey("dictionary.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.OrganizationMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/OrganizationMenu.do");
            curItem.setMessageKey("organization.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.PatientTypeMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/PatientTypeMenu.do");
            curItem.setMessageKey("patienttype.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.SiteInformationMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/SiteInformationMenu.do");
            curItem.setMessageKey("siteInformation.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.SampleEntryMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/SampleEntryConfigMenu.do");
            curItem.setMessageKey("sample.entry.browse.title");
            adminMenuItems.add(curItem);
        }

        curItem = new AdminMenuItem();
        curItem.setPath("/TestManagementConfigMenu.do");
        curItem.setMessageKey("configuration.test.management");
        adminMenuItems.add(curItem);

        curItem = new AdminMenuItem();
        curItem.setPath("/BatchTestReassignment.do");
        curItem.setMessageKey("configuration.batch.test.reassignment");
        adminMenuItems.add(curItem);

        curItem = new AdminMenuItem();
        curItem.setPath("/MenuStatementConfigMenu.do");
        curItem.setMessageKey("MenuStatementConfig.browse.title");
        adminMenuItems.add(curItem);

        if (adminFields.useField(Field.PATIENT_ENTRY_CONFIGURATION)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/PatientConfigurationMenu.do");
            curItem.setMessageKey("patientEntryConfiguration.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.ResultInformationMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/ResultConfigurationMenu.do");
            curItem.setMessageKey("resultConfiguration.browse.title");
            adminMenuItems.add(curItem);
        }

        // gnr: pivotal 176523804, 176372736
        if (false) {
            if (adminFields.useField(Field.NON_CONFORMITY_CONFIGURATION)) {
                curItem = new AdminMenuItem();
                curItem.setPath("/NonConformityConfigurationMenu.do");
                curItem.setMessageKey("nonConformityConfiguration.browse.title");
                adminMenuItems.add(curItem);
            }
            curItem = new AdminMenuItem();
            curItem.setPath("/ReportConfiguration.do");
            curItem.setMessageKey("banner.menu.reportManagement");
            adminMenuItems.add(curItem);
        }

        if (adminFields.useField(Field.PRINTED_REPORTS_CONFIGURATION)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/PrintedReportsConfigurationMenu.do");
            curItem.setMessageKey("printedReportsConfiguration.browse.title");
            adminMenuItems.add(curItem);
        }

        if (adminFields.useField(Field.WORKPLAN_CONFIGURATION)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/WorkplanConfigurationMenu.do");
            curItem.setMessageKey("workplanConfiguration.browse.title");
            adminMenuItems.add(curItem);
        }

        if (adminFields.useField(Field.TypeOfSamplePanelMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/TypeOfSamplePanelMenu.do");
            curItem.setMessageKey("typeofsample.panel");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.TypeOfSampleTestMenu)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/TypeOfSampleTestMenu.do");
            curItem.setMessageKey("typeofsample.test");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.TestUsageAggregatation)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/TestUsageConfiguration.do");
            curItem.setMessageKey("testusageconfiguration.browse.title");
            adminMenuItems.add(curItem);
        }
        if (adminFields.useField(Field.RESULT_REPORTING_CONFIGURATION)) {
            curItem = new AdminMenuItem();
            curItem.setPath("/ResultReportingConfiguration.do");
            curItem.setMessageKey("resultreporting.browse.title");
            adminMenuItems.add(curItem);
        }
        curItem = new AdminMenuItem();
        curItem.setPath("/BarcodeConfiguration.do");
        curItem.setMessageKey("barcodeconfiguration.browse.title");
        adminMenuItems.add(curItem);

        curItem = new AdminMenuItem();
        curItem.setPath("/ValidationConfigurationMenu.do");
        curItem.setMessageKey("validationconfiguration.browse.title");
        adminMenuItems.add(curItem);

        curItem = new AdminMenuItem();
        curItem.setPath("/ExternalConnectionsMenu.do");
        curItem.setMessageKey("externalConnections.browse.title");
        adminMenuItems.add(curItem);

        curItem = new AdminMenuItem();
        curItem.setPath("/ListPlugins.do");
        curItem.setMessageKey("plugin.menu.list.plugins");
        adminMenuItems.add(curItem);

        if (permissionBase.equals("ROLE")) {
            curItem = new AdminMenuItem();
            curItem.setPath("/UnifiedSystemUserMenu.do");
            curItem.setMessageKey("unifiedSystemUser.browser.title");
            adminMenuItems.add(curItem);
        }

        curItem = new AdminMenuItem();
        curItem.setPath("/TestNotificationConfigMenu.do");
        curItem.setMessageKey("testnotificationconfig.browse.title");
        adminMenuItems.add(curItem);
    }

    @Override
    public List<AdminMenuItem> getActiveItemsSorted() {
        List<AdminMenuItem> sortedMenuItems = new ArrayList<>(adminMenuItems);
        sortedMenuItems.sort(new Comparator<AdminMenuItem>() {

            @Override
            public int compare(AdminMenuItem arg0, AdminMenuItem arg1) {
                return MessageUtil.getMessage(arg0.getMessageKey(), Locale.ENGLISH)
                        .compareTo(MessageUtil.getMessage(arg1.getMessageKey(), Locale.ENGLISH));
            }

        });
        return sortedMenuItems;
    }

    @Override
    public void refreshConfiguration() {
        createActiveList();
    }

}
