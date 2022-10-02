package org.omnifaces.showcase.cdi;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.FacesException;
import jakarta.faces.convert.DateTimeConverter;
import jakarta.faces.validator.LengthValidator;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

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
	private String text1;

	// Like <f:viewParam name="text2" value="#{bean.text2}" validatorMessage="..."><f:validateLength minimum="3">
	@Inject @Param(
		validatorClasses = LengthValidator.class,
		validatorAttributes = @Attribute(name="minimum", value="3"),
		validatorMessage = "{1}: Value is too too small! Please enter a minimum of 3 characters.")
	private String text2;

	// Multi-valued parameters are not possible with <f:viewParam>; using JSR303 bean validation via the @NotNull constraint.
	@Inject @Param @NotNull(message="{0} is required")
	private List<String> text3;

	// Like <f:viewParam name="number" value="#{bean.number}"> using implicit JSF integer converter.
	@Inject @Param
	private Integer number;

	// Like <f:viewParam name="date" value="#{bean.date}" converterMessage="..."><f:convertDateTime pattern="yyyyMMdd">
	@Inject @Param(
		converterClass = DateTimeConverter.class,
		converterAttributes = { @Attribute(name="pattern", value="yyyyMMdd") },
		converterMessage="{1}: \"{0}\" is not the date format we had in mind! Please use the format yyyyMMdd.")
	private Date date;

	// Like <f:viewParam name="nsEntity" value="#{bean.nsEntity}" converter="nonSerializableEntityConverter">
	@Inject @Param(converter = "nonSerializableEntityConverter")
	private ParamValue<NonSerializableEntity> nsEntity;

	private String result;

	@PostConstruct
	public void init() {
		if (isValidationFailed()) {
			result = "Validation has failed!";
			return;
		}

		// Simulate serialization.
		ParamValue<NonSerializableEntity> nsEntityCopy = copy(nsEntity);

		result = String.format("You entered text1 '%s', text2 '%s', text3 '%s', number '%d', date '%5$tY%5$tm%5$td',"
			+ " entity '%6$s' and entity copy '%7$s'", text1, text2, text3, number, date, nsEntity.getValue(),
				nsEntityCopy.getValue());

		Messages.addGlobalInfo("Yes, no validation errors!");
	}

	public String getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T copy(T source) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		T copy = null;

		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(source);
		}
		catch (IOException e) {
			throw new FacesException(e);
		}

		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
			copy = (T) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			throw new FacesException(e);
		}

		return copy;
	}

}