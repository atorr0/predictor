package com.github.atorr0.predictor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;

import com.github.atorr0.predictor.Main;

public class TestUtils {

	public static InputStream toInputStream(final Class<?> class1) throws URISyntaxException, FileNotFoundException {
		return new FileInputStream(new File(Main.class.getResource("Main.class").toURI()));
	}

	public static InputStream toInputStream(final String eclipseProjectFileName) throws URISyntaxException, FileNotFoundException {
		return new FileInputStream(findBackwards(eclipseProjectFileName));
	}

	public static File findBackwards(final String name) {

		File f = new File(".").getAbsoluteFile();

		while (f != null && !new File(f, name).exists())
			f = f.getParentFile();

		return f != null ? new File(f, name) : null;
	}

}
