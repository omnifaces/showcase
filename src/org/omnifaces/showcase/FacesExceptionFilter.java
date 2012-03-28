package org.omnifaces.showcase;

import java.io.IOException;

import javax.faces.FacesException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * JSF wraps all business exceptions in a {@link FacesException}. This filter will extract its cause until it's not a
 * {@link FacesException} anymore and then rethrow it in a {@link ServletException} so that the standard Servlet API
 * error page handling can do its job properly on business exceptions thrown during JSF requests.
 *
 * @author Bauke Scholtz
 */
@WebFilter(servletNames = "facesServlet")
public class FacesExceptionFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// NOOP.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		}
		catch (ServletException e) {
			Throwable cause = e.getCause();

			while (cause instanceof FacesException && cause.getCause() != null) {
				 cause = cause.getCause();
			}

			throw new ServletException(cause);
		}
	}

	@Override
	public void destroy() {
		// NOOP.
	}

}