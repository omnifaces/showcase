prettyPrint();

jsf.ajax.addOnEvent(function(data) {
	$("html").toggleClass("progress", data.status == "begin");
});

$(document).ajaxStart(function() {
	$("html").addClass("progress");
}).ajaxStop(function() {
	$("html").removeClass("progress");
});