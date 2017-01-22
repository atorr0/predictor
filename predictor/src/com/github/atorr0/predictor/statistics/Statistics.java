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

	protected long misses = 0;

	public float getHitRatio() {

		long total = 0;
		for (final Long misses : missesList)
			total += misses;

		return 1f - Float.parseFloat("" + total) / missesList.size();
	}

	public long incrementMisses() {
		return ++misses;
	}

	public void next() {

		missesList.add(misses);
		misses = 0;
	}

	@Override
	public String toString() {

		return "Statistics [" //
				+ "hitRatio=" + getHitRatio() //
				+ ", missesList=" + missesList //
				+ ", misses=" + misses //
				+ "]";
	}
}