package com.github.atorr0.predictor;

import java.io.File;
import java.io.FileInputStream;

import com.github.atorr0.predictor.logic.NegativeLogic;
import com.github.atorr0.predictor.logic.StandaloneLogic;

public class Main {

	static File findBackwards(final String name) {

		File f = new File(".").getAbsoluteFile();

		while (f != null && !new File(f, name).exists())
			f = f.getParentFile();

		return f != null ? new File(f, name) : null;
	}

	public static void main(final String[] args) throws Exception {

		System.out.println("Small test");
		{
			final StandaloneLogic<Boolean> logic = new NegativeLogic();
			logic.build(new byte[] { 10 });
			logic.run();

			System.out.println(logic.getStatistics());
		}

		System.out.println("Simple test with pom.xml");
		{
			final StandaloneLogic<Boolean> logic = new NegativeLogic();

			final File file = findBackwards("pom.xml");
			logic.build(new FileInputStream(file));
			logic.run();

			System.out.println(logic.getStatistics());
		}

		System.out.println("Simple test with Main.class");
		{
			final StandaloneLogic<Boolean> logic = new NegativeLogic();

			final File file = new File(Main.class.getResource("Main.class").toURI());
			logic.build(new FileInputStream(file));
			logic.run();

			System.out.println(logic.getStatistics());
		}
	}
}
