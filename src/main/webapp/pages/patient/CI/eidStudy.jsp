<%@ page language="java" contentType="text/html; charset=utf-8"
    import="us.mn.state.health.lims.common.action.IActionConstants,
            us.mn.state.health.lims.common.util.DateUtil,
            us.mn.state.health.lims.common.util.StringUtil"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
function EidProjectChecker() {
    this.idPre = "eid.";

    // eid.eidTypeOfClinicOther
    this.displayTypeOfClinicOther = function () {
        var field = $("eid.eidTypeOfClinic");
        enableSelectedRequiredSubfield(field, $("eid.eidTypeOfClinicOther"), -1, "eid.eidTypeOfClinicOtherRow" );
    }

    this.refresh = function () {
        this.refreshBase();         
        this.displayTypeOfClinicOther();
        var s = $("eid.subjectNumber").value;
        $("eid.infantID").value   = s.slice(-4);     // last 4 chars
        $("eid.codeSiteID").value = s.slice(3,-4); // and the numbers before that, without DBS
        
    }

    this.checkAllSampleItemFields = function () {
        this.checkSampleItem($('eid.dryTubeTaken'));
        this.checkSampleItem($('eid.dbsTaken'));
        this.checkSampleItem($('eid.dbsTaken'), $('eid.dnaPCR'));
    }
    
    this.checkEIDWhichPCR = function (field) {
        makeDirty();
        if (field.selectedIndex == 1) {
            var otherField = $("eid.reasonForSecondPCRTest"); 
            otherField.selectedIndex = otherField.options.length - 1;  
        }
        compareAllObservationHistoryFields(true)
    }
    
    this.setFieldsForEdit = function(canEditPatientIDs, canEditSampleIDs) {
        this.setFieldReadOnly("labNoForDisplay", !canEditSampleIDs);
        this.setFieldReadOnly("infantID", !canEditPatientIDs);
        this.setFieldReadOnly("codeSiteID", !canEditPatientIDs);
        this.setFieldReadOnly("siteSubjectNumber", !canEditPatientIDs);
    }   
}
EidProjectChecker.prototype = new BaseProjectChecker();
eid = new EidProjectChecker();
</script>

<h2><spring:message code="sample.entry.project.EID.title"/></h2>   
<table width="100%">
    <tr>
        <td class="required" width="2%">*</td>
        <td width="28%">
            <spring:message code="sample.entry.project.receivedDate"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
        </td>
        <td width="70%">
        <app:text name="${form.formName}"
                property="receivedDateForDisplay"
                onkeyup="addDateSlashes(this, event);"
                onchange="eid.checkReceivedDate(false)"
                styleClass="text"
                id="eid.receivedDateForDisplay" maxlength="10"/>
                <div id="eid.receivedDateForDisplayMessage" class="blank" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
             <spring:message code="sample.entry.project.receivedTime" />&nbsp;<spring:message code="sample.military.time.format"/>
        </td>
        <td>
        <app:text name="${form.formName}"
            property="receivedTimeForDisplay"
            onkeyup="filterTimeKeys(this, event);"              
            onblur="eid.checkReceivedTime(true);"
            styleClass="text"
            id="eid.receivedTimeForDisplay" maxlength="5"/>
            <div id="eid.receivedTimeForDisplayMessage" class="blank" />
        </td>
    </tr>
    <tr>
        <td class="required">*</td>
        <td>
            <spring:message code="sample.entry.project.dateTaken"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
        </td>
        <td>
        <app:text name="${form.formName}"
                property="interviewDate"
                onkeyup="addDateSlashes(this, event);"
                onchange="eid.checkInterviewDate(true);"
                styleClass="text"
                id="eid.interviewDate" maxlength="10"/>
                <div id="eid.interviewDateMessage" class="blank" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <spring:message code="sample.entry.project.timeTaken"/>&nbsp;<spring:message code="sample.military.time.format"/>
        </td>
        <td>
        <app:text name="${form.formName}"
                property="interviewTime"
                onkeyup="filterTimeKeys(this, event);"                
                onblur="eid.checkInterviewTime(true);"
                styleClass="text"
                id="eid.interviewTime" maxlength="5"/>
                <div id="eid.interviewTimeMessage" class="blank" />
        </td>
    </tr>       
    <tr>
        <td class="required">*</td>
        <td>
            <spring:message code="sample.entry.project.siteName"/>
        </td>

        <td>
            <html:select name="${form.formName}"
                         property="ProjectData.EIDSiteName"
                         id="eid.centerName"
                         onchange="eid.checkCenterName(true)">
                <app:optionsCollection name="${form.formName}"
                    property="organizationTypeLists.EID_ORGS_BY_NAME.list"
                    label="organizationName"
                    value="id" />
            </html:select>
        </td>
    </tr>
    <tr>
        <td class="required">*</td>
        <td><spring:message code="sample.entry.project.siteCode"/></td>
        <td style="width: 40%;">
            <html:select name="${form.formName}"  property="ProjectData.EIDsiteCode" styleClass="text"
                    id="eid.centerCode"
                    onchange="eid.checkCenterCode(true);" >
                <app:optionsCollection name="${form.formName}" property="organizationTypeLists.EID_ORGS.list" label="doubleName" value="id" />
            </html:select>
            <div id="eid.centerCodeMessage" class="blank"/>
        </td>

    </tr>
    <tr>
        <td class="required">+</td>
        <td><spring:message code="sample.entry.project.EID.infantNumber"/></td>
        <td>
            <div class="blank">DBS</div>
            <INPUT type="text" name="eid.codeSiteId" id="eid.codeSiteID" size="4" class="text"
                onchange="handleDBSSubjectId(); makeDirty();"
                maxlength="4" />
            <INPUT type="text" name="eid.infantID" id="eid.infantID" size="4" class="text"
                onchange="handleDBSSubjectId(); makeDirty();"
                maxlength="4" />
            <app:text name="${form.formName}" property="subjectNumber"
                    styleClass="text" style="display:none;"
                    id="eid.subjectNumber"
                    onchange="checkRequiredField(this); makeDirty();" />
            <div id="eid.subjectNumberMessage" class="blank" ></div>
        </td>
    </tr>
    <tr>
        <td class="required">+</td>
        <td><spring:message code="sample.entry.project.EID.siteInfantNumber"/></td>
        <td>
            <app:text name="${form.formName}"
                property="siteSubjectNumber"
                id="eid.siteSubjectNumber"
                styleClass="text"
                onchange="eid.checkSiteSubjectNumber(true)"/>
            <div id="eid.siteSubjectNumberMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td class="required">*</td>
        <td>
            <%=StringUtil.getContextualMessageForKey("quick.entry.accession.number")%>
        </td>
        <td>
            <div class="blank"><spring:message code="sample.entry.project.LDBS"/></div>
            <INPUT type="text" name="eid.labNoForDisplay" id="eid.labNoForDisplay" size="5" class="text"
                onchange="handleLabNoChange( this, '<spring:message code="sample.entry.project.LDBS"/>', false );makeDirty();"
                maxlength="5" />
            <app:text name="${form.formName}" property="labNo"
                    styleClass="text" style="display:none;"
                    id="eid.labNo" />
            <div id="eid.labNoMessage" class="blank" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidWhichPCR" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.whichPCR"
                    styleClass="text" id="eid.whichPCR"
                    onchange="eid.checkEIDWhichPCR(this)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_WHICH_PCR.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.whichPCRMessage" class="blank"/>
        </td>
    </tr>

    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="sample.entry.project.EID.reasonForPCRTest" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.reasonForSecondPCRTest" styleClass="text" id="eid.reasonForSecondPCRTest"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_SECOND_PCR_REASON.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.reasonForSecondPCRTestMessage" class="blank"/>
        </td>
    </tr>

    <tr>
        <td></td>
        <td>
            <spring:message code="patient.project.nameOfRequestor" />
        </td>
        <td>
            <app:text name="${form.formName}"
                      property="observations.nameOfRequestor"
                      onchange="makeDirty();compareAllObservationHistoryFields(true)"
                      styleClass="text"
                      id="eid.nameOfRequestor" size="50"/>
            <div id="eid.nameOfRequestorMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <spring:message code="patient.project.nameOfSampler" />
        </td>
        <td>
            <html:text name="${form.formName}"
                      property="observations.nameOfSampler"
                      onchange="makeDirty();compareAllObservationHistoryFields(true)"
                      styleClass="text"
                      id="eid.nameOfSampler" size="50"/>
            <div id="eid.nameOfSamplerMessage" class="blank"></div>
        </td>
    </tr>
        <tr>
            <td></td>
            <td colspan="3" class="sectionTitle">
                <spring:message code="sample.entry.project.title.infantInformation" />
            </td>
        </tr>

        <tr>
            <td class="required">*</td>
            <td>
                <spring:message code="patient.birthDate" />&nbsp;<%=DateUtil.getDateUserPrompt()%>
            </td>
            <td>
                <app:text name="${form.formName}"
                      property="birthDateForDisplay"
                      styleClass="text"
                      size="20"
                      maxlength="10"
                      id="eid.dateOfBirth"
                      onkeyup="addDateSlashes(this, event);"
                      onchange="eid.checkDateOfBirth(false);"
                      />
                <div id="eid.dateOfBirthMessage" class="blank" ></div>
            </td>
        </tr>
        <tr>
            <td class="required"></td>
            <td>
                <spring:message code="patient.age" />
            </td>
            <td>
                <label for="eid.month" ><spring:message code="label.month" /></label>
                <INPUT type="text" name="age" id="eid.month" size="3"
                    onchange="eid.checkAge( this, true, 'month' );clearField('eid.ageWeek');"
                    maxlength="2" />
                <label for="eid.ageWeek" ><spring:message code="label.week" /></label>
                <INPUT type="text" name="ageWeek" id="eid.ageWeek" size="3"
                    onchange="eid.checkAge( this, true, 'week' ); clearField('eid.month');"
                    maxlength="2" />
                <div id="ageMessage" class="blank" ></div>
            </td>
        </tr>

        <tr>
            <td class="required">*</td>
            <td>
                <spring:message code="patient.gender" />
            </td>
            <td>
                <html:select name="${form.formName}"
                         property="gender"
                         id="eid.gender"
                         onchange="eid.checkGender(true)" >
                <app:optionsCollection name="${form.formName}" property="formLists.GENDERS"
                    label="localizedName" value="genderType" />
                </html:select>
                <div id="eid.genderMessage" class="blank" />
            </td>
        </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidBenefitPTME" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidInfantPTME" styleClass="text" id="eid.eidInfantPTME"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.YES_NO.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.InfantPTMEMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidTypeOfClinic" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidTypeOfClinic" styleClass="text" id="eid.eidTypeOfClinic"
                    onchange="makeDirty();projectChecker.displayTypeOfClinicOther();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_TYPE_OF_CLINIC.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidTypeOfClinicMessage" class="blank"/>
        </td>
    </tr>
    <tr id="eid.eidTypeOfClinicOtherRow" style="display: none">
        <td>
        </td>
        <td class="observationsSubquestion"><em><spring:message code="patient.project.specify" /></em></td>
        <td>
            <html:text name="${form.formName}"
                      property="observations.eidTypeOfClinicOther"
                      onchange="makeDirty();compareAllObservationHistoryFields(true)"
                      styleClass="text"
                      id="eid.eidTypeOfClinicOther" />
            <div id="eid.eidTypeOfClinicOtherMessage" class="blank"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidHowChildFed" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidHowChildFed" styleClass="text" id="eid.eidHowChildFed"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_HOW_CHILD_FED.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidHowChildFedMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidStoppedBreastfeeding"/></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidStoppedBreastfeeding" styleClass="text" id="eid.eidStoppedBreastfeeding"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_STOPPED_BREASTFEEDING.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidStoppedBreastfeedingMessage" class="blank"/>
        </td>
    </tr>

    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidInfantSymptomatic" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidInfantSymptomatic" styleClass="text" id="eid.eidInfantSymptomatic"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.YES_NO.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidInfantSymptomaticMessage" class="blank"/>
        </td>
    </tr>
        <td></td>
            <td class="observationsQuestion"><spring:message code="patient.project.eidInfantProphy" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidInfantsARV" styleClass="text" id="eid.eidInfantsARV"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_INFANT_PROPHYLAXIS_ARV.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidInfantsARVMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td></td>
            <td class="observationsQuestion"><spring:message code="patient.project.eidInfantCotrimoxazole" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidInfantCotrimoxazole" styleClass="text" id="eid.eidInfantCotrimoxazole"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.YES_NO_UNKNOWN.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidInfantCotrimoxazoleMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td colspan="3" class="sectionTitle">
            <spring:message code="sample.entry.project.title.mothersInformation" />
        </td>
    </tr>   
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidMothersStatus" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidMothersHIVStatus" styleClass="text" id="eid.eidMothersHIVStatus"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_MOTHERS_HIV_STATUS.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidMothersHIVStatusMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="observationsQuestion"><spring:message code="patient.project.eidMothersARV" /></td>
        <td>
            <html:select name="${form.formName}" property="observations.eidMothersARV" styleClass="text" id="eid.eidMothersARV"
                    onchange="makeDirty();compareAllObservationHistoryFields(true)" >
                <app:optionsCollection name="${form.formName}" property="dictionaryLists.EID_MOTHERS_ARV_TREATMENT.list" label="localizedName" value="id" />
            </html:select>
            <div id="eid.eidMothersARVMessage" class="blank"/>
        </td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>

    <tr><td colspan="5"><hr/></td></tr>
    <tr id="eid.patientRecordStatusRow"style="display: none;">
        <td class="required"></td>
        <td>
            <spring:message code="patient.project.patientRecordStatus" />
        </td>
        <td>
        <INPUT type="text" id="eid.PatientRecordStatus" size="20" class="readOnly text" disabled="disabled" readonly="readonly" />
        <div id="eid.PatientRecordStatusMessage" class="blank"></div>
        </td>
    </tr>
    <tr id="eid.sampleRecordStatusRow" style="display: none;">
        <td class="required"></td>
        <td>
            <spring:message code="patient.project.sampleRecordStatus" />
        </td>
        <td>
        <INPUT type="text" id="eid.SampleRecordStatus" size="20" class="readOnly text" disabled="disabled" readonly="readonly" />
        <div id="eid.SampleRecordStatusMessage" class="blank"></div>
        </td>
    </tr>
    <tr><td colspan="6"><hr/></td></tr>
    <tr id="eid.underInvestigationRow">
        <td class="required"></td>
        <td>
            <spring:message code="patient.project.underInvestigation" />
        </td>
        <td>
            <html:select name="${form.formName}"
            property="observations.underInvestigation" onchange="makeDirty();compareAllObservationHistoryFields(true)"
            id="eid.underInvestigation">
            <app:optionsCollection name="${form.formName}"
                property="dictionaryLists.YES_NO.list" label="localizedName"
                value="id" />
            </html:select>
        </td>
    </tr>    
    <tr id="eid.underInvestigationCommentRow" >
        <td class="required"></td>
        <td>
            <spring:message code="patient.project.underInvestigationComment" />
        </td>
        <td colspan="3">
            <app:text name="${form.formName}" property="ProjectData.underInvestigationNote" maxlength="1000" size="80"
                onchange="makeDirty();" id="eid.underInvestigationComment" />
        </td>
    </tr>
</table>
