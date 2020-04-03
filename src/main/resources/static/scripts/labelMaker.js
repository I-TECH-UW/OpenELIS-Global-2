//attempt the same page load with override
function override() {
	var url = new URL(window.location.href);
	url.searchParams.set('override', 'true');
	window.location.href = url.toString();
}