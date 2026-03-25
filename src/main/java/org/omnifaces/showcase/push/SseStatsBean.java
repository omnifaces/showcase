package org.omnifaces.showcase.push;

import static org.omnifaces.cdi.Push.Type.SSE;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.Startup;
import org.omnifaces.showcase.PageView;

/**
 * Backing bean for the SSE stats demo page.
 * Observes {@link PageView} CDI events and pushes them to connected SSE clients.
 */
@Named
@Startup
public class SseStatsBean {

	private static final int MAX_LAST_PAGE_VIEWS = 20;
	private Deque<PageView> lastPageViews;

	@Inject @Push(type = SSE)
	private PushContext sseStats;

	@PostConstruct
	public void init() {
		lastPageViews = new LinkedBlockingDeque<>(MAX_LAST_PAGE_VIEWS);
	}

	/**
	 * Observes page view events and pushes them to connected SSE clients.
	 * @param pageView The page view event.
	 */
	public void onPageView(@Observes PageView pageView) {
		while (!lastPageViews.offerFirst(pageView)) {
			lastPageViews.pollLast();
		}

		sseStats.send(pageView);
	}

	/**
	 * Returns the maximum number of page views to display.
	 * @return The maximum number of page views.
	 */
	public int getMaxLastPageViews() {
		return MAX_LAST_PAGE_VIEWS;
	}

	/**
	 * Returns the last page views.
	 * @return The last page views.
	 */
	@Produces @Model
	public List<PageView> getLastSsePageViews() {
		return new ArrayList<>(lastPageViews);
	}

}
