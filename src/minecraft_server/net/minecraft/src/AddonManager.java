package net.minecraft.src;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.server.MinecraftServer;

public class AddonManager extends FCAddOn
{
	public static final String addonVersion = "2.12";

	public static AddonDefs addonDefs;
	public static AddonRecipes addonRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	private static ArrayList<String> loadedAddons = new ArrayList<String>();

	private static boolean isObfuscated = false;
	private static boolean newSoundsInstalled = true;
	
	private static NetServerHandler netServerHandler;
	
	private static boolean awaitingLoginAck = false;
	private static int ticksSinceAckRequested = 0;
	private static final int maxTicksForAckWait = 20;

	public static final String addonCustomPacketChannelVersionCheck = "Deco|VC";
	public static final String addonCustomPacketChannelVersionCheckAck = "Deco|VC_Ack";

	public static final int addonCustomBlockBreakAuxFXID = 3000;
	public static final int addonCustomBlockConvertAuxFXID = 3001;
	public static final int addonCustomBlockPlaceAuxFXID = 3002;
	
	public static final int addonShaftRippedOffLogAuxFXID = 3100;
	public static final int addonDoorWoodOpenAuxFXID = 3101;
	public static final int addonDoorWoodCloseAuxFXID = 3102;
	public static final int addonDoorIronOpenAuxFXID = 3103;
	public static final int addonDoorIronCloseAuxFXID = 3104;
	public static final int addonTrapdoorOpenAuxFXID = 3105;
	public static final int addonTrapdoorCloseAuxFXID = 3106;
	//Reserved for iron trapdoor
	public static final int addonChestOpenAuxFXID = 3109;
	public static final int addonChestCloseAuxFXID = 3110;
	public static final int addonPaintingPlaceAuxFXID = 3111;
	public static final int addonPaintingBreakAuxFXID = 3112;
	public static final int addonItemFramePlaceAuxFXID = 3113;
	public static final int addonItemFrameBreakAuxFXID = 3114;
	public static final int addonItemFrameAddItemAuxFXID = 3115;
	public static final int addonItemFrameRotateItemAuxFXID = 3116;
	public static final int addonItemFrameRemoveItemAuxFXID = 3117;
	
	public static final String addonParticleSmokeColumn = "signalSmoke";

	@Override
	public void PreInitialize() {
		AddonUtilsObfuscationMap.initialize();
		//AddonUtilsObfuscationMap.listAllBlockFields();
		//AddonUtilsObfuscationMap.listAllItemFields();
	}

	@Override
	public void Initialize()
	{
		FCAddOnHandler.LogMessage("Deco Addon Initializing...");

		addonDefs = AddonDefs.instance;
		addonRecipes = AddonRecipes.instance;

		addonDefs.addDefinitions();
		addonRecipes.addAllAddonRecipes();

		FCAddOnHandler.LogMessage("Deco Addon Initialization Complete.");
	}

	@Override
	public void PostInitialize() {

	}

	public static void ServerPlayerConnectionInitialized(NetServerHandler var0, EntityPlayerMP var1) {
		netServerHandler = var0;
		
		if (!MinecraftServer.getServer().isSinglePlayer())
		{
			FCUtilsWorld.SendPacketToPlayer(var0, new Packet3Chat("\u00a7f" + "Deco V" + addonVersion));

			ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
			DataOutputStream dataOutput = new DataOutputStream(byteArrayOutput);

			try
			{
				dataOutput.writeUTF(addonVersion);
			}
			catch (Exception var9)
			{
				var9.printStackTrace();
			}

			Packet250CustomPayload var4 = new Packet250CustomPayload(addonCustomPacketChannelVersionCheck, byteArrayOutput.toByteArray());
			FCUtilsWorld.SendPacketToPlayer(var0, var4);
			awaitingLoginAck = true;
		}
		else {
			FCUtilsWorld.SendPacketToPlayer(var0, new Packet3Chat("\u00a7f" + "Deco V" + addonVersion));
		}
	}

	public boolean getObfuscation() {
		return isObfuscated();
	}

	private static boolean Create_HasCall=false;
	public void OnLanguageLoaded(StringTranslate Language)
	{
		int Index = 0;
		while(Index<Names.size())
		{
			if(NameTargets.get(Index) instanceof Item)
			{
				Language.GetTranslateTable().put(((Item)NameTargets.get(Index)).getUnlocalizedName() + ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof Block)
			{
				Language.GetTranslateTable().put(((Block)NameTargets.get(Index)).getUnlocalizedName() + ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof ItemStack)
			{
				Language.GetTranslateTable().put(Item.itemsList[((ItemStack)NameTargets.get(Index)).itemID].getUnlocalizedName(((ItemStack)NameTargets.get(Index)))+ ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof String)
			{
				Language.GetTranslateTable().put(NameTargets.get(Index).toString(), Names.get(Index));
			}
			else System.out.println("You can't name that kind of object!");
			Index++;
		}
	}
	public static void Name(Object target, String name)
	{
		Names.add(name);
		NameTargets.add(target);
	}
	public static void NameSubBlocks(Block sidingAndCorner, Block mouldingAndDecorative, String name)
	{
		Name(sidingAndCorner, "Siding And Corner");
		Name(mouldingAndDecorative, "Moulding And Decorative");

		String tag = sidingAndCorner.getUnlocalizedName();
		Name(tag + ".siding" + ".name", name + " Siding");
		Name(tag + ".corner" + ".name", name + " Corner");
		Name(tag + ".bench" + ".name", name + " Bench");
		Name(tag + ".fence" + ".name", name + " Fence");

		tag = mouldingAndDecorative.getUnlocalizedName();
		Name(tag + ".moulding" + ".name", name + " Moulding");
		Name(tag + ".column" + ".name", name + " Column");
		Name(tag + ".pedestal" + ".name", name + " Pedestal");
		Name(tag + ".table" + ".name", name + " Table");
	}
	public static void NameSubBlocks_Wall(Block sidingAndCorner, Block mouldingAndDecorative, String name)
	{
		Name(sidingAndCorner, "Siding And Corner");
		Name(mouldingAndDecorative, "Moulding And Decorative");

		String tag = sidingAndCorner.getUnlocalizedName();
		Name(tag + ".siding" + ".name", name + " Siding");
		Name(tag + ".corner" + ".name", name + " Corner");
		Name(tag + ".bench" + ".name", name + " Bench");
		Name(tag + ".fence" + ".name", name + " Wall");

		tag = mouldingAndDecorative.getUnlocalizedName();
		Name(tag + ".moulding" + ".name", name + " Moulding");
		Name(tag + ".column" + ".name", name + " Column");
		Name(tag + ".pedestal" + ".name", name + " Pedestal");
		Name(tag + ".table" + ".name", name + " Table");
	}

	//Use to replace block ids
	public static int ReplaceBlockID(Block block)
	{
		Block.blocksList[block.blockID] = null;
		return block.blockID;
	}

	//Does really hacky stuff using reflection to replace final references to vanilla blocks
	public static void SetVanillaBlockFinal(String blockName, Block oldBlock, Block newBlock) {
		try {
			String name;

			if (isObfuscated())
				name = AddonUtilsObfuscationMap.getBlockLookup(blockName);
			else
				name = blockName;

			Field block = (AddonDefs.terracotta.getClass().getDeclaredField(name));
			block.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(block, block.getModifiers() & ~Modifier.FINAL);

			//Block.blocksList[oldBlock.blockID] = null;
			block.set(newBlock, newBlock);
			block.setAccessible(false);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				SetVanillaBlockFinal(blockName, oldBlock, newBlock);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	//Does really hacky stuff using reflection to replace final references to vanilla blocks
	public static void SetVanillaItemFinal(String itemName, Item oldItem, Item newItem) {
		try {
			String name;

			if (isObfuscated())
				name = AddonUtilsObfuscationMap.getItemLookup(itemName);
			else
				name = itemName;

			Field item = (AddonDefs.glassChunk.getClass().getDeclaredField(name));
			item.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField( "modifiers" );
			modifiersField.setAccessible( true );
			modifiersField.setInt( item, item.getModifiers() & ~Modifier.FINAL );

			//Block.blocksList[oldBlock.blockID] = null;
			item.set(newItem, newItem);
			item.setAccessible(false);
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				SetVanillaItemFinal(itemName, oldItem, newItem);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void ReplaceSpawnableEntity(String name, Class oldEntity, Class newEntity, boolean allowOldClass) {
		try {
			for (BiomeGenBase b : BiomeGenBase.biomeList) {
				if (b == null)
					continue;

				Field creatureList;
				Field monsterList;
				Field waterCreatureList;
				Field caveCreatureList;

				if (isObfuscated()) {
					if (b.getClass().getSuperclass().equals(BiomeGenBase.class)) {
						creatureList = b.getClass().getSuperclass().getDeclaredField("K");
						monsterList = b.getClass().getSuperclass().getDeclaredField("J");
						waterCreatureList = b.getClass().getSuperclass().getDeclaredField("L");
						caveCreatureList = b.getClass().getSuperclass().getDeclaredField("M");
					}
					else {
						creatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("K");
						monsterList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("J");
						waterCreatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("L");
						caveCreatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("M");
					}
				}
				else {
					if (b.getClass().getSuperclass().equals(BiomeGenBase.class)) {
						creatureList = b.getClass().getSuperclass().getDeclaredField("spawnableCreatureList");
						monsterList = b.getClass().getSuperclass().getDeclaredField("spawnableMonsterList");
						waterCreatureList = b.getClass().getSuperclass().getDeclaredField("spawnableWaterCreatureList");
						caveCreatureList = b.getClass().getSuperclass().getDeclaredField("spawnableCaveCreatureList");
					}
					else {
						creatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("spawnableCreatureList");
						monsterList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("spawnableMonsterList");
						waterCreatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("spawnableWaterCreatureList");
						caveCreatureList = b.getClass().getSuperclass().getSuperclass().getDeclaredField("spawnableCaveCreatureList");
					}
				}

				creatureList.setAccessible(true);
				monsterList.setAccessible(true);
				waterCreatureList.setAccessible(true);
				caveCreatureList.setAccessible(true);

				ArrayList<SpawnListEntry> creature = (ArrayList<SpawnListEntry>)creatureList.get(b);
				ArrayList<SpawnListEntry> monster = (ArrayList<SpawnListEntry>)monsterList.get(b);
				ArrayList<SpawnListEntry> water = (ArrayList<SpawnListEntry>)waterCreatureList.get(b);
				ArrayList<SpawnListEntry> cave = (ArrayList<SpawnListEntry>)caveCreatureList.get(b);

				for (SpawnListEntry s : creature) {
					if (s.entityClass == oldEntity) {
						s.entityClass = newEntity;
					}
				}

				for (SpawnListEntry s : monster) {
					if (s.entityClass == oldEntity) {
						s.entityClass = newEntity;
					}
				}

				for (SpawnListEntry s : water) {
					if (s.entityClass == oldEntity) {
						s.entityClass = newEntity;
					}
				}

				for (SpawnListEntry s : cave) {
					if (s.entityClass == oldEntity) {
						s.entityClass = newEntity;
					}
				}

				if (allowOldClass)
					replaceEntityMappingWithAllowanceForOldClass(oldEntity, newEntity, name);
				else
					EntityList.ReplaceExistingMapping(newEntity, name);
			}
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				ReplaceSpawnableEntity(name, oldEntity, newEntity, allowOldClass);
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

	public static void MakeStorage(Item subItem, Block container)
	{
		FCRecipes.AddRecipe(new ItemStack(container), new Object[]{"XXX","XXX","XXX",'X',subItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(subItem, 9), new ItemStack[]{new ItemStack(container)});
	}
	public static void MakeStorage(Item subItem, Item container)
	{
		FCRecipes.AddRecipe(new ItemStack(container), new Object[]{"XXX","XXX","XXX",'X',subItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(subItem, 9), new ItemStack[]{new ItemStack(container)});
	}
	public static void MakeStorage(ItemStack subItem, ItemStack container)
	{
		FCRecipes.AddRecipe(container, new Object[]{"XXX","XXX","XXX",'X',subItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(subItem.itemID, 9,subItem.getItemDamage()), new ItemStack[]{container});
	}
	public static void Register(Block target)
	{
		Item.itemsList[target.blockID] = new AddonItemBlockWithCustomSound(target.blockID - 256);
	}
	public static void Register(Block target, String name)
	{
		Register(target);
		Name(target, name);
	}
	public static void Register(Block target, String[] names, String preTitle, String[] titles, String postTitle)
	{
		Item.itemsList[target.blockID] = new AddonItemMultiBlock(target, names, preTitle, titles, postTitle);
	}

	public static void Register(Block target, String[] names, String[] titles)
	{
		Item.itemsList[target.blockID] = new AddonItemMultiBlock(target, names, "", titles, "");
	}

	public static void Register(Block target, String[] names, String[] titles, String postTitle)
	{
		Item.itemsList[target.blockID] = new AddonItemMultiBlock(target, names, "", titles, postTitle);
	}

	public static void Register(Block target, String[] names, String preTitle, String[] titles)
	{
		Item.itemsList[target.blockID] = new AddonItemMultiBlock(target, names, preTitle, titles, "");
	}

	public static boolean getNewSoundsInstalled() {
		return newSoundsInstalled;
	}

	public static boolean isObfuscated() {
		return isObfuscated;
	}

	public static void setObfuscated(boolean isObfuscated) {
		AddonManager.isObfuscated = isObfuscated;
	}

	public static boolean ServerCustomPacketReceived(MinecraftServer mcServer, Packet250CustomPayload packet, NetServerHandler serverHandler)
	{
        if (AddonManager.addonCustomPacketChannelVersionCheckAck.equals(packet.channel)) {
        	FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + "Deco Addon version check successful."));
        	awaitingLoginAck = false;
        	ticksSinceAckRequested = 0;
        }
        
		return false;
	}
	
	public static boolean getAwaitingLoginAck() {
		return awaitingLoginAck;
	}
	
	public static void incrementTicksSinceAckRequested() {
		ticksSinceAckRequested++;
	}
	
	public static void handleAckCheck() {
		if (ticksSinceAckRequested > maxTicksForAckWait) {
			FCUtilsWorld.SendPacketToPlayer(netServerHandler, new Packet3Chat("\u00a74" + "WARNING: Client Deco Addon not installed, or version 2.11 or earlier is installed on client."));
			awaitingLoginAck = false;
			ticksSinceAckRequested = 0;
		}
	}
}
