package org.omnifaces.showcase.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;

@ManagedBean
@ApplicationScoped
public class GraphicImageBean {

	private static final Map<Long, String> IMAGES = Collections.unmodifiableMap(new TreeMap<Long, String>() { private static final long serialVersionUID = 1L; {
		put(1L, "black");
		put(2L, "blue");
		put(3L, "yellow");
		put(4L, "gray");
		put(5L, "red");
		put(6L, "green");
	}});

	public InputStream getLogo() {
		// Note: this is a dummy example. In reality, you should be able to take e.g. a Long argument as ID and then
		// return the desired byte[] content from some service class by given ID.
		return Faces.getResourceAsStream("/resources/layout/img/OmniFaces-logo-90x90-black.png");
	}

	public byte[] getContent(Long id) throws IOException {
		// Note: this is a dummy example. In reality, you should be able to return the desired byte[] content from some
		// service class by given ID.
		return Utils.toByteArray(Faces.getResourceAsStream("/resources/layout/img/OmniFaces-logo-90x90-" + IMAGES.get(id) + ".png"));
	}

	public Long[] getIds() {
		// Note: this is just a dummy example. In reality, you should be able to obtain them from another request/view
		// scoped bean as ID of an entity representing the image.
		return IMAGES.keySet().toArray(new Long[IMAGES.size()]);
	}

}