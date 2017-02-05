package com.github.atorr0.predictor.logic._1bit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class _1BitIterator implements Iterable<Boolean> {

	protected byte[] bs;

	protected InputStream is;

	public _1BitIterator(final byte[] bs) {
		this.bs = bs;
	}

	public _1BitIterator(final byte[] bs, final InputStream is) {
		this.bs = bs;
		this.is = is;
	}

	public _1BitIterator(final InputStream is) {
		this.is = is;
	}

	@Override
	public Iterator<Boolean> iterator() {
		return is != null ? new InputStreamIterator(is) : new ByteArrayIterator(bs);
	}

}

class ByteArrayIterator implements Iterator<Boolean> {

	protected byte[] bs;
	protected long bitIndex = -1;

	public ByteArrayIterator(final byte[] bs) {

		if (bs == null)
			throw new IllegalArgumentException();

		this.bs = bs;
	}

	@Override
	public boolean hasNext() {
		return (bitIndex + 1) / 8 < bs.length;
	}

	@Override
	public Boolean next() {

		final byte b = bs[(int) ++bitIndex / 8];

		return (b & (0x80 >> (bitIndex % 8))) == 0 ? Boolean.FALSE : Boolean.TRUE;
	}
}

class InputStreamIterator implements Iterator<Boolean> {

	protected InputStream is;
	protected long bitIndex;
	protected byte b;

	public InputStreamIterator(final InputStream is) {
		this.is = is;
		bitIndex = -1;
	}

	@Override
	public boolean hasNext() {

		final boolean byteConsumed = bitIndex % 8 == 0;
		if (!byteConsumed)
			return true;

		// Read new byte
		try {
			final int i = is.read();
			b = (byte) i;
			bitIndex = 0;

			return i != -1;
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Boolean next() {
		return (b & (0x80 >> (++bitIndex % 8))) == 0 ? Boolean.FALSE : Boolean.TRUE;
	}
}
