package com.github.atorr0.predictor.logic._1bit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Simple prediction logic that negates the underlying Logic.
 *
 * @author https://github.com/atorr0
 */
public class NotLogic extends _1BitStandaloneLogic {

	protected _1BitStandaloneLogic delegateLogic;

	public NotLogic(final _1BitStandaloneLogic logic) {
		delegateLogic = logic;
	}

	@Override
	public void build(final byte[] bs) {
		delegateLogic.build(bs);
	}

	@Override
	public void build(final InputStream is) throws IOException {
		delegateLogic.build(is);
	}

	@Override
	public void feedback(final Boolean t) {
		delegateLogic.feedback(t);
	}

	@Override
	public Iterator<Boolean> iterator() {
		return delegateLogic.iterator();
	}

	@Override
	public Boolean predict() {
		return !delegateLogic.predict();
	}

	@Override
	public void run() {
		delegateLogic.run();
	}

}
