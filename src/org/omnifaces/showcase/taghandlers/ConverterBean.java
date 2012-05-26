package org.omnifaces.showcase.taghandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ConverterBean {

	private List<Item> items;

	@PostConstruct
	public void init() {
		items = new ArrayList<Item>();

		for (String language : new String[] { "en", "es", "fr", "de", "nl", "ar", "he", "zh" }) {
			Locale locale = new Locale(language);
			items.add(new Item(locale, ((SimpleDateFormat) DateFormat.getDateTimeInstance(
				DateFormat.FULL, DateFormat.FULL, locale)).toPattern()));
		}
	}

	public List<Item> getItems() {
		return items;
	}

	public static class Item {

		private Locale locale;
		private String pattern;

		public Item() {
			//
		}

		public Item(Locale locale, String pattern) {
			this.locale = locale;
			this.pattern = pattern;
		}

		public Locale getLocale() {
			return locale;
		}

		public String getPattern() {
			return pattern;
		}

	}

}