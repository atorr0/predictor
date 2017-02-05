package com.github.atorr0.predictor.logic;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.github.atorr0.predictor.TestUtils;
import com.github.atorr0.predictor.logic._1bit.BitCountingLogic;
import com.github.atorr0.predictor.logic._1bit.NegativeLogic;
import com.github.atorr0.predictor.logic._1bit.NotLogic;
import com.github.atorr0.predictor.logic._1bit.SameLogic;
import com.github.atorr0.predictor.logic._1bit._1BitStandaloneLogic;

public class MultiLogicTest {

	@Test
	public void test1() {

		System.out.println("test1");

		final MultiLogic<Boolean> multiLogic = new MultiLogic<Boolean>(_1BitStandaloneLogic.PROBABILITY_SPACE);

		multiLogic.add(new NegativeLogic());
		multiLogic.add(new BitCountingLogic(1));
		multiLogic.build(new byte[] { 10 });

		multiLogic.run();

		System.out.println(multiLogic.getStatistics());
		
		assertTrue(true);
	}

	@Test
	public void test2() throws FileNotFoundException, IOException {

		System.out.println("test2");

		System.gc();

		final MultiLogic<Boolean> multiLogic = new MultiLogic<Boolean>(_1BitStandaloneLogic.PROBABILITY_SPACE);

		multiLogic.add(new NegativeLogic());
		multiLogic.add(new BitCountingLogic(1));
		multiLogic.add(new BitCountingLogic(2));
		multiLogic.add(new BitCountingLogic(3));
		multiLogic.add(new BitCountingLogic(4));
		multiLogic.add(new BitCountingLogic(8));
		multiLogic.add(new BitCountingLogic(16));
		multiLogic.add(new BitCountingLogic(32));
		multiLogic.add(new BitCountingLogic(64));
		multiLogic.add(new BitCountingLogic(128));
		multiLogic.add(new BitCountingLogic(256));
		multiLogic.build(new FileInputStream(TestUtils.findBackwards("target/test-classes/Front_mouvt_brownien.png")));

		multiLogic.run();

		System.out.println(multiLogic.getStatistics());
		
		assertTrue(true);
	}

	@Test
	public void test3() throws FileNotFoundException, IOException {

		System.out.println("test3");

		final String[] files = { "Front_mouvt_brownien.png", "Mandel_zoom_11_satellite_double_spiral.jpg",
				"resources.7z", "Spain_Sagrada_Familia.jpg" };
		for (String file : files) {

			System.gc();

			final MultiLogic<Boolean> multiLogic = new MultiLogic<Boolean>(_1BitStandaloneLogic.PROBABILITY_SPACE);

			multiLogic.add(new NegativeLogic());
			final int[] is = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 32, 33, 64, 128, 256, 512, //
					1024, 2048, 4096, 8192, 16384 };
			for (int i : is) {
				multiLogic.add(new SameLogic(new BitCountingLogic(i)));
				multiLogic.add(new NotLogic(new BitCountingLogic(i)));
			}

			multiLogic.build(new FileInputStream(TestUtils.findBackwards("target/test-classes/" + file)));

			System.out.println(file + " ...");

			multiLogic.run();

			System.out.println(multiLogic.getStatistics());
		}

		assertTrue(true);
	}
}
