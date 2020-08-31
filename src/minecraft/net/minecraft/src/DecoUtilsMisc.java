package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class DecoUtilsMisc {
	private static Map<Integer, String> formatMap = new HashMap<Integer, String>();
	
	static {
		formatMap.put(0, "0");
		formatMap.put(1, "4");
		formatMap.put(2, "2");
		formatMap.put(3, "4");
		formatMap.put(4, "1");
		formatMap.put(5, "5");
		formatMap.put(6, "3");
		formatMap.put(7, "7");
		formatMap.put(8, "8");
		formatMap.put(9, "c");
		formatMap.put(10, "a");
		formatMap.put(11, "e");
		formatMap.put(12, "9");
		formatMap.put(13, "d");
		formatMap.put(14, "6");
		formatMap.put(15, "f");
	}
	
	public static String getFormatCodeFromDyeMetadata(int meta) {
		String format = "§";
		meta %= 16;

		return format + formatMap.get(meta);
	}
}
