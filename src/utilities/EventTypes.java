package utilities;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EventTypes {
	FUNCTIONAL(new Short("1")),
	PROCESS(new Short("2")),
	INFRAESTRUCTURE(new Short("3"));
	
	private static final Map<Short,EventTypes> lookup = new HashMap<Short,EventTypes>();

	static {
	    for(EventTypes s : EnumSet.allOf(EventTypes.class))
	         lookup.put(s.getCode(), s);
	}
	
	private short code;
	
	private EventTypes(short code) {
	    this.code = code;
	}
	
	public short getCode() { return code; }
	
	public static EventTypes get(short code) { 
	    return lookup.get(code); 
	}

}
