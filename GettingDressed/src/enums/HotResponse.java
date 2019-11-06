package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin L
 * Enum class to hold all of the responses for hot weather.
 */

public enum HotResponse {

	ONE("sandals"),
	TWO("sunglasses"),
	THREE("fail"),
	FOUR("shirt"),
	FIVE("fail"),
	SIX("shorts"),
	SEVEN("leaving house"),
	EIGHT("Removing PJs");
	
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