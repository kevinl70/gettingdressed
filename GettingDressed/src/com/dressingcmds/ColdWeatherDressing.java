package com.dressingcmds;

import com.enums.ColdResponse;
import java.lang.StringBuilder;

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
		outputMessage.append(ColdResponse.valueOf(command).getValue());
		return outputMessage.toString();
	}

}
