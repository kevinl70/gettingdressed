package com.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin L
 * Enum class to hold all of the responses for hot weather.
 */

public enum HotResponse {

	_1("sandals"),
	_2("sunglasses"),
	_3("fail"),
	_4("shirt"),
	_5("fail"),
	_6("shorts"),
	_7("leaving house"),
	_8("Removing PJs");
	
	private final String value;
	
	private static Map<String,HotResponse> hotEnums;
	
	private HotResponse(final String value){
		this.value = value;
		HotResponse.addEnumToEnumMap(this);
	}

	public String getValue(){
		return value;
	}
	
	private static void addEnumToEnumMap(final HotResponse type){
		if (hotEnums == null){
			hotEnums = new HashMap<String, HotResponse>();
		}
		hotEnums.put(type.getValue(), type);
	}
	
	/* Given a string value, return the corresponding enum. */
	public static HotResponse lookupByValue(final String strValue) {
		// Return the value seachedfrom the HashMap
		return hotEnums.get(strValue);
	}	
	
}