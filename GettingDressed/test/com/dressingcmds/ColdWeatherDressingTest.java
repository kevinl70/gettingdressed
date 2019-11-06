package com.dressingcmds;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColdWeatherDressingTest {

	@Test
	public void testGetDressed() {
		final String command = "_2";
		ColdWeatherDressing cwd = new ColdWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see value is hat", "hat", output);
	}	
}