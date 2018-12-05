<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
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

    this.idPre = "vl.";
	this.displayAnyCurrentDiseases = function () {
	    var field = $("vl.anyCurrentDiseases");
		showElements((field.selectedIndex == 1) , "vl.currentDiseasesRow0,vl.currentDiseasesRow1,vl.currentDiseasesRow2,vl.currentDiseasesRow3,vl.currentDiseasesRow4,vl.currentDiseasesRow5,vl.currentDiseasesRow6,vl.currentDiseasesRow7,vl.currentDiseasesRow8,vl.currentDiseasesRow9,vl.currentDiseasesRow" );
		this.displayCurrentDiseasesOther($('vl.currentDiseases'), (field.selectedIndex == 1)?1:"never");
	}

	this.displayCurrentDiseasesOther = function (field, enableIndex) {
		enableSelectedRequiredSubfield(field, $('vl.currentDiseasesValue'), 1, 'vl.currentDiseasesValueRow')
	}

	
	this.checkCurrentARVTreatment = function () {
		clearFormElements("vl.arvTreatmentInitDate,vl.arvTreatmentRegime,vl.currentARVTreatmentINNs0,vl.currentARVTreatmentINNs1,vl.currentARVTreatmentINNs2,vl.currentARVTreatmentINNs3");
		this.displayedByCurrentARVTreatment();
		this.checkCurrentARVTreatment();
	}
	
	this.displayedByCurrentARVTreatment = function () {
	    var field = $("vl.currentARVTreatment");
		showElements( (field.selectedIndex == 1), "vl.ARVTreatmentInitDateRow,vl.arvTreatmentRegimeRow,vl.onGoingARVTreatmentINNsRow,vl.currentARVTreatmentINNRow0,vl.currentARVTreatmentINNRow1,vl.currentARVTreatmentINNRow2,vl.currentARVTreatmentINNRow3" );
		this.displayedByCurrentARVTreatment(1);
	}
	
	this.checkCurrentARVTreatment = function () {
		clearFormElements("vl.currentARVTreatmentINNs0,vl.currentARVTreatmentINNs1,vl.currentARVTreatmentINNs2,vl.currentARVTreatmentINNs3");
		this.displayedByCurrentARVTreatment(1);
		clearFormElements("vl.arvTreatmentAnyAdverseEffects");
		this.displayedByArvTreatmentAnyAdverseEffects();
	}
	
	this.displayedByCurrentARVTreatment = function (enableIndex) {
		var field = $("vl.currentARVTreatment");
		showElements( (field.selectedIndex == enableIndex), "vl.arvTreatmentAnyAdverseEffectsRow,vl.onGoingARVTreatmentINNsRow,vl.currentARVTreatmentINNRow0,vl.currentARVTreatmentINNRow1,vl.currentARVTreatmentINNRow2,vl.currentARVTreatmentINNRow3");
		this.displayedByArvTreatmentAnyAdverseEffects(1/*(field.selectedIndex == 1)?1:"never"*/);
	}
	
	this.checkArvTreatmentAnyAdverseEffects = function() {
		clearFormElements( "vl.arvTreatmentAdvEffType0,vl.arvTreatmentAdvEffType1,vl.arvTreatmentAdvEffType2,vl.arvTreatmentAdvEffType3");
		clearFormElements( "vl.arvTreatmentAdvEffGrd0,vl.arvTreatmentAdvEffGrd1,vl.arvTreatmentAdvEffGrd2,vl.arvTreatmentAdvEffGrd3");
		this.displayedByArvTreatmentAnyAdverseEffects(1);
	}

	this.displayedByArvTreatmentAnyAdverseEffects = function (enableIndex) {
		var field = $("vl.arvTreatmentAnyAdverseEffects");
		showElements( (field.selectedIndex == enableIndex), "vl.arvTreatmentAdverseEffectsRow0,vl.arvTreatmentAdverseEffectsRow1,vl.arvTreatmentAdverseEffectsRow2,vl.arvTreatmentAdverseEffectsRow3");
	}

	this.displayARVTreatmentNew = function () { /** called from SUI 24 **/
	    var field1 = $("vl.arvTreatmentNew");
		showElements( (field1.selectedIndex == 1), "vl.arvTreatmentRegimeRow" );
		showElements( (field1.selectedIndex == 1), "vl.prescribedARVTreatmentINNsRow,vl.futureARVTreatmentINNsRow0,vl.futureARVTreatmentINNsRow1,vl.futureARVTreatmentINNsRow2,vl.futureARVTreatmentINNsRow3" );
	}

	this.displayCotriTreatment = function () {
	    var field = $("vl.cotrimoxazoleTreatment");
		showElements( (field.selectedIndex == 1), "vl.cotrimoxazoleTreatmentAnyAdverseEffectsRow");
		this.displayCotriAdverseEffects();
	}

	this.displayCotriAdverseEffects = function ( field, enableIndex ) {
		var field = $('vl.cotrimoxazoleTreatAnyAdvEff');
		showElements( (field.selectedIndex == 1), "vl.cotrimoxazoleTreatAdvEffRow0,vl.cotrimoxazoleTreatAdvEffRow1,vl.cotrimoxazoleTreatAdvEffRow2,vl.cotrimoxazoleTreatAdvEffRow3");
	}
	this.displayAny2ndTreatment = function () {
		var field = $('vl.anySecondaryTreatment');
		showElements( (field.selectedIndex == 1), "vl.secondaryTreatmentRow");
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
vl = new ArvFollowupProjectChecker();

</script>

<h2>
	<bean:message key="sample.entry.project.VL.title" />
</h2>
<table style="width:100%">

   <tr>
        <td class="required" width="2%">*</td>
        <td width="28%">
            <bean:message key="sample.entry.project.receivedDate"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
        </td>
        <td width="70%">
        <app:text name="<%=formName%>"
                property="receivedDateForDisplay"
                onkeyup="addDateSlashes(this, event);"
                onchange="vl.checkReceivedDate(false)"
                styleClass="text"
                styleId="vl.receivedDateForDisplay" maxlength="10"/>
                <div id="vl.receivedDateForDisplayMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
             <bean:message key="sample.entry.project.receivedTime" />&nbsp;<bean:message key="sample.military.time.format"/>
        </td>
        <td>
        <app:text name="<%=formName%>"
            property="receivedTimeForDisplay"
            onkeyup="filterTimeKeys(this, event);"              
            onblur="vl.checkReceivedTime(true);"
            styleClass="text"
            styleId="vl.receivedTimeForDisplay" maxlength="5"/>
            <div id="vl.receivedTimeForDisplayMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td class="required">*</td>
        <td>
            <bean:message key="sample.entry.project.dateTaken"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
        </td>
        <td>
        <app:text name="<%=formName%>"
                property="interviewDate"
                onkeyup="addDateSlashes(this, event);"
                onchange="vl.checkInterviewDate(true);"
                styleClass="text"
                styleId="vl.interviewDate" maxlength="10"/>
                <div id="vl.interviewDateMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <bean:message key="sample.entry.project.timeTaken"/>&nbsp;<bean:message key="sample.military.time.format"/>
        </td>
        <td>
        <app:text name="<%=formName%>"
                property="interviewTime"
                onkeyup="filterTimeKeys(this, event);"                
                onblur="vl.checkInterviewTime(true);"
                styleClass="text"
                styleId="vl.interviewTime" maxlength="5"/>
                <div id="vl.interviewTimeMessage" class="blank"></div>
        </td>
    </tr>       

	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.entry.project.vl.siteName" />
		</td>
		<td>
			<html:select name="<%=formName%>" 
			    property="ProjectData.ARVcenterName"
				styleId="vl.centerName" 
				onchange="vl.checkCenterName(false)">
				<app:optionsCollection name="<%=formName%>"
					property="organizationTypeLists.ARV_ORGS_BY_NAME.list" 
					label="organizationName"
					value="id" />
			</html:select>
			<div id="vl.centerNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 05 -->
		<td class="required">*</td>
		<td>
			<bean:message key="sample.entry.project.vl.siteCode" />
		</td>
		<td>
			<html:select name="<%=formName%>" 
			property="ProjectData.ARVcenterCode" 
			styleId="vl.centerCode"
			onchange="vl.checkCenterCode(false)" >
			<app:optionsCollection name="<%=formName%>" 
			property="organizationTypeLists.ARV_ORGS.list" 
			label="doubleName" 
			value="id" />
			</html:select>
		</td>
	</tr>
	
	<tr> <!-- SUI 02 -->
		<td class="required">+</td>
		<td class="">
			<bean:message key="patient.subject.number"/>
		</td>
		<td>
			<app:text name="<%=formName%>" property="subjectNumber"
					  onchange="vl.checkSubjectNumber(true);"
					  styleId="vl.subjectNumber"
					  styleClass="text"
					  maxlength="7"/>
			<div id="vl.subjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td class="required">+</td>
		<td><bean:message key="patient.site.subject.number"/></td>
		<td>
			<app:text name="<%=formName%>" property="siteSubjectNumber"
				styleId="vl.siteSubjectNumber" styleClass="text"
				onchange="vl.checkSiteSubjectNumber(true, false); makeDirty();"
			/>
			<div id="vl.siteSubjectNumberMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 03 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.labNo" />
		</td>
		<td>
			<div class="blank"><bean:message key="sample.entry.project.LART"/></div>
			<INPUT type="text" name="vl.labNoForDisplay" id="vl.labNoForDisplay" size="5" class="text"
			   	onchange="handleLabNoChange( this, '<bean:message key="sample.entry.project.LART"/>', false ); makeDirty();"
			   	maxlength="5" />
		  	<app:text name="<%=formName%>" property="labNo"
				styleClass="text" style="display: none;"
				styleId="vl.labNo" />
			<div id="vl.labNoMessage" class="blank"></div>
		</td>
		<td>
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
				onchange="vl.checkDateOfBirth(false)"	styleClass="text" styleId="vl.dateOfBirth" maxlength="10"/>
			<div id="vl.dateOfBirthMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
			<td ></td>
			<td>
				<bean:message  key="patient.age" />
			</td>
			<td>
				<label for="vl.age" ><bean:message  key="label.year" /></label>
				<INPUT type="text" name="ageYear" id="vl.age" size="3"
				   	onchange="vl.checkAge( this, true, 'year' );"
				   	maxlength="2" />
				<div id="vl.ageMessage" class="blank" ></div>
			</td>
	</tr>
	<tr> <!-- SUI 08 -->
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.gender" />
		</td>
		<td>
			<html:select name="<%=formName%>" 
			property="gender"
			onchange="vl.checkGender(false)" 
			styleId="vl.gender"  >
			<app:optionsCollection 
				name="<%=formName%>" 
				property="formLists.GENDERS"
				label="localizedName" 
				value="genderType" />
			</html:select>
			<div id="vl.genderMessage" class="blank"></div>
		</td>
	</tr>
	<tr > 
		<td></td>
		<td>
			<bean:message key="patient.project.hivType" />
		</td>
		<td>
			<html:select name="<%=formName%>"
					 property="observations.hivStatus"
					 onchange="vl.checkHivStatus(true);"
					 styleId="vl.hivType"  >
				<app:optionsCollection 
				    name="<%=formName%>" 
				    property="dictionaryLists.HIV_STATUSES.list"
					label="localizedName" 
					value="id" />
			</html:select>
			<div id="vl.hivStatusMessage" class="blank"></div>
		</td>
	</tr>
	
	
	<tr><td colspan="5"><hr/></td></tr><!-- _________________________________________________ -->

	<tr> 
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="sample.entry.project.arv.treatment"/>
		</td>
		<td>
			<html:select name="<%=formName%>" 
			    property="observations.currentARVTreatment"
				onchange="vl.checkCurrentARVTreatment();compareAllObservationHistoryFields(true);"	
				styleId="vl.currentARVTreatment"  >
				<app:optionsCollection name="<%=formName%>" 
				    property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="vl.ARVTreatmentInitDateRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.entry.project.arv.treatment.initDate"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.arvTreatmentInitDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="vl.checkReceivedDate(false);"
				styleClass="text"
				styleId="vl.arvTreatmentInitDate" maxlength="10"/>
				<div id="vl.arvTreatmentInitDateMessage" class="blank"></div>
		</td>
	</tr>
	
	<tr id="vl.arvTreatmentRegimeRow" style="display:none">
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.entry.project.arv.treatment.therap.line"/>
		</td>
		<td>
			<html:select name="<%=formName%>" property="observations.arvTreatmentRegime"
				onchange="compareAllObservationHistoryFields(true);" 
				styleId="vl.arvTreatmentRegime" styleClass="text" >
				<app:optionsCollection name="<%=formName%>" property="dictionaryLists.ARV_REGIME.list" label="localizedName" value="id" />
			</html:select>
			<div id="vl.arvTreatmentRegimeMessage" class="blank"></div>
		</td>
	</tr>
	
	<tr id="vl.onGoingARVTreatmentINNsRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.entry.project.arv.treatment.regimen" />
		</td>
	</tr>
	<logic:iterate id="ongoingARVTreatment" indexId="i" name="<%=formName%>" property="observations.currentARVTreatmentINNsList" >
		<tr style="display:none" id='<%= "vl.currentARVTreatmentINNRow" + i %>'>
			<td></td>
			<td class="bulletItem"><%= i+1 %>)</td>
			<td>
				<html:text name="<%=formName%>"
					property='<%= "observations.currentARVTreatmentINNs[" + i + "]" %>'
					onchange="compareAllObservationHistoryFields(true);"
					styleClass="text" 
					styleId='<%= "vl.currentARVTreatmentINNs" + i %>' >
				</html:text>
				<div id='<%= "vl.currentARVTreatmentINNs" + i %>Message' class="blank"></div>
			</td>
		</tr>
	</logic:iterate>
		
	<tr><td colspan="5"><hr/></td></tr><!-- __________________________________________________________________________________________________ -->
	
	<tr > 
		<td></td>
		<td>
			<bean:message key="sample.entry.project.vl.reason" />
		</td>
		<td>
			<html:select name="<%=formName%>"
					 property="observations.hivStatus"
					 onchange="vl.checkHivStatus(true);"
					 styleId="vl.hivType"  >
				<app:optionsCollection 
				    name="<%=formName%>" 
				    property="dictionaryLists.ARV_REASON_FOR_VL_DEMAND.list"
					label="localizedName" 
					value="id" />
			</html:select>
			<div id="vl.hivStatusMessage" class="blank"></div>
		</td>
	</tr>
	
    <tr><td colspan="5"><hr/></td></tr><!-- _________________________________________________ -->
	
	<tr>
		<td></td>
		<td>
			<bean:message key="patient.project.cd4Count" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.cd4Count"
				onchange="compareAllObservationHistoryFields(true);" maxlength="4"
				styleClass="text" styleId="vl.cd4Count" />
			<div id="vl.cd4CountMessage" class="blank"></div>
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
				styleClass="text" styleId="vl.cd4Percent" />
			<div id="vl.cd4PercentMessage" class="blank"></div>
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
			<div id="vl.priorCd4DateMessage" class="blank"></div>
		</td>
	</tr>
	<tr> <!-- SUI 16 -->
		<td></td>
		<td>
			<bean:message key="patient.project.nameOfDoctor" />
		</td>
		<td>
			<app:text name="<%=formName%>" 
			property="observations.nameOfDoctor"
				onchange="compareAllObservationHistoryFields(true);" 
				styleClass="text" 
				styleId="vl.nameOfDoctor" size="50"/>
			<div id="vl.nameOfDoctorMessage" class="blank"></div>
		</td>
	</tr>
	
 	

</table>
