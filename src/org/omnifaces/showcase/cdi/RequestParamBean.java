package org.omnifaces.showcase.cdi;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.RequiredValidator;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.requestparam.Attribute;
import org.omnifaces.cdi.requestparam.Param;
import org.omnifaces.cdi.requestparam.ParamValue;

@Named
@RequestScoped
public class RequestParamBean {

	// Custom validator with local validator message based on EL
	@Inject @Param(validatorClasses = RequiredValidator.class, validatorMessage = "#{validatorMessages.requiredMsg}")
	private ParamValue<String> text;
	
	// No explicit converters or validators, only a required attribute depending on the default message
	@Inject	@Param(required = true)
	private ParamValue<String> moretext;

	// A very bare injection, but depending implicitly on the JSF default String to Integer converter
	@Inject @Param
	private ParamValue<Integer> number;

	// A custom validator with a local attribute and message
	@Inject @Param(converterClass = DateTimeConverter.class, converterAttributes = @Attribute(name="pattern", value="yyyyMMdd"), 
				   converterMessage="{1}: \"{0}\" is not the date format we had in mind! Please use yyyyMMdd.")
	private ParamValue<Date> date;
	
	@Inject @Param @NotNull
	private ParamValue<String> foo;

	private String result;

	@PostConstruct
	public void init() {
		if (isValidationFailed()) {
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
