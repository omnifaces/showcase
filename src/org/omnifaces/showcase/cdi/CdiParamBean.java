package org.omnifaces.showcase.cdi;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LengthValidator;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.param.Attribute;
import org.omnifaces.cdi.param.ParamValue;

@Named
@RequestScoped
public class CdiParamBean {

	// Like <f:viewParam name="text1" value="#{bean.text1}" required="true">
	@Inject @Param(required = true)
	private ParamValue<String> text1;

	// Like <f:viewParam name="text2" value="#{bean.text2}" validatorMessage="..."><f:validateLength minimum="3">
	@Inject @Param(validatorClasses = LengthValidator.class,
		validatorAttributes = @Attribute(name="minimum", value="3"),
		validatorMessage = "{1}: Value is too too small! Please enter a minimum of 3 characters.")
	private ParamValue<String> text2;

	// Like <f:viewParam name="text1" value="#{bean.text1}"> using JSR303 bean validation.
	@Inject @Param @NotNull
	private ParamValue<String> text3;

	// Like <f:viewParam name="number" value="#{bean.number}"> using implicit JSF integer converter.
	@Inject @Param
	private ParamValue<Integer> number;

	// Like <f:viewParam name="date" value="#{bean.date}" converterMessage="..."><f:convertDateTime pattern="yyyyMMdd">
	@Inject @Param(
		converterClass = DateTimeConverter.class,
		converterAttributes = @Attribute(name="pattern", value="yyyyMMdd"),
		converterMessage="{1}: \"{0}\" is not the date format we had in mind! Please use yyyyMMdd format.")
	private ParamValue<Date> date;

	private String result;

	@PostConstruct
	public void init() {
		if (isValidationFailed()) {
			result = "Validation has failed!";
			return;
		}

		String text1 = this.text1.getValue();
		String text2 = this.text2.getValue();
		String text3 = this.text3.getValue();
		Integer number = this.number.getValue();
		Date date = this.date.getValue();

		result = String.format(
			"You entered text1 '%s', text2 '%s', text3 '%s', number '%d' and date '%5$tY%5$tm%5$td'.",
			text1, text2, text3, number, date);
	}

	public String getResult() {
		return result;
	}

}
