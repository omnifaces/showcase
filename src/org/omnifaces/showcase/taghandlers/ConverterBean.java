package org.omnifaces.showcase.taghandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ConverterBean {

	private List<Locale> locales;

	@PostConstruct
	public void init() {
		locales = new ArrayList<Locale>();

		for (String language : new String[] { "en", "es", "fr", "de", "nl", "ar", "he", "zh" }) {
			locales.add(new Locale(language));
		}
	}

	public List<Locale> getLocales() {
		return locales;
	}

}