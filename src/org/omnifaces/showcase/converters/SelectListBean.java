package org.omnifaces.showcase.converters;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEntity;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class SelectListBean implements Serializable {

	private static final long serialVersionUID = -2808326493872513302L;
	
	private List<ExampleEntity> source;
	private DualListModel<ExampleEntity> all;
	
	@PostConstruct
	public void init() {
		source = asList(
			new ExampleEntity(1l, "Amsterdam"),
			new ExampleEntity(2l, "Frankfurt"),
			new ExampleEntity(3l, "Berlin")
		);
		
		all = new DualListModel<ExampleEntity>(
			source, 
			new ArrayList<ExampleEntity>()
		);
	}

	public List<ExampleEntity> getSource() {
		return source;
	}
	
	public DualListModel<ExampleEntity> getAll() {
		return all;
	}

	public void setAll(DualListModel<ExampleEntity> all) {
		this.all = all;
	}
	
	public List<ExampleEntity> getSelected() {
		return all.getTarget();
	}
	
}
