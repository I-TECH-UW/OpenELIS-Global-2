function testConnection() {

	jQuery("#connect-wait").show();
	jQuery("#connect-success").hide();
	jQuery("#connect-partial").hide();
	jQuery("#connect-fail").hide();
		
	new Ajax.Request('TestExternalConnection',
			{
				method : 'post', 
    			parameters: jQuery(document.getElementById("mainForm")).serialize().replace(/\+/g,'%20'),
				requestHeaders : {
					"X-CSRF-Token" : getCsrfToken()
				},
				onSuccess : function (xhr) { testConnectionSuccess(xhr)},
				onFailure : function () { testConnectionFailure()}
			}); 
	
}

function testConnectionById(id) {

	jQuery("#connect-wait-" + id).show();
	jQuery("#connect-success-" + id).hide();
	jQuery("#connect-partial-" + id).hide();
	jQuery("#connect-fail-" + id).hide();
		
	new Ajax.Request('TestExternalConnection',
			{
				method : 'get', 
				parameters: encodeURIComponent(id),
				onSuccess : function (xhr) { testConnectionSuccess(xhr, id)},
				onFailure : function () { testConnectionFailure(id)}
			});
	
}

function testConnectionSuccess(xhr, id) {
	if (id) {
		jQuery("#connect-wait-" + id).hide();
	} else {
		jQuery("#connect-wait").hide();
	}
	console.log(xhr.responseText);
	var jsonResponse = JSON.parse(xhr.responseText);
	if (jsonResponse.GET == 200  || jsonResponse.GET == 202) {
		showConnectionSuccess(id);
	} else {
		if (jsonResponse.OPTIONS != -1 || jsonResponse.GET != -1 ) {
			showConnectionReachable(id);
		} else {
			showConnectionUnreachable(id);
		}
	}
}



function showConnectionSuccess(id) {
	if (id) {
		jQuery("#connect-success-" + id).show();
	} else {
		jQuery("#connect-success").show();
	}
}

function showConnectionReachable(id) {
	if (id) {
		jQuery("#connect-partial-" + id).show();
	} else {
		jQuery("#connect-partial").show();
	}
}

function showConnectionUnreachable(id) {
	if (id) {
		jQuery("#connect-fail-" + id).show();
	} else {
		jQuery("#connect-fail").show();
	}
}

function testConnectionFailure(id) {
	if (id) {
		jQuery("#connect-wait-" + id).hide();
	} else {
		jQuery("#connect-wait").hide();
	}
	showConnectionUnreachable(id);
	console.log("an exception occurred trying to reach the external server");
}