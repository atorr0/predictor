package com.github.atorr0.predictor.logic._1bit;

/**
 * Simple prediction logic that predicts the next bit as the negation of the
 * previous one.
 *
 * @author https://github.com/atorr0
 */
public class NegativeLogic extends _1BitStandaloneLogic {

	protected Boolean previous = Boolean.FALSE;

	@Override
	public void feedback(final Boolean t) {
		previous = t;
	}

	@Override
	public Boolean predict() {
		return !previous;
	}

}
