package org.omnifaces.showcase.components;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.Category;
import org.omnifaces.showcase.model.Product;

@Named
@ViewScoped
public class SelectItemGroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product selectedProduct;
	private List<Category> categories;
	private List<SelectItem> categorySelectItems;

	@PostConstruct
	public void init() {
		categories = loadExampleCategories();
		categorySelectItems = categories.stream().map(category -> {
			SelectItemGroup group = new SelectItemGroup(category.getName());
			group.setSelectItems(category.getProducts().stream()
				.map(product -> new SelectItem(product, product.getName()))
				.toArray(SelectItem[]::new));
			return group;
		}).collect(toList());
	}

	private List<Category> loadExampleCategories() {
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("Animals", new Product("Cat"), new Product("Dog"), new Product("Parrot")));
		categories.add(new Category("Cars", new Product("Alfa Romeo"), new Product("BMW"), new Product("Hyundai"), new Product("Toyota")));
		return categories;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<SelectItem> getCategorySelectItems() {
		return categorySelectItems;
	}

}