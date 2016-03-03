package org.omnifaces.showcase;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Servlets;

public class PageView implements Comparable<PageView> {

	private final LocalDateTime timestamp;
	private final String uri;
	private final String userHash;
	private final String userAgent;
	private final String referrer;

	public PageView(HttpServletRequest request) {
		timestamp = LocalDateTime.now();
		uri = Servlets.getRequestURIWithQueryString(request);
		String sessionId = request.getSession().getId();
		userHash = Integer.toHexString(sessionId.substring(0, sessionId.length() / 2).hashCode());
		userAgent = request.getHeader("user-agent");
		referrer = request.getHeader("referer").split("[?#;]")[0];
	}

	public String getTimestamp() {
		return timestamp.toString();
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

	@Override
	public int compareTo(PageView other) {
		return other.timestamp.compareTo(timestamp);
	}

}