package org.omnifaces.showcase.push;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.Startup;
import org.omnifaces.showcase.PageView;
import org.omnifaces.util.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

@Named
@Startup
public class StatsBean {

	private static final long MAX_LAST_PAGE_VIEWS = 20;
	private ConcurrentMap<UUID, PageView> lastPageViews;

	@Inject
	private PushContext pushContext;

	@PostConstruct
	public void init() {
		lastPageViews = new ConcurrentLinkedHashMap.Builder<UUID, PageView>()
			.maximumWeightedCapacity(MAX_LAST_PAGE_VIEWS)
			.build();
	}

	public void onPageView(@Observes PageView pageView) {
		lastPageViews.put(UUID.randomUUID(), pageView);
		pushContext.send("stats", pageView);
	}

	public long getMaxLastPageViews() {
		return MAX_LAST_PAGE_VIEWS;
	}

	@Produces
	@Named
	@RequestScoped
	public List<PageView> getLastPageViews() {
		return lastPageViews.values().stream().sorted().collect(Collectors.<PageView>toList());
	}

}