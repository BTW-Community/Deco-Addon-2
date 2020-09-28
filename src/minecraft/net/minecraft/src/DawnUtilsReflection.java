package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DawnUtilsReflection {
	private static boolean isObfuscated = false;
	
	private static Map<String, String> blockMap = new HashMap<String, String>();
	private static Map<String, String> itemMap = new HashMap<String, String>();

	/**
	 * Replaces a reference to a vanilla block
	 */
	public static void replaceVanillaBlock(String blockName, Block oldBlock, Block newBlock) {
		try {
			String name;

			/*if (isObfuscated())
				name = getBlockLookup(blockName);
			else*/
				name = blockName;

			Field block = (Block.class.getDeclaredField(name));
			block.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(block, block.getModifiers() & ~Modifier.FINAL);
			
			block.set(newBlock, newBlock);
			block.setAccessible(false);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			/*if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				replaceVanillaBlock(blockName, oldBlock, newBlock);
			}*/
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Replaces a reference to a vanilla item
	 */
	public static void replaceVanillaItem(String itemName, Item oldItem, Item newItem) {
		try {
			String name;

			if (isObfuscated())
				name = getItemLookup(itemName);
			else
				name = itemName;

			Field item = (Item.class.getDeclaredField(name));
			item.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField( "modifiers" );
			modifiersField.setAccessible( true );
			modifiersField.setInt( item, item.getModifiers() & ~Modifier.FINAL );
			
			item.set(newItem, newItem);
			item.setAccessible(false);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				replaceVanillaItem(itemName, oldItem, newItem);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Replaces the entity mapping from a class to a string, used for spawning mobs and for loading saves.
	 * Safer than EntityList.ReplaceExistingMapping() because this version will not crash if the game tries to create an entity with the old class.
	 */
	public static void replaceEntityMappingWithAllowanceForOldClass(Class oldClass, Class newClass, String name) {
		EntityList.ReplaceExistingMapping(newClass, name);
		Field classToIdMappingField;
		Field classToStringMappingField;
		
		try {
			if (isObfuscated()) {
				classToIdMappingField = EntityList.class.getDeclaredField("e");
				classToStringMappingField = EntityList.class.getDeclaredField("c");
			}
			else {
				classToIdMappingField = EntityList.class.getDeclaredField("classToIDMapping");
				classToStringMappingField = EntityList.class.getDeclaredField("classToStringMapping");
			}

			classToIdMappingField.setAccessible(true);
			classToStringMappingField.setAccessible(true);
			
			Map classToIDMapping = (Map) classToIdMappingField.get(null);
			Map classToStringMapping = (Map) classToStringMappingField.get(null);
			classToIDMapping.put(oldClass, EntityList.getEntityID((Entity)newClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {null})));
			classToStringMapping.put(oldClass, name);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				replaceEntityMappingWithAllowanceForOldClass(oldClass, newClass, name);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void registerBlockObfuscationMappping(String blockName, String obfuscatedName) {
		blockMap.put(blockName, obfuscatedName);
	}
	
	public static void registerItemObfuscationMappping(String itemName, String obfuscatedName) {
		itemMap.put(itemName, obfuscatedName);
	}
	
	public static String getBlockLookup(String blockName) {
		return blockMap.get(blockName);
	}
	
	public static String getItemLookup(String itemName) {
		return itemMap.get(itemName);
	}

	public static boolean isObfuscated() {
		return isObfuscated;
	}

	public static void setObfuscated(boolean isObfuscated) {
		DawnUtilsReflection.isObfuscated = isObfuscated;
	}
	
	//Client only
	public static void ReplaceEntityRenderMapping(Class entity, Render newRender) {
		try {
			Field rendererMapField;

			if (isObfuscated()) {
				rendererMapField = RenderManager.class.getDeclaredField("q");
			}
			else {
				rendererMapField = RenderManager.class.getDeclaredField("entityRenderMap");
			}

			rendererMapField.setAccessible(true);
			Map specialRendererMap = (Map)rendererMapField.get(RenderManager.instance);
			specialRendererMap.put(entity, newRender);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				ReplaceEntityRenderMapping(entity, newRender);
			}
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void AddCustomTileEntityRenderer(Class tileEntityClass, TileEntitySpecialRenderer customRenderer) {
		TileEntityRenderer renderer = TileEntityRenderer.instance;

		try {
			Field specialRendererMapField;

			if (isObfuscated()) {
				specialRendererMapField = renderer.getClass().getDeclaredField("j");
			}
			else {
				specialRendererMapField = renderer.getClass().getDeclaredField("specialRendererMap");
			}

			specialRendererMapField.setAccessible(true);
			Map specialRendererMap = (Map)specialRendererMapField.get(renderer);
			specialRendererMap.put(tileEntityClass, customRenderer);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				AddCustomTileEntityRenderer(tileEntityClass, customRenderer);
			}
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
