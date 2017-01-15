package com.github.atorr0.predictor.logic._1bit;

import java.util.Iterator;
import java.util.List;

import com.github.atorr0.predictor.logic.StandaloneLogic;

/**
 * Simple prediction logic that predicts the next bit supposing that the bits
 * follow a .
 *
 * @author https://github.com/atorr0
 */
public class CountingLogic extends StandaloneLogic<Boolean> {

	protected Boolean previous = Boolean.FALSE;

	public CountingLogic() {
		super(2);
	}

	@Override
	protected void feedback(final Boolean t) {
		previous = t;
	}

	@Override
	public Iterator<Boolean> iterator() {

		return new Iterator<Boolean>() {

			private long bitIndex = -1;

			@Override
			public boolean hasNext() {
				return (bitIndex + 1) / 8 < bs.length;
			}

			@Override
			public Boolean next() {

				final byte b = bs[(int) ++bitIndex / 8];

				return (b & (0x80 >> (bitIndex % 8))) == 0 ? Boolean.FALSE : Boolean.TRUE;
			}
		};
	}

	@Override
	protected Boolean predict(final List<Boolean> previousResults) {
		return previousResults.isEmpty() ? !previous : previous;
	}

}
