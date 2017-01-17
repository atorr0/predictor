package com.github.atorr0.predictor.logic._1bit;

import java.util.Iterator;
import java.util.List;

import com.github.atorr0.predictor.logic.StandaloneLogic;

/**
 * Class with the following responsibilities:
 * <ul>
 * <li>Definition of the main API for 1 bit prediction logic</li>
 * <li>Implementation of the retry loop</li>
 * </ul>
 *
 * @author https://github.com/atorr0
 */
public abstract class _1BitStandaloneLogic extends StandaloneLogic<Boolean> {

	protected _1BitStandaloneLogic() {
		super(2);
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

			statistics.incrementHits();

			feedback(t);
			statistics.next();
		}
	}
}
