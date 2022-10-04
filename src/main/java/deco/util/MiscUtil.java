package deco.util;

import java.util.HashMap;
import java.util.Map;

public class MiscUtil {
	private static Map<Integer, String> formatMap = new HashMap<Integer, String>();
	
	public static String getFormatCodeFromDyeMetadata(int meta) {
		String format = "§";
		meta %= 16;
		
		return format + formatMap.get(meta);
	}
}
