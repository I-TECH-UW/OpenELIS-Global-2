<%@page import="org.openelisglobal.common.action.IActionConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.formfields.FormFields,
	org.openelisglobal.sample.util.AccessionNumberUtil,
	        org.openelisglobal.common.formfields.FormFields.Field,
	        org.openelisglobal.common.util.ConfigurationProperties,
	        org.openelisglobal.common.util.IdValuePair,
	        org.openelisglobal.common.util.ConfigurationProperties.Property,
	        org.openelisglobal.common.util.DateUtil,
	        org.openelisglobal.internationalization.MessageUtil,
	        org.openelisglobal.common.util.Versioning"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags"%>

<c:set var="formName" value="${form.formName}" />
<c:set var="entryDate" value="${form.currentDate}" />

<link href="select2/css/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="select2/js/select2.min.js"></script>
<script type="text/javascript" src="scripts/additional_utilities.js"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?"></script>
<script type="text/javascript" src="scripts/laborder.js?"></script>

<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?" />
<script type="text/javascript" src="select2/js/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="select2/css/select2.min.css">

<script type="text/javascript">
	function showHideSection(button, targetId) {
		targetId = targetId + button.name
		if (button.value == "+") {
			showSection(button, targetId);
		} else {
			hideSection(button, targetId);
		}
	}

	function showSection(button, targetId) {
		jQuery("#" + targetId).show();
		button.value = "-";
		console.log(targetId);
	}

	function hideSection(button, targetId) {
		jQuery("#" + targetId).hide();
		button.value = "+";
		console.log(targetId);
	}
</script>



<div id="tb_container">
	<%=MessageUtil.getContextualMessage("referring.order.number")%>:
	<form:input id="externalOrderNumber" path="externalOrderNumber"
		onchange="checkOrderReferral();makeDirty();" />
	<input type="button" name="searchExternalButton"
		value='<%=MessageUtil.getMessage("label.button.search")%>'
		onclick="checkOrderReferral();makeDirty();">
	<%=MessageUtil.getContextualMessage("referring.order.not.found")%>
	<hr style="width: 100%; height: 1px" />
	<br>
	<div id=orderEntrySection>
		<input type="button" name="showHide" value='-'
			onclick="showHideSection(this, 'orderDisplay');" id="orderSectionId">
		<%=MessageUtil.getContextualMessage("sample.entry.order.label")%>
		<span class="requiredlabel">*</span>
		<table id="orderDisplayshowHide">
			<tr>
				<td style="width: 35%"><%=MessageUtil.getContextualMessage("quick.entry.accession.number")%>
					:</td>
				<td style="width: 65%"><form:input path="labNo"
						maxlength='<%=Integer.toString(AccessionNumberUtil.getMaxAccessionLength())%>'
						onchange="checkAccessionNumber(this);" cssClass="text" id="labNo" />

					<spring:message code="sample.entry.scanner.instructions"
						htmlEscape="false" /> <input type="button"
					id="generateAccessionButton"
					value='<%=MessageUtil.getMessage("sample.entry.scanner.generate")%>'
					onclick="setOrderModified();getNextAccessionNumber(); "
					class="textButton"></td>
			</tr>

			<tr>
				<td><spring:message code="sample.entry.requestDate" />: <span
					class="requiredlabel">*</span><span style="font-size: xx-small;"><%=DateUtil.getDateUserPrompt()%></span></td>
				<td><form:input path="requestDate" id="requestDate"
						cssClass="required"
						onchange="setOrderModified();checkValidEntryDate(this, 'past')"
						onkeyup="addDateSlashes(this, event);" maxlength="10" />
			</tr>
			<tr>
				<td><%=MessageUtil.getContextualMessage("quick.entry.received.date")%>
					: <span class="requiredlabel">*</span> <span
					style="font-size: xx-small;"><%=DateUtil.getDateUserPrompt()%>
				</span></td>
				<td colspan="2"><form:input path="receivedDate"
						onchange="setOrderModified();checkValidEntryDate(this, 'past');"
						onkeyup="addDateSlashes(this, event);" maxlength="10"
						cssClass="text required" id="receivedDateForDisplay" /></td>
			</tr>
			<tr>
				<td><%=MessageUtil.getContextualMessage("sample.tb.reference.unit")%>
					: <span class="requiredlabel">*</span></td>
				<td colspan="2"><form:select path="referringSiteCode"
						id="referringSiteCode" cssClass="centerCodeClass">
						<form:options items="${form.referralOrganizations}"
							itemLabel="value" itemValue="id" />
					</form:select></td>
			</tr>
			<tr class="provider-info-row provider-extra-info-row">
				<td><%=MessageUtil.getContextualMessage("sample.entry.provider.name")%>:
				</td>
				<td><form:input path="providerLastName" id="providerLastName"
						onchange="" /></td>

			</tr>
			<tr class="provider-info-row provider-extra-info-row">
				<td><%=MessageUtil.getContextualMessage("sample.entry.provider.firstName")%>:
				</td>
				<td><form:input path="providerFirstName" id="providerFirstName"
						onchange="" /></td>
			</tr>

			<tr class="spacerRow">
				<td>&nbsp;</td>
			</tr>
		</table>
		<hr style="width: 100%; height: 2px" />
	</div>
	<br />
	<div id=patientEntrySection>
		<input type="button" name="showHide" value='-'
			onclick="showHideSection(this, 'patientDisplay');"
			id="patientSectionId">
		<%=MessageUtil.getContextualMessage("sample.entry.patient")%>
		<span class="requiredlabel">*</span>
		<table id="patientDisplayshowHide">
			<tr>
				<td style=""><spring:message code="patient.subject.tbnumber" />:
					<span class="requiredlabel">*</span></td>
				<td><form:input path="tbSubjectNumber" id="tbSubjectNumber"
						onchange="validateSubjectNumber(this, 'subjectNumber');"
						cssClass="text" /></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td style=""><spring:message code="patient.epiLastName" /> : <span
					class="requiredlabel">*</span></td>
				<td><form:input path="patientLastName" id="lastNameID"
						onchange="updatePatientEditStatus();" /></td>
				<td style=""><spring:message code="patient.epiFirstName" /> :<span
					class="requiredlabel"></span></td>
				<td><form:input path="patientFirstName" id="firstNameID"
						onchange="" size="25" /></td>
			</tr>
			<tr>
				<td style=""><spring:message code="person.phone" />:</td>
				<td><form:input path="patientPhone" cssClass="text" onchange=""
						id="patientPhone" /></td>
				<td style=""><spring:message code="person.streetAddress" />:</td>
				<td><form:input path="patientAddress" cssClass="text" size="25"
						onchange="" id="patientAddress" /></td>
			</tr>
			<tr>
				<td style=""><spring:message code="patient.birthDate" />&nbsp;<%=DateUtil.getDateUserPrompt()%>:
					<span class="requiredlabel">*</span></td>
				<td><form:input path="patientBirthDate"
						onkeyup="addDateSlashes(this,event); normalizeDateFormat(this);"
						onchange="checkValidAgeDate( this ); updatePatientEditStatus();"
						id="dateOfBirthID" cssClass="text" size="20" maxlength="10" />
					<div id="patientbirthDateMessage" class="blank"></div></td>
				<td style=""><spring:message code="patient.age" />:</td>
				<td><form:input path="patientAge"
						onchange="handleAgeChange(); " id="ageYears" cssClass="text"
						size="3" maxlength="3" placeholder="years" />
					<div class="blank">
						<spring:message code="years.label" />
					</div>
					<div id="ageYearsMessage" class="blank"></div></td>
				<td style=""><spring:message code="patient.gender" />: <span
					class="requiredlabel">*</span></td>
				<td><form:select path="patientGender" id="genderID" onchange="">

						<option value=" "></option>
						<form:options items="${form.genders}" itemLabel="value"
							itemValue="id" />
					</form:select></td>
			</tr>
			<tr class="spacerRow">
				<td>&nbsp;</td>
			</tr>
		</table>
		<hr style="width: 100%; height: 2px" />
	</div>
	<br />
	<div id=sampleEntrySection>
		<input type="button" name="showHide" value='-'
			onclick="showHideSection(this, 'sampleDisplay');"
			id="sampleSectionId">
		<%=MessageUtil.getContextualMessage("sample.entry.sampleList.label")%>
		<span class="requiredlabel">*</span>
		<div id="sampleDisplayshowHide">
			<table>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.specimen.nature")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbSpecimenNature"
							id="tbSpecimenNature">
							<form:options items="${form.tbSpecimenNatures}" itemLabel="value"
								itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.order.reasons")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbOrderReason"
							id="tbOrderReasons">
							<form:options items="${form.tbOrderReasons}" itemLabel="value"
								itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.diagnostic.reasons")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbDiagnosticReason"
							id="tbDiagnosticReasons">
							<form:options items="${form.tbDiagnosticReasons}" itemLabel="value"
								itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.followup.reasons")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbFollowupReason"
							id="tbFollowupReasons">
							<form:options items="${form.tbFollowupReasons}" itemLabel="value"
								itemValue="id" />
						</form:select> <form:select path="tbFollowupPeriod" id="tbFollowupPeriod" /></td>
				</tr>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.diagnostic.methods")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbDiagnosticMethod"
							id="tbDiagnosticMethod" multiple="true">
							<form:options items="${form.tbDiagnosticMethods}" itemLabel="value"
								itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td><%=MessageUtil.getContextualMessage("sample.tb.aspects")%>
						: <span class="requiredlabel">*</span></td>
					<td colspan="2"><form:select path="tbAspect" id="tbAspects">
							<form:options items="${form.tbAspects}" itemLabel="value"
								itemValue="id" />
						</form:select></td>
				</tr>
			</table>
			<br />
			<div id="testSelections" class="testSelections">
				<table style="margin-left: 1%; width: 60%;" id="addTables">
					<tr>
						<td style="width: 30%; vertical-align: top;"><span
							class="caption"> <spring:message
									code="sample.entry.panels" />
						</span></td>
						<td style="width: 70%; vertical-align: top; margin-left: 3%;">
							<span class="caption"> <spring:message
									code="sample.entry.available.tests" />
						</span>
						</td>
					</tr>
					<tr>
						<td><select class="panelDropdown" style="width: 97%;"
							multiple="multiple">
						</select></td>
						<td><select class="testDropdown" style="width: 97%;"
							multiple="multiple">
						</select></td>
					</tr>
					<tr>
						<td style="width: 30%; vertical-align: top;">
							<table style="width: 97%" id="addPanelTable"
								class="addPanelTable">
								<tr>
									<th style="width: 20%">&nbsp;</th>
									<th style="width: 80%"><spring:message
											code="sample.entry.panel.name" /></th>
								</tr>

							</table>
						</td>
						<td style="width: 70%; vertical-align: top; margin-left: 3%;">
							<table style="width: 97%" id="addTestTable" class="addTestTable">
								<tr>
									<th style="width: 5%">&nbsp;</th>
									<th style="width: 50%"><spring:message
											code="sample.entry.available.test.names" /></th>
									<th style="width: 40%; display: none;" id="sectionHead">
										Section</th>
									<th style="width: 20%">&nbsp;</th>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<hr style="width: 100%; height: 2px" />
	</div>
</div>

<br />
<script type="text/javascript">
	function pageOnLoad() {
		jQuery('.centerCodeClass').select2();
	}
</script>