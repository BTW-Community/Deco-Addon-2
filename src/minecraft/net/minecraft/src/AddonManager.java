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

	private static Minecraft mc;
	
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
		mc = Minecraft.getMinecraft();
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

	public static boolean ServerCustomPacketReceived(MinecraftServer mcServer, Packet250CustomPayload packet)
	{
        if (AddonManager.addonCustomPacketChannelVersionCheckAck.equals(packet.channel)) {
			mc.thePlayer.addChatMessage("\u00a7f" + "Deco Addon version check successful.");
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

	public boolean ClientCustomPacketReceived(Minecraft mc, Packet250CustomPayload packet)
	{
		try
		{
			WorldClient world = mc.theWorld;
			DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int packetType;
			int var9;

			if (packet.channel.equals(addonCustomPacketChannelVersionCheck))
			{
				String var33 = dataStream.readUTF();

				if (!var33.equals(addonVersion))
				{
					mc.thePlayer.addChatMessage("\u00a74" + "WARNING: Deco Addon version mismatch detected! Local Version: " + this.addonVersion + " Server Version: " + var33);
				}
				
				ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
				DataOutputStream dataOutput = new DataOutputStream(byteArrayOutput);

				try
				{
					dataOutput.writeUTF(addonVersion);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				Packet250CustomPayload ackPacket = new Packet250CustomPayload(addonCustomPacketChannelVersionCheckAck, byteArrayOutput.toByteArray());
				mc.getNetHandler().addToSendQueue(ackPacket);

				return true;
			}
		}
		catch (IOException var23)
		{
			var23.printStackTrace();
		}

		return false;
	}

	//Used to modify existing client side packet250 behavior
	public static boolean interceptCustomClientPacket(Minecraft mc, Packet250CustomPayload packet) {
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
					var5 = new AddonEntityCanvas(world, var8, var9, var10, var11, var12);
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
		case addonCustomBlockBreakAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;

			if (blockID > 0)
			{
				Block block = Block.blocksList[blockID];
				if (!AddonUtilsSound.playCustomSoundForBlockBreak(mc, world, x, y, z, block, blockMeta)) {
					mc.sndManager.playSound(block.stepSound.getBreakSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				}
			}

			mc.effectRenderer.addBlockDestroyEffects(x, y, z, data & 4095, data >> 12 & 255);
			return true;
		case addonCustomBlockConvertAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;

			if (blockID > 0)
			{
				Block block = Block.blocksList[blockID];
				if (!AddonUtilsSound.playCustomSoundForBlockConvert(mc, world, x, y, z, block, blockMeta)) {
					mc.sndManager.playSound(block.stepSound.getBreakSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				}
			}

			mc.effectRenderer.addBlockDestroyEffects(x, y, z, data & 4095, data >> 12 & 255);
			return true;
		case addonCustomBlockPlaceAuxFXID:
			blockID = data & 4095;
			blockMeta = data >> 12 & 255;
			
			if (blockID > 0)
			{
				Block block = Block.blocksList[blockID];
				mc.sndManager.playSound(block.stepSound.getPlaceSound(), (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			}
			
			return true;
        case addonShaftRippedOffLogAuxFXID:
            AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.strip", 3.0F, 0.25F + rand.nextFloat() * 0.25F, "mob.zombie.woodbreak", 0.25F, 1.0F + rand.nextFloat() * 0.25F);
            return true;
        case addonDoorWoodOpenAuxFXID:
            AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonDoorWoodCloseAuxFXID:
            AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonDoorIronOpenAuxFXID:
            AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonDoorIronCloseAuxFXID:
            AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonTrapdoorOpenAuxFXID:
        	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonTrapdoorCloseAuxFXID:
        	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonChestOpenAuxFXID:
        	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.chestOpen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F, "random.chestopen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonChestCloseAuxFXID:
        	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.chestClose", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F, "random.chestclosed", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        	return true;
        case addonPaintingPlaceAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.painting.place", 1, 1);
        	return true;
        case addonPaintingBreakAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.painting.break", 1, 1);
        	return true;
        case addonItemFramePlaceAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.place", 1, 1);
        	return true;
        case addonItemFrameBreakAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.break", 1, 1);
        	return true;
        case addonItemFrameAddItemAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.addItem", 1, 1);
        	return true;
        case addonItemFrameRotateItemAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.rotateItem", 1, 1);
        	return true;
        case addonItemFrameRemoveItemAuxFXID:
        	AddonUtilsSound.playSoundWithNullFallback(world, x, y, z, "deco.misc.itemFrame.removeItem", 1, 1);
        	return true;
		default:
			return false;
		}
	}
	
	public static EntityFX spawnCustomParticle(Minecraft mc, World world, String particleType, double x, double y, double z, double velX, double velY, double velZ) {
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
            	if (particleType.equals(addonParticleSmokeColumn)) {
            		
            	}
            	
            	return fx;
            }
        }
        
		return null;
	}

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
