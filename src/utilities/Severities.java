package utilities;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Severities {
	CRITIC(new Short("1")),
	WARNING(new Short("2")),
	NOTIFICATION(new Short("3"));
	
	private static final Map<Short,Severities> lookup = new HashMap<Short,Severities>();

	static {
	    for(Severities s : EnumSet.allOf(Severities.class))
	         lookup.put(s.getCode(), s);
	}
	
	private short code;
	
	private Severities(short code) {
	    this.code = code;
	}
	
	public short getCode() { return code; }
	
	public static Severities get(short code) { 
	    return lookup.get(code); 
	}

}
