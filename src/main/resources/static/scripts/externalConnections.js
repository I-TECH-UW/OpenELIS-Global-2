function testConnection() {

	jQuery("#connect-wait").show();
	jQuery("#connect-success").hide();
	jQuery("#connect-partial").hide();
	jQuery("#connect-fail").hide();
		
	new Ajax.Request('TestExternalConnection',
			{
				method : 'post', 
    			parameters: jQuery(document.getElementById("mainForm")).serialize().replace(/\+/g,'%20'),
			    //indicator: 'throbbing',
				requestHeaders : {
					"X-CSRF-Token" : getCsrfToken()
				},
				onSuccess : testConnectionSuccess,
				onFailure : testConnectionFailure
			});
	
}

function testConnectionById(id) {

	jQuery("#connect-wait").show();
	jQuery("#connect-success").hide();
	jQuery("#connect-partial").hide();
	jQuery("#connect-fail").hide();
		
	new Ajax.Request('TestExternalConnection/' + encodeURIComponent(id),
			{
				method : 'get', 
			    //indicator: 'throbbing',
				onSuccess : testConnectionSuccess,
				onFailure : testConnectionFailure
			});
	
}

function testConnectionSuccess(xhr) {
	jQuery("#connect-wait").hide();
	console.log(xhr.responseText);
	var jsonResponse = JSON.parse(xhr.responseText);
	if (jsonResponse.GET == 200  || jsonResponse.GET == 202) {
		showConnectionSuccess();
	} else {
		if (jsonResponse.OPTIONS != -1 || jsonResponse.GET != -1 ) {
			showConnectionReachable();
		} else {
			showConnectionUnreachable();
		}
		}
}

function showConnectionSuccess() {
	jQuery("#connect-success").show();
}

function showConnectionReachable() {
	jQuery("#connect-partial").show();
}

function showConnectionUnreachable() {
	jQuery("#connect-fail").show();
}

function testConnectionFailure() {
	showConnectionUnreachable();
	console.log("an exception occurred trying to reach the external server");
}