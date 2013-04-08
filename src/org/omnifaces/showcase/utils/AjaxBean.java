package org.omnifaces.showcase.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIData;

import org.omnifaces.showcase.model.ExampleEntity;
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
		data.put("beans", Arrays.asList(new ExampleEntity(1L, "one"), new ExampleEntity(2L, "two")));
		Ajax.data(data);
		Ajax.oncomplete("showData()");
	}

	public void updateRow(UIData table, long index) { // Due to JBoss 7.0 bug, we can't use int argument. This is fixed in 7.1.
		Ajax.updateRow(table, (int) index);
	}

	public void updateColumn(UIData table, long index) { // Due to JBoss 7.0 bug, we can't use int argument. This is fixed in 7.1.
		Ajax.updateColumn(table, (int) index);
	}

}