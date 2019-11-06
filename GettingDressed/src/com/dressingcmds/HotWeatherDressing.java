package com.dressingcmds;

import java.lang.StringBuilder;
import com.enums.HotResponse;

/**
 * @author Kevin.LaFrancis
 * Class used to pull the values of the commands from the Hot enum. Rules to be enforced in the util class. 
 */

public class HotWeatherDressing implements DressingCmdStrategy {

	@Override
	public String getDressed(String command) {

		StringBuilder outputMessage = new StringBuilder();
		outputMessage.append(HotResponse.valueOf(command).getValue());
		return outputMessage.toString();
	}
}