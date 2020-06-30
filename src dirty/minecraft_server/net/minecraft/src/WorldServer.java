package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.server.MinecraftServer;

public class WorldServer extends World
{
    private final MinecraftServer mcServer;
    private final EntityTracker theEntityTracker;
    private final FCChunkTracker m_chunkTracker;
    private Set field_73064_N;

    /** All work to do in future ticks. */
    private TreeSet pendingTickListEntries;
    public ChunkProviderServer theChunkProviderServer;

    /** Whether or not level saving is enabled */
    public boolean levelSaving;

    /** is false if there are no players */
    private boolean allPlayersSleeping;
    private int updateEntityTick = 0;
    private final Teleporter field_85177_Q;

    /**
     * Double buffer of ServerBlockEventList[] for holding pending BlockEventData's
     */
    private ServerBlockEventList[] blockEventCache = new ServerBlockEventList[] {new ServerBlockEventList((ServerBlockEvent)null), new ServerBlockEventList((ServerBlockEvent)null)};

    /**
     * The index into the blockEventCache; either 0, or 1, toggled in sendBlockEventPackets  where all BlockEvent are
     * applied locally and send to clients.
     */
    private int blockEventCacheIndex = 0;
    private static final WeightedRandomChestContent[] bonusChestContent = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.stick.itemID, 0, 1, 3, 10), new WeightedRandomChestContent(Block.planks.blockID, 0, 1, 3, 10), new WeightedRandomChestContent(Block.wood.blockID, 0, 1, 3, 10), new WeightedRandomChestContent(Item.axeStone.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.axeWood.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.pickaxeStone.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.pickaxeWood.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 2, 3, 5), new WeightedRandomChestContent(Item.bread.itemID, 0, 2, 3, 3)};
    private ArrayList field_94579_S = new ArrayList();

    /** An IntHashMap of entity IDs (integers) to their Entity objects. */
    private IntHashMap entityIdMap;
    private boolean m_bHasTicked = false;
    protected LinkedList m_chunksToCheckForUnloadList = new LinkedList();
    private long m_lNoPlayersOnServerTickCount = 0L;
    private final int m_iChunksAroundSpawnToCheckForUnload = 13;

    public WorldServer(MinecraftServer par1MinecraftServer, ISaveHandler par2ISaveHandler, String par3Str, int par4, WorldSettings par5WorldSettings, Profiler par6Profiler, ILogAgent par7ILogAgent)
    {
        super(par2ISaveHandler, par3Str, par5WorldSettings, WorldProvider.getProviderForDimension(par4), par6Profiler, par7ILogAgent);
        this.mcServer = par1MinecraftServer;
        this.saveHandler.LoadModSpecificData(this);
        this.theEntityTracker = new EntityTracker(this);
        this.m_chunkTracker = new FCChunkTracker(this, par1MinecraftServer.getConfigurationManager().getViewDistance());

        if (this.entityIdMap == null)
        {
            this.entityIdMap = new IntHashMap();
        }

        if (this.field_73064_N == null)
        {
            this.field_73064_N = new HashSet();
        }

        if (this.pendingTickListEntries == null)
        {
            this.pendingTickListEntries = new TreeSet();
        }

        this.field_85177_Q = new Teleporter(this);
        this.worldScoreboard = new ServerScoreboard(par1MinecraftServer);
        ScoreboardSaveData var8 = (ScoreboardSaveData)this.mapStorage.loadData(ScoreboardSaveData.class, "scoreboard");

        if (var8 == null)
        {
            var8 = new ScoreboardSaveData();
            this.mapStorage.setData("scoreboard", var8);
        }

        var8.func_96499_a(this.worldScoreboard);
        ((ServerScoreboard)this.worldScoreboard).func_96547_a(var8);
    }

    /**
     * Runs a single tick for the world
     */
    public void tick()
    {
        super.tick();

        if (this.getWorldInfo().isHardcoreModeEnabled() && this.difficultySetting < 3)
        {
            this.difficultySetting = 3;
        }
        else if (this.difficultySetting < 2)
        {
            this.difficultySetting = 2;
        }

        this.provider.worldChunkMgr.cleanupCache();

        if (this.areAllPlayersAsleep())
        {
            boolean var1 = false;

            if (this.spawnHostileMobs && this.difficultySetting >= 1)
            {
                ;
            }

            if (!var1)
            {
                long var2 = this.worldInfo.getWorldTime() + 24000L;
                this.worldInfo.setWorldTime(var2 - var2 % 24000L);
                this.wakeAllPlayers();
            }
        }

        this.theProfiler.startSection("mobSpawner");

        if (this.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
        {
            SpawnerAnimals.findChunksForSpawning(this, this.spawnHostileMobs, this.spawnPeacefulMobs, false);
        }

        this.theProfiler.endStartSection("chunkSource");
        this.chunkProvider.unloadQueuedChunks();
        int var4 = this.calculateSkylightSubtracted(1.0F);

        if (var4 != this.skylightSubtracted)
        {
            this.skylightSubtracted = var4;
        }

        this.worldInfo.incrementTotalWorldTime(this.worldInfo.getWorldTotalTime() + 1L);
        this.worldInfo.setWorldTime(this.worldInfo.getWorldTime() + 1L);
        this.theProfiler.endStartSection("tickPending");
        this.tickUpdates(false);
        this.theProfiler.endStartSection("tickTiles");
        this.tickBlocksAndAmbiance();
        this.theProfiler.endStartSection("chunkMap");
        this.m_chunkTracker.Update();
        this.theProfiler.endStartSection("village");
        this.villageCollectionObj.tick();
        this.theProfiler.endStartSection("portalForcer");
        this.field_85177_Q.removeStalePortalLocations(this.getTotalWorldTime());
        this.theProfiler.endSection();
        this.sendAndApplyBlockEvents();
    }

    /**
     * only spawns creatures allowed by the chunkProvider
     */
    public SpawnListEntry spawnRandomCreature(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        List var5 = this.getChunkProvider().getPossibleCreatures(par1EnumCreatureType, par2, par3, par4);
        return var5 != null && !var5.isEmpty() ? (SpawnListEntry)WeightedRandom.getRandomItem(this.rand, var5) : null;
    }

    /**
     * Updates the flag that indicates whether or not all players in the world are sleeping.
     */
    public void updateAllPlayersSleepingFlag()
    {
        this.allPlayersSleeping = !this.playerEntities.isEmpty();
        Iterator var1 = this.playerEntities.iterator();

        while (var1.hasNext())
        {
            EntityPlayer var2 = (EntityPlayer)var1.next();

            if (!var2.isPlayerSleeping())
            {
                this.allPlayersSleeping = false;
                break;
            }
        }
    }

    protected void wakeAllPlayers()
    {
        this.allPlayersSleeping = false;
        Iterator var1 = this.playerEntities.iterator();

        while (var1.hasNext())
        {
            EntityPlayer var2 = (EntityPlayer)var1.next();

            if (var2.isPlayerSleeping())
            {
                var2.wakeUpPlayer(false, false, true);
            }
        }

        this.resetRainAndThunder();
    }

    private void resetRainAndThunder()
    {
        this.worldInfo.setRainTime(0);
        this.worldInfo.setRaining(false);
        this.worldInfo.setThunderTime(0);
        this.worldInfo.setThundering(false);
    }

    public boolean areAllPlayersAsleep()
    {
        if (this.allPlayersSleeping && !this.isRemote)
        {
            Iterator var1 = this.playerEntities.iterator();

            while (var1.hasNext())
            {
                EntityPlayer var2 = (EntityPlayer)var1.next();

                if (!var2.isPlayerFullyAsleep())
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * plays random cave ambient sounds and runs updateTick on random blocks within each chunk in the vacinity of a
     * player
     */
    protected void tickBlocksAndAmbiance()
    {
        super.tickBlocksAndAmbiance();
        int var1 = 0;
        int var2 = 0;
        Iterator var3 = this.m_activeChunksCoordsList.iterator();

        while (var3.hasNext())
        {
            ChunkCoordIntPair var4 = (ChunkCoordIntPair)var3.next();
            int var5 = var4.chunkXPos * 16;
            int var6 = var4.chunkZPos * 16;
            this.theProfiler.startSection("getChunk");
            Chunk var7 = this.getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
            this.moodSoundAndLightCheck(var5, var6, var7);
            this.theProfiler.endStartSection("tickChunk");
            var7.updateSkylight();
            this.theProfiler.endStartSection("thunder");
            int var8;
            int var9;
            int var10;
            int var11;

            if (this.rand.nextInt(50000) == 0 && this.isRaining() && this.isThundering())
            {
                this.updateLCG = this.updateLCG * 3 + 1013904223;
                var8 = this.updateLCG >> 2;
                var9 = var5 + (var8 & 15);
                var10 = var6 + (var8 >> 8 & 15);
                var11 = this.getPrecipitationHeight(var9, var10);

                if (this.CanLightningStrikeAtPos(var9, var11, var10))
                {
                    FCUtilsBlockPos var12 = new FCUtilsBlockPos(var9, var11, var10);
                    this.AdjustLightningPosForSurroundings(var12);

                    if (this.IsBlockPosActive(var12.i, var12.j, var12.k))
                    {
                        this.addWeatherEffect(new FCEntityLightningBolt(this, (double)var12.i, (double)var12.j, (double)var12.k));
                    }
                }
            }

            this.theProfiler.endStartSection("iceandsnow");
            int var21;

            if (this.rand.nextInt(16) == 0)
            {
                this.updateLCG = this.updateLCG * 3 + 1013904223;
                var8 = this.updateLCG >> 2;
                var9 = var8 & 15;
                var10 = var8 >> 8 & 15;
                var11 = this.getPrecipitationHeight(var9 + var5, var10 + var6);

                if (this.isBlockFreezableNaturally(var9 + var5, var11 - 1, var10 + var6))
                {
                    this.setBlock(var9 + var5, var11 - 1, var10 + var6, Block.ice.blockID);
                }

                if (this.isRaining() && this.canSnowAt(var9 + var5, var11, var10 + var6))
                {
                    this.setBlock(var9 + var5, var11, var10 + var6, Block.snow.blockID);
                }
                else if (this.isRaining() && this.canSnowAt(var9 + var5, var11 + 1, var10 + var6))
                {
                    this.setBlock(var9 + var5, var11 + 1, var10 + var6, Block.snow.blockID);
                }

                if (this.isRaining())
                {
                    BiomeGenBase var13 = this.getBiomeGenForCoords(var9 + var5, var10 + var6);

                    if (var13.CanRainInBiome())
                    {
                        var21 = this.getBlockId(var9 + var5, var11 - 1, var10 + var6);

                        if (var21 != 0)
                        {
                            Block.blocksList[var21].fillWithRain(this, var9 + var5, var11 - 1, var10 + var6);
                        }
                    }
                }
            }

            this.theProfiler.endStartSection("tickTiles");
            ExtendedBlockStorage[] var22 = var7.getBlockStorageArray();
            var9 = var22.length;

            for (var10 = 0; var10 < var9; ++var10)
            {
                ExtendedBlockStorage var14 = var22[var10];

                if (var14 != null && var14.getNeedsRandomTick())
                {
                    for (int var15 = 0; var15 < 3; ++var15)
                    {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        var21 = this.updateLCG >> 2;
                        int var16 = var21 & 15;
                        int var17 = var21 >> 8 & 15;
                        int var18 = var21 >> 16 & 15;
                        int var19 = var14.getExtBlockID(var16, var18, var17);
                        ++var2;
                        Block var20 = Block.blocksList[var19];

                        if (var20 != null && var20.getTickRandomly())
                        {
                            ++var1;
                            var20.RandomUpdateTick(this, var16 + var5, var18 + var14.getYLocation(), var17 + var6, this.rand);
                        }
                    }
                }
            }

            this.theProfiler.endSection();
        }

        this.ModUpdateTick();
    }

    /**
     * Returns true if the given block will receive a scheduled tick in the future. Args: X, Y, Z, blockID
     */
    public boolean isBlockTickScheduled(int par1, int par2, int par3, int par4)
    {
        NextTickListEntry var5 = new NextTickListEntry(par1, par2, par3, par4);
        return this.field_94579_S.contains(var5);
    }

    /**
     * Used to schedule a call to the updateTick method on the specified block.
     */
    public void scheduleBlockUpdate(int par1, int par2, int par3, int par4, int par5)
    {
        this.func_82740_a(par1, par2, par3, par4, par5, 0);
    }

    public void func_82740_a(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        NextTickListEntry var7 = new NextTickListEntry(par1, par2, par3, par4);

        if (this.scheduledUpdatesAreImmediate && par4 > 0)
        {
            if (Block.blocksList[par4].func_82506_l())
            {
                if (this.IsBlockPosActive(var7.xCoord, var7.yCoord, var7.zCoord))
                {
                    int var8 = this.getBlockId(var7.xCoord, var7.yCoord, var7.zCoord);

                    if (var8 == var7.blockID && var8 > 0)
                    {
                        Block.blocksList[var8].updateTick(this, var7.xCoord, var7.yCoord, var7.zCoord, this.rand);
                    }
                }

                return;
            }

            par5 = 1;
        }

        if (this.IsBlockPosActive(par1, par2, par3))
        {
            if (par4 > 0)
            {
                var7.setScheduledTime((long)par5 + this.worldInfo.getWorldTotalTime());
                var7.func_82753_a(par6);
            }

            if (!this.field_73064_N.contains(var7))
            {
                this.field_73064_N.add(var7);
                this.pendingTickListEntries.add(var7);
            }
        }
    }

    /**
     * Schedules a block update from the saved information in a chunk. Called when the chunk is loaded.
     */
    public void scheduleBlockUpdateFromLoad(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        NextTickListEntry var7 = new NextTickListEntry(par1, par2, par3, par4);
        var7.func_82753_a(par6);

        if (par4 > 0)
        {
            var7.setScheduledTime((long)par5 + this.worldInfo.getWorldTotalTime());
        }

        if (!this.field_73064_N.contains(var7))
        {
            this.field_73064_N.add(var7);
            this.pendingTickListEntries.add(var7);
        }
    }

    /**
     * Updates (and cleans up) entities and tile entities
     */
    public void updateEntities()
    {
        super.updateEntities();
    }

    /**
     * Resets the updateEntityTick field to 0
     */
    public void resetUpdateEntityTick()
    {
        this.updateEntityTick = 0;
    }

    /**
     * Runs through the list of updates to run and ticks them
     */
    public boolean tickUpdates(boolean par1)
    {
        int var2 = this.pendingTickListEntries.size();

        if (var2 != this.field_73064_N.size())
        {
            throw new IllegalStateException("TickNextTick list out of synch");
        }
        else
        {
            if (var2 > 1000)
            {
                var2 = 1000;
            }

            this.theProfiler.startSection("cleaning");
            NextTickListEntry var3;

            for (int var4 = 0; var4 < var2; ++var4)
            {
                var3 = (NextTickListEntry)this.pendingTickListEntries.first();

                if (!par1 && var3.scheduledTime > this.worldInfo.getWorldTotalTime())
                {
                    break;
                }

                this.pendingTickListEntries.remove(var3);
                this.field_73064_N.remove(var3);
                this.field_94579_S.add(var3);
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("ticking");
            Iterator var13 = this.field_94579_S.iterator();

            while (var13.hasNext())
            {
                var3 = (NextTickListEntry)var13.next();
                var13.remove();

                if (this.IsBlockPosActive(var3.xCoord, var3.yCoord, var3.zCoord))
                {
                    int var5 = this.getBlockId(var3.xCoord, var3.yCoord, var3.zCoord);

                    if (var5 > 0 && Block.isAssociatedBlockID(var5, var3.blockID))
                    {
                        try
                        {
                            Block.blocksList[var5].updateTick(this, var3.xCoord, var3.yCoord, var3.zCoord, this.rand);
                        }
                        catch (Throwable var12)
                        {
                            CrashReport var7 = CrashReport.makeCrashReport(var12, "Exception while ticking a block");
                            CrashReportCategory var8 = var7.makeCategory("Block being ticked");
                            int var9;

                            try
                            {
                                var9 = this.getBlockMetadata(var3.xCoord, var3.yCoord, var3.zCoord);
                            }
                            catch (Throwable var11)
                            {
                                var9 = -1;
                            }

                            CrashReportCategory.func_85068_a(var8, var3.xCoord, var3.yCoord, var3.zCoord, var5, var9);
                            throw new ReportedException(var7);
                        }
                    }
                }
            }

            this.theProfiler.endSection();
            this.field_94579_S.clear();
            return !this.pendingTickListEntries.isEmpty();
        }
    }

    public List getPendingBlockUpdates(Chunk par1Chunk, boolean par2)
    {
        ArrayList var3 = null;
        ChunkCoordIntPair var4 = par1Chunk.getChunkCoordIntPair();
        int var5 = (var4.chunkXPos << 4) - 2;
        int var6 = var5 + 16 + 2;
        int var7 = (var4.chunkZPos << 4) - 2;
        int var8 = var7 + 16 + 2;

        for (int var9 = 0; var9 < 2; ++var9)
        {
            Iterator var10;

            if (var9 == 0)
            {
                var10 = this.pendingTickListEntries.iterator();
            }
            else
            {
                var10 = this.field_94579_S.iterator();

                if (!this.field_94579_S.isEmpty())
                {
                    System.out.println(this.field_94579_S.size());
                }
            }

            while (var10.hasNext())
            {
                NextTickListEntry var11 = (NextTickListEntry)var10.next();

                if (var11.xCoord >= var5 && var11.xCoord < var6 && var11.zCoord >= var7 && var11.zCoord < var8)
                {
                    if (par2)
                    {
                        this.field_73064_N.remove(var11);
                        var10.remove();
                    }

                    if (var3 == null)
                    {
                        var3 = new ArrayList();
                    }

                    var3.add(var11);
                }
            }
        }

        return var3;
    }

    /**
     * Will update the entity in the world if the chunk the entity is in is currently loaded or its forced to update.
     * Args: entity, forceUpdate
     */
    public void updateEntityWithOptionalForce(Entity par1Entity, boolean par2)
    {
        if (!this.mcServer.getCanSpawnAnimals() && (par1Entity instanceof EntityAnimal || par1Entity instanceof EntityWaterMob))
        {
            par1Entity.setDead();
        }

        if (!this.mcServer.getCanSpawnNPCs() && par1Entity instanceof INpc)
        {
            par1Entity.setDead();
        }

        if (!(par1Entity.riddenByEntity instanceof EntityPlayer))
        {
            int var3 = MathHelper.floor_double(par1Entity.posX);
            int var4 = MathHelper.floor_double(par1Entity.posZ);

            if (par2 && !this.IsBlockPosActive(var3, 0, var4) && par1Entity.addedToChunk)
            {
                if (par1Entity.ridingEntity == null)
                {
                    par1Entity.OutOfUpdateRangeUpdate();
                }

                return;
            }

            super.updateEntityWithOptionalForce(par1Entity, par2);
        }
    }

    /**
     * direct call to super.updateEntityWithOptionalForce
     */
    public void uncheckedUpdateEntity(Entity par1Entity, boolean par2)
    {
        super.updateEntityWithOptionalForce(par1Entity, par2);
    }

    /**
     * Creates the chunk provider for this world. Called in the constructor. Retrieves provider from worldProvider?
     */
    protected IChunkProvider createChunkProvider()
    {
        IChunkLoader var1 = this.saveHandler.getChunkLoader(this.provider);
        this.theChunkProviderServer = new ChunkProviderServer(this, var1, this.provider.createChunkGenerator());
        return this.theChunkProviderServer;
    }

    public List getAllTileEntityInBox(int var1, int var2, int var3, int var4, int var5, int var6)
    {
        return this.getTileEntityList(var1, var2, var3, var4, var5, var6);
    }

    /**
     * get a list of tileEntity's
     */
    public List getTileEntityList(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        ArrayList var7 = new ArrayList();

        for (int var8 = 0; var8 < this.loadedTileEntityList.size(); ++var8)
        {
            TileEntity var9 = (TileEntity)this.loadedTileEntityList.get(var8);

            if (var9.xCoord >= par1 && var9.yCoord >= par2 && var9.zCoord >= par3 && var9.xCoord < par4 && var9.yCoord < par5 && var9.zCoord < par6)
            {
                var7.add(var9);
            }
        }

        return var7;
    }

    /**
     * Called when checking if a certain block can be mined or not. The 'spawn safe zone' check is located here.
     */
    public boolean canMineBlock(EntityPlayer par1EntityPlayer, int par2, int par3, int par4)
    {
        return !this.mcServer.func_96290_a(this, par2, par3, par4, par1EntityPlayer);
    }

    protected void initialize(WorldSettings par1WorldSettings)
    {
        if (this.entityIdMap == null)
        {
            this.entityIdMap = new IntHashMap();
        }

        if (this.field_73064_N == null)
        {
            this.field_73064_N = new HashSet();
        }

        if (this.pendingTickListEntries == null)
        {
            this.pendingTickListEntries = new TreeSet();
        }

        this.createSpawnPosition(par1WorldSettings);
        super.initialize(par1WorldSettings);
    }

    /**
     * creates a spawn position at random within 256 blocks of 0,0
     */
    protected void createSpawnPosition(WorldSettings par1WorldSettings)
    {
        if (!this.provider.canRespawnHere())
        {
            this.worldInfo.setSpawnPosition(0, this.provider.getAverageGroundLevel(), 0);
        }
        else
        {
            this.findingSpawnPoint = true;
            WorldChunkManager var2 = this.provider.worldChunkMgr;
            List var3 = var2.getBiomesToSpawnIn();
            Random var4 = new Random(this.getSeed());
            ChunkPosition var5 = var2.findBiomePosition(0, 0, 256, var3, var4);
            int var6 = 0;
            int var7 = this.provider.getAverageGroundLevel();
            int var8 = 0;

            if (var5 != null)
            {
                var6 = var5.x;
                var8 = var5.z;
            }
            else
            {
                this.getWorldLogAgent().func_98236_b("Unable to find spawn biome");
            }

            int var9 = 0;

            while (!this.provider.canCoordinateBeSpawn(var6, var8))
            {
                var6 += var4.nextInt(64) - var4.nextInt(64);
                var8 += var4.nextInt(64) - var4.nextInt(64);
                ++var9;

                if (var9 == 1000)
                {
                    break;
                }
            }

            this.worldInfo.setSpawnPosition(var6, var7, var8);
            this.findingSpawnPoint = false;

            if (par1WorldSettings.isBonusChestEnabled())
            {
                this.createBonusChest();
            }
        }
    }

    /**
     * Creates the bonus chest in the world.
     */
    protected void createBonusChest()
    {
        FCWorldGeneratorBonusBasket var1 = new FCWorldGeneratorBonusBasket();

        for (int var2 = 0; var2 < 10; ++var2)
        {
            int var3 = this.worldInfo.getSpawnX() + this.rand.nextInt(6) - this.rand.nextInt(6);
            int var4 = this.worldInfo.getSpawnZ() + this.rand.nextInt(6) - this.rand.nextInt(6);
            int var5 = this.getTopSolidOrLiquidBlock(var3, var4) + 1;

            if (var1.generate(this, this.rand, var3, var5, var4))
            {
                break;
            }
        }
    }

    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return this.provider.getEntrancePortalLocation();
    }

    /**
     * Saves all chunks to disk while updating progress bar.
     */
    public void saveAllChunks(boolean par1, IProgressUpdate par2IProgressUpdate) throws MinecraftException
    {
        if (this.chunkProvider.canSave())
        {
            if (par2IProgressUpdate != null)
            {
                par2IProgressUpdate.displaySavingString("Saving level");
            }

            this.saveLevel();

            if (par2IProgressUpdate != null)
            {
                par2IProgressUpdate.displayLoadingString("Saving chunks");
            }

            this.chunkProvider.saveChunks(par1, par2IProgressUpdate);
            this.saveHandler.SaveModSpecificData(this);
        }
    }

    public void func_104140_m()
    {
        if (this.chunkProvider.canSave())
        {
            this.chunkProvider.func_104112_b();
        }
    }

    /**
     * Saves the chunks to disk.
     */
    protected void saveLevel() throws MinecraftException
    {
        this.checkSessionLock();
        this.saveHandler.saveWorldInfoWithPlayer(this.worldInfo, this.mcServer.getConfigurationManager().getHostPlayerData());
        this.mapStorage.saveAllData();
    }

    /**
     * Start the skin for this entity downloading, if necessary, and increment its reference counter
     */
    protected void obtainEntitySkin(Entity par1Entity)
    {
        super.obtainEntitySkin(par1Entity);
        this.entityIdMap.addKey(par1Entity.entityId, par1Entity);
        Entity[] var2 = par1Entity.getParts();

        if (var2 != null)
        {
            for (int var3 = 0; var3 < var2.length; ++var3)
            {
                this.entityIdMap.addKey(var2[var3].entityId, var2[var3]);
            }
        }
    }

    /**
     * Decrement the reference counter for this entity's skin image data
     */
    protected void releaseEntitySkin(Entity par1Entity)
    {
        super.releaseEntitySkin(par1Entity);
        this.entityIdMap.removeObject(par1Entity.entityId);
        Entity[] var2 = par1Entity.getParts();

        if (var2 != null)
        {
            for (int var3 = 0; var3 < var2.length; ++var3)
            {
                this.entityIdMap.removeObject(var2[var3].entityId);
            }
        }
    }

    /**
     * Returns the Entity with the given ID, or null if it doesn't exist in this World.
     */
    public Entity getEntityByID(int par1)
    {
        return (Entity)this.entityIdMap.lookup(par1);
    }

    /**
     * adds a lightning bolt to the list of lightning bolts in this world.
     */
    public boolean addWeatherEffect(Entity par1Entity)
    {
        if (super.addWeatherEffect(par1Entity))
        {
            Packet71Weather var2 = new Packet71Weather(par1Entity);

            if (par1Entity instanceof FCEntityLightningBolt)
            {
                var2.isLightningBolt = 1;
            }

            this.mcServer.getConfigurationManager().sendPacketToPlayersAroundPoint(par1Entity.posX, par1Entity.posY, par1Entity.posZ, 512.0D, this.provider.dimensionId, var2);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * sends a Packet 38 (Entity Status) to all tracked players of that entity
     */
    public void setEntityState(Entity par1Entity, byte par2)
    {
        Packet38EntityStatus var3 = new Packet38EntityStatus(par1Entity.entityId, par2);
        this.getEntityTracker().sendPacketToTrackedPlayersAndTrackedEntity(par1Entity, var3);
    }

    /**
     * returns a new explosion. Does initiation (at time of writing Explosion is not finished)
     */
    public Explosion newExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, boolean par9, boolean par10)
    {
        Explosion var11 = new Explosion(this, par1Entity, par2, par4, par6, par8);
        var11.isFlaming = par9;
        var11.isSmoking = par10;
        var11.doExplosionA();
        var11.doExplosionB(false);

        if (!par10)
        {
            var11.affectedBlockPositions.clear();
        }

        Iterator var12 = this.playerEntities.iterator();

        while (var12.hasNext())
        {
            EntityPlayer var13 = (EntityPlayer)var12.next();

            if (var13.getDistanceSq(par2, par4, par6) < 4096.0D)
            {
                ((EntityPlayerMP)var13).playerNetServerHandler.sendPacket(new Packet60Explosion(par2, par4, par6, par8, var11.affectedBlockPositions, (Vec3)var11.func_77277_b().get(var13)));
            }
        }

        return var11;
    }

    /**
     * Adds a block event with the given Args to the blockEventCache. During the next tick(), the block specified will
     * have its onBlockEvent handler called with the given parameters. Args: X,Y,Z, BlockID, EventID, EventParameter
     */
    public void addBlockEvent(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        BlockEventData var7 = new BlockEventData(par1, par2, par3, par4, par5, par6);
        Iterator var8 = this.blockEventCache[this.blockEventCacheIndex].iterator();

        while (var8.hasNext())
        {
            BlockEventData var9 = (BlockEventData)var8.next();

            if (var9.equals(var7))
            {
                return;
            }
        }

        this.blockEventCache[this.blockEventCacheIndex].add(var7);
    }

    /**
     * Send and apply locally all pending BlockEvents to each player with 64m radius of the event.
     */
    private void sendAndApplyBlockEvents()
    {
        while (!this.blockEventCache[this.blockEventCacheIndex].isEmpty())
        {
            int var1 = this.blockEventCacheIndex;
            this.blockEventCacheIndex ^= 1;
            Iterator var2 = this.blockEventCache[var1].iterator();

            while (var2.hasNext())
            {
                BlockEventData var3 = (BlockEventData)var2.next();

                if (this.onBlockEventReceived(var3))
                {
                    this.mcServer.getConfigurationManager().sendPacketToPlayersAroundPoint((double)var3.getX(), (double)var3.getY(), (double)var3.getZ(), 64.0D, this.provider.dimensionId, new Packet54PlayNoteBlock(var3.getX(), var3.getY(), var3.getZ(), var3.getBlockID(), var3.getEventID(), var3.getEventParameter()));
                }
            }

            this.blockEventCache[var1].clear();
        }
    }

    /**
     * Called to apply a pending BlockEvent to apply to the current world.
     */
    private boolean onBlockEventReceived(BlockEventData par1BlockEventData)
    {
        int var2 = this.getBlockId(par1BlockEventData.getX(), par1BlockEventData.getY(), par1BlockEventData.getZ());
        return var2 == par1BlockEventData.getBlockID() ? Block.blocksList[var2].onBlockEventReceived(this, par1BlockEventData.getX(), par1BlockEventData.getY(), par1BlockEventData.getZ(), par1BlockEventData.getEventID(), par1BlockEventData.getEventParameter()) : false;
    }

    /**
     * Syncs all changes to disk and wait for completion.
     */
    public void flush()
    {
        this.saveHandler.flush();
    }

    /**
     * Gets the MinecraftServer.
     */
    public MinecraftServer getMinecraftServer()
    {
        return this.mcServer;
    }

    /**
     * Gets the EntityTracker
     */
    public EntityTracker getEntityTracker()
    {
        return this.theEntityTracker;
    }

    public Teleporter getDefaultTeleporter()
    {
        return this.field_85177_Q;
    }

    public void ModSpecificTick()
    {
        if (!this.m_bHasTicked)
        {
            this.m_bHasTicked = true;
            this.MarkChunksAroundSpawnToCheckForUnload();
        }

        this.CheckChunksToUnloadList();
    }

    public void AddChunkToCheckForUnloadList(int var1, int var2)
    {
        this.m_chunksToCheckForUnloadList.add(new ChunkCoordIntPair(var1, var2));
    }

    public void AddChunkRangeToCheckForUnloadList(int var1, int var2, int var3, int var4)
    {
        for (int var5 = var1; var5 <= var3; ++var5)
        {
            for (int var6 = var2; var6 <= var4; ++var6)
            {
                this.AddChunkToCheckForUnloadList(var5, var6);
            }
        }
    }

    private void CheckChunksToUnloadList()
    {
        if (!this.m_chunksToCheckForUnloadList.isEmpty())
        {
            Iterator var1 = this.m_chunksToCheckForUnloadList.iterator();

            while (var1.hasNext())
            {
                ChunkCoordIntPair var2 = (ChunkCoordIntPair)var1.next();

                if (this.CheckChunkShouldBeUnloaded(var2.chunkXPos, var2.chunkZPos))
                {
                    this.theChunkProviderServer.ForceAddToChunksToUnload(var2.chunkXPos, var2.chunkZPos);
                }
            }

            this.m_chunksToCheckForUnloadList.clear();
        }
    }

    private boolean CheckChunkShouldBeUnloaded(int var1, int var2)
    {
        return this.chunkExists(var1, var2) && !this.m_chunkTracker.IsChunkBeingWatched(var1, var2) && !this.theChunkProviderServer.IsSpawnChunk(var1, var2);
    }

    private void MarkChunksAroundSpawnToCheckForUnload()
    {
        if (this.provider.canRespawnHere())
        {
            int var1 = this.worldInfo.getSpawnX() >> 4;
            int var2 = this.worldInfo.getSpawnZ() >> 4;
            this.AddChunkRangeToCheckForUnloadList(var1 - 13, var2 - 13, var1 + 13, var2 + 13);
        }
    }

    public boolean IsUpdateScheduledForBlock(int var1, int var2, int var3, int var4)
    {
        NextTickListEntry var5 = new NextTickListEntry(var1, var2, var3, var4);
        return this.field_73064_N.contains(var5);
    }

    /**
     * Updates all weather states.
     */
    protected void updateWeather()
    {
        super.updateWeather();

        if (this.worldInfo.m_bPreviouslyRaining != this.worldInfo.isRaining())
        {
            if (this.worldInfo.isRaining())
            {
                this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet70GameEvent(1, 0));
            }
            else
            {
                this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet70GameEvent(2, 0));
            }

            this.worldInfo.m_bPreviouslyRaining = this.worldInfo.isRaining();
        }

        if (this.worldInfo.m_bPreviouslyThundering != this.worldInfo.isThundering())
        {
            if (this.worldInfo.isThundering())
            {
                this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet70GameEvent(7, 0));
            }
            else
            {
                this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet70GameEvent(8, 0));
            }

            this.worldInfo.m_bPreviouslyThundering = this.worldInfo.isThundering();
        }
    }

    private void ModUpdateTick()
    {
        this.ValidateMagneticPointList();
        this.ValidateLootingBeaconList();
        this.ValidateSpawnLocationList();
    }

    private void ValidateMagneticPointList()
    {
        int var1 = (int)this.getWorldTime();

        if ((var1 & 15) == 0)
        {
            int var2 = this.m_MagneticPointList.m_MagneticPoints.size();

            if (var2 > 0)
            {
                var1 >>= 4;
                int var3 = var1 % var2;
                FCMagneticPoint var4 = (FCMagneticPoint)this.m_MagneticPointList.m_MagneticPoints.get(var3);

                if (this.checkChunksExist(var4.m_iIPos, 0, var4.m_iKPos, var4.m_iIPos, 0, var4.m_iKPos) && this.getBlockId(var4.m_iIPos, var4.m_iJPos, var4.m_iKPos) != Block.beacon.blockID)
                {
                    this.m_MagneticPointList.m_MagneticPoints.remove(var3);
                }
            }
        }
    }

    private void ValidateLootingBeaconList()
    {
        int var1 = (int)this.getWorldTime();

        if ((var1 & 15) == 0)
        {
            int var2 = this.m_LootingBeaconLocationList.m_EffectLocations.size();

            if (var2 > 0)
            {
                var1 >>= 4;
                int var3 = var1 % var2;
                FCBeaconEffectLocation var4 = (FCBeaconEffectLocation)this.m_LootingBeaconLocationList.m_EffectLocations.get(var3);

                if (this.checkChunksExist(var4.m_iIPos, 0, var4.m_iKPos, var4.m_iIPos, 0, var4.m_iKPos) && this.getBlockId(var4.m_iIPos, var4.m_iJPos, var4.m_iKPos) != Block.beacon.blockID)
                {
                    this.m_LootingBeaconLocationList.m_EffectLocations.remove(var3);
                }
            }
        }
    }

    private void ValidateSpawnLocationList()
    {
        long var1 = this.getWorldTime();

        if ((var1 & 15L) == 0L)
        {
            Iterator var3 = this.m_SpawnLocationList.m_SpawnLocations.iterator();

            while (var3.hasNext())
            {
                FCSpawnLocation var4 = (FCSpawnLocation)var3.next();

                if (var1 < var4.m_lSpawnTime || var1 - var4.m_lSpawnTime > 24000L)
                {
                    var3.remove();
                }
            }
        }
    }

    protected void AdjustLightningPosForSurroundings(FCUtilsBlockPos var1)
    {
        int var2 = var1.j;
        int var3 = var1.i - 16;
        int var4 = var1.k - 16;
        int var5 = var1.i + 16;
        int var6 = var1.k + 16;
        int var9;

        for (int var7 = var3; var7 <= var5; ++var7)
        {
            for (int var8 = var4; var8 <= var6; ++var8)
            {
                var9 = this.getPrecipitationHeight(var7, var8);

                if (var9 > var2 && this.CanLightningStrikeAtPos(var7, var9, var8))
                {
                    var1.i = var7;
                    var2 = var1.j = var9;
                    var1.k = var8;
                }
            }
        }

        List var14 = this.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var2, (double)var4, (double)var5 + 1.0D, 256.0D, (double)var6 + 1.0D));
        Iterator var15 = var14.iterator();
        int var10;
        int var11;

        while (var15.hasNext())
        {
            Entity var16 = (Entity)var15.next();

            if (var16.isEntityAlive() && var16.AttractsLightning())
            {
                var10 = (int)var16.boundingBox.maxY + 1;

                if (var10 > var2)
                {
                    var11 = MathHelper.floor_double(var16.posX);
                    int var12 = MathHelper.floor_double(var16.posZ);
                    int var13 = this.getPrecipitationHeight(var11, var12);

                    if (var13 <= var10 && this.CanLightningStrikeAtPos(var11, var13, var12))
                    {
                        var1.i = var11;
                        var2 = var1.j = var13;
                        var1.k = var12;
                    }
                }
            }
        }

        if (this.IsBlockPosActive(var1.i, var1.j, var1.k))
        {
            var3 = var1.i - 16;
            var4 = var1.k - 16;
            var5 = var1.i + 16;
            var6 = var1.k + 16;

            for (var9 = var3; var9 <= var5; ++var9)
            {
                for (var10 = var4; var10 <= var6; ++var10)
                {
                    var11 = this.getPrecipitationHeight(var9, var10);

                    if (var11 > var2 && this.getBlockId(var9, var11 - 1, var10) == FCBetterThanWolves.fcBlockLightningRod.blockID)
                    {
                        var1.i = var9;
                        var2 = var1.j = var11;
                        var1.k = var10;
                    }
                }
            }
        }
    }

    public int GetClampedViewDistanceInChunks()
    {
        int var1 = this.getMinecraftServer().getConfigurationManager().getViewDistance();
        return MathHelper.clamp_int(var1, 3, 15);
    }

    protected void UpdateActiveChunkMap()
    {
        super.UpdateActiveChunkMap();
        this.UpdateServerIdleState();

        if (this.provider.dimensionId == 0 && !this.IsServerIdle())
        {
            ChunkCoordinates var1 = this.getSpawnPoint();
            this.AddAreaAroundChunkToActiveChunkMap(var1.posX >> 4, var1.posZ >> 4);
        }
    }

    public FCChunkTracker GetChunkTracker()
    {
        return this.m_chunkTracker;
    }

    protected void UpdateServerIdleState()
    {
        if (!this.AreAnyPlayersOnServer())
        {
            ++this.m_lNoPlayersOnServerTickCount;
        }
        else
        {
            this.m_lNoPlayersOnServerTickCount = 0L;
        }
    }

    protected boolean IsServerIdle()
    {
        return this.m_lNoPlayersOnServerTickCount >= 1200L;
    }

    protected boolean AreAnyPlayersOnServer()
    {
        return this.mcServer.getCurrentPlayerCount() > 0;
    }
}
