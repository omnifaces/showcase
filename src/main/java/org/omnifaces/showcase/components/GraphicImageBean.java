package org.omnifaces.showcase.components;

import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Faces;

@ManagedBean
@ApplicationScoped
public class GraphicImageBean {

	public InputStream getLogo() {
		// Note: this is a dummy example. In real, you should be able to take e.g. Long argument and then return
		// the desired byte[] content from some service class.
		return Faces.getResourceAsStream("/resources/layout/img/logo-70x70.png");
	}

}