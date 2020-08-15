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
		blockMap.put("fence", "bd");
		blockMap.put("netherFence", "bF");
		blockMap.put("cobblestoneWall", "cf");
		blockMap.put("oreNetherQuartz", "ct");
		blockMap.put("sandStone", "U");
		blockMap.put("stairsSandStone", "bU");
		blockMap.put("planks", "B");
		blockMap.put("stairsWoodOak", "ax");
		blockMap.put("stairsWoodSpruce", "ca");
		blockMap.put("stairsWoodBirch", "cb");
		blockMap.put("stairsWoodJungle", "cc");
		blockMap.put("obsidian", "at");
		blockMap.put("signPost", "aH");
		blockMap.put("signWall", "aM");
		blockMap.put("thinGlass", "bu");
		blockMap.put("fenceIron", "bt");
		blockMap.put("fire", "av");
		blockMap.put("stoneButton", "aV");
		blockMap.put("woodenButton", "cj");
		blockMap.put("fenceGate", "bz");
		blockMap.put("cocoaPlant", "bT");
		blockMap.put("doorWood", "aI");
		blockMap.put("waterMoving", "E");
		blockMap.put("waterStill", "F");
		blockMap.put("lavaMoving", "G");
		blockMap.put("lavaStill", "H");
		blockMap.put("doorIron", "aP");
		blockMap.put("grass", "y");
		blockMap.put("dirt", "z");
		blockMap.put("mycelium", "bC");
		blockMap.put("leaves", "O");
		blockMap.put("vine", "by");
		blockMap.put("tallGrass", "ab");
		
		itemMap.put("enderPearl", "bo");
		itemMap.put("sign", "av");
		itemMap.put("shears", "bf");
		itemMap.put("flowerPot", "bK");;
		itemMap.put("doorWood", "aw");
		itemMap.put("coal", "n");
		itemMap.put("itemFrame", "bJ");
		itemMap.put("painting", "at");
		itemMap.put("doorIron", "aC");
	}
	
	public static String getBlockLookup(String blockName) {
		return blockMap.get(blockName);
	}
	
	public static String getItemLookup(String itemName) {
		return itemMap.get(itemName);
	}
}
