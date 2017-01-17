package com.github.atorr0.predictor.logic._1bit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Simple prediction logic that negates the underlying Logic.
 *
 * @author https://github.com/atorr0
 */
public class NotLogic extends _1BitStandaloneLogic {

	protected _1BitStandaloneLogic delegateLogic;

	public NotLogic(_1BitStandaloneLogic logic) {
		delegateLogic = logic;
	}

	public Iterator<Boolean> iterator() {
		return delegateLogic.iterator();
	}

	@Override
	public void feedback(Boolean t) {
		delegateLogic.feedback(t);
	}

	@Override
	public Boolean predict() {
		return delegateLogic.predict();
	}

	public void build(byte[] bs) {
		delegateLogic.build(bs);
	}

	public void build(InputStream is) throws IOException {
		delegateLogic.build(is);
	}

	public void run() {
		delegateLogic.run();
	}

}
