package com.dressingcmds;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColdWeatherDressingTest {

	@Test
	public void testGetDressedValidateCmd() {
		final String command = "_2";
		ColdWeatherDressing cwd = new ColdWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see value is hat", "hat", output);
	}
	
	@Test
	public void testGetDressedValidateBadCmd() {
		final String command = "_10";
		ColdWeatherDressing cwd = new ColdWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see repsonse is empty", "",output);
	}
	
	@Test
	public void testGetDressedValidateEmptyString() {
		final String command = "";
		ColdWeatherDressing cwd = new ColdWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see repsonse is empty", "",output);
	}

	@Test
	public void testGetDressedValidateNullString() {
		final String command = null;
		ColdWeatherDressing cwd = new ColdWeatherDressing();
		String output = cwd.getDressed(command);
		assertEquals("check to see repsonse is empty", "",output);
	}	
}