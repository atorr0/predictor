package com.github.atorr0.predictor.logic._1bit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Test;

public class _1BitIteratorTest {

	@Test
	public void test1() {

		final Iterator<Boolean> it = new _1BitIterator(new byte[] { 10 }).iterator();

		assertTrue(it.hasNext());
		assertFalse(it.next());
		assertFalse(it.next());
		assertFalse(it.next());
		assertFalse(it.next());
		//
		assertTrue(it.next());
		assertFalse(it.next());
		assertTrue(it.next());
		assertFalse(it.next());
		assertFalse(it.hasNext());
	}

	@Test
	public void test2() {

		final byte[][] bss = new byte[][] { new byte[] { -127 }, new byte[] { (byte) 0x81 } };

		for (final byte[] bs : bss) {

			final Iterator<Boolean> it = new _1BitIterator(bs).iterator();

			assertTrue(it.hasNext());
			assertTrue(it.next());
			assertFalse(it.next());
			assertFalse(it.next());
			assertFalse(it.next());
			//
			assertFalse(it.next());
			assertFalse(it.next());
			assertFalse(it.next());
			assertTrue(it.next());
			assertFalse(it.hasNext());
		}
	}

}
