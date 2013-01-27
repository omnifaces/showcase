package org.omnifaces.showcase.functions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class FunctionsBean {
	
	int dayAsInteger = 1;
	int yearAsInteger = 2003;
	int monthNumberAsInteger = 1;
	int dayOfWeekNumberAsInteger = 1;

	public int getDayAsInteger() {
		return dayAsInteger;
	}

	public void setDayAsInteger(int dayAsInteger) {
		this.dayAsInteger = dayAsInteger;
	}

	public int getYearAsInteger() {
		return yearAsInteger;
	}

	public void setYearAsInteger(int yearAsInteger) {
		this.yearAsInteger = yearAsInteger;
	}

	public int getMonthNumberAsInteger() {
		return monthNumberAsInteger;
	}

	public void setMonthNumberAsInteger(int monthNumberAsInteger) {
		this.monthNumberAsInteger = monthNumberAsInteger;
	}

	public int getDayOfWeekNumberAsInteger() {
		return dayOfWeekNumberAsInteger;
	}

	public void setDayOfWeekNumberAsInteger(int dayOfWeekNumberAsInteger) {
		this.dayOfWeekNumberAsInteger = dayOfWeekNumberAsInteger;
	}

}
