package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AddonUtilsObfuscationMap {
	private static Map<String, String> blockMap = new HashMap<String, String>();
	private static Map<String, String> itemMap = new HashMap<String, String>();
	
	public static void initialize() {
		blockMap.put("wood", "N");
		blockMap.put("trapdoor", "bo");
		blockMap.put("portal", "bi");
	}
	
	public static String getBlockLookup(String blockName) {
		return blockMap.get(blockName);
	}
	
	public static String getItemLookup(String itemName) {
		return itemMap.get(itemName);
	}
	
	public static void listAllBlockFields() {
		Block block = new Block(4095, Material.rock);
		Field[] fields = block.getClass().getDeclaredFields();
		
		for (Field f : fields) {
			System.out.println(f);
		}
	}
	
	public static void listAllItemFields() {
		
	}
}
