package org.omnifaces.showcase.converters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.ExampleEntity;

@Named
@ViewScoped
public class AutoCompleteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExampleEntity selectedEntity;
	private List<ExampleEntity> availableEntities;

	public List<ExampleEntity> autoComplete(String query) {
		availableEntities = new ArrayList<>();

		for (long i = 0; i < 5; i++) {
			availableEntities.add(new ExampleEntity(i, query + i));
		}

		return availableEntities;
	}

	public ExampleEntity getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(ExampleEntity selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public List<ExampleEntity> getAvailableEntities() {
		return availableEntities;
	}

}