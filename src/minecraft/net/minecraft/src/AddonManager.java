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
	public static final String addonVersion = "2.11d";

	public static AddonDefs addonDefs;
	public static AddonRecipes addonRecipes;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	private static ArrayList<String> loadedAddons = new ArrayList<String>();

	private static boolean isObfuscated = false;
	private static boolean newSoundsInstalled = true;

	private static Minecraft mc;

	public static final String addonCustomPacketChannelVersionCheck = "Deco|VC";

	public static final int addonCustomBlockBreakAuxFXID = 3000;
	public static final int addonCustomBlockConvertAuxFXID = 3001;
	public static final int addonShaftRippedOffLogAuxFXID = 3100;

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

				if (var33.equals(addonVersion))
				{
					mc.thePlayer.addChatMessage("\u00a7f" + "Deco Addon version check successful.");
				}
				else
				{
					mc.thePlayer.addChatMessage("\u00a74" + "WARNING: Deco Addon version mismatch detected! Local Version: " + this.addonVersion + " Server Version: " + var33);
				}

				return true;
			}
		}
		catch (IOException var23)
		{
			var23.printStackTrace();
		}

		return false;
	}

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
        case addonShaftRippedOffLogAuxFXID:
        	if (getNewSoundsInstalled())
        		world.playSound(x, y, z, "deco.random.strip", 1, 0.5F + rand.nextFloat() * 0.25F);
        	else
        		world.playSound(x, y, z, "mob.zombie.woodbreak", 0.25F, 1.0F + rand.nextFloat() * 0.25F);
            return true;
		default:
			return false;
		}
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
