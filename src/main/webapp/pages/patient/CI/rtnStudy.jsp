<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			org.apache.commons.httpclient.NameValuePair,
			us.mn.state.health.lims.common.util.DateUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>

<script type="text/javascript">

function RtnProjectChecker() {
	this.idPre = "rtn.";

	this.displayNationality = function() {
		enableSelectedRequiredSubfield($(this.idPre + 'nationality'),
				$(this.idPre + 'nationalityOther'), -2,
				this.idPre + 'nationalityOtherRow')
	}

	this.checkNameOfDoctor = function(blanksAllowed) {
		makeDirty();
		checkRequiredField($("rtn.nameOfDoctor"));
		compareAllObservationHistoryFields(true);
	}

	this.checkAllSampleFields = function(blanksAllowed) {
		this.checkInterviewDate(blanksAllowed);
		this.checkReceivedDate(blanksAllowed);
        this.checkInterviewTime(true);
        this.checkReceivedTime(true);		
	}

	this.refresh = function() {
		this.refreshBase();
		this.displayNationality();
	}

	this.setFieldsForEdit = function(canEditPatientIDs, canEditSampleIDs) {
		this.setFieldReadOnly("labNoForDisplay", !canEditSampleIDs);
	}
}

RtnProjectChecker.prototype = new BaseProjectChecker();
rtn = new RtnProjectChecker();
</script>

<h2>
	<spring:message code="sample.entry.project.RTN.title" />
</h2>
<table>
	<tr>
		<td class="required">
			*
		</td>
		<td>
			<spring:message code="sample.entry.project.receivedDate" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="${form.formName}" property="receivedDateForDisplay"
				onkeyup="addDateSlashes(this, event);"
				onchange="rtn.checkReceivedDate(false); makeDirty();"
				styleClass="text"
				id="rtn.receivedDateForDisplay" maxlength="10" />
			<div id="rtn.receivedDateForDisplayMessage" class="blank" />
		</td>
	</tr>
	<tr>
		<!-- DEM 01 -->
		<td class="required">
			*
		</td>
		<td class="observationsQuestion">
			<spring:message code="sample.entry.project.dateTaken" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="${form.formName}" property="interviewDate"
				onkeyup="addDateSlashes(this, event);"
				onchange="rtn.checkInterviewDate(false)" styleClass="text"
				maxlength="10" id="rtn.interviewDate" />
			<div id="rtn.interviewDateMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 02 -->
		<td class="required">
			*
		</td>
		<td id="observationsQuestion">
			<spring:message code="patient.project.nameOfDoctor" />
		</td>
		<td>
			<app:text name="${form.formName}" property="observations.nameOfDoctor"
				styleClass="text" id="rtn.nameOfDoctor" size="50"
				onchange="rtn.checkNameOfDoctor(true);" />
			<div id="nameOfDoctorMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 03 -->
		<td class="required">
			*
		</td>
		<td>
			<spring:message code="patient.project.hospitals" />
		</td>
		<td>
			<html:select name="${form.formName}" property="centerCode"
				id="rtn.centerCode" styleClass="text"
				onchange="rtn.checkCenterCode(false)">
				<app:optionsCollection name="${form.formName}"
					property="organizationTypeLists.RTN_HOSPITALS.list"
					label="doubleName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr>
		<!-- DEM 04 -->
		<td class="required">
			*
		</td>
		<td class="observationsQuestion">
			<spring:message code="patient.project.service" />
		</td>
		<td>
			<html:select name="${form.formName}" property="observations.service"
				id="rtn.service" styleClass="text"
				onchange="compareAllObservationHistoryFields(true);">
				<app:optionsCollection name="${form.formName}"
					property="organizationTypeLists.RTN_SERVICES.list"
					label="doubleName" value="id" />
			</html:select>
		</td>
	</tr>
	<tr>
		<!-- DEM 05 -->
		<td></td>
		<td class="observationsQuestion">
			<spring:message code="patient.project.hospitalPatient" />
		</td>
		<td>
			<html:select name="${form.formName}"
				property="observations.hospitalPatient" styleClass="text"
				id="rtn.hospitalPatient"
				onchange="compareAllObservationHistoryFields(true);">
				<app:optionsCollection name="${form.formName}"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="rtn.hospitalPatientMessage" class="blank" />
		</td>
	</tr>
	<tr>
		<!-- DEM 06 -->
		<td></td>
		<td class="observationsQuestion">
			<spring:message code="patient.project.patientFamilyName" />
		</td>
		<td>
			<app:text name="${form.formName}" property="lastName" styleClass="text"
				id="rtn.patientFamilyName" onchange="rtn.checkFamilyName(true)"
				size="40" />
			<div id="rtn.patientFamilyNameMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 07 -->
		<td></td>
		<td>
			<spring:message code="patient.project.patientFirstNames" />
		</td>
		<td>
			<app:text name="${form.formName}" property="firstName" styleClass="text"
				id="rtn.patientFirstNames" onchange="rtn.checkFirstNames(true)"
				size="40" />
			<div id="rtn.patientFirstNamesMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 08 -->
		<td class="required">
			*
		</td>
		<td>
			<spring:message code="patient.project.dateOfBirth" />
			&nbsp;
			<%=DateUtil.getDateUserPrompt()%>
		</td>
		<td>
			<app:text name="${form.formName}" property="birthDateForDisplay"
				styleClass="text" id="rtn.dateOfBirth" maxlength="10"
				onkeyup="addDateSlashes(this, event);"
				onchange="rtn.checkDateOfBirth(true)" />
			<div id="rtn.dateOfBirthMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<spring:message code="patient.age" />
		</td>
		<td>
			<label for="rtn.age">
				<spring:message code="label.year" />
			</label>
			<INPUT type="text" name="ageYear" id="rtn.age" size="3"
				onchange="rtn.checkAge( this, true, 'year' ); clearField('rtn.month');"
				maxlength="2" />
			<label for="rtn.month">
				<spring:message code="label.month" />
			</label>
			<INPUT type="text" name="ageMonth" id="rtn.month" size="3"
				onchange="rtn.checkAge( this, true, 'month' );clearField('rtn.age');"
				maxlength="2" />
			<div id="rtn.ageMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 10 -->
		<td class="required">
			*
		</td>
		<td>
			<spring:message code="patient.project.gender" />
		</td>
		<td>
			<html:select name="${form.formName}" property="gender"
				id="rtn.gender" styleClass="text"
				onchange="rtn.checkGender(true)">
				<app:optionsCollection name="${form.formName}"
					property="formLists.GENDERS" label="localizedName"
					value="genderType" />
			</html:select>
			<div id="genderMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 11 -->
		<td></td>
		<td>
			<spring:message code="patient.project.nationality" />

		</td>
		<td>
			<html:select name="${form.formName}" property="observations.nationality"
				id="rtn.nationality" styleClass="text"
				onchange="rtn.displayNationality();compareAllObservationHistoryFields(true)">
				<app:optionsCollection name="${form.formName}"
					property="dictionaryLists.NATIONALITIES.list" label="localizedName"
					value="id" />
			</html:select>
			<div id="rtn.nationalityMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="rtn.nationalityOtherRow" style="display: none">
		<!-- DEM 11.1 -->
		<td class="required">
			*
		</td>
		<td class="observationsSubquestion">
			<spring:message code="patient.project.nationalityOther" />
		</td>
		<td>
			<app:text name="${form.formName}"
				property="observations.nationalityOther"
				onchange="checkRequiredField(this);compareAllObservationHistoryFields(true)"
				styleClass="text" id="rtn.nationalityOther" />
			<div id="rtn.nationalityOtherMessage" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 12 -->
		<td class="required"></td>
		<td>
			<spring:message code="patient.project.serologyReason" />
		</td>
		<td>
			<app:text name="${form.formName}" property="observations.reason"
				styleClass="text" id="rtn.reason"
				onchange="compareAllObservationHistoryFields(true)" />
			<div id="rtn.reason" class="blank"></div>
		</td>
	</tr>
	<tr>
		<!-- DEM 13 -->
		<td class="required">
			*
		</td>
		<td>
			<spring:message code="patient.project.labNo" />
		</td>
		<td>
			<div class="blank">
				<spring:message code="sample.entry.project.LRTN" />
			</div>
			<INPUT type=text name="rtn.labNoForDisplay" id="rtn.labNoForDisplay"
				size="5" class="text"
				onchange="handleLabNoChange( this, 'LRTN', false ); makeDirty();"
				maxlength="5" />
			<app:text name="${form.formName}" property="labNo" styleClass="text"
				style="display:none;" id="rtn.labNo" />
			<div id="rtn.labNoMessage" class="blank" />
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<hr />
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center">
			<spring:message code="patient.project.medicalHistory" />
		</td>
	</tr>		
	<logic:iterate id="disease" indexId="i" name="${form.formName}"
		type="NameValuePair" property="observations.rtnPriorDiseasesList">
		<tr id='<%="priorDiseasesRow" + i%>' >
			<td></td>
			<td class="observationsQuestion observationsSubquestion">
				<bean:write name="disease" property="value" />
			</td>
			<td>
				<html:select name="${form.formName}"
					property='<%= "observations." + disease.getName() %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true)"
					id='<%= "rtn." + disease.getName() %>'>
					<app:optionsCollection name="${form.formName}"
						property="dictionaryLists.YES_NO_UNKNOWN_NA_NOTSPEC.list"
						label="localizedName" value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	<tr><td colspan="2" style="text-align:center"><spring:message code="patient.project.physicalExam" /></td>
	<!-- current -->
	<logic:iterate id="disease" indexId="i" name="${form.formName}"
		type="NameValuePair" property="observations.rtnCurrentDiseasesList">
		<tr id='<%="currentDiseasesRow" + i%>' >
			<td></td>
			<td class="observationsQuestion observationsSubquestion">
				<bean:write name="disease" property="value" />
			</td>
			<td>
				<html:select name="${form.formName}"
					property='<%= "observations." + disease.getName() %>'
					onchange="makeDirty();compareAllObservationHistoryFields(true)"
					id='<%= "rtn." + disease.getName() %>'>
					<app:optionsCollection name="${form.formName}"
						property="dictionaryLists.YES_NO_UNKNOWN_NA_NOTSPEC.list"
						label="localizedName" value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
		<%--
	<logic:iterate id="disease" indexId="i" name="${form.formName}" property="dictionaryLists.RTN_DISEASES.list" >
		<tr id='<%= "rtn.priorDiseasesRow" + i %>' > <!-- CLI 09.<%= i %> -->
			<td></td>
			<td class="observationQuestion"><bean:write name="disease" property="localizedName"/></td>
			<td>
				<html:select name="${form.formName}" property='<%= "observations.priorDiseases[" + i + "]" %>'
				    id='<%= "rtn.priorDiseases" + i %>' onchange="compareAllObservationHistoryFields(true)">
					<app:optionsCollection name="${form.formName}" property="dictionaryLists.YES_NO_UNKNOWN_NA_NOTSPEC.list" label="localizedName" value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	<logic:iterate id="disease" indexId="i" name="${form.formName}" property="dictionaryLists.RTN_EXAM_DISEASES.list" >
		<tr id='<%= "rtn.currDiseasesRow" + i %>' > <!-- CLI 09.n -->
			<td></td>
			<td class="observationQuestion"><bean:write name="disease" property="localizedName"/></td>
			<td>
				<html:select name="${form.formName}" property='<%= "observations.currentDiseases[" + i + "]" %>'
				    id='<%= "rtn.currentDiseases" + i %>' onchange="compareAllObservationHistoryFields(true)">
					<app:optionsCollection name="${form.formName}" property="dictionaryLists.YES_NO_UNKNOWN_NA_NOTSPEC.list" label="localizedName" value="id" />
				</html:select>
			</td>
		</tr>
	</logic:iterate>
	 --%>
	<tr>
		<td colspan="5">
			<hr />
		</td>
	</tr>
	<tr>
		<td colspan="5">
		</td>
	</tr>
	<tr id="rtn.patientRecordStatusRow" style="display: none;">
		<td class="required"></td>
		<td>
			<spring:message code="patient.project.patientRecordStatus" />
		</td>
		<td>
			<INPUT type="text" id="rtn.PatientRecordStatus" size="20"
				class="text readOnly" disabled="disabled" readonly="readonly" />
			<div id="rtn.PatientRecordStatusMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="rtn.sampleRecordStatusRow" style="display: none;">
		<td class="required"></td>
		<td>
			<spring:message code="patient.project.sampleRecordStatus" />
		</td>
		<td>
			<INPUT type="text" id="rtn.SampleRecordStatus" size="20"
				class="text readOnly" disabled="disabled" readonly="readonly" />
			<div id="rtn.SampleRecordStatusMessage" class="blank"></div>
		</td>
	</tr>
	<tr id="rtn.underInvestigationRow">
		<td class="required"></td>
		<td>
			<spring:message code="patient.project.underInvestigation" />
		</td>
		<td>
			<html:select name="${form.formName}"
				property="observations.underInvestigation"
				onchange="makeDirty();compareAllObservationHistoryFields(true)"
				id="rtn.underInvestigation">
				<app:optionsCollection name="${form.formName}"
					property="dictionaryLists.YES_NO.list" label="localizedName"
					value="id" />
			</html:select>
		</td>
	</tr>
	<tr id="rtn.underInvestigationCommentRow">
		<td class="required"></td>
		<td>
			<spring:message code="patient.project.underInvestigationComment" />
		</td>
		<td colspan="3">
			<app:text name="${form.formName}"
				property="ProjectData.underInvestigationNote" maxlength="1000"
				size="80" onchange="makeDirty();"
				id="rtn.underInvestigationComment" />
		</td>
	</tr>
</table>