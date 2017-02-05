package com.github.atorr0.predictor.statistics;

/**
 * Simple stats. for hits & misses.
 *
 * @author https://github.com/atorr0
 */
public class VerboseStatistics extends Statistics {

	@Override
	public String toString() {

		return "Statistics [" //
				+ "hitRatio=" + getHitRatio() //
				+ ", missesList=" + missesList //
				+ ", misses=" + misses //
				+ "]";
	}
}