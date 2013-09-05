package org.omnifaces.showcase.cdi;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import org.omnifaces.showcase.model.NonSerializableEntity;
import org.omnifaces.util.Messages;

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

	// Like <f:viewParam name="text1" value="#{bean.text1}"> using JSR303 bean validation via the @NotNull constraint
	@Inject @Param @NotNull
	private ParamValue<String> text3;

	// Like <f:viewParam name="number" value="#{bean.number}"> using implicit JSF integer converter.
	@Inject @Param
	private ParamValue<Integer> number;

	// Like <f:viewParam name="date" value="#{bean.date}" converterMessage="..."><f:convertDateTime pattern="yyyyMMdd">
	@Inject @Param(
		converterClass = DateTimeConverter.class,
		converterAttributes = @Attribute(name="pattern", value="yyyyMMdd"),
		converterMessage="{1}: \"{0}\" is not the date format we had in mind! Please use the format yyyyMMdd.")
	private ParamValue<Date> date;

	// Like <f:viewParam name="date" value="#{bean.nsEntity}"><f:converter converterId="nonSerializableEntityConverter"/>
	@Inject @Param(converter = "nonSerializableEntityConverter")
	private ParamValue<NonSerializableEntity> nsEntity;

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

		NonSerializableEntity nonSerializableValue = nsEntity.getValue();

		// Copy the ParamValue to simulate a passivation/activation cycle
		ParamValue<NonSerializableEntity> nsEntityCopy = copy(nsEntity);

		// Getting the value from the copied ParamValue will cause the converter to run
		// again on the stored raw value.
		NonSerializableEntity nonSerializableValueCopy = nsEntityCopy.getValue();

		result = String.format(
			"You entered text1 '%s', text2 '%s', text3 '%s', number '%d', date '%5$tY%5$tm%5$td'",
			text1, text2, text3, number, date);

		result += String.format(", entity '%s' and entity copy '%s'", nonSerializableValue.getValue(), nonSerializableValueCopy.getValue());

		Messages.addGlobalInfo("Yes, no validation errors!");
	}

	public String getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T copy(T source) {
		T target = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
			objectOutputStream.writeObject(source);
			objectOutputStream.flush();
			objectOutputStream.close();

			target = (T) new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray())).readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		return target;
	}

}
