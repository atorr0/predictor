package com.github.atorr0.predictor.logic._1bit;

import java.util.List;

/**
 * Simple prediction logic that predicts the next bit by counting the number of
 * bits sets to 1.
 *
 * @author https://github.com/atorr0
 */
public class BitCountingLogic extends _1BitStandaloneLogic {

	protected int[] bitCounts;

	protected byte[] bitBuffer;
	protected long bitBufferIndex;

	protected int bitCount;

	public BitCountingLogic(final int bitCount) {
		super();
		init(bitCount);
	}

	@Override
	public void feedback(final Boolean t) {
		// TODO

	}

	protected void init(final int bitCount) {

		final long allocatedBytes = 4L * bitCount + ((7L + bitCount) / 8);

		System.out.println(String.format("Allocationg %s bytes for a bit count of %s", allocatedBytes, bitCount));

		bitCounts = new int[bitCount];
		bitBuffer = new byte[(7 + bitCount) / 8];
		bitBufferIndex = this.bitCount = 0;
	}

	@Override
	public Boolean predict() {
		// TODO
		throw new UnsupportedOperationException("Not implemented!");
	}

}
