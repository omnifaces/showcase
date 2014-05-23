package org.omnifaces.showcase.cdi;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.Startup;

@Named
@Startup
public class MyStartupBean {

	private Date startupDate;

	@PostConstruct
	public void init() {
		startupDate = new Date();
	}

	public Date getStartupDate() {
		return startupDate;
	}

}