package org.omnifaces.showcase.cdi;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.RequiredValidator;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.requestparam.Attribute;
import org.omnifaces.cdi.requestparam.Param;
import org.omnifaces.cdi.requestparam.ParamValue;
import org.omnifaces.util.Faces;

@Named
@RequestScoped
public class RequestParamBean {

	@Inject @Param(validatorClasses = { RequiredValidator.class })
	private ParamValue<String> text;

	@Inject @Param
	private ParamValue<Integer> number;

	@Inject @Param(converterClass = DateTimeConverter.class, converterAttributes = { @Attribute(name="pattern", value="yyyyMMdd") })
	private ParamValue<Date> date;

	private String result;

	@PostConstruct
	public void init() {
		if (Faces.isValidationFailed()) {
			result = "Validation has failed!";
			return;
		}

		String text = this.text.getValue();
		Integer number = this.number.getValue();
		Date date = this.date.getValue();

		result = String.format(
			"You entered text '%s', number '%d' and date '%3$tY%3$tm%3$td'.",
			text, number, date);
	}

	public String getResult() {
		return result;
	}

}
