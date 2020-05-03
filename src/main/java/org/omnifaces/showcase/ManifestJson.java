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
		return asList(ImageResource.of("layout/img/OmniFaces-logo.svg"));
	}

	@Override
	public String getThemeColor() {
		return "#373737";
	}

	@Override
	public String getBackgroundColor() {
		return "#f2f2f2";
	}

}
