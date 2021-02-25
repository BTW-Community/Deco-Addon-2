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

public class DecoManager extends AddonExt
{
	public static DecoDefs decoDefs;
	public static DecoRecipes decoRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();

	private static boolean newSoundsInstalled = true;

	public static final int decoCustomBlockBreakAuxFXID = 3000;
	public static final int decoCustomBlockConvertAuxFXID = 3001;
	public static final int decoCustomBlockPlaceAuxFXID = 3002;

	public static final int decoShaftRippedOffLogAuxFXID = 3100;
	public static final int decoDoorWoodOpenAuxFXID = 3101;
	public static final int decoDoorWoodCloseAuxFXID = 3102;
	public static final int decoDoorIronOpenAuxFXID = 3103;
	public static final int decoDoorIronCloseAuxFXID = 3104;
	public static final int decoTrapdoorOpenAuxFXID = 3105;
	public static final int decoTrapdoorCloseAuxFXID = 3106;
	public static final int decoTrapdoorIronOpenAuxFXID = 3107;
	public static final int decoTrapdoorIronCloseAuxFXID = 3108;
	public static final int decoChestOpenAuxFXID = 3109;
	public static final int decoChestCloseAuxFXID = 3110;
	public static final int decoPaintingPlaceAuxFXID = 3111;
	public static final int decoPaintingBreakAuxFXID = 3112;
	public static final int decoItemFramePlaceAuxFXID = 3113;
	public static final int decoItemFrameBreakAuxFXID = 3114;
	public static final int decoItemFrameAddItemAuxFXID = 3115;
	public static final int decoItemFrameRotateItemAuxFXID = 3116;
	public static final int decoItemFrameRemoveItemAuxFXID = 3117;

	public static final String decoParticleSmokeColumn = "signalSmoke";

	public static final String decoPacketChannelRender = "Deco|Render";

	public DecoManager() {
		super("Deco Addon", "2.13.3", "Deco");
	}

	@Override
	public void PreInitialize() {
		
	}

	@Override
	public void Initialize()
	{
		FCAddOnHandler.LogMessage("Deco Addon Initializing...");

		decoDefs = DecoDefs.instance;
		decoRecipes = DecoRecipes.instance;

		decoDefs.addDefinitions();
		decoRecipes.addAllDecoRecipes();

		FCAddOnHandler.LogMessage("Deco Addon Initialization Complete.");
	}

	public String GetLanguageFilePrefix()
	{
		return "Deco";
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
		Item.itemsList[target.blockID] = new DecoItemBlockWithCustomSound(target.blockID - 256);
	}
	public static void Register(Block target, String name)
	{
		Register(target);
		Name(target, name);
	}
	public static void Register(Block target, String[] names, String preTitle, String[] titles, String postTitle)
	{
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static void Register(Block target, String[] names)
	{
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static void Register(Block target, String[] names, String[] titles)
	{
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static void Register(Block target, String[] names, String[] titles, String postTitle)
	{
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static void Register(Block target, String[] names, String preTitle, String[] titles)
	{
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static boolean getNewSoundsInstalled() {
		return newSoundsInstalled;
	}
}
