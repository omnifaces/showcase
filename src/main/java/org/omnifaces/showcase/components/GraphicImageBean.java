package org.omnifaces.showcase.components;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;

@ManagedBean
@ApplicationScoped
public class GraphicImageBean {

	public InputStream getLogo() {
		// Note: this is a dummy example. In reality, you should be able to take e.g. a Long argument as ID and then
		// return the desired byte[] content from some service class by given ID.
		return Faces.getResourceAsStream("/resources/layout/img/logo-70x70.png");
	}

	public byte[] getContent(Long id) throws IOException {
		// Note: this is a dummy example. In reality, you should be able to return the desired byte[] content from some
		// service class by given ID.
		return Utils.toByteArray(Faces.getResourceAsStream("/resources/layout/img/logo-70x70-" + id + ".png"));
	}

	public Long[] getIds() {
		// Note: this is just a dummy example. In reality, you should be able to obtain them from another request/view
		// scoped bean as ID of an entity representing the image.
		return new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L };
	}

}