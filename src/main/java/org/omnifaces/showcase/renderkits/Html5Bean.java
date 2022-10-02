package org.omnifaces.showcase.renderkits;

import java.util.Date;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Html5Bean {

	private String text1;
	private String text2;
	private String text3;
	private String secret;
	private String search;
	private String email;
	private String url;
	private String phone;
	private Integer range;
	private Integer number;
	private Date date;
	private String text4;
	private String text5;
	private String text6;

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText4() {
		return text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public String getText5() {
		return text5;
	}

	public void setText5(String text5) {
		this.text5 = text5;
	}

	public String getText6() {
		return text6;
	}

	public void setText6(String text6) {
		this.text6 = text6;
	}

}