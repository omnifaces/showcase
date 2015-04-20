package org.omnifaces.showcase.functions;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

@ManagedBean
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