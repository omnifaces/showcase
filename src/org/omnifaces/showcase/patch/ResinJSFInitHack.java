package org.omnifaces.showcase.patch;

import static javax.servlet.SessionTrackingMode.URL;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

public class ResinJSFInitHack implements ServletContainerInitializer {
	
	private static final Logger logger = Logger.getLogger(ResinJSFInitHack.class.getName());

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext servletContext) throws ServletException {
		
		String info = servletContext.getServerInfo();
		if (info != null && info.startsWith("Resin")) {
			try {
				servletContext.addListener("com.sun.faces.config.ConfigureListener");
			} catch (Exception e) {
				logger.log(Level.WARNING, 
					"Server seems to be Resin, but could not install 'com.sun.faces.config.ConfigureListener' " +
					"Showcase app may not run.", e
				);
			}
		} else {
			
			// Despite being Java EE 6 certified, Resin does not implement ServletContext#setSessionTrackingModes
			
			try {
				Set<SessionTrackingMode> trackingModes = new HashSet<SessionTrackingMode>();
				trackingModes.add(URL);
				servletContext.setSessionTrackingModes(trackingModes);
				
				servletContext.getSessionCookieConfig().setHttpOnly(true);
			} catch (Exception e) {
				logger.log(Level.WARNING, "Could not set session config", e);
			}
		}
		
	}

}
