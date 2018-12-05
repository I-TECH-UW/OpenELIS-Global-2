<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			org.apache.commons.httpclient.NameValuePair,
			us.mn.state.health.lims.common.util.DateUtil" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<bean:define id="formName"
	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<script type="text/javascript">
/*
 * Functionality just for this form and its fields
 */
function ArvInitialProjectChecker() {
	this.name = "ArvInitialProjectChecker";
	
	this.displayAnyPriorDiseases = function() {
	    var field = $("anyPriorDiseases");
		showElements((field.selectedIndex == 1),
				"priorDiseasesRow0,priorDiseasesRow1,priorDiseasesRow2,priorDiseasesRow3,priorDiseasesRow4,priorDiseasesRow5,priorDiseasesRow6,priorDiseasesRow7,priorDiseasesRow8,priorDiseasesRow9,priorDiseasesRow10,priorDiseasesRow");
		this.displayPriorDiseasesOther($('priorDiseases'),
				(field.selectedIndex == 1) ? 1 : "never");
	}

	this.displayPriorDiseasesOther = function(field, enableIndex) {
		enableSelectedRequiredSubfield(field, $('priorDiseasesValue'), enableIndex, 'priorDiseasesValueRow')
	}

	this.displayAnyCurrentDiseases = function() {
	    var field =$("anyCurrentDiseases");
		showElements(
				(field.selectedIndex == 1),
				"currentDiseasesRow0,currentDiseasesRow1,currentDiseasesRow2,currentDiseasesRow3,currentDiseasesRow4,currentDiseasesRow5,currentDiseasesRow6,currentDiseasesRow7,currentDiseasesRow8,currentDiseasesRow9,currentDiseasesRow10,currentDiseasesRow");
		this.displayCurrentDiseasesOther($('currentDiseases'),
				(field.selectedIndex == 1) ? 1 : "never");
	}

	this.displayCurrentDiseasesOther = function(field, enableIndex) {
		enableSelectedRequiredSubfield(field, $('currentDiseasesValue'), enableIndex, 'currentDiseasesValueRow')
	}

	this.checkArvProphylaxisBenefit = function (field) {
	    makeDirty();
	   	var prophyTreat = $("arvProphylaxis");
	    if (field.selectedIndex == 2) { // NO
	    	prophyTreat.selectedIndex = prophyTreat.options.length - 1; // NA is last choice
	    } else if (field.selectedIndex == 1 ) {	// explicit YES
	    	prophyTreat.selectedIndex = 0; // make the user pick again	    
	    }
		this.displayArvProphylaxisBenefit();
		compareAllObservationHistoryFields(true);
	}
	
	this.displayArvProphylaxisBenefit = function () {
		enableSelectedRequiredSubfield($('arvProphylaxisBenefit'), $('arvProphylaxis'), 1, 'arvProphylaxisRow');
	}

	this.displayPriorARVTreatment = function() {
		showElements(
				($("priorARVTreatment").selectedIndex == 1), // YES
				"priorARVInnRow,priorARVTreatmentRow0,priorARVTreatmentRow1,priorARVTreatmentRow2,priorARVTreatmentRow3");
	}

	this.displayNationality = function () {
		enableSelectedRequiredSubfield($('nationality'), $('nationalityOther'), -2, 'nationalityOtherRow' )
	}
	
	
	this.checkAnyCurrentDiseases = function (field) {
		makeDirty();
		this.displayAnyCurrentDiseases();
		var treatField = $("currentOITreatment");
		var treatNAIndex = treatField.options.length - 1;	// NA is in the last position
		if (field.selectedIndex == 2) { // No => treatment NA (the last choice)
			treatField.selectedIndex = treatNAIndex;  
		} else if (field.selectedIndex == 1 && treatField.selectedIndex == treatNAIndex ) { // Yes => move NA back to blank
			treatField.selectedIndex = 0;
		}
		compareAllObservationHistoryFields(true);
	}

	/**
	 * Check that all hidden and disabled fields are set in the right state.
	 **/
	this.refresh = function () {
		this.refreshBase();		
		this.displayPriorARVTreatment();
		this.displayArvProphylaxisBenefit();
		this.displayAnyPriorDiseases();
		this.displayAnyCurrentDiseases();
		this.displayNationality();
	}
}
ArvInitialProjectChecker.prototype = new BaseProjectChecker();
iarv = new ArvInitialProjectChecker();
</script>

<h2>
	<bean:message key="sample.entry.project.initialARV.title" />
</h2>
<table>
	<tr>
		<td class="required">
			*
		</td>
		<td>
			<bean:message key="sample.entry.project.receivedDate" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="<%=formName%>" property="receivedDateForDisplay"
				onkeyup="addDateSlashes(this, event);"
				onchange="iarv.checkReceivedDate(false)" styleClass="text"
				styleId="receivedDateForDisplay" maxlength="10"/>
			<div id="receivedDateForDisplayMessage" class="blank" />
		</td>
	</tr>
	<tr>
		<!-- DEM 01 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.interviewDate" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="<%=formName%>" property="interviewDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="iarv.checkInterviewDate(false)" styleClass="text"
				styleId="interviewDate" maxlength="10"/>
			<div id="interviewDateMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 02 -->
		<td class="required">
			+
		</td>
		<td>
			<bean:message key="patient.subject.number" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="subjectNumber"
				onchange="iarv.checkSubjectNumber(true)"
				styleId="subjectNumber" styleClass="text"
				maxlength="7" />
			<div id="subjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td class="required">+</td>
		<td>
			<bean:message key="patient.site.subject.number" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="siteSubjectNumber"
				onchange="iarv.checkSiteSubjectNumber(true, false)"
				styleId="siteSubjectNumber" styleClass="text" />
			<div id="siteSubjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 03 -->
		<td class="required">
			*
		</td>
		<td>
			<bean:message key="patient.project.labNo" />
		</td>
		<td>
			<div class="blank">
				<bean:message key="sample.entry.project.LART" />
			</div>
			<INPUT type="text" name="arv.labNoForDisplay" id="labNoForDisplay"
				size="5" class="text"
				onchange="handleLabNoChange( this, '<bean:message key="sample.entry.project.LART"/>', false); makeDirty();"
				maxlength="5" />
			<app:text name="<%=formName%>" property="labNo"
				styleClass="text" style="display: none;"
				styleId="labNo" />
			<div id="labNoMessage" class="blank" ></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 04 -->
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.centerName" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="centerName"
				styleId="centerName" onchange="iarv.checkCenterName(true)">
				<app:optionsCollection name="<%=formName%>"
					property="organizationTypeLists.ARV_ORGS_BY_NAME.list" label="organizationName"
					value="id" />
			</html:select>
		</td>
	</tr>

	<tr>
		<!-- DEM 05 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.centerCode" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="centerCode"
				styleId="centerCode" onchange="iarv.checkCenterCode(true)">
				<app:optionsCollection name="<%=formName%>"
					property="organizationTypeLists.ARV_ORGS.list" label="doubleName"
					value="id" />
			</html:select>
			<div id="centerCodeMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 06 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientFamilyName" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="lastName"
				onchange="iarv.checkFamilyName(true)" styleClass="text" styleId="patientFamilyName"
				size="40"/>
			<div id="patientFamilyNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 07 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientFirstNames" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="firstName" size="40"
				onchange="iarv.checkFirstNames(true)" styleClass="text" styleId="patientFirstNames" />
			<div id="patientFirstNamesMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 08 -->
		<td class="required">
			*
		</td>
		<td>
			<bean:message key="patient.project.gender" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="gender"
				onchange="iarv.checkGender(false)" styleId="gender">
				<app:optionsCollection name="<%=formName%>"
					property="formLists.GENDERS" label="localizedName"
					value="genderType" />
			</html:select>
			<div id="genderMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 09 -->
		<td class="required">
			*
		</td>
		<td>
			<bean:message key="patient.project.dateOfBirth" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="<%=formName%>" property="birthDateForDisplay"
				onkeyup="addDateSlashes(this, event);"
				onchange="iarv.checkDateOfBirth(false)" styleClass="text"
				styleId="dateOfBirth" maxlength="10"/>
			<div id="dateOfBirthMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
			<td ></td>
			<td>
				<bean:message  key="patient.age" />
			</td>
			<td>
				<label for="age" ><bean:message  key="label.year" /></label>
				<INPUT type="text" name="ageYear" id="age" size="3"
				   	onchange="iarv.checkAge( this, true, 'year' );"
				   	maxlength="2" />
				<div id="ageMessage" class="blank" ></div>
			</td>
	</tr>
	<tr id="hivStatusRow" style="display: none;">
		<td></td>
		<td>
			<bean:message key="patient.project.hivStatus" />
		</td>
		<td>
			<html:select name="<%=formName%>"
					 property="observations.hivStatus"
					 onchange="iarv.checkHivStatus(true);"
					 styleId="hivStatus"  >
				<app:optionsCollection name="<%=formName%>" property="ProjectData.hivStatusList"
					label="localizedName" value="id" />
			</html:select>
			<div id="hivStatusMessage" class="blank"></div>
		</td>
	</tr>

	<tr>
		<!-- DEM 10 -->
		<td></td>
		<td>
			<bean:message key="patient.project.educationLevel" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.educationLevel" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="educationLevel">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.EDUCATION_LEVELS.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="educationLevelMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 11 -->
		<td></td>
		<td>
			<bean:message key="patient.project.maritalStatus" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.maritalStatus" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="maritalStatus">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.MARITAL_STATUSES.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="maritalStatusMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 12 -->
		<td></td>
		<td>
			<bean:message key="patient.project.nationality" />

		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.nationality"
				onchange="iarv.displayNationality();compareAllObservationHistoryFields(true)"
				styleId="nationality" styleClass="text">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.SIMPLIFIED_NATIONALITIES.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="nationalityMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="nationalityOtherRow" style="display: none">
		<!-- DEM 12.1 a -->
		<td class="required">
			*
		</td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.nationalityOther" />
		</td>
		<td>
			<app:text name="<%=formName%>"
				property="observations.nationalityOther"
				onchange="checkRequiredField(this); makeDirty();compareAllObservationHistoryFields(true)" styleClass="text"
				styleId="nationalityOther" />
			<div id="nationalityOtherMessage" class="blank"></div>
		</td>
	</tr>

	<tr>
		<!-- DEM 13 -->
		<td></td>
		<td>
			<bean:message key="patient.project.legalResidence" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.legalResidence"
				onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleClass="text" styleId="legalResidence" />
			<div id="legalResidenceMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 14 -->
		<td></td>
		<td>
			<bean:message key="patient.project.nameOfDoctor" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.nameOfDoctor"
				onchange="compareAllObservationHistoryFields(true)"
				styleClass="text" styleId="nameOfDoctor" size="50"/>
			<div id="nameOfDoctorMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<hr />
		</td>
	</tr>
	<tr>
		<!-- CLI 01 Prior Diseases ------------------------------------------------------------------------------->
		<td></td>
		<td>
			<br />
			<bean:message key="patient.project.antecedents" />
			<br />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.anyPriorDiseases"
				onchange="iarv.displayAnyPriorDiseases();makeDirty();compareAllObservationHistoryFields(true)"
				styleClass="text" styleId="anyPriorDiseases">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="anyPriorDiseasesMessage" class="blank" />
		</td>
	</tr>
	<logic:iterate  id="disease" indexId="i" name="<%=formName%>" type="NameValuePair"
		property="observations.priorDiseasesList">
		<tr id='<%="priorDiseasesRow" + i%>' style="display: none">
			<!-- CLI 09.n -->
			<td></td>
			<td class="observationsQuestion observationsSubquestion">
				<bean:write name="disease" property="value" />
			</td>
			<td>
				<html:select name="<%=formName%>"
					property='<%= "observations." + disease.getName() %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true)"					
					styleId="<%= disease.getName() %>"
					>
					<app:optionsCollection name="<%=formName%>"
						property="dictionaryLists.YES_NO.list" label="localizedName"
						value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	<tr id="priorDiseasesRow" style="display: none">
		<td></td>
		<td class="observationsQuestion observationsSubquestion">
			<bean:message key="patient.project.other" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.priorDiseases"
				onchange="iarv.displayPriorDiseasesOther(this, 1);makeDirty();compareAllObservationHistoryFields(true)"
				styleId="priorDiseases">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="priorDiseasesValueRow" style="display: none">
		<td class="required">
			*
		</td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.specify" />
		</td>
		<td>
			<html:text name="<%=formName%>"
				property="observations.priorDiseasesValue" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="priorDiseasesValue">
			</html:text>
			<div id="priorDiseasesMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!--CLI 02 -->
		<td>
		</td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.arvProphylaxisBenefit" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.arvProphylaxisBenefit"
				onchange="iarv.checkArvProphylaxisBenefit(this)"
				styleId="arvProphylaxisBenefit">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="arvProphylaxisBenefitMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="arvProphylaxisRow" style="display: none">
		<!--CLI 03 -->
		<td class="required">
			*
		</td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.arvProphylaxis" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.arvProphylaxis"
				onchange="checkRequiredField(this); makeDirty();compareAllObservationHistoryFields(true)"
				styleId="arvProphylaxis">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.ARV_PROPHYLAXIS.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="arvProphylaxisMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!--CLI 04 -->
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.currentARVTreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.currentARVTreatment" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="currentARVTreatment">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="currentARVTreatmentMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- CLI 05 -->
		<td>
		</td>
		<td class="observationsQuestion">
			<bean:message key="patient.project.priorARVTreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.priorARVTreatment"
				onchange="iarv.displayPriorARVTreatment(); makeDirty();compareAllObservationHistoryFields(true)"
				styleId="priorARVTreatment">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO_NA.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="priorARVTreatmentINNMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="priorARVInnRow" style="display: none">
		<!-- CLI 06 -->
		<td></td>
		<td class="observationsSubquestion" colspan="2" id="priorARVInn">
			<bean:message key="patient.project.priorARVInn" />
		</td>
	</tr>
	<logic:iterate id="prevARVTreat" indexId="i" name="<%=formName%>"
		property="observations.priorARVTreatmentINNsList">
		<tr id='<%="priorARVTreatmentRow" + i%>'
			class="observationsSubquestion" style="display: none">
			<td></td>
			<td class="bulletItem" id='<%="priorARVTreatmentItem" + i%>'><%=i + 1%>)
			</td>
			<td>
				<html:text name="<%=formName%>"
					property='<%= "observations.priorARVTreatmentINNs[" + i + "]" %>'
					styleClass="text" onchange="makeDirty();compareAllObservationHistoryFields(true)"
					styleId='<%= "priorARVTreatmentINNs" + i %>'>
				</html:text>
				<div id='<%="priorARVTreatmentINNs" + i + "Message"%>'
					class="blank"></div>
			</td>
		</tr>
	</logic:iterate>

	<tr>
		<!-- CLI 07 -->
		<td></td>
		<td>
			<bean:message key="patient.project.cotrimoxazoleTreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.cotrimoxazoleTreatment"
				onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="cotrimoxazoleTreatment">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="cotrimoxazoleTreatmentMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- CLI 08 -->
		<td></td>
		<td>
			<bean:message key="patient.project.aidsStage" />
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.aidsStage"
				onchange="makeDirty();compareAllObservationHistoryFields(true)" styleId="aidsStage">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.AIDS_STAGES.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="aidsStageMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<hr />
		</td>
	</tr>
	<!-- Current Diseases ------------------------------------------------------------------------------->
	<tr>
		<!-- CLI 09.n -->
		<td></td>
		<td class="observationsQuestion">
			<br />
			<bean:message key="patient.project.anyCurrentDiseases" />
			<br />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.anyCurrentDiseases"
				onchange="iarv.checkAnyCurrentDiseases(this)"
				styleClass="text" styleId="anyCurrentDiseases">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="anyCurrentDiseasesMessage" class="blank"></div>
		</td>
	</tr>
	<logic:iterate  id="disease" indexId="i" name="<%=formName%>" type="NameValuePair"
		property="observations.currentDiseasesList">
		<tr id='<%="currentDiseasesRow" + i%>' style="display: none">
			<!-- CLI 09.n -->
			<td></td>
			<td class="observationsQuestion observationsSubquestion">
				<bean:write name="disease" property="value" />
			</td>
			<td>
				<html:select name="<%=formName%>"
					property='<%= "observations." + disease.getName() %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true)"					
					styleId="<%= disease.getName() %>"
					>
					<app:optionsCollection name="<%=formName%>"
						property="dictionaryLists.YES_NO.list" label="localizedName"
						value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	<tr id="currentDiseasesRow" style="display: none">
		<td></td>
		<td class="observationsQuestion observationsSubquestion">
			<bean:message key="patient.project.other" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.currentDiseases"
				onchange="iarv.displayCurrentDiseasesOther(this, 1);makeDirty();compareAllObservationHistoryFields(true)"
				styleId="currentDiseases">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
		</td>
	</tr>

	<tr id="currentDiseasesValueRow" style="display: none">
		<td class="required">
			*
		</td>
		<td class="observationsSubquestion">
			<bean:message key="patient.project.specify" />
		</td>
		<td>
			<html:text name="<%=formName%>"
				property="observations.currentDiseasesValue" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="currentDiseasesValue">
			</html:text>
			<div id="currentDiseasesMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- CLI 10 -->
		<td></td>
		<td>
			<bean:message key="patient.project.currentOITreatment" />
		</td>
		<td>
			<html:select name="<%=formName%>"
				property="observations.currentOITreatment" onchange="makeDirty();compareAllObservationHistoryFields(true)"
				styleId="currentOITreatment">
				<app:optionsCollection name="<%=formName%>"
					property="dictionaryLists.YES_NO_NA.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="currentOITreatment" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- CLI 11 -->
		<td></td>
		<td>
			<bean:message key="patient.project.patientWeight" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.patientWeight"
				onchange="makeDirty();compareAllObservationHistoryFields(true)" styleClass="text" styleId="patientWeight" maxlength="3"/>
			<div id="patientWeightMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- CLI 11 -->
		<td></td>
		<td>
			<bean:message key="patient.project.karnofskyScore" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.karnofskyScore"
				onchange="makeDirty();compareAllObservationHistoryFields(true)" styleClass="text" styleId="karnofskyScore" maxlength="3"/>
			<div id="karnofskyScoreMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<hr />
		</td>
	</tr>
	<tr><td colspan="5"> </td></tr>
 	<tr id="patientRecordStatusRow"style="display: none;">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.patientRecordStatus" />
		</td>
		<td>
		<INPUT type="text" id="PatientRecordStatus" size="20" class="text readOnly" disabled="disabled" readonly="readonly" />
		<div id="PatientRecordStatusMessage" class="blank"></div>
		</td>
    </tr>
 	<tr id="sampleRecordStatusRow" style="display: none;">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.sampleRecordStatus" />
		</td>
		<td>
		<INPUT type="text" id="SampleRecordStatus" size="20" class="text readOnly" disabled="disabled" readonly="readonly" />
		<div id="SampleRecordStatusMessage" class="blank"></div>
		</td>
    </tr>
	<tr id="underInvestigationRow">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.underInvestigation" />
		</td>
		<td>
			<html:select name="<%=formName%>"
			property="observations.underInvestigation" onchange="makeDirty();compareAllObservationHistoryFields(true)"
			styleId="underInvestigation">
			<app:optionsCollection name="<%=formName%>"
				property="dictionaryLists.YES_NO.list" label="localizedName"
				value="id" />
			</html:select>
		</td>
    </tr>    
 	<tr id="underInvestigationCommentRow">
		<td class="required"></td>
		<td>
			<bean:message key="patient.project.underInvestigationComment" />
		</td>
		<td colspan="3">
			<app:text name="<%=formName%>" property="ProjectData.underInvestigationNote" maxlength="1000" size="80"
				onchange="makeDirty();" styleId="underInvestigationComment" />
		</td>
    </tr>
</table>