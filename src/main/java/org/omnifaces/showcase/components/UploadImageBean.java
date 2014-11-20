package org.omnifaces.showcase.components;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.omnifaces.util.Utils;

@ManagedBean
@RequestScoped // Can be @ViewScoped, but caution should be taken with byte[] property. You don't want to save it in session.
public class UploadImageBean {

	private Part file;
	private byte[] content;

	public void read() throws IOException {
		content = Utils.toByteArray(file.getInputStream());
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public byte[] getContent() {
		return content;
	}

}