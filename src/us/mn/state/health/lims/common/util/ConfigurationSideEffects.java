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
package us.mn.state.health.lims.common.util;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.menu.daoimpl.MenuDAOImpl;
import us.mn.state.health.lims.menu.util.MenuUtil;
import us.mn.state.health.lims.menu.valueholder.Menu;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;


public class ConfigurationSideEffects {
	private static final RoleDAO roleDAO = new RoleDAOImpl();
	private static final SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
	
	
	
	public void siteInformationChanged( SiteInformation siteInformation){
		if( "modify results role".equals(siteInformation.getName())){
			Role modifierRole = roleDAO.getRoleByName("Results modifier");
			
			if( modifierRole != null && modifierRole.getId() != null){
				modifierRole.setActive("true".equals(siteInformation.getValue()));
				modifierRole.setSysUserId(siteInformation.getSysUserId());
				roleDAO.updateData(modifierRole);
			}
				
		}
		
		if("siteNumber".equals(siteInformation.getName())){
			SiteInformation accessionFormat = siteInformationDAO.getSiteInformationByName("acessionFormat");
			if( "SiteYearNum".equals(accessionFormat.getValue())){
				SiteInformation accessionPrefix = siteInformationDAO.getSiteInformationByName("Accession number prefix");
				if( GenericValidator.isBlankOrNull(accessionPrefix.getValue())){
					accessionPrefix.setValue(siteInformation.getValue());
					accessionPrefix.setSysUserId(siteInformation.getSysUserId());
					siteInformationDAO.updateData(accessionPrefix);
				}
			}
		}

//--------------------------
		if("Patient management tab".equals(siteInformation.getName())){
			MenuDAOImpl menuDAO = new MenuDAOImpl();
			boolean active = "true".equals(siteInformation.getValue());

			Menu parentMenu = menuDAO.getMenuByElementId("menu_patient");
			if( parentMenu != null ){
				parentMenu.setIsActive(active);
				menuDAO.updateData( parentMenu);
			}



			Menu menu = menuDAO.getMenuByElementId("menu_patient_add_or_edit");
			if( menu != null ){
				menu.setIsActive( active);
				menuDAO.updateData(menu);
								}
			
			
			Menu parentmenustudy = menuDAO.getMenuByElementId("menu_patient_study");
			if( parentmenustudy != null ){
				parentmenustudy.setIsActive( active);
				menuDAO.updateData(parentmenustudy);
								}
			
			Menu menustudycreate = menuDAO.getMenuByElementId("menu_patient_create");
			if( menustudycreate != null ){
				menustudycreate.setIsActive( active);
				menuDAO.updateData(menustudycreate);
								}
			
			
			Menu menustudycreateinitial = menuDAO.getMenuByElementId("menu_patient_create_initial");
			if( menustudycreateinitial != null ){
				menustudycreateinitial.setIsActive( active);
				menuDAO.updateData(menustudycreateinitial);
								}
			
			Menu menustudycreatedouble = menuDAO.getMenuByElementId("menu_patient_create_double");
			if( menustudycreatedouble != null ){
				menustudycreatedouble.setIsActive( active);
				menuDAO.updateData(menustudycreatedouble);
								}
			
			Menu menustudyedit = menuDAO.getMenuByElementId("menu_patient_edit");
			if( menustudyedit != null ){
				menustudyedit.setIsActive( active);
				menuDAO.updateData(menustudyedit);
								}
			Menu menustudyconsult = menuDAO.getMenuByElementId("menu_patient_consult");
			if( menustudyconsult != null ){
				menustudyconsult.setIsActive( active);
				menuDAO.updateData(menustudyconsult);
								}
			
			MenuUtil.forceRebuild();
		}
//-------- Study menu
		
		//------sample----
		
		if("Study Management tab".equals(siteInformation.getName())){
			MenuDAOImpl menuDAO = new MenuDAOImpl();
			boolean active = "true".equals(siteInformation.getValue());

			Menu parentMenuStudy = menuDAO.getMenuByElementId("menu_sample_create");
			if( parentMenuStudy != null ){
				parentMenuStudy.setIsActive(active);
				menuDAO.updateData( parentMenuStudy);
			}

			Menu menusamplecreateinitial = menuDAO.getMenuByElementId("menu_sample_create_initial");
			if( menusamplecreateinitial != null ){
				menusamplecreateinitial.setIsActive( active);
				menuDAO.updateData(menusamplecreateinitial);
								}
			
			
			Menu menusamplecreatedouble = menuDAO.getMenuByElementId("menu_sample_create_double");
			if( menusamplecreatedouble != null ){
				menusamplecreatedouble.setIsActive( active);
				menuDAO.updateData(menusamplecreatedouble);
								}
			
					
			
			Menu menusampleconsult = menuDAO.getMenuByElementId("menu_sample_consult");
			if( menusampleconsult != null ){
				menusampleconsult.setIsActive( active);
				menuDAO.updateData(menusampleconsult);
						}
			
			//------Patient----
			
			
			Menu menustudycreate2 = menuDAO.getMenuByElementId("menu_patient_create");
			if( menustudycreate2 != null ){
				menustudycreate2.setIsActive( active);
				menuDAO.updateData(menustudycreate2);
								}
			
			
			Menu menustudycreateinitial2 = menuDAO.getMenuByElementId("menu_patient_create_initial");
			if( menustudycreateinitial2 != null ){
				menustudycreateinitial2.setIsActive( active);
				menuDAO.updateData(menustudycreateinitial2);
								}
			
			Menu menustudycreatedouble2 = menuDAO.getMenuByElementId("menu_patient_create_double");
			if( menustudycreatedouble2 != null ){
				menustudycreatedouble2.setIsActive( active);
				menuDAO.updateData(menustudycreatedouble2);
								}
			
			Menu menustudyedit2 = menuDAO.getMenuByElementId("menu_patient_edit");
			if( menustudyedit2 != null ){
				menustudyedit2.setIsActive( active);
				menuDAO.updateData(menustudyedit2);
								}
			
			Menu menustudyconsult2 = menuDAO.getMenuByElementId("menu_patient_consult");
			if( menustudyconsult2 != null ){
				menustudyconsult2.setIsActive( active);
				menuDAO.updateData(menustudyconsult2);
								}
			//------report----
			
			Menu menureportstudy = menuDAO.getMenuByElementId("menu_reports_study");
				if( menureportstudy != null ){
					menureportstudy.setIsActive( active);
					menuDAO.updateData(menureportstudy); }
			
		    Menu menureportspatients = menuDAO.getMenuByElementId("menu_reports_patients");
				if( menureportspatients != null ){
					menureportspatients.setIsActive( active);
					menuDAO.updateData(menureportspatients); }	
					
		    Menu menureportsarv = menuDAO.getMenuByElementId("menu_reports_arv");
				if( menureportsarv != null ){
					menureportsarv.setIsActive( active);
					menuDAO.updateData(menureportsarv); }
					
		    Menu menureportsarvinitial1 = menuDAO.getMenuByElementId("menu_reports_arv_initial1");
				if( menureportsarvinitial1 != null ){
					menureportsarvinitial1.setIsActive( active);
					menuDAO.updateData(menureportsarvinitial1); }					
			
			Menu menureportsarvinitial2 = menuDAO.getMenuByElementId("menu_reports_arv_initial2");
				if( menureportsarvinitial2 != null ){
					menureportsarvinitial2.setIsActive( active);
					menuDAO.updateData(menureportsarvinitial2); }
						
			Menu menureportarvfollowup1 = menuDAO.getMenuByElementId("menu_reports_arv_followup1");
				if( menureportarvfollowup1 != null ){
					menureportarvfollowup1.setIsActive( active);
					menuDAO.updateData(menureportarvfollowup1); }	
								
			Menu menureportarvfollowup2 = menuDAO.getMenuByElementId("menu_reports_arv_followup2");
				if( menureportarvfollowup2 != null ){
					menureportarvfollowup2.setIsActive( active);
					menuDAO.updateData(menureportarvfollowup2); }
								
			Menu menureportseid = menuDAO.getMenuByElementId("menu_reports_eid");
				if( menureportseid != null ){
					menureportseid.setIsActive( active);
					menuDAO.updateData(menureportseid); }		
									
									
			Menu menureporteidversion1 = menuDAO.getMenuByElementId("menu_reports_eid_version1");
				if( menureporteidversion1 != null ){
					menureporteidversion1.setIsActive( active);
					menuDAO.updateData(menureporteidversion1); }
									
			Menu menureporteidversion2 = menuDAO.getMenuByElementId("menu_reports_eid_version2");
				if( menureporteidversion2 != null ){
					menureporteidversion2.setIsActive( active);
					menuDAO.updateData(menureporteidversion2); }	
											
			Menu menureportsindeterminate = menuDAO.getMenuByElementId("menu_reports_indeterminate");
				if( menureportsindeterminate != null ){
					menureportsindeterminate.setIsActive( active);
					menuDAO.updateData(menureportsindeterminate); }
											
			Menu menureportsindeterminateversion1 = menuDAO.getMenuByElementId("menu_reports_indeterminate_version1");
				if( menureportsindeterminateversion1 != null ){
					menureportsindeterminateversion1.setIsActive( active);
					menuDAO.updateData(menureportsindeterminateversion1); }					
	
			Menu menureportsindeterminateversion2 = menuDAO.getMenuByElementId("menu_reports_indeterminate_version2");
				if( menureportsindeterminateversion2 != null ){
					menureportsindeterminateversion2.setIsActive( active);
					menuDAO.updateData(menureportsindeterminateversion2); }
				
			Menu menureportsindeterminatelocation = menuDAO.getMenuByElementId("menu_reports_indeterminate_location");
				if( menureportsindeterminatelocation != null ){
					menureportsindeterminatelocation.setIsActive( active);
					menuDAO.updateData(menureportsindeterminatelocation); }	
														
			Menu menureportspecial = menuDAO.getMenuByElementId("menu_reports_special");
				if( menureportspecial != null ){
					menureportspecial.setIsActive( active);
					menuDAO.updateData(menureportspecial); }
														
			Menu menureportspatientcollection = menuDAO.getMenuByElementId("menu_reports_patient_collection");
				if( menureportspatientcollection != null ){
					menureportspatientcollection.setIsActive( active);
					menuDAO.updateData(menureportspatientcollection); }									

			Menu menureportsassociated = menuDAO.getMenuByElementId("menu_reports_patient_associated");
				if( menureportsassociated != null ){
					menureportsassociated.setIsActive( active);
					menuDAO.updateData(menureportsassociated); }
															
			Menu menureportsindicator = menuDAO.getMenuByElementId("menu_reports_indicator");
				if( menureportsindicator != null ){
					menureportsindicator.setIsActive( active);
					menuDAO.updateData(menureportsindicator); }	
																	
			Menu menureportsindicatorperformance = menuDAO.getMenuByElementId("menu_reports_indicator_performance");
				if( menureportsindicatorperformance != null ){
					menureportsindicatorperformance.setIsActive( active);
					menuDAO.updateData(menureportsindicatorperformance); }
																	
			Menu menureportsvalidationbacklog = menuDAO.getMenuByElementId("menu_reports_validation_backlog.study");
				if( menureportsvalidationbacklog != null ){
					menureportsvalidationbacklog.setIsActive( active);
					menuDAO.updateData(menureportsvalidationbacklog); }					
															
			Menu menureportsnonconformitystudy = menuDAO.getMenuByElementId("menu_reports_nonconformity.study");
				if( menureportsnonconformitystudy != null ){
					menureportsnonconformitystudy.setIsActive( active);
					menuDAO.updateData(menureportsnonconformitystudy); }
																		
			Menu menureportnonconformitydatestudy = menuDAO.getMenuByElementId("menu_reports_nonconformity_date.study");
				if( menureportnonconformitydatestudy != null ){
					menureportnonconformitydatestudy.setIsActive( active);
					menuDAO.updateData(menureportnonconformitydatestudy); }	
																				
			Menu menureportsnonconformitysection = menuDAO.getMenuByElementId("menu_reports_nonconformity_section.study");
				if( menureportsnonconformitysection != null ){
					menureportsnonconformitysection.setIsActive( active);
					menuDAO.updateData(menureportsnonconformitysection); }
																				
			Menu menureportsnonconformitynotification = menuDAO.getMenuByElementId("menu_reports_nonconformity_notification.study");
				if( menureportsnonconformitynotification != null ){
					menureportsnonconformitynotification.setIsActive( active);
					menuDAO.updateData(menureportsnonconformitynotification); }		
				
									
			Menu menureportsfolowrequired = menuDAO.getMenuByElementId("menu_reports_followupRequired_ByLocation.study");
				if( menureportsfolowrequired != null ){
					menureportsfolowrequired.setIsActive( active);
					menuDAO.updateData(menureportsfolowrequired); }
									
			Menu menureportsexport = menuDAO.getMenuByElementId("menu_reports_export");
				if( menureportsexport != null ){
					menureportsexport.setIsActive( active);
					menuDAO.updateData(menureportsexport); }	
											
			Menu menureportsauditTrail = menuDAO.getMenuByElementId("menu_reports_auditTrail.study");
				if( menureportsauditTrail != null ){
					menureportsauditTrail.setIsActive( active);
					menuDAO.updateData(menureportsauditTrail); }
											
			Menu menureportsarvall = menuDAO.getMenuByElementId("menu_reports_arv_all");
				if( menureportsarvall != null ){
					menureportsarvall.setIsActive( active);
					menuDAO.updateData(menureportsarvall); }					
									
			Menu menureportsvl = menuDAO.getMenuByElementId("menu_reports_vl");
				if( menureportsvl != null ){
					menureportsvl.setIsActive( active);
					menuDAO.updateData(menureportsvl); }
												
			Menu menureportsvlversion1 = menuDAO.getMenuByElementId("menu_reports_vl_version1");
				if( menureportsvlversion1 != null ){
					menureportsvlversion1.setIsActive( active);
					menuDAO.updateData(menureportsvlversion1); }	
				
				
			Menu menunonconformitylabno = menuDAO.getMenuByElementId("menu_reports_nonconformity.Labno");
				if( menunonconformitylabno != null ){
					menunonconformitylabno.setIsActive( active);
					menuDAO.updateData(menunonconformitylabno); }	
				
				
				
				
			
				//------validation----		
				
			Menu menuvalidationstudy = menuDAO.getMenuByElementId("menu_resultvalidation_study");
				if( menuvalidationstudy != null ){
					menuvalidationstudy.setIsActive( active);
					menuDAO.updateData(menuvalidationstudy); }
				
			Menu menuvalidationimmuno = menuDAO.getMenuByElementId("menu_resultvalidation_immunology");
				if( menuvalidationimmuno != null ){
					menuvalidationimmuno.setIsActive( active);
					menuDAO.updateData(menuvalidationimmuno); }
				
			Menu menuvalidationbio = menuDAO.getMenuByElementId("menu_resultvalidation_biochemistry");
				if( menuvalidationbio != null ){
					menuvalidationbio.setIsActive( active);
					menuDAO.updateData(menuvalidationbio); }	
				
			Menu menuvalidationsero = menuDAO.getMenuByElementId("menu_resultvalidation_serology");
				if( menuvalidationsero != null ){
					menuvalidationsero.setIsActive( active);
					menuDAO.updateData(menuvalidationsero); }				
				

			Menu menuvalidationdnapcr = menuDAO.getMenuByElementId("menu_resultvalidation_dnapcr");
				if( menuvalidationdnapcr != null ){
					menuvalidationdnapcr.setIsActive( active);
					menuDAO.updateData(menuvalidationdnapcr); }
				
			Menu menuvalidationvirology = menuDAO.getMenuByElementId("menu_resultvalidation_virology");
				if( menuvalidationvirology != null ){
					menuvalidationvirology.setIsActive( active);
					menuDAO.updateData(menuvalidationvirology); }
				
			Menu menuvalidationVL = menuDAO.getMenuByElementId("menu_resultvalidation_viralload");
				if( menuvalidationVL != null ){
					menuvalidationVL.setIsActive( active);
					menuDAO.updateData(menuvalidationVL); }	
				
			Menu menuvalidationgeno= menuDAO.getMenuByElementId("menu_resultvalidation_genotyping");
				if( menuvalidationgeno != null ){
					menuvalidationgeno.setIsActive( active);
					menuDAO.updateData(menuvalidationgeno); }		
				
		
			MenuUtil.forceRebuild();
		}	

//--------	
	}

}
