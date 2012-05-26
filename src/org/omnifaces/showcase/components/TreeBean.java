package org.omnifaces.showcase.components;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.showcase.model.ExampleEntity;

@ManagedBean
@ViewScoped
public class TreeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeModel<ExampleEntity> tree;

	@PostConstruct
	public void init() {
		tree = new ListTreeModel<ExampleEntity>();
		tree.addChild(new ExampleEntity(1L, "One"))
				.addChild(new ExampleEntity(2L, "Two")).getParent()
				.addChild(new ExampleEntity(3L, "Three"))
					.addChild(new ExampleEntity(4L, "Four")).getParent()
					.addChild(new ExampleEntity(5L, "Five"))
						.addChild(new ExampleEntity(6L, "Six")).getParent()
						.getParent()
					.getParent()
				.addChild(new ExampleEntity(7L, "Seven")).getParent()
				.addChild(new ExampleEntity(8L, "Eight")).getParent()
				.getParent()
			.addChild(new ExampleEntity(9L, "Nine"))
				.addChild(new ExampleEntity(10L, "Ten"));
	}

	public TreeModel<ExampleEntity> getTree() {
		return tree;
	}

}
