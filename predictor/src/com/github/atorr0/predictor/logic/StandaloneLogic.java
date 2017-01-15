package com.github.atorr0.predictor.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Class with the following responsibilities:
 * <ul>
 * <li>Definition of the main API for prediction logic</li>
 * <li>Implementation of the retry loop</li>
 * <li>Handling of statistics</li>
 * </ul>
 *
 * TODO Change byte[] working to InputStream.<br>
 * TODO A bit more documentation.
 *
 * @author https://github.com/atorr0
 */
public abstract class StandaloneLogic<T> implements Iterable<T> {

	protected byte[] bs;

	protected int probabilitySpace;

	protected Statistics statistics;

	/**
	 * Unique ctor.
	 *
	 * @param probabilitySpace
	 */
	protected StandaloneLogic(final int probabilitySpace) {
		this.probabilitySpace = probabilitySpace;
		statistics = new Statistics();
	}

	public void build(final byte[] bs) {
		this.bs = bs;
	}

	public void build(final InputStream ins) throws IOException {
		bs = IOUtils.toByteArray(ins);
	}

	protected abstract void feedback(T t);

	public Statistics getStatistics() {
		return statistics;
	}

	protected abstract T predict(final List<T> previousResults);

	public void run() {

		final List<T> previousResults = new ArrayList<>(probabilitySpace / 2);

		for (final T t : this) {

			previousResults.clear();

			for (int i = 0; i < probabilitySpace; i++) {

				final T predicted = predict(previousResults);
				if (predicted.equals(t)) {

					statistics.hits++;
					break;
				} else {

					statistics.misses++;
					previousResults.add(t);
				}
			}

			feedback(t);
			statistics.next();
		}
	}

	/**
	 * Simple stats. for hits & misses.
	 *
	 * @author https://github.com/atorr0
	 */
	public static final class Statistics {

		protected final List<Long> missesList = new ArrayList<>();

		protected long hits = 0, misses = 0;

		protected void next() {

			missesList.add(misses);
			hits = 0;
			misses = 0;
		}

		@Override
		public String toString() {
			return "Statistics [missesList=" + missesList + ", hits=" + hits + ", misses=" + misses + "]";
		}

	}
}
