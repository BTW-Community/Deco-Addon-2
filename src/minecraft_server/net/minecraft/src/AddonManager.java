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
	public static final String addonVersion = "2.11d";
	
	public static AddonDefs addonDefs;
	public static AddonRecipes addonRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	private static ArrayList<String> loadedAddons = new ArrayList<String>();

	private static boolean isObfuscated = false;
	private static boolean newSoundsInstalled = true;
	
	public static final String addonCustomPacketChannelVersionCheck = "Deco|VC";

	public static final int addonCustomBlockBreakAuxFXID = 3000;
	public static final int addonCustomBlockConvertAuxFXID = 3001;
	public static final int addonShaftRippedOffLogAuxFXID = 3100;

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

            Packet250CustomPayload var4 = new Packet250CustomPayload("Deco|VC", byteArrayOutput.toByteArray());
            FCUtilsWorld.SendPacketToPlayer(var0, var4);
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

	public static void ReplaceSpawnableEntity(String name, Class oldEntity, Class newEntity) {
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

				EntityList.ReplaceExistingMapping(newEntity, name);
			}
		} catch (NoSuchFieldException e) {
			if (isObfuscated()) {
				e.printStackTrace();
			}
			else {
				setObfuscated(true);
				ReplaceSpawnableEntity(name, oldEntity, newEntity);
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
		Item.itemsList[target.blockID] = new ItemBlock(target.blockID - 256);
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
}
