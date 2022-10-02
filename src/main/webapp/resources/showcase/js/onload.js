// Syntax highlighting.
prettyPrint();

// JSF ajax progress indicator.
faces.ajax.addOnEvent(function(data) {
	$("html").toggleClass("progress", data.status == "begin");
});

// jQuery and PrimeFaces ajax progress indicator.
$(document).on("ajaxStart pfAjaxSend", function() {
	$("html").addClass("progress");
}).on("ajaxStop pfAjaxComplete", function() {
	$("html").removeClass("progress");
});