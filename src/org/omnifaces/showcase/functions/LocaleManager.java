package org.omnifaces.showcase.functions;

import static org.omnifaces.util.Faces.getDefaultLocale;
import static org.omnifaces.util.Faces.getViewRoot;
import static org.omnifaces.util.Utils.coalesce;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LocaleManager {

    private Locale locale =	coalesce(getLocale(), getDefaultLocale());

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        getViewRoot().setLocale(locale);
    }

}