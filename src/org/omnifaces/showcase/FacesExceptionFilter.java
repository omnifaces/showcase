package org.omnifaces.showcase;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.FacesException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter will solve 2 problems with faces exceptions.
 * <ol>
 * <li>Mojarra's FacesFileNotFoundException needs to be interpreted as 404.
 * <li>Root cause of FacesException needs to be unwrapped to utilize standard Servlet API error page handling.
 * </ol>
 *
 * @author Bauke Scholtz
 */
@WebFilter(servletNames = { "facesServlet", "FacesServlet", "Faces Servlet" })
public class FacesExceptionFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// NOOP.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			chain.doFilter(request, response);
		}
		catch (FileNotFoundException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND, ((HttpServletRequest) request).getRequestURI());
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