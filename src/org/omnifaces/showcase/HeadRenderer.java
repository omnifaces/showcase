/*
 * Copyright 2013 OmniFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.showcase;

import java.io.IOException;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

/**
 * A custom renderer so that PrimeFaces theme file is included as head resource instead of being hardcoded. This way
 * the CombinedResourceHandler will be able to pickup it.
 *
 * @author Bauke Scholtz
 */
@ResourceDependency(library="primefaces-aristo", name="theme.css")
public class HeadRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		context.getResponseWriter().startElement("head", component);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// NOOP.
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		for (UIComponent resource : context.getViewRoot().getComponentResources(context, "head")) {
			resource.encodeAll(context);
		}

		context.getResponseWriter().endElement("head");
	}

}