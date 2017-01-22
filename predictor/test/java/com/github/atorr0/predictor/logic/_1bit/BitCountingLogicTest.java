package com.github.atorr0.predictor.logic._1bit;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCountingLogicTest {

	@Test
	public void test() {

		BitCountingLogic logic = new BitCountingLogic(4);

		logic.build(new byte[] { 10 });

		// logic.feedback(true);
		// logic.feedback(false);
		// logic.feedback(true);

		logic.run();

		System.out.println(logic.getStatistics().toString());

	}

}
