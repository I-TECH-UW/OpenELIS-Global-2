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
 * an object gathers all the methods about tweeking the VL form together in one place.
 **/
function VLProjectChecker() {

    this.idPre = "vl.";
    var specialKeys = new Array();
    specialKeys.push(8); //Backspace
	
    this.checkDate = function (field,blanksAllowed) {
    	makeDirty();
		if (field == null) return; // just so we don't have to have this field on all forms, but is listed in checkAllSampleFields
		checkValidDate(field);
		checkRequiredField(field, blanksAllowed);
		compareSampleField( field.id, false, blanksAllowed);
	};
   
	this.checkVLRequestReason = function () {
		clearFormElements("vl.vlOtherReasonForRequest");
		this.displayedByReasonOther();
	};
	this.displayedByReasonOther = function () {
	    var field = $("vl.vlReasonForRequest");
		showElements( (field.selectedIndex == 5), "vl.reasonOtherRow" );
	};

	this.checkInterruptedARVTreatment = function () {
		clearFormElements("vl.arvTreatmentInitDate,vl.arvTreatmentRegime,vl.currentARVTreatmentINNs0,vl.currentARVTreatmentINNs1,vl.currentARVTreatmentINNs2,vl.currentARVTreatmentINNs3");
		this.displayedByInterruptedARVTreatment();
	};	
	
	this.displayedByInterruptedARVTreatment = function () {
	    var field = $("vl.currentARVTreatment");
		showElements( (field.selectedIndex == 1), "vl.arvTreatmentInitDateRow,vl.arvTreatmentTherapRow,vl.onGoingARVTreatmentINNsRow,vl.currentARVTreatmentINNRow0,vl.currentARVTreatmentINNRow1,vl.currentARVTreatmentINNRow2,vl.currentARVTreatmentINNRow3" );
	};
	
	this.checkVLBenefit = function () {
		clearFormElements("vl.priorVLLab,vl.priorVLValue,vl.priorVLDate");
		this.displayedByVLBenefit();
	};
	this.displayedByVLBenefit = function () {
	    var field = $("vl.vlBenefit");
		showElements( (field.selectedIndex == 1), "vl.priorVLLabRow,vl.priorVLValueRow,vl.priorVLDateRow" );
	};
	function IsNumeric(field,e) {
        var keyCode = e.which ? e.which : e.keyCode
        var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
        document.getElementById("error").style.display = ret ? "none" : "inline";
        return ret;
    };
    
    this.refresh = function () {
		this.refreshBase();		
		this.displayedByVLBenefit();
		this.displayedByReasonOther();
		this.displayedByInterruptedARVTreatment();
	};

}

VLProjectChecker.prototype = new BaseProjectChecker();
/// the object which knows about VL questions and which fields to show etc.
vl = new VLProjectChecker();

</script>

<h2>
	<bean:message key="sample.entry.project.VL.title" />
</h2>
<table style="width:100%">
<tr> 
		<td class="required">*</td>
		<td>
			<bean:message key="sample.entry.project.ARV.centerName" />
		</td>
		<td>
			<html:select name="<%=formName%>" 
			    property="ProjectData.ARVcenterName"
				styleId="vl.centerName" 
				onchange="vl.checkCenterName(true)">
				<app:optionsCollection name="<%=formName%>"
					property="organizationTypeLists.ARV_ORGS_BY_NAME.list" 
					label="organizationName"
					value="id" />
			</html:select>
			<div id="vl.centerNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.centerCode" />
		</td>
		<td>
			<html:select name="<%=formName%>" 
			property="ProjectData.ARVcenterCode" 
			styleId="vl.centerCode"
			onchange="vl.checkCenterCode(true)" >
			<app:optionsCollection name="<%=formName%>" 
			property="organizationTypeLists.ARV_ORGS.list" 
			label="doubleName" 
			value="id" />
			</html:select>
			<div id="vl.centerCodeMessage" class="blank"></div>
		</td>
	</tr>
		<tr> 
		<td></td>
		<td>
			<bean:message key="patient.project.nameOfClinician" />
		</td>
		<td>
			<app:text name="<%=formName%>" 
			property="observations.nameOfDoctor"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				styleClass="text" 
				styleId="vl.nameOfDoctor" size="50"/>
			<div id="vl.nameOfDoctorMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
        <td></td>
        <td>
            <bean:message key="patient.project.nameOfSampler" />
        </td>
        <td>
            <html:text name="<%=formName%>"
                      property="observations.nameOfSampler"
                      onchange="makeDirty();compareAllObservationHistoryFields(true)"
                      styleClass="text"
                      styleId="vl.nameOfSampler" size="50"/>
            <div id="vl.nameOfSamplerMessage" class="blank"></div>
        </td>
    </tr>
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
	<tr> 
		<td class="required">*</td>
		<td>
			<bean:message key="patient.project.labNo" />
		</td>
		<td>
			<div class="blank"><bean:message key="sample.entry.project.LVL"/></div>
			<INPUT type="text" name="vl.labNoForDisplay" id="vl.labNoForDisplay" size="5" class="text"
			   	onchange="handleLabNoChange( this, '<bean:message key="sample.entry.project.LVL"/>', false ); makeDirty();"
			   	maxlength="5" />
		  	<app:text name="<%=formName%>" property="labNo"
				styleClass="text" style="display: none;"
				styleId="vl.labNo" />
			<div id="vl.labNoMessage" class="blank"></div>
		</td>
		<td>
		</td>
	</tr>
	<tr> 
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
	<tr> 
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
	
	<tr> 
		<td></td>
		<td class="observationsQuestion">
            <bean:message key="sample.project.vlPregnancy" />
        </td>
        <td>
            <html:select name="<%=formName%>"
            property="observations.vlPregnancy" onchange="makeDirty();compareAllObservationHistoryFields(true)"
            styleId="vl.vlPregnancy">
            <app:optionsCollection name="<%=formName%>"
                property="dictionaryLists.YES_NO.list" label="localizedName"
                value="id" />
            </html:select>
        </td>
    </tr>  
    
    <tr> 
		<td></td>
		<td class="observationsQuestion">
            <bean:message key="sample.project.vlSuckle" />
        </td>
        <td>
            <html:select name="<%=formName%>"
            property="observations.vlSuckle" onchange="makeDirty();compareAllObservationHistoryFields(true)"
            styleId="vl.vlSuckle">
            <app:optionsCollection name="<%=formName%>"
                property="dictionaryLists.YES_NO.list" label="localizedName"
                value="id" />
            </html:select>
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
					 styleId="vl.hivStatus"  >
				<app:optionsCollection 
				    name="<%=formName%>" 
				    property="ProjectData.hivStatusList"
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
				onchange="vl.checkInterruptedARVTreatment();compareAllObservationHistoryFields(true);"	
				styleId="vl.currentARVTreatment"  >
				<app:optionsCollection name="<%=formName%>" 
				    property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="vl.arvTreatmentInitDateRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.entry.project.arv.treatment.initDate"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.arvTreatmentInitDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="vl.checkDate(this,false);"
				styleClass="text"
				styleId="vl.arvTreatmentInitDate" maxlength="10"/>
				<div id="vl.arvTreatmentInitDateMessage" class="blank"></div>
		</td>
	</tr>
	
	<tr id="vl.arvTreatmentTherapRow" style="display:none">
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.entry.project.arv.treatment.therap.line"/>
		</td>
		<td>
			<html:select name="<%=formName%>" 
			    property="observations.arvTreatmentRegime"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				styleId="vl.arvTreatmentRegime" 
				styleClass="text" >
				<app:optionsCollection 
				name="<%=formName%>" 
				property="dictionaryLists.ARV_REGIME.list" 
				label="localizedName" value="id" />
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
					onchange="makeDirty();compareAllObservationHistoryFields(true);" 
					styleClass="text" styleId='<%= "vl.currentARVTreatmentINNs" + i %>' >
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
					 property="observations.vlReasonForRequest"
				     onchange="vl.checkVLRequestReason();compareAllObservationHistoryFields(true);"	
					 styleId="vl.vlReasonForRequest"  >
				<app:optionsCollection 
				    name="<%=formName%>" 
				    property="dictionaryLists.ARV_REASON_FOR_VL_DEMAND.list"
					label="localizedName" 
					value="id" />
			</html:select>
			<div id="vl.vlReasonForRequestMessage" class="blank"></div>
		</td>
	</tr>
	
	<tr id="vl.reasonOtherRow" style="display:none"> 
		<td></td>
		<td class="Subquestion">
			<bean:message key="sample.entry.project.vl.specify"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.vlOtherReasonForRequest"
				onchange="compareAllObservationHistoryFields(true);" 
				styleClass="text"
				styleId="vl.vlOtherReasonForRequest" maxlength="50"/>
				<div id="vl.vlOtherReasonForRequestMessage" class="blank"></div>
		</td>
	</tr>
	
    <tr><td colspan="5"><hr/></td></tr><!-- _________________________________________________ -->
	
	<tr> 
		<td></td>
		<td colspan="3" class="sectionTitle">
			<bean:message key="sample.project.cd4init" />
		</td>
	</tr>
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.cd4Count" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.initcd4Count"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				maxlength="4"
				styleClass="text" 
				styleId="vl.initcd4Count" />
			<div id="vl.initcd4CountMessage" class="blank"></div>
		</td>
	</tr>
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.cd4Percent" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.initcd4Percent"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				styleClass="text" 
				styleId="vl.initcd4Percent" />
			<div id="vl.initcd4PercentMessage" class="blank"></div>
		</td>
	</tr>	
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.Cd4Date" />
		</td>
		<td>
			<app:text name="<%=formName%>"
				property="observations.initcd4Date"
				onkeyup="addDateSlashes(this, event);"
				onchange="vl.checkDate(this,false);"
				styleClass="text"
				styleId="vl.initcd4Date" maxlength="10"/>
				<div id="vl.initcd4DateMessage" class="blank"></div>
		</td>
	</tr>
	<tr><td colspan="5"><hr/></td></tr><!-- _________________________________________________ -->
	
	<tr> 
		<td></td>
		<td colspan="3" class="sectionTitle">
			<bean:message key="sample.project.cd4demand" />
		</td>
	</tr>
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.cd4Count" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.demandcd4Count"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				maxlength="4"
				styleClass="text" 
				styleId="vl.demandcd4Count" />
			<div id="vl.demandcd4CountMessage" class="blank"></div>
		</td>
	</tr>
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.cd4Percent" />
		</td>
		<td>
			<app:text name="<%=formName%>" property="observations.demandcd4Percent"
				onchange="makeDirty();compareAllObservationHistoryFields(true);" 
				styleClass="text" 
				styleId="vl.demandcd4Percent" />
			<div id="vl.demandcd4PercentMessage" class="blank"></div>
		</td>
	</tr>	
	<tr> 
		<td></td>
		<td>
			<bean:message key="sample.project.Cd4Date" />
		</td>
		<td>
			<app:text name="<%=formName%>"
				property="observations.demandcd4Date"
				onkeyup="addDateSlashes(this, event);"
				onchange="vl.checkDate(this,false);"
				styleClass="text"
				styleId="vl.demandcd4Date" maxlength="10"/>
				<div id="vl.demandcd4DateMessage" class="blank"></div>
		</td>
	</tr>
	<tr><td colspan="5"><hr/></td></tr><!-- __________________________________________________________________________________________________ -->
    <tr> 
		<td></td>
		<td class="observationsQuestion">
			<bean:message key="sample.project.priorVLRequest"/>
		</td>
		<td>
			<html:select name="<%=formName%>" 
			    property="observations.vlBenefit"	
			    onchange="vl.checkVLBenefit();compareAllObservationHistoryFields(true);" 
				styleId="vl.vlBenefit"  >
				<app:optionsCollection name="<%=formName%>" 
				    property="dictionaryLists.YES_NO.list"
					label="localizedName" value="id" />
			</html:select>
			<div id="vl.vlBenefitMessage" class="blank"></div>
		</td>
	</tr>
	
	<tr id="vl.priorVLLabRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.project.priorVLLab"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.priorVLLab"
				styleClass="text"
				styleId="vl.priorVLLab" maxlength="50"/>
				<div id="vl.priorVLLabMessage" class="blank"></div>
		</td>
		
	</tr>
	<tr id="vl.priorVLValueRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.project.VLValue"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.priorVLValue"
				styleClass="text"
				onkeypress="vl.IsNumeric(this,event);"
				styleId="vl.priorVLValue" maxlength="10"/>
				<div id="vl.priorVLValueMessage" class="blank"></div>
		</td>
		
	</tr>
	
	<tr id="vl.priorVLDateRow" style="display:none"> 
		<td></td>
		<td class="observationsSubquestion">
			<bean:message key="sample.project.VLDate"/>
		</td>
		<td>
		<app:text name="<%=formName%>"
				property="observations.priorVLDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="vl.checkDate(this,false);"
				styleClass="text"
				styleId="vl.priorVLDate" maxlength="10"/>
				<div id="vl.priorVLDateMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
        <td>&nbsp;</td>
    </tr>

    <tr><td colspan="5"><hr/></td></tr>
    <tr id="vl.patientRecordStatusRow"style="display: none;">
        <td class="required"></td>
        <td>
            <bean:message key="patient.project.patientRecordStatus" />
        </td>
        <td>
        <INPUT type="text" id="vl.PatientRecordStatus" size="20" class="readOnly text" disabled="disabled" readonly="readonly" />
        <div id="vl.PatientRecordStatusMessage" class="blank"></div>
        </td>
    </tr>
    <tr id="vl.sampleRecordStatusRow" style="display: none;">
        <td class="required"></td>
        <td>
            <bean:message key="patient.project.sampleRecordStatus" />
        </td>
        <td>
        <INPUT type="text" id="vl.SampleRecordStatus" size="20" class="readOnly text" disabled="disabled" readonly="readonly" />
        <div id="vl.SampleRecordStatusMessage" class="blank"></div>
        </td>
    </tr>
    <tr><td colspan="6"><hr/></td></tr>
    <tr id="vl.underInvestigationRow">
        <td class="required"></td>
        <td>
            <bean:message key="patient.project.underInvestigation" />
        </td>
        <td>
            <html:select name="<%=formName%>"
            property="observations.underInvestigation" onchange="makeDirty();compareAllObservationHistoryFields(true)"
            styleId="vl.underInvestigation">
            <app:optionsCollection name="<%=formName%>"
                property="dictionaryLists.YES_NO.list" label="localizedName"
                value="id" />
            </html:select>
        </td>
    </tr>    
    <tr id="vl.underInvestigationCommentRow" >
        <td class="required"></td>
        <td>
            <bean:message key="patient.project.underInvestigationComment" />
        </td>
        <td colspan="3">
            <app:text name="<%=formName%>" property="ProjectData.underInvestigationNote" maxlength="1000" size="80"
                onchange="makeDirty();" styleId="vl.underInvestigationComment" />
        </td>
    </tr>
	
	
</table>
