package org.omnifaces.showcase.taghandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ConverterBean {

	private List<Locale> locales;

	@PostConstruct
	public void init() {
		locales = new ArrayList<>();

		for (String language : new String[] { "en", "es", "fr", "de", "nl", "ar", "he", "zh" }) {
			locales.add(new Locale(language));
		}
	}

	public List<Locale> getLocales() {
		return locales;
	}

}