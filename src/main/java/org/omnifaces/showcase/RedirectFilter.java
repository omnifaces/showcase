package org.omnifaces.showcase;

import static org.omnifaces.util.Servlets.getRequestURLWithQueryString;
import static org.omnifaces.util.Servlets.redirectPermanent;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
