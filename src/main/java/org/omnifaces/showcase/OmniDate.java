package org.omnifaces.showcase;

import java.util.Date;

/**
 * Fix for GlassFish 4.0 not able to load java.util.Data as JSF native managed bean.
 * See https://java.net/jira/browse/GLASSFISH-20775
 * @author Arjan Tijms
 *
 */
public class OmniDate extends Date {
	private static final long serialVersionUID = -9196263323752494128L;
}
