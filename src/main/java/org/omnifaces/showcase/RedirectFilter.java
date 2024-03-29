/*
 * Copyright 2020 OmniFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.showcase;

import static org.omnifaces.util.Servlets.getRequestURLWithQueryString;
import static org.omnifaces.util.Servlets.redirectPermanent;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.omnifaces.filter.HttpFilter;

/**
 * Make sure that HTTP is 301-redirected to HTTPS.
 * The default security constraint does only a 302.
 */
@WebFilter("/*")
public class RedirectFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws ServletException, IOException {
		if (!request.isSecure())
		{
			redirectPermanent(response, getRequestURLWithQueryString(request).replaceFirst("http:", "https:").replaceFirst(":8080", ":8443"));
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

}
