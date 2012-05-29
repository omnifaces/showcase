prettyPrint();

jsf.ajax.addOnEvent(function(data) {
	data.source.style.cursor = document.body.style.cursor = (data.status == "begin") ? "progress" : "auto";
});