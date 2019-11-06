package com.dressingcmds;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Kevin
 *
 */
public class HotWeatherDressingTest {

	/**
	 * Test method for {@link com.dressingcmds.HotWeatherDressing#getDressed(java.lang.String)}.
	 */
	@Test
	public void testGetDressed() {
		final String command = "_2";
		HotWeatherDressing cwd = new HotWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see value is sunglasses", "sunglasses", output);
	}
}