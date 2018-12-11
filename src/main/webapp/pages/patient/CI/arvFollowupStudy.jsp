<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
	        us.mn.state.health.lims.common.formfields.FormFields,
	        org.apache.commons.httpclient.NameValuePair,
	        us.mn.state.health.lims.common.util.DateUtil"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

/**
 * an object gathers all the methods about tweeking the followup ARV form together in one place.
 **/
function ArvFollowupProjectChecker() {

    this.idPre = "farv.";
	this.displayAnyCurrentDiseases = function () {
	    var field = $("farv.anyCurrentDiseases");
		showElements((field.selectedIndex == 1) , "farv.currentDiseasesRow0,farv.currentDiseasesRow1,farv.currentDiseasesRow2,farv.currentDiseasesRow3,farv.currentDiseasesRow4,farv.currentDiseasesRow5,farv.currentDiseasesRow6,farv.currentDiseasesRow7,farv.currentDiseasesRow8,farv.currentDiseasesRow9,farv.currentDiseasesRow" );
		this.displayCurrentDiseasesOther($('farv.currentDiseases'), (field.selectedIndex == 1)?1:"never");
	}

	this.displayCurrentDiseasesOther = function (field, enableIndex) {
		enableSelectedRequiredSubfield(field, $('farv.currentDiseasesValue'), 1, 'farv.currentDiseasesValueRow')
	}

	
	this.checkInterruptedARVTreatment = function () {
		clearFormElements("farv.priorARVTreatment,farv.arvTreatmentChange");
		this.displayedByInterruptedARVTreatment();
		this.checkPriorARVTreatment();
	}
	
	this.displayedByInterruptedARVTreatment = function () {
	    var field = $("farv.interruptedARVTreatment");
		showElements( (field.selectedIndex == 2), "farv.priorARVTreatmentRow,farv.arvTreatmentChangeRow" );
		this.displayedByPriorARVTreatment(1);
	}
	
	this.checkPriorARVTreatment = function () {
		clearFormElements("farv.priorARVTreatmentINNs0,farv.priorARVTreatmentINNs1,farv.priorARVTreatmentINNs2,farv.priorARVTreatmentINNs3");
		this.displayedByPriorARVTreatment(1);
		clearFormElements("farv.arvTreatmentAnyAdverseEffects");
		this.displayedByArvTreatmentAnyAdverseEffects();
	}
	
	this.displayedByPriorARVTreatment = function (enableIndex) {
		var field = $("farv.priorARVTreatment");
		showElements( (field.selectedIndex == enableIndex), "farv.arvTreatmentAnyAdverseEffectsRow,farv.onGoingARVTreatmentINNsRow,farv.priorARVTreatmentINNRow0,farv.priorARVTreatmentINNRow1,farv.priorARVTreatmentINNRow2,farv.priorARVTreatmentINNRow3");
		this.displayedByArvTreatmentAnyAdverseEffects(1/*(field.selectedIndex == 1)?1:"never"*/);
	}
	
	this.checkArvTreatmentAnyAdverseEffects = function() {
		clearFormElements( "farv.arvTreatmentAdvEffType0,farv.arvTreatmentAdvEffType1,farv.arvTreatmentAdvEffType2,farv.arvTreatmentAdvEffType3");
		clearFormElements( "farv.arvTreatmentAdvEffGrd0,farv.arvTreatmentAdvEffGrd1,farv.arvTreatmentAdvEffGrd2,farv.arvTreatmentAdvEffGrd3");
		this.displayedByArvTreatmentAnyAdverseEffects(1);
	}

	this.displayedByArvTreatmentAnyAdverseEffects = function (enableIndex) {
		var field = $("farv.arvTreatmentAnyAdverseEffects");
		showElements( (field.selectedIndex == enableIndex), "farv.arvTreatmentAdverseEffectsRow0,farv.arvTreatmentAdverseEffectsRow1,farv.arvTreatmentAdverseEffectsRow2,farv.arvTreatmentAdverseEffectsRow3");
	}

	this.displayARVTreatmentNew = function () { /** called from SUI 24 **/
	    var field1 = $("farv.arvTreatmentNew");
		showElements( (field1.selectedIndex == 1), "farv.arvTreatmentRegimeRow" );
		showElements( (field1.selectedIndex == 1), "farv.prescribedARVTreatmentINNsRow,farv.futureARVTreatmentINNsRow0,farv.futureARVTreatmentINNsRow1,farv.futureARVTreatmentINNsRow2,farv.futureARVTreatmentINNsRow3" );
	}

	this.displayCotriTreatment = function () {
	    var field = $("farv.cotrimoxazoleTreatment");
		showElements( (field.selectedIndex == 1), "farv.cotrimoxazoleTreatmentAnyAdverseEffectsRow");
		this.displayCotriAdverseEffects();
	}

	this.displayCotriAdverseEffects = function ( field, enableIndex ) {
		var field = $('farv.cotrimoxazoleTreatAnyAdvEff');
		showElements( (field.selectedIndex == 1), "farv.cotrimoxazoleTreatAdvEffRow0,farv.cotrimoxazoleTreatAdvEffRow1,farv.cotrimoxazoleTreatAdvEffRow2,farv.cotrimoxazoleTreatAdvEffRow3");
	}
	this.displayAny2ndTreatment = function () {
		var field = $('farv.anySecondaryTreatment');
		showElements( (field.selectedIndex == 1), "farv.secondaryTreatmentRow");
	}

	/**
	 * Check that all hidden and disabled fields are set in the right state.
	 **/
	this.refresh = function () {
		this.refreshBase();		
		this.displayAnyCurrentDiseases();
		this.displayedByInterruptedARVTreatment();
		this.displayARVTreatmentNew();
		this.displayCotriTreatment();
		this.displayAny2ndTreatment();
	}

	var only2CharsComparator = new function () {
	    this.compare = function(existing, newVal) {
	    	if ( existing == undefined ) {
	    		return newVal == null || newVal == "";
	    	} else {
				return existing.substring(0,2) == newVal.substring(0,2);
			}
		}
	}

	this.checkFamilyName = function (blanksAllowed) {
		makeDirty();
		compareFieldToExisting( this.idPre + "patientFamilyName", false, patientLoader, blanksAllowed, "lastName", only2CharsComparator);
	}

	this.checkFirstNames = function (blanksAllowed) {
		makeDirty();
		compareFieldToExisting( this.idPre + "patientFirstNames", false, patientLoader, blanksAllowed, "firstName", only2CharsComparator);
	}

}

ArvFollowupProjectChecker.prototype = new BaseProjectChecker();
/// the object which knows about Followup ARV questions and which fields to show etc.
farv = new ArvFollowupProjectChecker();

</script>

<h2>
	<bean:message key="sample.entry.project.followupARV.title" />
</h2>
<table style="width:100%">

	<tr>
		<td class="required">*</td>
		<td>
			<bean:message key="sample.entry.project.receivedDate"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="receivedDateForDisplay"
				onkeyup="addDateSlashes(this, event);"
				onchange="farv.checkReceivedDate(false);"
				styleClass="text"
				styleId="farv.receivedDateForDisplay" maxlength="10"/>
				<div id="farv.receivedDateForDisplayMessage" class="blank" />
		</td>
	</tr>
	<tr><!-- SUI 01 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.interviewDate" />&nbsp;<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="<%=formName%>" property="interviewDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="farv.checkInterviewDate(false);"
				styleClass="text" styleId="farv.interviewDate" maxlength="10"/>
			<div id="farv.interviewDateMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 02 -->
		<td class="required">+</td>
		<td class="">
			<bean:message key="patient.subject.number"/>
		</td>
		<td>
			<app:text name="<%=formName%>" property="subjectNumber"
					  onchange="farv.checkSubjectNumber(true);"
					  styleId="farv.subjectNumber"
					  styleClass="text"
					  maxlength="7"/>
			<div id="farv.subjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td class="required">+</td>
		<td><bean:message key="patient.site.subject.number"/></td>
		<td>
			<app:text name="<%=formName%>" property="siteSubjectNumber"
				styleId="farv.siteSubjectNumber" styleClass="text"
				onchange="farv.checkSiteSubjectNumber(true, false); makeDirty();"
			/>
			<div id="farv.siteSubjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 03 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.labNo" />
		</td>
		<td>
			<div class="blank"><bean:message key="sample.entry.project.LART"/></div>
			<INPUT type="text" name="farv.labNoForDisplay" id="farv.labNoForDisplay" size="5" class="text"
			   	onchange="handleLabNoChange( this, '<bean:message key="sample.entry.project.LART"/>', false ); makeDirty();"
			   	maxlength="5" />
		  	<app:text name="<%=formName%>" property="labNo"
				styleClass="text" style="display: none;"
				styleId="farv.labNo" />
			<div id="farv.labNoMessage" class="blank" />
		</td>
		<td>
		</td>
	</tr>
	<tr> <!-- SUI 04 -->
		<td></td>
		<td>
			<bean:message key="patient.project.centerName" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="centerName"
				styleId="farv.centerName" onchange="farv.checkCenterName(false)">
				<app:optionsCollection name="<%=formName%>"
					property="organizationTypeLists.ARV_ORGS_BY_NAME.list" label="organizationName"
					value="id" />
			</html:select>
			<div id="farv.centerNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 05 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.centerCode" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="centerCode" styleId="farv.centerCode"
					 onchange="farv.checkCenterCode(false)" >
				<app:optionsCollection name="<%=formName%>" property="organizationTypeLists.ARV_ORGS.list" label="doubleName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr> <!-- SUI 06 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientFamilyName" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="lastName"
				onchange="farv.checkFamilyName(true)" maxlength="2" styleClass="text" styleId="farv.patientFamilyName" />
			<div id="farv.patientFamilyNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 07 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientFirstNames" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="firstName"
				onchange="farv.checkFirstNames(true)" maxlength="2" styleClass="text" styleId="farv.patientFirstNames" />
			<div id="patientFirstNamesMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 08 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.gender" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="gender"
					 onchange="farv.checkGender(false)" styleId="farv.gender"  >
				<app:optionsCollection name="<%=formName%>" property="formLists.GENDERS"
					label="localizedName" value="genderType" />
			</html:select>
			<div id="farv.genderMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 09 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.dateOfBirth" />&nbsp;<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="<%=formName%>" property="birthDateForDisplay"
				onkeyup="addDateSlashes(this, event);"
				onchange="farv.checkDateOfBirth(false)"	styleClass="text" styleId="farv.dateOfBirth" maxlength="10"/>
			<div id="farv.dateOfBirthMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
			<td ></td>
			<td>
				<bean:message  key="patient.age" />
			</td>
			<td>
				<label for="farv.age" ><bean:message  key="label.year" /></label>
				<INPUT type="text" name="ageYear" id="farv.age" size="3"
				   	onchange="farv.checkAge( this, true, 'year' );"
				   	maxlength="2" />
				<div id="farv.ageMessage" class="blank" ></div>
			</td>
	</tr>
	<tr> <!-- SUI 10 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientWeight" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.patientWeight"
				onchange="compareAllObservationHistoryFields(true);" styleClass="text" styleId="farv.patientWeight" maxlength="3"/>
			<div id="farv.patientWeightMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 11 -->
		<td></td>
		<td>
			<bean:message key="patient.project.karnofskyScore" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.karnofskyScore"
				onchange="compareAllObservationHistoryFields(true);" styleClass="text" styleId="farv.karnofskyScore" maxlength="3"/>
			<div id="farv.karnofskyScoreMessage" class="blank"></div>
		</td>
	</tr>
	<tr > <!-- SUI 12 -->
		<td></td>
		<td>
			<bean:message key="patient.project.hivStatus" />
		</td>
		<td>
			<html:select name="<%=formName%>"
					 property="observations.hivStatus"
					 onchange="farv.checkHivStatus(true);"
					 styleId="farv.hivStatus"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.HIV_STATUSES.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="farv.hivStatusMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 13 -->
		<td></td>
		<td>
			<bean:message key="patient.project.cd4Count" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.cd4Count"
				onchange="compareAllObservationHistoryFields(true);" maxlength="4"
				styleClass="text" styleId="farv.cd4Count" />
			<div id="farv.cd4CountMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 14 -->
		<td></td>
		<td>
			<bean:message key="patient.project.cd4Percent" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.cd4Percent"
				onchange="compareAllObservationHistoryFields(true);"
				styleClass="text" styleId="farv.cd4Percent" />
			<div id="farv.cd4PercentMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 15 -->
		<td></td>
		<td>
			<bean:message key="patient.project.priorCd4Date" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.priorCd4Date"
				onkeyup="addDateSlashes(this, event);"
				onchange="compareAllObservationHistoryFields(true);"
				styleClass="text" styleId="priorCd4Date" />
			<div id="farv.priorCd4DateMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 16 -->
		<td></td>
		<td>
			<bean:message key="patient.project.nameOfDoctor" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.nameOfDoctor"
				onchange="compareAllObservationHistoryFields(true);" styleClass="text" styleId="farv.nameOfDoctor" size="50"/>
			<div id="farv.nameOfDoctorMessage" class="blank"></div>
		</td>
	</tr>
	<tr><td colspan="5"><hr/></td></tr><!-- _________________________________________________ -->
	<tr> <!-- SUI 17 -->
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.anyDiseasesSinceLast" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.anyCurrentDiseases"
				onchange="farv.displayAnyCurrentDiseases();compareAllObservationHistoryFields(true);" styleId="farv.anyCurrentDiseases"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<!-- Diseases -->
	<logic:iterate  id="disease" indexId="i" name="<%=formName%>" type="NameValuePair"
		property="observations.currentDiseasesList">
		<tr id='<%="farv.currentDiseasesRow" + i%>' style="display: none">
			<!-- CLI 09.n -->
			<td></td>
			<td class="observationsQuestion observationsSubquestion">
				<bean:write name="disease" property="value" />
			</td>
			<td>
				<html:select name="<%=formName%>"
					property='<%= "observations." + disease.getName() %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true)"					
					styleId='<%= "farv." + disease.getName() %>'
					>
					<app:optionsCollection name="<%=formName%>"
						property="dictionaryLists.YES_NO.list" label="localizedName"
						value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	<tr id="farv.currentDiseasesRow" style="display:none">
		<td></td>
		<td class="observationsQuestion observationsSubquestion">
			<bean:message key="patient.project.other"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.currentDiseases"
				onchange="farv.displayCurrentDiseasesOther(this, 1);compareAllObservationHistoryFields(true);"
				styleId="farv.currentDiseases"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="farv.currentDiseasesValueRow" style="display:none">
		<td class="required">*</td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.specify"/>
		</td>
		<td>
			<html:text name="<%=formName%>" property="observations.currentDiseasesValue"
				onchange="compareAllObservationHistoryFields(true);"
				styleId="farv.currentDiseasesValue"  >
			</html:text>
			<div id="farv.currentDiseasesMessage" class="blank" ></div>
		</td>
	</tr>

	<tr> <!-- SUI 18 -->
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.antiTbTreatment"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.antiTbTreatment"
				onchange="compareAllObservationHistoryFields(true);"	styleId="farv.antiTbTreatment"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr> <!-- SUI 19 -->
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.interruptedARVTreatment"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.interruptedARVTreatment"
				onchange="farv.checkInterruptedARVTreatment();compareAllObservationHistoryFields(true);"	styleId="farv.interruptedARVTreatment"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="farv.priorARVTreatmentRow" style="display:none"> <!-- SUI 20 -->
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.onGoingARVTreatment"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.priorARVTreatment"
				onchange="farv.checkPriorARVTreatment();compareAllObservationHistoryFields(true);compareAllObservationHistoryFields(true);"	styleId="farv.priorARVTreatment"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO_NA.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="farv.onGoingARVTreatmentINNsRow" style="display:none"> <!--SUI 21 -->
		<td></td>
		<td class="observationsSubSubquestion" colspan="2">
			<bean:message key="patient.project.onGoingARVTreatmentINNs" />
		</td>
	</tr>
	<logic:iterate id="ongoingARVTreatment" indexId="i" name="<%=formName%>" property="observations.priorARVTreatmentINNsList" >
		<tr style="display:none" id='<%= "farv.priorARVTreatmentINNRow" + i %>'><!-- SUI 21 -->
			<td></td>
			<td class="bulletItem"><%= i+1 %>)</td>
			<td>
				<html:text name="<%=formName%>"
					property='<%= "observations.priorARVTreatmentINNs[" + i + "]" %>'
					onchange="compareAllObservationHistoryFields(true);"
					styleClass="text" styleId='<%= "farv.priorARVTreatmentINNs" + i %>' >
				</html:text>
				<div id='<%= "farv.priorARVTreatmentINNs" + i %>Message' class="blank"></div>
			</td>
		</tr>
	</logic:iterate>

	<tr id="farv.arvTreatmentAnyAdverseEffectsRow" style="display:none"> <!-- SUI 22 -->
		<td></td>
		<td class="observationsSubSubquestion">
			<bean:message key="patient.project.treatmentAnyAdverseEffects"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.arvTreatmentAnyAdverseEffects"
				onchange="farv.checkArvTreatmentAnyAdverseEffects();compareAllObservationHistoryFields(true);"	styleId="farv.arvTreatmentAnyAdverseEffects" >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO_NA.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="farv.arvTreatmentAnyAdverseEffectsMessage" class="blank"></div>
		</td>
	</tr>
	<logic:iterate id="adverseEffect" indexId="i" name="<%=formName%>" property="observations.arvTreatmentAdverseEffects" >
		<tr style="display:none" id='<%= "farv.arvTreatmentAdverseEffectsRow" + i%>'><!-- SUI 22.n -->
			<td ></td>
			<td style="text-align:right">
				<bean:message key="patient.project.treatmentAdverseEffects.type"/>
				<html:text name="<%=formName%>" property='<%= "observations.arvTreatmentAdverseEffects[" + i + "].type" %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true);"
					styleId='<%= "farv.arvTreatmentAdvEffType" + i%>'/>
			</td>
			<td >
				<bean:message key="patient.project.treatmentAdverseEffects.grade"/>
				<html:text name="<%=formName%>" property='<%= "observations.arvTreatmentAdverseEffects[" + i + "].grade" %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true);"
					styleId='<%= "farv.arvTreatmentAdvEffGrd" + i%>' />
				<div id="<%= "farv.arvTreatmentAdvEffType" + i + "Message" %>" class="blank"/>
				<div id="<%= "farv.arvTreatmentAdvEffGrd" + i + "Message" %>" class="blank"/>
			</td>
		</tr>
	</logic:iterate>
	<tr id="farv.arvTreatmentChangeRow" style="display:none"> <!-- SUI 23 -->
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.arvTreatmentChange"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.arvTreatmentChange"
				onchange="compareAllObservationHistoryFields(true);" styleClass="text" styleId="farv.arvTreatmentChange"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO_NA.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="farv.arvTreatmentChangeMessage" class="blank"></div>
		</td>
	</tr>
	<tr><td colspan="5"><hr/></td></tr><!-- __________________________________________________________________________________________________ -->
	<tr> <!-- SUI 24 -->
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.arvTreatmentNew"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.arvTreatmentNew"
				onchange="farv.displayARVTreatmentNew();compareAllObservationHistoryFields(true);" styleId="farv.arvTreatmentNew" styleClass="text" >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list" label="localizedName" value="id" />
			</html:select>
			<div id="ARVTreatmentNewMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="farv.arvTreatmentRegimeRow" style="display:none"> <!-- SUI 25 -->
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.arvTreatmentRegime"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.arvTreatmentRegime"
				onchange="compareAllObservationHistoryFields(true);" styleId="farv.arvTreatmentRegime" styleClass="text" >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.ARV_REGIME.list" label="localizedName" value="id" />
			</html:select>
			<div id="farv.arvTreatmentRegimeMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="farv.prescribedARVTreatmentINNsRow" style="display:none"> <!--SUI 26 -->
		<td></td>
		<td class="observationsSubquestion" colspan="2">
			<bean:message key="patient.project.prescribedARVTreatmentINNs" />
		</td>
	</tr>
	<logic:iterate id="futureARVTreatment" indexId="i" name="<%=formName%>" property="observations.futureARVTreatmentINNsList" >
		<tr id='<%= "farv.futureARVTreatmentINNsRow" + i %>' style="display:none"><!-- SUI 26.n -->
			<td></td>
			<td class="bulletItem"><%= i+1 %>)</td>
			<td>
				<html:text name="<%=formName%>" property='<%= "observations.futureARVTreatmentINNs[" + i + "]" %>'
					onchange="compareAllObservationHistoryFields(true);"
					styleClass="text" styleId='<%= "farv.futureARVTreatmentINNs" + i %>' >
				</html:text>
				<div id="farv.futureARVTreatmentINNs_Message" class="blank"></div>
			</td>
		</tr>
	</logic:iterate>
	<tr> <!-- SUI 27 -->
		<td></td>
		<td>
			<bean:message key="patient.project.cotrimoxazoleTreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.cotrimoxazoleTreatment"
				onchange="farv.displayCotriTreatment(); compareAllObservationHistoryFields(true);"	styleId="farv.cotrimoxazoleTreatment"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="farv.cotrimoxazoleTreatmentMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="farv.cotrimoxazoleTreatmentAnyAdverseEffectsRow" style="display:none"> <!-- SUI 28 -->
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.treatmentAnyAdverseEffects"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.cotrimoxazoleTreatmentAnyAdverseEffects"
				onchange="farv.displayCotriAdverseEffects();compareAllObservationHistoryFields(true);"	styleId="farv.cotrimoxazoleTreatAnyAdvEff"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO_NA.list" label="localizedName" value="id" />
			</html:select>
			<div id="farv.cotrimoxazoleTreatmentAnyAdverseEffects" class="blank"></div>
		</td>
	</tr>
	<logic:iterate id="adverseEffect" indexId="i" name="<%=formName%>" property="observations.cotrimoxazoleTreatmentAdverseEffects" >
		<tr id='<%= "farv.cotrimoxazoleTreatAdvEffRow" + i %>' style="display:none"><!-- SUI 29 -->
			<td ></td>
			<td style="text-align:right">
				<bean:message key="patient.project.treatmentAdverseEffects.type"/>
				<html:text name="<%=formName%>" property='<%= "observations.cotrimoxazoleTreatmentAdverseEffects[" + i + "].type" %>'
					onchange="compareAllObservationHistoryFields(true);" styleId='<%= "farv.cotrimoxazoleTreatAdvEffType" + i %>'/>
			</td>
			<td>
				<bean:message key="patient.project.treatmentAdverseEffects.grade"/>
				<html:text name="<%=formName%>" property='<%= "observations.cotrimoxazoleTreatmentAdverseEffects[" + i + "].grade" %>'
					onchange="compareAllObservationHistoryFields(true);" styleId='<%= "farv.cotrimoxazoleTreatAdvEffGrd" + i %>' />
			</td>
		</tr>
	</logic:iterate>
	<tr> <!-- SUI 30 -->
		<td ></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.anySecondaryTreatment"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.anySecondaryTreatment"
				onchange="farv.displayAny2ndTreatment();compareAllObservationHistoryFields(true);"	styleId="farv.anySecondaryTreatment"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.YES_NO_NA.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="farv.anySecondaryTreatmentMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="farv.secondaryTreatmentRow" style="display:none"> <!-- SUI 31 -->
		<td></td>
		<td class="observationsQuestion observationsSubquestion" >
			<bean:message key="patient.project.secondaryTreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.secondaryTreatment"
				onchange="compareAllObservationHistoryFields(true);" styleId="farv.secondaryTreatment" styleClass="text"  >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.ARV_PROPHYLAXIS_2.list" label="localizedName" value="id" />
			</html:select>
			<div id="farv.secondaryTreatmentMessage" class="blank"></div>
		</td>
	</tr>
	<tr><!-- SUI 32 -->
		<td></td>
		<td><bean:message key="patient.project.clinicVisits" /></td>
		<td>
			<html:text name="<%=formName%>" property="observations.clinicVisits"
				onchange="compareAllObservationHistoryFields(true);"
				styleClass="text" styleId="farv.clinicVisits"  >
			</html:text>
			<div id="observations.clinicVisitsMessage" class="blank"></div>
		</td>
	</tr>
	<tr><td colspan="5"><hr/></td></tr><!-- __________________________________________________________________________________________________ -->
	 	<tr id="farv.patientRecordStatusRow" style="display: none;">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.patientRecordStatus" />
		</td>
		<td>
		<INPUT type="text" id="farv.PatientRecordStatus" size="20" class="text readOnly" disabled="disabled" readonly="readonly" />
		<div id="farv.PatientRecordStatusMessage" class="blank"></div>
		</td>
    </tr>
 	<tr id="farv.sampleRecordStatusRow" style="display: none;">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.sampleRecordStatus" />
		</td>
		<td>
		<INPUT type="text" id="farv.SampleRecordStatus" size="20" class="text readOnly" disabled="disabled" readonly="readonly" />
		<div id="farv.SampleRecordStatusMessage" class="blank"></div>
		</td>
    </tr>
	<tr id="farv.underInvestigationRow">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.underInvestigation" />
		</td>
		<td>
			<html:select name="<%=formName%>"
			property="observations.underInvestigation" onchange="makeDirty();compareAllObservationHistoryFields(true)"
			styleId="farv.underInvestigation">
			<app:optionsCollection name="<%=formName%>"
				property="dictionaryLists.YES_NO.list" label="localizedName"
				value="id" />
			</html:select>
		</td>
    </tr>    
	<tr id="farv.underInvestigationCommentRow" >
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.underInvestigationComment" />
		</td>
		<td colspan="3">
			<app:text name="<%=formName%>" property="ProjectData.underInvestigationNote" maxlength="1000" size="80"
				onchange="makeDirty();" styleId="farv.underInvestigationComment" />
		</td>
    </tr>
</table>
