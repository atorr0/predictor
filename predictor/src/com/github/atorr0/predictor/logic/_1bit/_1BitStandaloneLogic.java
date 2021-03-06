package com.github.atorr0.predictor.logic._1bit;

import java.util.Iterator;
import java.util.List;

import com.github.atorr0.predictor.logic.StandaloneLogic;

/**
 * Definition and optimization of the 1 bit prediction logic.
 *
 * @author https://github.com/atorr0
 */
public abstract class _1BitStandaloneLogic extends StandaloneLogic<Boolean> {

	public static final int PROBABILITY_SPACE = 2;

	protected _1BitStandaloneLogic() {
		super(PROBABILITY_SPACE);
	}

	@Override
	public Iterator<Boolean> iterator() {
		return new _1BitIterator(bs, is).iterator();
	}

	public abstract Boolean predict();

	/**
	 * Delegation to {@link #predict()} as it's pointless to have a previous
	 * results list (on 1 bit predictions).
	 *
	 * @see com.github.atorr0.predictor.logic.StandaloneLogic#predict(java.util.List)
	 */
	@Override
	public final Boolean predict(final List<Boolean> previousPredictions) {
		return predict();
	}

	@Override
	public void run() {

		for (final Boolean t : this) {

			final Boolean predicted = predict();
			if (!predicted.equals(t))
				statistics.incrementMisses();

			feedback(t);
			statistics.next();
		}
	}
}
