package com.dressingcmds;

import java.lang.StringBuilder;
import org.apache.commons.lang3.StringUtils;
import com.enums.HotResponse;

/**
 * @author Kevin.LaFrancis
 * Class used to pull the values of the commands from the Hot enum. Rules to be enforced in the util class. 
 */

public class HotWeatherDressing implements DressingCmdStrategy {

	@Override
	public String getDressed(String command) {
		StringBuilder outputMessage = new StringBuilder();

		//Validate the cmd is not blank or null and process
		if (StringUtils.isNotEmpty(command)){
			try {
				outputMessage.append(HotResponse.valueOf(command).getValue());
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