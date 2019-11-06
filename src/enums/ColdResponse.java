package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * Enum class to hold all of the commands and the responses needed for cold weather. 
 */

public enum ColdResponse{
	
	ONE("boots"),
	TWO("hat"),
	THREE("socks"),
	FOUR("shirt"),
	FIVE("jacket"),
	SIX("pants"),
	SEVEN("leaving house"),
	EIGHT("Removing PJs");

	private final String value;
	private static Map<String,ColdResponse> coldEnums;
	
	private ColdResponse(final String value){
		this.value = value;
		ColdResponse.addEnumToEnumMap(this);
	}

	public String getValue(){
		return value;
	}
	
	private static void addEnumToEnumMap(final ColdResponse type){
		if (coldEnums == null){
			coldEnums = new HashMap<String, ColdResponse>();
		}
		coldEnums.put(type.getValue(), type);
	}
	
	/* Given a string value, return the corresponding enum. */
	public static ColdResponse lookupByValue(final String strValue) {
		// Return the value seachedfrom the HashMap
		return coldEnums.get(strValue);
	}	
}