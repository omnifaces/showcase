var functions = {
	scrollTo: function(idOrElement) {
		$element = (typeof idOrElement === "string") ? $("#" + idOrElement) : $(idOrElement);
		if ($element.length) {
			$([document.documentElement, document.body]).animate({ scrollTop: $element.offset().top }, 500);
		}
	}
}
