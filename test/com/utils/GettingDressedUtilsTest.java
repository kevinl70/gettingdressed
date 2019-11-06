/**
 * 
 */
package com.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.enums.TempatureType;

/**
 * @author Kevin
 *
 */
public class GettingDressedUtilsTest {
	
	private List<String> processedCmds = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.utils.GettingDressedUtils#isValidTempType(java.lang.String)}.
	 */
	@Test
	public void testIsValidTempType() {
		boolean validTempTypeHot = GettingDressedUtils.isValidTempType("Hot");
		assertTrue(validTempTypeHot);
		
		boolean validTempTypeCold = GettingDressedUtils.isValidTempType("Cold");
		assertTrue(validTempTypeCold);
	}
	
	/**
	 * Test method for {@link com.utils.GettingDressedUtils#isValidTempType(java.lang.String)}.
	 */
	@Test
	public void testIsValidTempTypeNull() {
		boolean validTempType = GettingDressedUtils.isValidTempType("");
		assertFalse(validTempType);
	}
	
	/**
	 * Test method for {@link com.utils.GettingDressedUtils#isValidTempType(java.lang.String)}.
	 */
	@Test
	public void testIsValidTempTypeWarm() {
		boolean validTempType = GettingDressedUtils.isValidTempType("Warm");
		assertFalse(validTempType);
	}	

	/**
	 * Test method for {@link com.utils.GettingDressedUtils#validateShirtIsOn(com.enums.TempatureType, java.util.List)}.
	 */
	@Test
	public void testValidateShirtIsOn() {
		this.processedCmds = new ArrayList<String>();
		for (int i = 1; i<7; i++){
			processedCmds.add(Integer.toString(i));
		}
		
		boolean isShirtOn = GettingDressedUtils.validateShirtIsOn(processedCmds);
		assertTrue(isShirtOn);
	}
	
	@Test
	public void testValidateShirtIsNotOn() {
		this.processedCmds = new ArrayList<String>();
		processedCmds.add("6");
		boolean isShirtOn = GettingDressedUtils.validateShirtIsOn(processedCmds);
		assertFalse(isShirtOn);
	}
	
	@Test
	public void testValidateShirtIsOnWithEmptyCmds() {
		this.processedCmds = new ArrayList<String>();
		boolean isShirtOn = GettingDressedUtils.validateShirtIsOn(processedCmds);
		assertFalse(isShirtOn);
	}

	/**
	 * Test method for {@link com.utils.GettingDressedUtils#isLastCmdNotValid(java.util.List)}.
	 */
	@Test
	public void testIsLastCmdNotValid() {
		this.processedCmds = new ArrayList<String>();
		for (int i = 1; i<8; i++){
			processedCmds.add(Integer.toString(i));
		}
		boolean lastCmdIsNotValid = GettingDressedUtils.isLastCmdNotValid(processedCmds);
		assertFalse(lastCmdIsNotValid);
	}
	
	@Test
	public void testIsLastCmdNotValidEmptyCmds() {
		this.processedCmds = new ArrayList<String>();
		boolean lastCmdIsNotValid = GettingDressedUtils.isLastCmdNotValid(processedCmds);
		assertTrue(lastCmdIsNotValid);
	}	
	
	@Test
	public void testIsLastCmdNotValidCorrectCmd() {
		this.processedCmds = new ArrayList<String>();
		for (int i = 1; i<5; i++){
			processedCmds.add(Integer.toString(i));
		}
		boolean lastCmdIsNotValid = GettingDressedUtils.isLastCmdNotValid(processedCmds);
		assertTrue(lastCmdIsNotValid);
	}	

	/**
	 * Test method for {@link com.utils.GettingDressedUtils#canPutFootwearOn(com.enums.TempatureType, java.util.List)}.
	 */
	@Test
	public void testCanPutFootwearOnCold() {
		TempatureType tempType = TempatureType.COLD;
		this.processedCmds = new ArrayList<String>();
		for (int i = 1; i<7; i++){
			processedCmds.add(Integer.toString(i));
		}
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertTrue(canPutFootwearOn);
	}
	
	@Test
	public void testCanPutFootwearOnColdtMissingCmds() {
		TempatureType tempType = TempatureType.COLD;
		this.processedCmds = new ArrayList<String>();
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertFalse(canPutFootwearOn);
	}
	
	@Test
	public void testCanPutFootwearOnColdtMissingTriggerCmd() {
		TempatureType tempType = TempatureType.COLD;
		this.processedCmds = new ArrayList<String>();
		this.processedCmds.add("5");
		this.processedCmds.add("4");
		this.processedCmds.add("2");
		
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertFalse(canPutFootwearOn);
	}		
	
	/** Testing of hot temp **/
	@Test
	public void testCanPutFootwearOnHot() {
		TempatureType tempType = TempatureType.HOT;
		this.processedCmds = new ArrayList<String>();
		processedCmds.add("6");
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertTrue(canPutFootwearOn);
	}

	@Test
	public void testCanPutFootwearOnHotMissingCmds() {
		TempatureType tempType = TempatureType.HOT;
		this.processedCmds = new ArrayList<String>();
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertFalse(canPutFootwearOn);
	}
	
	@Test
	public void testCanPutFootwearOnHotMissingTriggerCmd() {
		TempatureType tempType = TempatureType.HOT;
		this.processedCmds = new ArrayList<String>();
		this.processedCmds.add("8");
		this.processedCmds.add("4");
		this.processedCmds.add("2");
		
		boolean canPutFootwearOn = GettingDressedUtils.canPutFootwearOn(tempType, this.processedCmds);
		assertFalse(canPutFootwearOn);
	}	
	
}
