package com.dressingcmds;

import com.enums.ColdResponse;
import java.lang.StringBuilder;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kevin.LaFrancis
 * Class used to pull the values of the commands from the Cold enum. Rules to be enforced in the util class. 
 */

public class ColdWeatherDressing implements DressingCmdStrategy {

	/* (non-Javadoc)
	 * @see com.dressingcmds.DressingCommands#getDressed(java.util.List)
	 */
	@Override
	public String getDressed(String command) {
		StringBuilder outputMessage = new StringBuilder();
		
		//Validate the cmd is not blank or null and process
		if (StringUtils.isNotEmpty(command)){
			
			try {
				outputMessage.append(ColdResponse.valueOf(command).getValue());
			} catch (IllegalArgumentException iae) {
				System.out.println("Illegal command issued: "+ command);
				outputMessage.append("");
			} catch (Exception e) {
				System.out.println("Exception caught: " + e.getMessage());
				outputMessage.append("");
			}
		}
		return outputMessage.toString();
	}
}