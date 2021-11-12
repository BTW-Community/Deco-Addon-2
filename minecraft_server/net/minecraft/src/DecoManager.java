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

public class DecoManager extends FCAddOn
{
	private static DecoManager instance;
	public static DecoDefs decoDefs;
	public static DecoRecipes decoRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();

	private static boolean newSoundsInstalled = true;
	
	public static boolean disableHardcoreBouncing = true;

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

	private DecoManager() {
		super("Deco Addon", "3.0.4", "Deco");
	}
	
	public static DecoManager getInstance() {
		if (instance == null) {
			instance = new DecoManager();
		}
		
		return instance;
	}

	@Override
	public void PreInitialize() {
		this.registerProperty("DisableHardcoreBouncing", "True", "Set the following to false to disable placing blocks while jumping");
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage("Deco Addon Initializing...");

		decoDefs = DecoDefs.instance;
		decoRecipes = DecoRecipes.instance;
		
		decoDefs.addDefinitions();
		decoRecipes.addAllDecoRecipes();

		FCAddOnHandler.LogMessage("Deco Addon Initialization Complete.");
	}

	@Override
	public String GetLanguageFilePrefix() {
		return "Deco";
	}
	
	@Override
	public void handleConfigProperties(Map<String, String> propertyValues) {
		this.disableHardcoreBouncing = Boolean.parseBoolean(propertyValues.get("DisableHardcoreBouncing"));
	}
	
	public static void register(Block target) {
		Item.itemsList[target.blockID] = new DecoItemBlockWithCustomSound(target.blockID - 256);
	}

	public static void register(Block target, String[] names) {
		Item.itemsList[target.blockID] = new DecoItemBlockMulti(target, names);
	}

	public static boolean getNewSoundsInstalled() {
		return newSoundsInstalled;
	}
}