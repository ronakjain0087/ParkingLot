/**
 * 
 */
package com.gojek.parkinglot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rkala
 *
 */
public class CommandInputHolder {
private static volatile Map<String, Integer> commandsParameterMap = new HashMap<String, Integer>();
	
	static
	{
		commandsParameterMap.put(CommonConstants.CREATE_PARKING_LOT, 1);
		commandsParameterMap.put(CommonConstants.PARK_VEHICLE, 2);
		commandsParameterMap.put(CommonConstants.LEAVE, 1);
		commandsParameterMap.put(CommonConstants.STATUS, 0);
		commandsParameterMap.put(CommonConstants.REG_NUMBER_BY_COLOR, 1);
		commandsParameterMap.put(CommonConstants.SLOT_NUMBER_BY_COLOR, 1);
		commandsParameterMap.put(CommonConstants.SLOTS_NUMBER_BY_REG_NUMBER, 1);
	}
	
	
	public static Map<String, Integer> getCommandsParameterMap()
	{
		return commandsParameterMap;
	}
	
	
	public static void addCommand(String command, int parameterCount)
	{
		commandsParameterMap.put(command, parameterCount);
	}
}
