package com.github.atorr0.predictor.logic._1bit;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.atorr0.predictor.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BitCountingLogicTest {

	@Test
	public void test00() {

		final BitCountingLogic logic = new BitCountingLogic(4);

		logic.build(new byte[] { 10 });

		logic.run();

		System.out.println(logic.getStatistics());

		assertTrue(true);
	}

	@Test
	public void testFiles() throws FileNotFoundException, IOException, URISyntaxException {

		{
			final BitCountingLogic logic = new BitCountingLogic(1);

			logic.build(TestUtils.toInputStream(BitCountingLogicTest.class));
			logic.run();

			System.out.println(logic.getStatistics().toString());
		}

		{
			final BitCountingLogic logic = new BitCountingLogic(3);

			logic.build(TestUtils.toInputStream("pom.xml"));
			logic.run();

			System.out.println(logic.getStatistics().toString());
		}

		{
			final BitCountingLogic logic = new BitCountingLogic(384);

			logic.build(new FileInputStream(TestUtils.findBackwards("target/test-classes/Front_mouvt_brownien.png")));
			logic.run();

			System.out.println(logic.getStatistics());
		}

		assertTrue(true);
	}
}
