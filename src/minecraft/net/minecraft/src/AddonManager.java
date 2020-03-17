package net.minecraft.src;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.MinecraftServer;

public class AddonManager extends FCAddOn
{
	public static final boolean DEBUG_ADDON_LOAD = false;
	public static AddonDefs addonDefs;
	public static AddonRecipes addonRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	private static ArrayList<String> loadedAddons = new ArrayList<String>();
	
	private static boolean isObfuscated = false;

	@Override
	public void PreInitialize() {
		AddonUtilsObfuscationMap.initialize();
	}
	
	@Override
	public void Initialize()
	{
		System.out.println("[INFO] AddonManager: Initialize");
		
		addonDefs = AddonDefs.instance;
		addonRecipes = AddonRecipes.instance;
		
		addonDefs.addDefinitions();
		addonRecipes.addAllAddonRecipes();
	}
	
	public boolean getObfuscation() {
		return isObfuscated;
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
			
			if (isObfuscated)
				name = AddonUtilsObfuscationMap.getBlockLookup(blockName);
			else
				name = blockName;
			
			Field block = (AddonDefs.terracotta.getClass().getDeclaredField(name));
			block.setAccessible(true);
			
			Field modifiersField = Field.class.getDeclaredField( "modifiers" );
            modifiersField.setAccessible( true );
            modifiersField.setInt( block, block.getModifiers() & ~Modifier.FINAL );
			
			//Block.blocksList[oldBlock.blockID] = null;
			block.set(newBlock, newBlock);
			block.setAccessible(false);
		} catch (NoSuchFieldException e) {
			if (isObfuscated) {
				e.printStackTrace();
			}
			else {
				isObfuscated = true;
				SetVanillaBlockFinal(blockName, oldBlock, newBlock);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Used to replace item ids
	public static int ReplaceItemID(Item item) {
		Item.itemsList[item.itemID] = null; 
		return item.itemID;
	}
	
	//Does really hacky stuff using reflection to replace final references to vanilla blocks
	public static void SetVanillaItemFinal(String name, Item oldItem, Item newItem) {
		try {
			Field item = (AddonDefs.glassChunk.getClass().getDeclaredField(name));
			item.setAccessible(true);
			
			Field modifiersField = Field.class.getDeclaredField( "modifiers" );
            modifiersField.setAccessible( true );
            modifiersField.setInt( item, item.getModifiers() & ~Modifier.FINAL );
			
			//Block.blocksList[oldBlock.blockID] = null;
			item.set(newItem, newItem);
			item.setAccessible(false);
		} catch (NoSuchFieldException e) {
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

	private static Map<String, Object> GetConfigInfo(String addonName)
	{
		File configFile = new File(new File("."),addonName+"Config.txt");
		Map<String,Object>results = new HashMap<String,Object>();
		try
		{
			BufferedReader reader=new BufferedReader(new FileReader(configFile));
			String line="";
			while((line=reader.readLine())!=null)
			{
				String[]splitLine=line.split("=");
				for (int i=0;i<splitLine.length;++i)
				splitLine[i]=splitLine[i].trim();
				results.put(splitLine[0],(splitLine[1]=="1"||splitLine[1]=="0")?splitLine[1]=="1":splitLine[1]);
			}
			reader.close();
		}
		catch(Exception x)
		{
			x.printStackTrace();
		}
		return results;
	}
	private static void WriteToConfigFile(String addonName, Map<String, Object> contents)
	{
		File configFile = new File(new File("."),addonName+"Config.txt");
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(configFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		for(String currentKey:contents.keySet())
		{
			Object value=contents.get(currentKey);
			writer.println(currentKey+"="+((value instanceof Boolean)?((Boolean)value==true?"1":"0"):value.toString()));
		}
		writer.close();
	}
	public static Map<String, Object> GetConfigInfo(String addonName, Map<String, Object> defaultConfig)
	{
		File configFile = new File(new File("."),addonName+"Config.txt");
		if(!configFile.exists())
		{
			WriteToConfigFile(addonName, defaultConfig);
			return defaultConfig;
		}
		Map<String,Object>currentConfig=GetConfigInfo(addonName),newConfig=defaultConfig;
		boolean NeedsRewrite = false;
		for(String currentKey:defaultConfig.keySet())
		{
			if(currentConfig.containsKey(currentKey))
			newConfig.put(currentKey, currentConfig.get(currentKey));
			else
			NeedsRewrite=true;
		}
		if(NeedsRewrite)
		WriteToConfigFile(addonName, currentConfig);
		return newConfig;
	}
	public static boolean require(String name)
	{
		//The original way I wrote this method was stupid and did not make sense.
		return isAddonLoaded(name);
	}
	public static boolean isAddonLoaded(String name)
	{
		return loadedAddons.contains(name);
	}
	public static boolean loadAddon(String name)
	{
		try
		{
			Class.forName("Addon_"+name).newInstance();
			System.out.println("[INFO] Loaded addon: " + name);
			loadedAddons.add(name);
			return true;
		}
		catch (ClassNotFoundException ex1)
		{
			try
			{
				Class.forName(AddonManager.class.getPackage().getName()+".Addon_"+name).newInstance();
				System.out.println("[INFO] Loaded addon: " + name);
				loadedAddons.add(name);
				return true;
			}
			catch (ClassNotFoundException ex2)
			{
				System.out.println("[INFO] Addon not found: " + name);
			}
			catch (Exception ex3)
			{
				System.out.println("[WARN] Problem loading addon: " + name);
				if(DEBUG_ADDON_LOAD) ex3.printStackTrace();
			}
			return false;
		}
		catch (Exception ex4)
		{
			System.out.println("[WARN] Problem loading addon: " + name);
			if(DEBUG_ADDON_LOAD) ex4.printStackTrace();
			return false;
		}
	}


	public static void serverCustomPacketReceived(MinecraftServer ms, EntityPlayerMP epmp, Packet250CustomPayload packet) {
		try {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

			if (packet.channel.equals("DECO|OLDGLASS")) {
				int size = dis.readInt();
				int damage = dis.readInt();

				ItemStack stack = new ItemStack(30008+256,size,damage);
				epmp.inventory.setInventorySlotContents(epmp.inventory.currentItem, stack);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
