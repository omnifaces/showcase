package org.omnifaces.showcase.functions;

import java.io.Serializable;

import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class FunctionsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int day = 1;
	private int year = 2003;
	private int month = 1;
	private int dayOfWeek = 1;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}