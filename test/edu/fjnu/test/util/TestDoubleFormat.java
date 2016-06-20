package edu.fjnu.test.util;

import org.junit.Test;

import edu.fjnu.util.DoubleFormat;

public class TestDoubleFormat {

	@Test
	public void testGetDoubleFormat(){
		System.out.println(DoubleFormat.getDoubleFormat(0, 2));
	}
}
