/**
 bd* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package org.openelisglobal.common.util;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuUtil;
import org.openelisglobal.menu.valueholder.Menu;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.role.valueholder.Role;
import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationSideEffects {
    @Autowired
    private RoleService roleService;
    @Autowired
    private SiteInformationService siteInformationService;
    @Autowired
    private MenuService menuService;

    public void siteInformationChanged(SiteInformation siteInformation) {
        if (Property.DEFAULT_LANG_LOCALE.getName().equals(siteInformation.getName())) {
            // this is done in SiteInformationController.java as we need to have the user
            // request to change the locale
        }

        if (Property.roleRequiredForModifyResults.getName().equals(siteInformation.getName())) {
            Role modifierRole = roleService.getRoleByName("Results modifier");

            if (modifierRole != null && modifierRole.getId() != null) {
                modifierRole.setActive("true".equals(siteInformation.getValue()));
                modifierRole.setSysUserId(siteInformation.getSysUserId());
                roleService.update(modifierRole);
            }

        }

        if (Property.SiteCode.getName().equals(siteInformation.getName())) {
            SiteInformation accessionFormat = siteInformationService.getSiteInformationByName("acessionFormat");
            if ("SiteYearNum".equals(accessionFormat.getValue())) {
                SiteInformation accessionPrefix = siteInformationService
                        .getSiteInformationByName("Accession number prefix");
                if (GenericValidator.isBlankOrNull(accessionPrefix.getValue())) {
                    accessionPrefix.setValue(siteInformation.getValue());
                    accessionPrefix.setSysUserId(siteInformation.getSysUserId());
                    siteInformationService.update(accessionPrefix);
                }
            }
        }

        if ("Non Conformity tab".equals(siteInformation.getName())) {
            boolean active = "true".equals(siteInformation.getValue());

            Menu parentMenu = menuService.getMenuByElementId("menu_nonconformity");
            if (parentMenu != null) {
                parentMenu.setIsActive(active);
                menuService.update(parentMenu);
            }

            MenuUtil.forceRebuild();
        }

//--------------------------
        if ("Patient management tab".equals(siteInformation.getName())) {
            boolean active = "true".equals(siteInformation.getValue());

            Menu parentMenu = menuService.getMenuByElementId("menu_patient");
            if (parentMenu != null) {
                parentMenu.setIsActive(active);
                menuService.update(parentMenu);
            }

            Menu menu = menuService.getMenuByElementId("menu_patient_add_or_edit");
            if (menu != null) {
                menu.setIsActive(active);
                menuService.update(menu);
            }

            Menu parentmenustudy = menuService.getMenuByElementId("menu_patient_study");
            if (parentmenustudy != null) {
                parentmenustudy.setIsActive(active);
                menuService.update(parentmenustudy);
            }

            Menu menustudycreate = menuService.getMenuByElementId("menu_patient_create");
            if (menustudycreate != null) {
                menustudycreate.setIsActive(active);
                menuService.update(menustudycreate);
            }

            Menu menustudycreateinitial = menuService.getMenuByElementId("menu_patient_create_initial");
            if (menustudycreateinitial != null) {
                menustudycreateinitial.setIsActive(active);
                menuService.update(menustudycreateinitial);
            }

            Menu menustudycreatedouble = menuService.getMenuByElementId("menu_patient_create_double");
            if (menustudycreatedouble != null) {
                menustudycreatedouble.setIsActive(active);
                menuService.update(menustudycreatedouble);
            }

            Menu menustudyedit = menuService.getMenuByElementId("menu_patient_edit");
            if (menustudyedit != null) {
                menustudyedit.setIsActive(active);
                menuService.update(menustudyedit);
            }
            Menu menustudyconsult = menuService.getMenuByElementId("menu_patient_consult");
            if (menustudyconsult != null) {
                menustudyconsult.setIsActive(active);
                menuService.update(menustudyconsult);
            }

            MenuUtil.forceRebuild();
        }
//-------- Study menu

        // ------sample----

        if ("Study Management tab".equals(siteInformation.getName())) {
            boolean active = "true".equals(siteInformation.getValue());

            Menu parentMenuStudy = menuService.getMenuByElementId("menu_sample_create");
            if (parentMenuStudy != null) {
                parentMenuStudy.setIsActive(active);
                menuService.update(parentMenuStudy);
            }

            Menu menusamplecreateinitial = menuService.getMenuByElementId("menu_sample_create_initial");
            if (menusamplecreateinitial != null) {
                menusamplecreateinitial.setIsActive(active);
                menuService.update(menusamplecreateinitial);
            }

            Menu menusamplecreatedouble = menuService.getMenuByElementId("menu_sample_create_double");
            if (menusamplecreatedouble != null) {
                menusamplecreatedouble.setIsActive(active);
                menuService.update(menusamplecreatedouble);
            }

            Menu menusampleconsult = menuService.getMenuByElementId("menu_sample_consult");
            if (menusampleconsult != null) {
                menusampleconsult.setIsActive(active);
                menuService.update(menusampleconsult);
            }

            // ------Patient----

            Menu menustudycreate2 = menuService.getMenuByElementId("menu_patient_create");
            if (menustudycreate2 != null) {
                menustudycreate2.setIsActive(active);
                menuService.update(menustudycreate2);
            }

            Menu menustudycreateinitial2 = menuService.getMenuByElementId("menu_patient_create_initial");
            if (menustudycreateinitial2 != null) {
                menustudycreateinitial2.setIsActive(active);
                menuService.update(menustudycreateinitial2);
            }

            Menu menustudycreatedouble2 = menuService.getMenuByElementId("menu_patient_create_double");
            if (menustudycreatedouble2 != null) {
                menustudycreatedouble2.setIsActive(active);
                menuService.update(menustudycreatedouble2);
            }

            Menu menustudyedit2 = menuService.getMenuByElementId("menu_patient_edit");
            if (menustudyedit2 != null) {
                menustudyedit2.setIsActive(active);
                menuService.update(menustudyedit2);
            }

            Menu menustudyconsult2 = menuService.getMenuByElementId("menu_patient_consult");
            if (menustudyconsult2 != null) {
                menustudyconsult2.setIsActive(active);
                menuService.update(menustudyconsult2);
            }
            // ------report----

            Menu menureportstudy = menuService.getMenuByElementId("menu_reports_study");
            if (menureportstudy != null) {
                menureportstudy.setIsActive(active);
                menuService.update(menureportstudy);
            }

            Menu menureportspatients = menuService.getMenuByElementId("menu_reports_patients");
            if (menureportspatients != null) {
                menureportspatients.setIsActive(active);
                menuService.update(menureportspatients);
            }

            Menu menureportsarv = menuService.getMenuByElementId("menu_reports_arv");
            if (menureportsarv != null) {
                menureportsarv.setIsActive(active);
                menuService.update(menureportsarv);
            }

            Menu menureportsarvinitial1 = menuService.getMenuByElementId("menu_reports_arv_initial1");
            if (menureportsarvinitial1 != null) {
                menureportsarvinitial1.setIsActive(active);
                menuService.update(menureportsarvinitial1);
            }

            Menu menureportsarvinitial2 = menuService.getMenuByElementId("menu_reports_arv_initial2");
            if (menureportsarvinitial2 != null) {
                menureportsarvinitial2.setIsActive(active);
                menuService.update(menureportsarvinitial2);
            }

            Menu menureportarvfollowup1 = menuService.getMenuByElementId("menu_reports_arv_followup1");
            if (menureportarvfollowup1 != null) {
                menureportarvfollowup1.setIsActive(active);
                menuService.update(menureportarvfollowup1);
            }

            Menu menureportarvfollowup2 = menuService.getMenuByElementId("menu_reports_arv_followup2");
            if (menureportarvfollowup2 != null) {
                menureportarvfollowup2.setIsActive(active);
                menuService.update(menureportarvfollowup2);
            }

            Menu menureportseid = menuService.getMenuByElementId("menu_reports_eid");
            if (menureportseid != null) {
                menureportseid.setIsActive(active);
                menuService.update(menureportseid);
            }

            Menu menureporteidversion1 = menuService.getMenuByElementId("menu_reports_eid_version1");
            if (menureporteidversion1 != null) {
                menureporteidversion1.setIsActive(active);
                menuService.update(menureporteidversion1);
            }

            Menu menureporteidversion2 = menuService.getMenuByElementId("menu_reports_eid_version2");
            if (menureporteidversion2 != null) {
                menureporteidversion2.setIsActive(active);
                menuService.update(menureporteidversion2);
            }

            Menu menureportsindeterminate = menuService.getMenuByElementId("menu_reports_indeterminate");
            if (menureportsindeterminate != null) {
                menureportsindeterminate.setIsActive(active);
                menuService.update(menureportsindeterminate);
            }

            Menu menureportsindeterminateversion1 = menuService
                    .getMenuByElementId("menu_reports_indeterminate_version1");
            if (menureportsindeterminateversion1 != null) {
                menureportsindeterminateversion1.setIsActive(active);
                menuService.update(menureportsindeterminateversion1);
            }

            Menu menureportsindeterminateversion2 = menuService
                    .getMenuByElementId("menu_reports_indeterminate_version2");
            if (menureportsindeterminateversion2 != null) {
                menureportsindeterminateversion2.setIsActive(active);
                menuService.update(menureportsindeterminateversion2);
            }

            Menu menureportsindeterminatelocation = menuService
                    .getMenuByElementId("menu_reports_indeterminate_location");
            if (menureportsindeterminatelocation != null) {
                menureportsindeterminatelocation.setIsActive(active);
                menuService.update(menureportsindeterminatelocation);
            }

            Menu menureportspecial = menuService.getMenuByElementId("menu_reports_special");
            if (menureportspecial != null) {
                menureportspecial.setIsActive(active);
                menuService.update(menureportspecial);
            }

            Menu menureportspatientcollection = menuService.getMenuByElementId("menu_reports_patient_collection");
            if (menureportspatientcollection != null) {
                menureportspatientcollection.setIsActive(active);
                menuService.update(menureportspatientcollection);
            }

            Menu menureportsassociated = menuService.getMenuByElementId("menu_reports_patient_associated");
            if (menureportsassociated != null) {
                menureportsassociated.setIsActive(active);
                menuService.update(menureportsassociated);
            }

            Menu menureportsindicator = menuService.getMenuByElementId("menu_reports_indicator");
            if (menureportsindicator != null) {
                menureportsindicator.setIsActive(active);
                menuService.update(menureportsindicator);
            }

            Menu menureportsindicatorperformance = menuService.getMenuByElementId("menu_reports_indicator_performance");
            if (menureportsindicatorperformance != null) {
                menureportsindicatorperformance.setIsActive(active);
                menuService.update(menureportsindicatorperformance);
            }

            Menu menureportsvalidationbacklog = menuService.getMenuByElementId("menu_reports_validation_backlog.study");
            if (menureportsvalidationbacklog != null) {
                menureportsvalidationbacklog.setIsActive(active);
                menuService.update(menureportsvalidationbacklog);
            }

            Menu menureportsnonconformitystudy = menuService.getMenuByElementId("menu_reports_nonconformity.study");
            if (menureportsnonconformitystudy != null) {
                menureportsnonconformitystudy.setIsActive(active);
                menuService.update(menureportsnonconformitystudy);
            }

            Menu menureportnonconformitydatestudy = menuService
                    .getMenuByElementId("menu_reports_nonconformity_date.study");
            if (menureportnonconformitydatestudy != null) {
                menureportnonconformitydatestudy.setIsActive(active);
                menuService.update(menureportnonconformitydatestudy);
            }

            Menu menureportsnonconformitysection = menuService
                    .getMenuByElementId("menu_reports_nonconformity_section.study");
            if (menureportsnonconformitysection != null) {
                menureportsnonconformitysection.setIsActive(active);
                menuService.update(menureportsnonconformitysection);
            }

            Menu menureportsnonconformitynotification = menuService
                    .getMenuByElementId("menu_reports_nonconformity_notification.study");
            if (menureportsnonconformitynotification != null) {
                menureportsnonconformitynotification.setIsActive(active);
                menuService.update(menureportsnonconformitynotification);
            }

            Menu menureportsfolowrequired = menuService
                    .getMenuByElementId("menu_reports_followupRequired_ByLocation.study");
            if (menureportsfolowrequired != null) {
                menureportsfolowrequired.setIsActive(active);
                menuService.update(menureportsfolowrequired);
            }

            Menu menureportsexport = menuService.getMenuByElementId("menu_reports_export");
            if (menureportsexport != null) {
                menureportsexport.setIsActive(active);
                menuService.update(menureportsexport);
            }

            Menu menureportsauditTrail = menuService.getMenuByElementId("menu_reports_auditTrail.study");
            if (menureportsauditTrail != null) {
                menureportsauditTrail.setIsActive(active);
                menuService.update(menureportsauditTrail);
            }

            Menu menureportsarvall = menuService.getMenuByElementId("menu_reports_arv_all");
            if (menureportsarvall != null) {
                menureportsarvall.setIsActive(active);
                menuService.update(menureportsarvall);
            }

            Menu menureportsvl = menuService.getMenuByElementId("menu_reports_vl");
            if (menureportsvl != null) {
                menureportsvl.setIsActive(active);
                menuService.update(menureportsvl);
            }

            Menu menureportsvlversion1 = menuService.getMenuByElementId("menu_reports_vl_version1");
            if (menureportsvlversion1 != null) {
                menureportsvlversion1.setIsActive(active);
                menuService.update(menureportsvlversion1);
            }

            Menu menunonconformitylabno = menuService.getMenuByElementId("menu_reports_nonconformity.Labno");
            if (menunonconformitylabno != null) {
                menunonconformitylabno.setIsActive(active);
                menuService.update(menunonconformitylabno);
            }

            // ------validation----

            Menu menuvalidationstudy = menuService.getMenuByElementId("menu_resultvalidation_study");
            if (menuvalidationstudy != null) {
                menuvalidationstudy.setIsActive(active);
                menuService.update(menuvalidationstudy);
            }

            Menu menuvalidationimmuno = menuService.getMenuByElementId("menu_resultvalidation_immunology");
            if (menuvalidationimmuno != null) {
                menuvalidationimmuno.setIsActive(active);
                menuService.update(menuvalidationimmuno);
            }

            Menu menuvalidationbio = menuService.getMenuByElementId("menu_resultvalidation_biochemistry");
            if (menuvalidationbio != null) {
                menuvalidationbio.setIsActive(active);
                menuService.update(menuvalidationbio);
            }

            Menu menuvalidationsero = menuService.getMenuByElementId("menu_resultvalidation_serology");
            if (menuvalidationsero != null) {
                menuvalidationsero.setIsActive(active);
                menuService.update(menuvalidationsero);
            }

            Menu menuvalidationdnapcr = menuService.getMenuByElementId("menu_resultvalidation_dnapcr");
            if (menuvalidationdnapcr != null) {
                menuvalidationdnapcr.setIsActive(active);
                menuService.update(menuvalidationdnapcr);
            }

            Menu menuvalidationvirology = menuService.getMenuByElementId("menu_resultvalidation_virology");
            if (menuvalidationvirology != null) {
                menuvalidationvirology.setIsActive(active);
                menuService.update(menuvalidationvirology);
            }

            Menu menuvalidationVL = menuService.getMenuByElementId("menu_resultvalidation_viralload");
            if (menuvalidationVL != null) {
                menuvalidationVL.setIsActive(active);
                menuService.update(menuvalidationVL);
            }

            Menu menuvalidationgeno = menuService.getMenuByElementId("menu_resultvalidation_genotyping");
            if (menuvalidationgeno != null) {
                menuvalidationgeno.setIsActive(active);
                menuService.update(menuvalidationgeno);
            }

            MenuUtil.forceRebuild();
        }

//--------
    }

}
