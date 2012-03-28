package org.omnifaces.showcase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;


public class Functions {

	public static String printViewSourceCode() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Utils.stream(Faces.getResourceAsStream(Faces.getViewId()), output);
		return new String(output.toByteArray(), "UTF-8");
	}

}
