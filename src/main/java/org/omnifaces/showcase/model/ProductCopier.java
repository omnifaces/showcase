package org.omnifaces.showcase.model;

import org.omnifaces.util.copier.Copier;

public class ProductCopier implements Copier {

	@Override
	public Product copy(Object object) {
		Product original = (Product) object;
		
		// Just for the example, don't use the copy ctor, but set fields manually.
		Product copy = new Product();
		copy.setNumber1(original.getNumber1());
		copy.setNumber2(original.getNumber2());
		
		return copy;
	}
	
}
