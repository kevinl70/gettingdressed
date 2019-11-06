package com.utils;

import java.util.ArrayList;
import java.util.List;

import com.dressingcmds.DressingCmdStrategy;
import com.enums.ColdResponse;
import com.enums.TempatureType;
import com.exceptions.DressingCommandException;

/**
 * @author Kevin
 *
 */
public class GettingDressedUtils {

	public static List<String> validateParams(String[] args) throws DressingCommandException {
		List<String> commandList = null; 
		String tempCmd = null;
		
		if (args.length > 0) {
			commandList = new ArrayList<String>();
			for (int i = 0; i < args.length; i++){
				if (i == 0 && (!args[i].equalsIgnoreCase(TempatureType.HOT.toString()) || 
						!args[i].equalsIgnoreCase(TempatureType.COLD.toString()))){
					throw new DressingCommandException("missing temperature type to get dressed.");
				}
				commandList.add(args[i]);
			
			}
		} else {
			throw new DressingCommandException("missing commands to get dressed.");
		}
		return commandList;
	}

	
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
			if (i==0 && !command.equalsIgnoreCase(Integer.toString(pajamaCmd))){
				throw new DressingCommandException("fail");
			}
			if (processedCmds.contains(command)){
				cmdOutput.append(failMsg);
				throw new DressingCommandException(cmdOutput.toString());
			}
			//Check to see if command is either headwear or jacket and make sure the shirt was put on first.
			if ((command.equalsIgnoreCase("2") || command.equalsIgnoreCase("5")) && 
					!GettingDressedUtils.validateShirtIsOn(tempType, processedCmds)){
				cmdOutput.append(failMsg);
				throw new DressingCommandException(cmdOutput.toString());
			}
			//Check to see if socks and pants were put on before 
			if (command.equalsIgnoreCase(Integer.toString(footwearCmd)) && 
					!GettingDressedUtils.canPutFootwearOn(tempType, processedCmds)){
				cmdOutput.append(failMsg);
				throw new DressingCommandException(cmdOutput.toString());
			}
			
			
			String response = dressingStragey.getDressed(cmdPrefix+command);
			if (response.equalsIgnoreCase(failMsg)){
				cmdOutput.append(response);
				throw new DressingCommandException(cmdOutput.toString());
			}
			
			processedCmds.add(command);
			cmdOutput.append(delimeter);
			command = null;
		}
				
		return cmdOutput.toString();
	}
	
	public static boolean validateShirtIsOn(final TempatureType tempType, final List<String> processedCmds) {
		Boolean validCmd = Boolean.TRUE;
		
		if (tempType == TempatureType.COLD && processedCmds.contains("4")){
			validCmd = Boolean.FALSE;
		}
		
		return validCmd.booleanValue();
	}
	
	
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