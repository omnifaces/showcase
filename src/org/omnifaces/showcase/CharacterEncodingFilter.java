/*
 * Copyright 2012 OmniFaces.
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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.filter.HttpFilter;

/**
 * PrimeFaces 3.x may call ExternalContext#getRequestParameterMap() in PartialViewContext#isAjaxRequest() before
 * JSF/Facelets gets the chance to set the request character encoding to UTF-8 during building/restoring the view.
 * This will cause the request character encoding to use the servletcontainer's default encoding which is more than
 * often ISO-8859-1 which will mess up everything. This filter fixes that by explicitly setting it to UTF-8 beforehand.
 *
 * @author Bauke Scholtz
 * @link http://code.google.com/p/primefaces/issues/detail?id=2223
 * @link http://stackoverflow.com/a/9839362/157882
 */
@WebFilter("/*")
public class CharacterEncodingFilter extends HttpFilter {

	@Override
	public void doFilter
		(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

}