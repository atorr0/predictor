package com.github.atorr0.predictor.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.atorr0.predictor.statistics.Statistics;
import com.github.atorr0.predictor.statistics.VerboseStatistics;

/**
 * Class with the following responsibilities:
 * <ul>
 * <li>Definition of the main API for prediction logic</li>
 * <li>Implementation of the retry loop</li>
 * <li>Handling of statistics</li>
 * </ul>
 *
 * TODO A bit more documentation.
 *
 * @author https://github.com/atorr0
 */
public abstract class StandaloneLogic<T> implements Iterable<T> {

	protected byte[] bs;

	protected int probabilitySpace;

	protected Statistics statistics;

	protected InputStream is;

	/**
	 * Unique ctor.
	 *
	 * @param probabilitySpace
	 */
	protected StandaloneLogic(final int probabilitySpace) {
		this.probabilitySpace = probabilitySpace;
		setStatistics(new VerboseStatistics());
	}

	public void build(final byte[] bs) {
		this.bs = bs;
	}

	public void build(final InputStream is) throws IOException {
		this.is = is;
	}

	public abstract void feedback(T t);

	public Statistics getStatistics() {
		return statistics;
	}

	public abstract T predict(final List<T> previousPredictions);

	public void run() {

		final List<T> previousPredictions = new ArrayList<>(probabilitySpace / 2);

		for (final T t : this) {

			previousPredictions.clear();

			for (int i = 1; i < probabilitySpace; i++) {

				final T predicted = predict(previousPredictions);
				if (predicted.equals(t))
					break;

				statistics.incrementMisses();
				previousPredictions.add(t);
			}

			feedback(t);
			statistics.next();
		}
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

}
