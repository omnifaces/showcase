package org.omnifaces.showcase.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.util.Ajax;

@ManagedBean
@RequestScoped
public class AjaxBean {

	public void update() {
		Ajax.update("form:timestamp");
	}

	public void callback() {
		Ajax.oncomplete("alert('Hi, I am the oncomplete callback script!')");
	}

	public void argument() {
		Ajax.data("foo", "bar");
		Ajax.data("first", "one", "second", "two");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bool", true);
		data.put("number", 1.2F);
		data.put("date", new Date());
		data.put("array", new Integer[] { 1, 2, 3, 4, 5 });
		data.put("list", Arrays.asList("one", "two", "three"));
		Ajax.data(data);
		Ajax.oncomplete("showData()");
	}

}