package org.omnifaces.showcase.functions;

import java.io.Serializable;
import java.util.Locale;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import org.omnifaces.util.Faces;

@Named
@SessionScoped
public class LocaleManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale = Faces.getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        Faces.setLocale(locale);
    }

}