package org.omnifaces.showcase.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ConvertersBean {

	private String[] array;
	private Set<String> set;
	private Map<String, String> map;
	private String[] bigArray;
	private List<String> bigList;

	@PostConstruct
	public void init() {
		array = new String[] { "item1", "item2", "item3" };

		set = new LinkedHashSet<>(3);
		set.add("item1");
		set.add("item2");
		set.add("item3");

		map = new LinkedHashMap<>(3);
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");

		bigArray = new String[] { "item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9" };
		bigList = Arrays.asList(bigArray);
	}

	public String[] getArray() {
		return array;
	}

	public Set<String> getSet() {
		return set;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public String[] getBigArray() {
		return bigArray;
	}

	public List<String> getBigList() {
		return bigList;
	}

	public Iterable<Integer> getIterable() {
		return new TestIterable();
	}

	private static class TestIterable implements Iterable<Integer> {

		@Override
		public Iterator<Integer> iterator() {
			return new Iterator<Integer>() {

				int index = 0;

				@Override
				public boolean hasNext() {
					return index < 3;
				}

				@Override
				public Integer next() {
					return index++;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}

			};
		}

	}

}