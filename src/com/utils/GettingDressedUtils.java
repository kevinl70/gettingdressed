package com.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dressingcmds.DressingCmdStrategy;
import com.enums.TempatureType;
import com.exceptions.DressingCommandException;

/**
 * @author Kevin
 * Util Class for getting dressed.
 *
 */
public class GettingDressedUtils {

	/**
	 * Method will validate the arguments passed in from main.
	 * @param args A String array representing the arguments passed in to main 
	 * @return A List of String objects representing the commands from the arguments
	 * @throws DressingCommandException The custom exception thrown if there is an exception 
	 */
	public static List<String> validateParams(String[] args) throws DressingCommandException {
		List<String> commandList = null; 
		String tempCmd = null;
		
		if (args.length > 0) {
			commandList = new ArrayList<String>();
			for (int i = 0; i < args.length; i++){
				tempCmd = StringUtils.strip(args[i].trim(), ",");
				
				//Check to see if the first argument/cmd is the temp and check validity, either hot or cold.
				if (i == 0 && (StringUtils.isEmpty(tempCmd) || !GettingDressedUtils.isValidTempType(tempCmd))) {
					System.out.println("missing temperature type to get dressed.");
					throw new DressingCommandException("missing temperature type to get dressed.");
				}
				
				commandList.add(tempCmd);
				tempCmd = null;
			}
		} else {
			throw new DressingCommandException("missing commands to get dressed.");
		}
		return commandList;
	}

	/**
	 * Method validates if the the temp passed in is valid to be used.
	 * @param tempCmd A string representing the temp type.
	 * @return A boolean which witll denote if the temp type is valid or not.
	 */
	public static boolean isValidTempType(String tempCmd){
		Boolean tempTypeFlag = Boolean.FALSE;
		//Check to see if the string is empty.
		if (StringUtils.isNotEmpty(tempCmd)){
			if (tempCmd.equalsIgnoreCase(TempatureType.HOT.toString())){
				tempTypeFlag = Boolean.TRUE;
			} else if (tempCmd.equalsIgnoreCase(TempatureType.COLD.toString())){
				tempTypeFlag = Boolean.TRUE;
			}
		}
		
		return tempTypeFlag.booleanValue();
	}
	
	/**
	 * Method to build the output based on the commands provided.
	 * @param cmdList A List of Strings representing the commands to be processed.
	 * @param dressingStragey The interface for getting dressed. Via polymorphism the implementation will be passed in, 
	 * @param tempType The enum representing the temp types.
	 * @return A String which contains the output based on the commands.
	 * @throws DressingCommandException The custom exception thrown if there is an exception 
	 */
	public static String buildCommandOutput(final List<String> cmdList,  DressingCmdStrategy dressingStragey, final TempatureType tempType) 
			throws DressingCommandException {
		
		final int footwearCmd = 1;
		final int pajamaCmd = 8;
		final String failMsg = "fail";
		final char cmdPrefix = '_';
		final char delimeter = ',';
				
		StringBuilder cmdOutput = new StringBuilder();
		List <String> processedCmds = new ArrayList<String>();
		
		for (int i=0;i<cmdList.size();i++){
			String command = new String(cmdList.get(i)); 

			//Check to see if the pajama cmd is the first cmd in the list.
			if (i==0 && !command.equalsIgnoreCase(Integer.toString(pajamaCmd))){
				cmdOutput.append(failMsg);
				break;
			}
			
			//Check to see if the cmd was already issued.
			if (processedCmds.contains(command)){
				cmdOutput.append(failMsg);
				break;
			}
			
			//Check to see if command is either headwear or jacket and make sure the shirt was put on first.
			if ((command.equalsIgnoreCase("2") || command.equalsIgnoreCase("5")) && 
					!GettingDressedUtils.validateShirtIsOn(processedCmds)){
				cmdOutput.append(failMsg);
				break;
			}
			//Check to see if socks and pants were put on before 
			if (command.equalsIgnoreCase(Integer.toString(footwearCmd)) && 
					!GettingDressedUtils.canPutFootwearOn(tempType, processedCmds)){
				cmdOutput.append(failMsg);
				break;
			}
						
			String response = dressingStragey.getDressed(cmdPrefix+command);
			
			//This will check to see if we pulled a "fail" or null response back.
			if (response.equalsIgnoreCase(failMsg) || StringUtils.isEmpty(response)){
				cmdOutput.append(response);
				break;
			}
			
			cmdOutput.append(response);
			processedCmds.add(command);
			cmdOutput.append(delimeter);
			command = null;
		}

		//verify thw last cmd is leaving the house.
		if (GettingDressedUtils.isLastCmdNotValid(processedCmds)){ 
			cmdOutput.append(failMsg);
		}
		
		return cmdOutput.toString();
	}
	
	/**
	 * Method to validate if the shirt command has been processed and is on.
	 * @param tempType An enum of the temp types.
	 * @param processedCmds A List of Stirngs of the previously processed commands.
	 * @return A boolean value denoting if the shirt command has been processed, true or false.
	 */
	public static boolean validateShirtIsOn(final List<String> processedCmds) {
		Boolean validCmd = Boolean.FALSE;
		
		if (!processedCmds.isEmpty() && processedCmds.contains("4")){
			validCmd = Boolean.TRUE;
		}
		
		return validCmd.booleanValue();
	}
	
	/**
	 * Method to validate if the last command being processed is valid.  Should be the leaving house command or #7.
	 * @param processedCmds A List of Stirngs of the previously processed commands.
	 * @return A boolean value denoting if the last command is valid or not, true or false.
	 */
	public static boolean isLastCmdNotValid(List<String> processedCmds){
		Boolean validLastCmd = Boolean.TRUE;
		
		if (!processedCmds.isEmpty()){
			String lastCmd = processedCmds.get(processedCmds.size()-1);
			if (lastCmd.equalsIgnoreCase("7")) {
				validLastCmd = Boolean.FALSE;			
			}
		}
		
		return validLastCmd.booleanValue();
	}
	
	/**
	 * Method to determine if we can put our footwear on first.  Checks to make sure the temp is cold, 
	 * and our pants and socks are on.  Is called when we get the footwear cmd.
	 * @param tempType An enum of the temp types.
	 * @param processedCmds A List of Stirngs of the previously processed commands.
	 * @return A boolen representing if one can put on footwear and proceed. 
	 */
	public static boolean canPutFootwearOn(final TempatureType tempType, final List<String> processedCmds){
		Boolean putFootwearOn = Boolean.FALSE;
		
		//Check to make sure pants/shorts are on
		if (processedCmds.contains("6")) {
			putFootwearOn = Boolean.TRUE;
		} 
		
		//Check to make sure if it is cold, and socks are on.
		if (tempType == TempatureType.COLD && !processedCmds.contains("3")) {
			putFootwearOn = Boolean.FALSE;
		}
		
		return putFootwearOn.booleanValue();
	}
}