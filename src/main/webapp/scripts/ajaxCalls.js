function defaultFailure(xhr){
    alert(xhr.responseText);
}

function getLabOrder( orderNumber, success, failure){
	if( !failure ){	failure = defaultFailure;}
	
	new Ajax.Request('ajaxQueryXML',
			{
				method : 'get', 
				parameters : "provider=LabOrderSearchProvider&orderNumber=" + orderNumber ,
			    //indicator: 'throbbing',
				onSuccess : success,
				onFailure : failure
			});

}

function getTestNames( testId, success, failure){
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request('ajaxQueryXML',
        {
            method : 'get',
            parameters : "provider=TestNamesProvider&testId=" + testId ,
            //indicator: 'throbbing',
            onSuccess : success,
            onFailure : failure
        });
}

function getTestEntities( testId, success, failure){
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request('ajaxQueryXML',
        {
            method : 'get',
            parameters : "provider=TestEntitiesProvider&testId=" + testId ,
            //indicator: 'throbbing',
            onSuccess : success,
            onFailure : failure
        });
}

/**
 * A generic way to get localized names for a given entity rather than a new call for each type.  Expand and document as needed
 * @param entitiyId
 * @param entityName may only be one of "panel"  The names can also be found in EntityNamesProvider
 * @param success
 * @param failure
 */
function getEntityNames( entitiyId,entityName ,success, failure){
    var permitted = ['panel','sampleType','testSection','unitOfMeasure'];

    if( permitted.indexOf(entityName) == -1){
        alert( "\"" + entityName + "\" has not been implemented for getEntityNames");
        return;
    }
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request('ajaxQueryXML',
        {
            method : 'get',
            parameters : "provider=EntityNamesProvider&entityId=" + entitiyId + "&entityName=" + entityName ,
            //indicator: 'throbbing',
            onSuccess : success,
            onFailure : failure
        });
}

function getDistrictsForRegion( regionId, selectedValue, success, failure){
	if( !failure ){	failure = defaultFailure;}
	
	new Ajax.Request('ajaxQueryXML',
			{
				method : 'get', 
				parameters : "provider=HealthDistrictForRegionProvider&regionId=" + regionId +"&selectedValue=" + selectedValue,
			    //indicator: 'throbbing',
				onSuccess : success,
				onFailure : failure
			});

}

function getCodeForOrganization( organizationId, success, failure){
	if( !failure ){	failure = defaultFailure;}
	
	new Ajax.Request('ajaxQueryXML',
			{
				method : 'get', 
				parameters : "provider=CodeForOrganizationProvider&organizationId=" + organizationId,
			    //indicator: 'throbbing',
				onSuccess : success,
				onFailure : failure
			});

}


function getTestsForSampleType(sampleTypeId, success, failure) {
	var request = "&sampleType=" + sampleTypeId;
	if( !failure ){	failure = defaultFailure;}
	
	new Ajax.Request('ajaxQueryXML', // url
	{// options
		method : 'get', // http method
		parameters : "provider=SampleEntryTestsForTypeProvider" + request,
		// indicator: 'throbbing'
		onSuccess : success,
		onFailure : failure
	});
}

function testConnectionOnServer(connectionId, url, success, failure) {
	var request = "&connectionId=" + connectionId + "&url=" + url;
	
	if( !failure ){	failure = defaultFailure;}
	
	new Ajax.Request('ajaxQueryXML', // url
	{// options
		method : 'get', // http method
		parameters : "provider=ConnectionTestProvider" + request,
		// indicator: 'throbbing'
		onSuccess : success,
		onFailure : failure
	});
}

function validateAccessionNumberOnServer(ignoreYear, ignoreUsage, fieldId, accessionNumber, success, failure) {
    if( !failure ){ failure = defaultFailure;}
    new Ajax.Request(
            'ajaxXML', // url
            {// options
                method : 'get', // http method
                parameters : 'provider=SampleEntryAccessionNumberValidationProvider&ignoreYear=' + ignoreYear + '&ignoreUsage=' + ignoreUsage + '&field='   + fieldId + '&accessionNumber=' + accessionNumber,
                indicator : 'throbbing',
                onSuccess : success,
                onFailure : failure
            });
}

function validateProjectAccessionNumberOnServer(fieldId, recordType, parseForProjectFormName, accessionNumber, success, failure) {
    if( !failure ){ failure = defaultFailure;}
    new Ajax.Request(
            'ajaxXML', // url
            {// options
                method : 'get', // http method
                parameters : 'provider=SampleEntryAccessionNumberValidationProvider&field=' + fieldId + '&recordType=' + recordType + '&parseForProjectFormName=' + parseForProjectFormName + '&accessionNumber=' + accessionNumber,
                indicator : 'throbbing',
                onSuccess : success,
                onFailure : failure
            });
}

function generateNextScanNumber(success, failure){
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request (
        'ajaxQueryXML',  //url
        {//options
            method: 'get', //http method
            parameters: "provider=SampleEntryGenerateScanProvider",
            //indicator: 'throbbing'
            onSuccess:  success,
            onFailure:  failure
        }
    );
}

function generateNextProgramScanNumber(prefix, success, failure){
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request (
        'ajaxQueryXML',  //url
        {//options
            method: 'get', //http method
            parameters: "provider=SampleEntryGenerateScanProvider&programCode=" + prefix,
            //indicator: 'throbbing'
            onSuccess:  success,
            onFailure:  failure
        }
    );
}

function validateNonConformityRecordNumberOnServer( field, success, failure){
	if( !failure){failure = defaultFailure;	}

	new Ajax.Request('ajaxXML',
			{
				method : 'get', 
				parameters : "provider=NonConformityRecordNumberValidationProvider&fieldId=" + field.id +"&value=" + field.value,
			    //indicator: 'throbbing',
				onSuccess : success,
				onFailure : failure
			});

}

function validatePhoneNumberOnServer( field, success, failure){
    if( !failure){failure = defaultFailure;	}

    new Ajax.Request('ajaxXML',
        {
            method : 'get',
            parameters : "provider=PhoneNumberValidationProvider&fieldId=" + field.id +"&value=" + field.value,
            //indicator: 'throbbing',
            onSuccess : success,
            onFailure : failure
        });

}

function validateSubjectNumberOnServer( subjectNumber, type, elementId, success, failure){
    if( !failure ){	failure = defaultFailure;}

    new Ajax.Request('ajaxXML',
        {
            method : 'get',
            parameters : "provider=SubjectNumberValidationProvider&subjectNumber=" + subjectNumber + "&numberType=" + type + "&fieldId=" + elementId,
            //indicator: 'throbbing',
            onSuccess : success,
            onFailure : failure
        });

}
function patientSearch(lastName, firstName, STNumber, subjectNumber, nationalId, labNumber, guid, suppressExternalSearch, success, failure){
	if( !failure){failure = defaultFailure;	}
	new Ajax.Request (
            'ajaxQueryXML',  //url
             {//options
               method: 'get', //http method
               parameters: "provider=PatientSearchProvider&lastName=" + lastName +
               			  "&firstName=" + firstName +
               			  "&STNumber=" + STNumber +
               			  "&subjectNumber=" + subjectNumber +
               			  "&nationalID=" + nationalId +
               			  "&labNumber=" + labNumber +
               			  "&guid=" + guid +
               			  "&suppressExternalSearch=" + suppressExternalSearch,
               onSuccess:  success,
               onFailure:  failure
              }
           );	
}

function getReflexUserChoice( resultId, analysisId, testId, accessionNumber, index, success, failure){
    if( !failure){failure = defaultFailure;	}
	
	new Ajax.Request (
            'ajaxQueryXML',  //url
            {//options
            method: 'get', //http method
            parameters: 'provider=TestReflexUserChoiceProvider&resultIds=' + resultId + 
            			'&analysisIds=' +  analysisId + 
            			'&testIds=' + testId + 
            			'&rowIndex=' + index +
            			'&accessionNumber=' + accessionNumber,
            indicator: 'throbbing',
            onSuccess:  success,
            onFailure:  failure
                 }
                );
	
}

function getProvidersForOrg( orgKey, success, failure){
    if( !failure){failure = defaultFailure;	}
    new Ajax.Request(
        'ajaxQueryXML',  //url
        {//options
            method: 'get', //http method
            parameters: "provider=RequestersForOrganizationProvider&orgId=" + orgKey,
            //indicator: 'throbbing'
            onSuccess: success,
            onFailure: failure
        }
    );
}

function getAllTestsForSampleType( sampleTypeId, success, failure){
    if( !failure){failure = defaultFailure;	}
    new Ajax.Request(
        'ajaxQueryXML',  //url
        {//options
            method: 'get', //http method
            parameters: "provider=AllTestsForSampleTypeProvider&sampleTypeId=" + sampleTypeId,
            //indicator: 'throbbing'
            onSuccess: success,
            onFailure: failure
        }
    );
}

function getPendingAnalysisForTest( testId, success, failure){
    if( !failure){failure = defaultFailure;	}
    new Ajax.Request(
        'ajaxQueryXML',  //url
        {//options
            method: 'get', //http method
            parameters: "provider=getPendingAnalysisForTestProvider&testId=" + testId,
            //indicator: 'throbbing'
            onSuccess: success,
            onFailure: failure
        }
    );
}

function postBatchSample(success, failure){
    if( !failure){failure = defaultFailure;	}
	new Ajax.Request(
		'SamplePatientEntrySave.do',  //url
		{//options
			method: 'POST', //http method
			parameters: jQuery(window.document.forms[0]).serialize().replace(/\+/g,'%20'),
		    onSuccess: success,
		    onFailure: failure
		}
	);
	
}

function postBatchSampleByProject(projectUrl, success, failure) {
    if( !failure){failure = defaultFailure;	}
    new Ajax.Request(
    		projectUrl,  //url
    		{//options
    			method: 'POST', //http method
    			parameters: jQuery(window.document.forms[0]).serialize().replace(/\+/g,'%20'),
    		    onSuccess: success,
    		    onFailure: failure
    		}
    	);
}


