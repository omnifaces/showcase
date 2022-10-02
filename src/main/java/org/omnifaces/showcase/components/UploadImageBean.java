package org.omnifaces.showcase.components;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import org.omnifaces.util.Utils;

@Named
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