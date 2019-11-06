package com;

import java.util.List;

import com.dressingcmds.ColdWeatherDressing;
import com.dressingcmds.HotWeatherDressing;
import com.enums.TempatureType;
import com.exceptions.DressingCommandException;
import com.utils.GettingDressedUtils;

/**
 * @author Kevin LaFrancis
 * Main class to pull in commands to get dressed and then show output.
 * Rules for getting dressed:
 * You start in the house with your PJ’s on
 * Pajamas must be taken off before anything else can be put on
 * Only 1 piece of each type of clothing may be put on
 * You cannot put on socks when it is hot
 * You cannot put on a jacket when it is hot
 * Socks must be put on before footwear
 * Pants must be put on before footwear
 * The shirt must be put on before the headwear or jacket
 * You cannot leave the house until all items of clothing are on (except socks and a jacket when it’s hot)
 * If an invalid command is issued, respond with “fail” and stop processing commands
 */

public class GettingDressed {

	/**
	 * @param args String array representing commands for getting dressed.
	 */
	public static void main(String[] args) {
		final String failMessage = "fail";
		final char delimeter = ',';
		StringBuilder outputMessage = null;
		
		/* Call the method to convert the String Array into a list */
		try {
			List<String> commandList = GettingDressedUtils.validateParams(args);
			String tempType = commandList.get(0);
			outputMessage = new StringBuilder(tempType). 
			append(delimeter);
			
			if (tempType.equalsIgnoreCase(TempatureType.HOT.toString())){
				commandList.remove(0);
				outputMessage.append(GettingDressedUtils.buildCommandOutput(commandList, new HotWeatherDressing(), null));				
			} else if (tempType.equalsIgnoreCase(TempatureType.COLD.toString())){ 
				commandList.remove(0);
				outputMessage.append(GettingDressedUtils.buildCommandOutput(commandList, new ColdWeatherDressing(), null));
			} else {
				outputMessage = new StringBuilder(failMessage);
			}
			
		} catch (DressingCommandException dce){
			outputMessage = new StringBuilder(failMessage);
		}
		
		System.out.println(outputMessage.toString());
	}

}
