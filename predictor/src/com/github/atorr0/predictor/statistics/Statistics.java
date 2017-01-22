package com.github.atorr0.predictor.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple stats. for hits & misses.
 *
 * @author https://github.com/atorr0
 */
public class Statistics {

	protected final List<Long> missesList = new ArrayList<>();

	protected long hits = 0, misses = 0;

	public long incrementHits() {
		return ++hits;
	}

	public long incrementMisses() {
		return ++misses;
	}

	public void next() {

		missesList.add(misses);
		hits = 0;
		misses = 0;
	}

	@Override
	public String toString() {

		long total = 0;
		for (Long misses : missesList)
			total += misses;

		final float hitRatio = Float.parseFloat("" + total) / missesList.size();

		return "Statistics [" //
				+ "hitRatio=" + hitRatio //
				+ ", missesList=" + missesList //
				+ ", hits=" + hits + ", misses=" + misses //
				+ "]";
	}
}