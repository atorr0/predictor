package com.github.atorr0.predictor.logic._1bit;

/**
 * Simple prediction logic that predicts the next bit by counting the number of
 * bits sets to 1.
 *
 * @author https://github.com/atorr0
 */
public class BitCountingLogic extends _1BitStandaloneLogic {

	private static final int _DEBUG_COUNT = 1000;
	protected int[] bitCounts;
	protected int bitCountsIndex;

	protected CircularBitBuffer buffer;

	public BitCountingLogic(final int bitCount) {
		super();
		init(bitCount);
	}

	private int _debugCount = _DEBUG_COUNT;

	@Override
	public void feedback(final Boolean t) {

		if (buffer.read())
			bitCountsIndex += !t && 0 <= bitCountsIndex - 1 ? -1 : 0;
		else
			bitCountsIndex += t && bitCountsIndex + 1 < bitCounts.length ? 1 : 0;

		bitCounts[bitCountsIndex]++;

		// Print debug info?
		if (--_debugCount == 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("bitCounts:[");
			for (int i : bitCounts)
				sb.append(i).append(',');

			System.out.println(sb + "] bitCountsIndex:" + bitCountsIndex);
			_debugCount = _DEBUG_COUNT;
		}

		buffer.write(t);
	}

	protected void init(final int bitCount) {

		if (bitCount <= 0)
			throw new IllegalArgumentException();

		final long allocationBytes = 4L * (1 + bitCount) + 4L + CircularBitBuffer.size(bitCount);

		System.out.println(String.format("Allocationg %s bytes for a bit count of %s", allocationBytes, bitCount));

		bitCounts = new int[bitCount + 1];
		buffer = new CircularBitBuffer(bitCount);
	}

	@Override
	public Boolean predict() {

		final int bitCounts0, bitCounts1;

		final boolean b = buffer.read();

		{
			int bci = bitCountsIndex;
			if (b)
				bci += bci - 1 < 0 ? 0 : -1;
			else
				bci += 0;

			bitCounts0 = bitCounts[bci];
		}

		{
			int bci = bitCountsIndex;
			if (b)
				bci += 0;
			else
				bci += bci + 1 == bitCounts.length ? 0 : 1;

			bitCounts1 = bitCounts[bci];
		}

		return bitCounts0 < bitCounts1;
	}

	public static class CircularBitBuffer {

		protected byte[] buffer;
		protected long index;

		public CircularBitBuffer(final int bitCount) {

			buffer = new byte[(7 + bitCount) / 8];
			index = 0;
		}

		public boolean read() {

			final int idx = (int) ((index / 8) % buffer.length);
			final int mask = 0x80 >> index % 8;

			return (buffer[idx] & mask) != 0;
		}

		public long size() {
			return buffer.length + Long.BYTES;
		}

		public static long size(final Integer bitCount) {
			return (7 + bitCount) / 8 + 8L;
		}

		public void write(final boolean b) {

			final int idx = (int) ((index / 8) % buffer.length);
			final int mask = 0x80 >> index % 8;

			if (b)
				buffer[idx] |= mask;
			else
				buffer[idx] &= 0xff ^ mask;

			index++;
		}
	}

}