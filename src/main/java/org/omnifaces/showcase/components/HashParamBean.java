package org.omnifaces.showcase.components;

import static org.omnifaces.util.Messages.addGlobalInfo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.event.HashChangeEvent;

@Named
@ViewScoped
public class HashParamBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String foo;
	private Long bar;

	public void action(String foo, Long bar) {
		setFoo(foo);
		setBar(bar);
	}

	public void onHashChange(@Observes HashChangeEvent event) {
		addGlobalInfo("hash string changed from ''{0}'' to ''{1}''", event.getOldValue(), event.getNewValue());
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		if (!Objects.equals(foo, this.foo)) {
			addGlobalInfo("hash param ''foo'' value changed from ''{0}'' to ''{1}''", this.foo, foo);
		}

		this.foo = foo;
	}

	public Long getBar() {
		return bar;
	}

	public void setBar(Long bar) {
		if (!Objects.equals(bar, this.bar)) {
			addGlobalInfo("hash param ''bar'' value changed from ''{0}'' to ''{1}''", this.bar, bar);
		}

		this.bar = bar;
	}

}