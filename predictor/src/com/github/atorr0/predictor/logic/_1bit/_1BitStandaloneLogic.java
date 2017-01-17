package com.github.atorr0.predictor.logic._1bit;

import java.util.Iterator;

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

}
