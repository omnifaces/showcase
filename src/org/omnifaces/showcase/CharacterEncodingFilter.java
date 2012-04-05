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
@WebFilter(servletNames="facesServlet")
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