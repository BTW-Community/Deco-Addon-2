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

import net.minecraft.client.Minecraft;
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

	private static Minecraft mc;

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
		super("Deco Addon", "3.0.7", "Deco");
	}
	
	public static DecoManager getInstance() {
		if (instance == null) {
			instance = new DecoManager();
		}
		
		return instance;
	}

	@Override
	public void PreInitialize() {
		//Client only
		mc = Minecraft.getMinecraft();
		
		this.registerProperty("DisableHardcoreBouncing", "True", "Set the following to false to disable placing blocks while jumping");
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage("Deco Addon Initializing...");

		decoDefs = DecoDefs.instance;
		decoRecipes = DecoRecipes.instance;

		//Client only
		addAllSoundsToPool();
		
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

	//CLIENT ONLY
	public static void installResource(String filename) {
		if (newSoundsInstalled) {
			File soundFile = new File(mc.mcDataDir, "resources/sound3/deco/" + filename + ".ogg");

			if (soundFile.exists())
				mc.installResource("sound3/deco/" + filename + ".ogg", soundFile);
			else {
				newSoundsInstalled = false;
				FCAddOnHandler.LogMessage("[Warning] Sound loading failed for: " + filename + ", falling back to vanilla sounds");
			}
		}
	}

	private void addAllSoundsToPool() {
		DecoManager.installResource("dig/lantern1");
		DecoManager.installResource("dig/lantern2");
		DecoManager.installResource("dig/lantern3");
		DecoManager.installResource("dig/lantern4");
		DecoManager.installResource("dig/lantern5");
		DecoManager.installResource("dig/lantern6");
		DecoManager.installResource("step/lantern1");
		DecoManager.installResource("step/lantern2");
		DecoManager.installResource("step/lantern3");
		DecoManager.installResource("step/lantern4");
		DecoManager.installResource("step/lantern5");
		DecoManager.installResource("step/lantern6");

		DecoManager.installResource("dig/chain1");
		DecoManager.installResource("dig/chain2");
		DecoManager.installResource("dig/chain3");
		DecoManager.installResource("dig/chain4");
		DecoManager.installResource("step/chain1");
		DecoManager.installResource("step/chain2");
		DecoManager.installResource("step/chain3");
		DecoManager.installResource("step/chain4");

		DecoManager.installResource("dig/netherrack1");
		DecoManager.installResource("dig/netherrack2");
		DecoManager.installResource("dig/netherrack3");
		DecoManager.installResource("dig/netherrack4");
		DecoManager.installResource("dig/netherrack5");
		DecoManager.installResource("dig/netherrack6");
		DecoManager.installResource("step/netherrack1");
		DecoManager.installResource("step/netherrack2");
		DecoManager.installResource("step/netherrack3");
		DecoManager.installResource("step/netherrack4");
		DecoManager.installResource("step/netherrack5");
		DecoManager.installResource("step/netherrack6");

		DecoManager.installResource("dig/netherbrick1");
		DecoManager.installResource("dig/netherbrick2");
		DecoManager.installResource("dig/netherbrick3");
		DecoManager.installResource("dig/netherbrick4");
		DecoManager.installResource("dig/netherbrick5");
		DecoManager.installResource("dig/netherbrick6");
		DecoManager.installResource("step/netherbrick1");
		DecoManager.installResource("step/netherbrick2");
		DecoManager.installResource("step/netherbrick3");
		DecoManager.installResource("step/netherbrick4");
		DecoManager.installResource("step/netherbrick5");
		DecoManager.installResource("step/netherbrick6");

		DecoManager.installResource("dig/bone1");
		DecoManager.installResource("dig/bone2");
		DecoManager.installResource("dig/bone3");
		DecoManager.installResource("dig/bone4");
		DecoManager.installResource("dig/bone5");
		DecoManager.installResource("step/bone1");
		DecoManager.installResource("step/bone2");
		DecoManager.installResource("step/bone3");
		DecoManager.installResource("step/bone4");
		DecoManager.installResource("step/bone5");

		DecoManager.installResource("dig/soulsand1");
		DecoManager.installResource("dig/soulsand2");
		DecoManager.installResource("dig/soulsand3");
		DecoManager.installResource("dig/soulsand4");
		DecoManager.installResource("dig/soulsand5");
		DecoManager.installResource("dig/soulsand6");
		DecoManager.installResource("dig/soulsand7");
		DecoManager.installResource("dig/soulsand8");
		DecoManager.installResource("dig/soulsand9");
		DecoManager.installResource("step/soulsand1");
		DecoManager.installResource("step/soulsand2");
		DecoManager.installResource("step/soulsand3");
		DecoManager.installResource("step/soulsand4");
		DecoManager.installResource("step/soulsand5");

		DecoManager.installResource("dig/soulsteel1");
		DecoManager.installResource("dig/soulsteel2");
		DecoManager.installResource("dig/soulsteel3");
		DecoManager.installResource("dig/soulsteel4");
		DecoManager.installResource("step/soulsteel1");
		DecoManager.installResource("step/soulsteel2");
		DecoManager.installResource("step/soulsteel3");
		DecoManager.installResource("step/soulsteel4");
		DecoManager.installResource("step/soulsteel5");
		DecoManager.installResource("step/soulsteel6");

		DecoManager.installResource("step/vine1");
		DecoManager.installResource("step/vine2");
		DecoManager.installResource("step/vine3");
		DecoManager.installResource("step/vine4");
		DecoManager.installResource("step/vine5");

		DecoManager.installResource("dig/bloodLog1");
		DecoManager.installResource("dig/bloodLog2");
		DecoManager.installResource("dig/bloodLog3");
		DecoManager.installResource("dig/bloodLog4");
		DecoManager.installResource("dig/bloodLog5");
		DecoManager.installResource("dig/bloodLog6");
		DecoManager.installResource("step/bloodLog1");
		DecoManager.installResource("step/bloodLog2");
		DecoManager.installResource("step/bloodLog3");
		DecoManager.installResource("step/bloodLog4");
		DecoManager.installResource("step/bloodLog5");
		DecoManager.installResource("step/bloodLog6");

		DecoManager.installResource("dig/groth1");
		DecoManager.installResource("dig/groth2");
		DecoManager.installResource("dig/groth3");
		DecoManager.installResource("dig/groth4");
		DecoManager.installResource("dig/groth5");
		DecoManager.installResource("dig/groth6");
		DecoManager.installResource("step/groth1");
		DecoManager.installResource("step/groth2");
		DecoManager.installResource("step/groth3");
		DecoManager.installResource("step/groth4");
		DecoManager.installResource("step/groth5");

		DecoManager.installResource("random/doorClose1");
		DecoManager.installResource("random/doorClose2");
		DecoManager.installResource("random/doorClose3");
		DecoManager.installResource("random/doorClose4");
		DecoManager.installResource("random/doorClose5");
		DecoManager.installResource("random/doorClose6");
		DecoManager.installResource("random/doorOpen1");
		DecoManager.installResource("random/doorOpen2");
		DecoManager.installResource("random/doorOpen3");
		DecoManager.installResource("random/doorOpen4");
		/*
		DecoManager.installResource("random/doorIronClose1");
		DecoManager.installResource("random/doorIronClose2");
		DecoManager.installResource("random/doorIronClose3");
		DecoManager.installResource("random/doorIronClose4");
		DecoManager.installResource("random/doorIronOpen1");
		DecoManager.installResource("random/doorIronOpen2");
		DecoManager.installResource("random/doorIronOpen3");
		DecoManager.installResource("random/doorIronOpen4");
		*/
		DecoManager.installResource("random/trapdoorClose1");
		DecoManager.installResource("random/trapdoorClose2");
		DecoManager.installResource("random/trapdoorClose3");
		DecoManager.installResource("random/trapdoorOpen1");
		DecoManager.installResource("random/trapdoorOpen2");
		DecoManager.installResource("random/trapdoorOpen3");
		DecoManager.installResource("random/trapdoorOpen4");
		/*
		DecoManager.installResource("random/trapdoorIronClose1");
		DecoManager.installResource("random/trapdoorIronClose2");
		DecoManager.installResource("random/trapdoorIronClose3");
		DecoManager.installResource("random/trapdoorIronClose4");
		DecoManager.installResource("random/trapdoorIronOpen1");
		DecoManager.installResource("random/trapdoorIronOpen2");
		DecoManager.installResource("random/trapdoorIronOpen3");
		DecoManager.installResource("random/trapdoorIronOpen4");
		*/
		DecoManager.installResource("random/gateClose1");
		DecoManager.installResource("random/gateClose2");
		DecoManager.installResource("random/gateOpen1");
		DecoManager.installResource("random/gateOpen2");

		DecoManager.installResource("random/chestClose1");
		DecoManager.installResource("random/chestClose2");
		DecoManager.installResource("random/chestClose3");
		DecoManager.installResource("random/chestOpen1");

		DecoManager.installResource("random/strip1");
		DecoManager.installResource("random/strip2");
		DecoManager.installResource("random/strip3");
		DecoManager.installResource("random/strip4");

		DecoManager.installResource("random/pumpkinCarve1");
		DecoManager.installResource("random/pumpkinCarve2");

		DecoManager.installResource("random/till1");
		DecoManager.installResource("random/till2");
		DecoManager.installResource("random/till3");
		DecoManager.installResource("random/till4");

		DecoManager.installResource("mob/squid/say1");
		DecoManager.installResource("mob/squid/say2");
		DecoManager.installResource("mob/squid/say3");
		DecoManager.installResource("mob/squid/say4");
		DecoManager.installResource("mob/squid/say5");
		DecoManager.installResource("mob/squid/death1");
		DecoManager.installResource("mob/squid/death2");
		DecoManager.installResource("mob/squid/death3");
		DecoManager.installResource("mob/squid/hurt1");
		DecoManager.installResource("mob/squid/hurt2");
		DecoManager.installResource("mob/squid/hurt3");

		DecoManager.installResource("misc/itemFrame/addItem1");
		DecoManager.installResource("misc/itemFrame/addItem2");
		DecoManager.installResource("misc/itemFrame/addItem3");
		DecoManager.installResource("misc/itemFrame/addItem4");
		DecoManager.installResource("misc/itemFrame/break1");
		DecoManager.installResource("misc/itemFrame/break2");
		DecoManager.installResource("misc/itemFrame/break3");
		DecoManager.installResource("misc/itemFrame/place1");
		DecoManager.installResource("misc/itemFrame/place2");
		DecoManager.installResource("misc/itemFrame/place3");
		DecoManager.installResource("misc/itemFrame/place4");
		DecoManager.installResource("misc/itemFrame/removeItem1");
		DecoManager.installResource("misc/itemFrame/removeItem2");
		DecoManager.installResource("misc/itemFrame/removeItem3");
		DecoManager.installResource("misc/itemFrame/removeItem4");
		DecoManager.installResource("misc/itemFrame/rotateItem1");
		DecoManager.installResource("misc/itemFrame/rotateItem2");
		DecoManager.installResource("misc/itemFrame/rotateItem3");
		DecoManager.installResource("misc/itemFrame/rotateItem4");

		DecoManager.installResource("misc/painting/break1");
		DecoManager.installResource("misc/painting/break2");
		DecoManager.installResource("misc/painting/break3");
		DecoManager.installResource("misc/painting/place1");
		DecoManager.installResource("misc/painting/place2");
		DecoManager.installResource("misc/painting/place3");
		DecoManager.installResource("misc/painting/place4");

		if (DecoManager.getNewSoundsInstalled()) {
			FCAddOnHandler.LogMessage("Deco Addon Sounds Successfully Loaded");
		}
	}

	public boolean ClientPlayCustomAuxFX(Minecraft mc, World world, EntityPlayer player, int id, int x, int y, int z, int data)
	{
		Random rand = world.rand;
		int blockID;
		int blockMeta;

		switch (id) {
		case decoCustomBlockBreakAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;

		if (blockID > 0)
		{
			Block block = Block.blocksList[blockID];
			if (!DecoUtilsSound.playCustomSoundForBlockBreak(mc, world, x, y, z, block, blockMeta)) {
				mc.sndManager.playSound(block.stepSound.getBreakSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			}
		}

		mc.effectRenderer.addBlockDestroyEffects(x, y, z, data & 4095, data >> 12 & 255);
		return true;
		case decoCustomBlockConvertAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;

			if (blockID > 0)
			{
				Block block = Block.blocksList[blockID];
				if (!DecoUtilsSound.playCustomSoundForBlockConvert(mc, world, x, y, z, block, blockMeta)) {
					mc.sndManager.playSound(block.stepSound.getBreakSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				}
			}

			mc.effectRenderer.addBlockDestroyEffects(x, y, z, data & 4095, data >> 12 & 255);
			return true;
		case decoCustomBlockPlaceAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;

			if (blockID > 0)
			{
				Block block = Block.blocksList[blockID];
				mc.sndManager.playSound(block.stepSound.getPlaceSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			}

			return true;
		case decoShaftRippedOffLogAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.strip", 3.0F, 0.25F + rand.nextFloat() * 0.25F, "mob.zombie.woodbreak", 0.25F, 1.0F + rand.nextFloat() * 0.25F);
			return true;
		case decoDoorWoodOpenAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoDoorWoodCloseAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoDoorIronOpenAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoDoorIronCloseAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoTrapdoorOpenAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoTrapdoorCloseAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoTrapdoorIronOpenAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorIronOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoTrapdoorIronCloseAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorIronClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoChestOpenAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.chestOpen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F, "random.chestopen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoChestCloseAuxFXID:
			DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.chestClose", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F, "random.chestclosed", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		case decoPaintingPlaceAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.painting.place", 1, 1);
			return true;
		case decoPaintingBreakAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.painting.break", 1, 1);
			return true;
		case decoItemFramePlaceAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.place", 1, 1);
			return true;
		case decoItemFrameBreakAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.break", 1, 1);
			return true;
		case decoItemFrameAddItemAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.addItem", 1, 1);
			return true;
		case decoItemFrameRotateItemAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.rotateItem", 1, 1);
			return true;
		case decoItemFrameRemoveItemAuxFXID:
			DecoUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.removeItem", 1, 1);
			return true;
		default:
			return false;
		}
	}

	public EntityFX spawnCustomParticle(Minecraft mc, World world, String particleType, double x, double y, double z, double velX, double velY, double velZ) {
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
			int particleSetting = mc.gameSettings.particleSetting;
			EntityFX fx = null;

			if (particleSetting == 1 && world.rand.nextInt(3) == 0) {
				particleSetting = 2;
			}

			double distX = mc.renderViewEntity.posX - x;
			double distY = mc.renderViewEntity.posY - y;
			double distZ = mc.renderViewEntity.posZ - z;
			double maxParticleDist = 16.0D;

			//Only renders particles within maxParticleDist
			if (distX * distX + distY * distY + distZ * distZ > maxParticleDist * maxParticleDist) {
				return null;
			}
			//Reduces or eliminates particles based on game setting
			else if (particleSetting > 1) {
				return null;
			}
			else {
				if (particleType.equals(decoParticleSmokeColumn)) {

				}

				return fx;
			}
		}

		return null;
	}
}
