package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class AddonUtilsObfuscationMap {
	private static Map<String, String> blockMap = new HashMap<String, String>();
	private static Map<String, String> itemMap = new HashMap<String, String>();
	
	public static void initialize() {
		blockMap.put("wood", "field_71951_J");
		blockMap.put("trapdoor", "field_72005_bk");
	}
	
	public static String getBlockLookup(String blockName) {
		return blockMap.get(blockName);
	}
	
	public static String getItemLookup(String itemName) {
		return itemMap.get(itemName);
	}
}
