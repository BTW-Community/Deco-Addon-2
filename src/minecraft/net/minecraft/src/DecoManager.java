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

public class DecoManager extends DawnAddon
{
	public static DecoDefs decoDefs;
	public static DecoRecipes decoRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();

	private static boolean newSoundsInstalled = true;

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

	public DecoManager() {
		super("Deco Addon", "2.13", "Deco");
	}

	@Override
	public void PreInitialize() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void Initialize()
	{
		FCAddOnHandler.LogMessage("Deco Addon Initializing...");

		decoDefs = DecoDefs.instance;
		decoRecipes = DecoRecipes.instance;

		decoDefs.registerObfuscationMappings();
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
		Item.itemsList[target.blockID] = new DecoItemMultiBlock(target, names, preTitle, titles, postTitle);
	}

	public static void Register(Block target, String[] names, String[] titles)
	{
		Item.itemsList[target.blockID] = new DecoItemMultiBlock(target, names, "", titles, "");
	}

	public static void Register(Block target, String[] names, String[] titles, String postTitle)
	{
		Item.itemsList[target.blockID] = new DecoItemMultiBlock(target, names, "", titles, postTitle);
	}

	public static void Register(Block target, String[] names, String preTitle, String[] titles)
	{
		Item.itemsList[target.blockID] = new DecoItemMultiBlock(target, names, preTitle, titles, "");
	}

	public static boolean getNewSoundsInstalled() {
		return newSoundsInstalled;
	}
	
	public static World getWorldFromChunkCache(ChunkCache chunkCache) {
		Field worldField;
		
		try {
			if (DawnUtilsReflection.isObfuscated()) {
				worldField = chunkCache.getClass().getDeclaredField("e");
			}
			else {
				worldField = chunkCache.getClass().getDeclaredField("worldObj");
			}

			worldField.setAccessible(true);
			
			return (World) worldField.get(chunkCache);
		} catch (NoSuchFieldException e) {
			if (DawnUtilsReflection.isObfuscated()) {
				e.printStackTrace();
			}
			else {
				DawnUtilsReflection.setObfuscated(true);
				getWorldFromChunkCache(chunkCache);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
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

	//Used to modify existing client side packet250 behavior
	public boolean interceptCustomClientPacket(Minecraft mc, Packet250CustomPayload packet) {
		try
		{
			WorldClient world = mc.theWorld;
			DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int packetType;
			int var9;

			if (packet.channel.equals(FCBetterThanWolves.fcCustomPacketChannelSpawnCustomEntity))
			{
				Object var5 = null;
				packetType = dataStream.readInt();
				int var7 = dataStream.readInt();

				if (packetType == 0)
				{
					int var8 = dataStream.readInt();
					var9 = dataStream.readInt();
					int var10 = dataStream.readInt();
					int var11 = dataStream.readInt();
					int var12 = dataStream.readInt();
					var5 = new DecoEntityCanvas(world, var8, var9, var10, var11, var12);
				}

				if (var5 != null)
				{
					world.addEntityToWorld(var7, (Entity)var5);
					return true;
				}
			}
		}
		catch (IOException var23)
		{
			var23.printStackTrace();
		}

		return false;
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
