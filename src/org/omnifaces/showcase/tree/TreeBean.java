package org.omnifaces.showcase.tree;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;

@ManagedBean
@RequestScoped
public class TreeBean {

	private TreeModel<Page> menu;

	@PostConstruct
	public void init() {
		menu = new ListTreeModel<Page>();
		menu.addChild(new Page("http://code.google.com/p/omnifaces/", "OmniFaces Homepage")).getParent()
			.addChild(new Page("index.xhtml", "OmniFaces Showcase"))
				.addChild(new Page(null, "Components"))
					.addChild(new Page("highlight.xhtml", "o:highlight")).getParent()
					.addChild(new Page("outputlabel.xhtml", "o:outputLabel")).getParent()
					.addChild(new Page("resourceinclude.xhtml", "o:resourceInclude")).getParent()
					.addChild(new Page("tree.xhtml", "o:tree")).getParent()
					.addChild(new Page("viewparam.xhtml", "o:viewParam")).getParent()
					.getParent()
				.addChild(new Page(null, "Tag Handlers"))
					.addChild(new Page("methodparam.xhtml", "o:methodParam")).getParent()
					.getParent()
				.addChild(new Page(null, "Validators"))
					.addChild(new Page("validateallornone.xhtml", "o:validateAllOrNone")).getParent()
					.addChild(new Page("validateequal.xhtml", "o:validateEqual")).getParent()
					.addChild(new Page("validateoneormore.xhtml", "o:validateOneOrMore")).getParent()
					.addChild(new Page("validateorder.xhtml", "o:validateOrder")).getParent()
					.addChild(new Page("validateunique.xhtml", "o:validateUnique")).getParent()
					.getParent()
				.addChild(new Page(null, "Converters"))
					.addChild(new Page("selectitemsconverter.xhtml", "omnifaces.SelectItemsConverter")).getParent()
					.getParent()
				.addChild(new Page(null, "Event Listeners"))
					.addChild(new Page("resetinputajaxactionlistener.xhtml", "org.omnifaces.event.ResetInputAjaxActionListener")).getParent()
					.getParent()
				.addChild(new Page(null, "Exception Handlers"))
					.addChild(new Page("fullajaxexceptionhandler.xhtml", "org.omnifaces.exceptionhandler.FullAjaxExceptionHandler")).getParent()
					.getParent()
				.getParent()
			.getParent()
		;
	}

	public TreeModel<Page> getMenu() {
		return menu;
	}

}
