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

import static org.omnifaces.util.Servlets.getRequestURI;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;

public final class PageView {

	private final String timestamp;
	private final String uri;
	private final String userHash;
	private final String userAgent;
	private final String referrer;

	public PageView(HttpServletRequest request) {
		timestamp = LocalDateTime.now().toString();
		uri = getRequestURI(request).split(";")[0];
		String sessionId = request.getSession().getId();
		userHash = Integer.toHexString(sessionId.substring(0, sessionId.length() / 2).hashCode());
		userAgent = request.getHeader("user-agent");
		referrer = request.getHeader("referer").split(";")[0];
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getUri() {
		return uri;
	}

	public String getUserHash() {
		return userHash;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getReferrer() {
		return referrer;
	}

}