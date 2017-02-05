package com.github.atorr0.predictor.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.github.atorr0.predictor.statistics.Statistics;

public class MultiLogic<T> extends StandaloneLogic<T> {

	protected Collection<HitCounter> logics;

	protected MultiLogic(final int probabilitySpace) {
		super(probabilitySpace);
		logics = new ArrayList<>();
		setStatistics(new Statistics());
	}

	public void add(final StandaloneLogic<T> logic) {
		this.logics.add(new HitCounter(logic));
	}

	@Override
	public void build(final byte[] bs) {
		logics.iterator().next().logic.build(bs);
	}

	@Override
	public void build(final InputStream is) throws IOException {
		logics.iterator().next().logic.build(is);
	}

	@Override
	public void feedback(final T t) {

		for (final HitCounter hc : logics) {
			hc.logic.feedback(t);
			if (hc.prediction.equals(t))
				hc.hits++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return logics.iterator().next().logic.iterator();
	}

	@Override
	public T predict(final List<T> previousPredictions) {

		// 1. Store all predictions
		for (final HitCounter hc : logics)
			hc.prediction = hc.logic.predict(previousPredictions);

		// 2. Select the best confidence value
		HitCounter best = logics.iterator().next();
		for (final HitCounter hc : logics)
			if (best.hits < hc.hits)
				best = hc;

		return best.prediction;
	}

	protected class HitCounter {

		protected long hits = 0;
		protected final StandaloneLogic<T> logic;
		protected T prediction;

		public HitCounter(final StandaloneLogic<T> logic) {
			this.logic = logic;
		}

	}

}