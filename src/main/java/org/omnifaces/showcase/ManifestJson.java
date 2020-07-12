package org.omnifaces.showcase;

import static java.util.Arrays.asList;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.omnifaces.resourcehandler.WebAppManifest;

@ApplicationScoped
public class ManifestJson extends WebAppManifest {

	@Override
	public String getName() {
		return "OmniFaces Showcase";
	}

	@Override
	public String getShortName() {
		return "OFS";
	}

	@Override
	public Collection<ImageResource> getIcons() {
		return asList(
			ImageResource.of("layout:img/OmniFaces-icon-192x192.png", Size.SIZE_192),
			ImageResource.of("layout:img/OmniFaces-icon-512x512.png", Size.SIZE_512)
		);
	}

	@Override
	public Display getDisplay() {
		return Display.STANDALONE;
	}

	@Override
	public String getThemeColor() {
		return "#373737";
	}

	@Override
	public String getBackgroundColor() {
		return "#f2f2f2";
	}

	@Override
	protected String getOfflineViewId() {
		return "/offline.xhtml";
	}

}
