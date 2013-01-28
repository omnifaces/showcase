// Syntax highlighting.
prettyPrint();

// JSF ajax progress indicator.
jsf.ajax.addOnEvent(function(data) {
	$("html").toggleClass("progress", data.status == "begin");
});

// PrimeFaces ajax progress indicator.
$(document).ajaxStart(function() {
	$("html").addClass("progress");
}).ajaxStop(function() {
	$("html").removeClass("progress");
});