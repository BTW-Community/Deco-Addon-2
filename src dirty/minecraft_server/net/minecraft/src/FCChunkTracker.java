package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;

public class FCChunkTracker
{
    public final WorldServer m_worldServer;
    private final LinkedList m_playersTracked = new LinkedList();
    public final LongHashMap m_trackerEntriesMap = new LongHashMap();
    private final LinkedList m_entriesRequiringClientUpdate = new LinkedList();
    private final int m_iChunkViewDistance;
    private final int[][] m_xzOffsets = new int[][] {{1, 0}, {0, 1}, { -1, 0}, {0, -1}};

    public FCChunkTracker(WorldServer var1, int var2)
    {
        if (var2 > 15)
        {
            throw new IllegalArgumentException("Too big view radius!");
        }
        else if (var2 < 3)
        {
            throw new IllegalArgumentException("Too small view radius!");
        }
        else
        {
            this.m_iChunkViewDistance = var2;
            this.m_worldServer = var1;
        }
    }

    public void Update()
    {
        if (!this.m_entriesRequiringClientUpdate.isEmpty())
        {
            Iterator var1 = this.m_entriesRequiringClientUpdate.iterator();

            while (var1.hasNext())
            {
                FCChunkTrackerEntry var2 = (FCChunkTrackerEntry)var1.next();
                var2.SendUpdatesToWatchingPlayers();
            }

            this.m_entriesRequiringClientUpdate.clear();
        }

        if (this.m_playersTracked.isEmpty())
        {
            WorldProvider var3 = this.m_worldServer.provider;

            if (!var3.canRespawnHere())
            {
                this.m_worldServer.theChunkProviderServer.unloadAllChunks();
            }
        }
    }

    public void FlagBlockForClientUpdate(int var1, int var2, int var3)
    {
        int var4 = var1 >> 4;
        int var5 = var3 >> 4;
        FCChunkTrackerEntry var6 = this.GetTrackerEntry(var4, var5);

        if (var6 != null)
        {
            if (!var6.RequiresClientUpdate())
            {
                this.m_entriesRequiringClientUpdate.add(var6);
            }

            var6.FlagBlockForUpdate(var1 & 15, var2, var3 & 15);
        }
    }

    public void AddPlayer(EntityPlayerMP var1)
    {
        int var2 = MathHelper.floor_double(var1.posX / 16.0D);
        int var3 = MathHelper.floor_double(var1.posZ / 16.0D);
        var1.managedPosX = var1.posX;
        var1.managedPosZ = var1.posZ;

        for (int var4 = var2 - this.m_iChunkViewDistance; var4 <= var2 + this.m_iChunkViewDistance; ++var4)
        {
            for (int var5 = var3 - this.m_iChunkViewDistance; var5 <= var3 + this.m_iChunkViewDistance; ++var5)
            {
                this.GetOrCreateTrackerEntry(var4, var5).AddPlayerWatching(var1);
            }
        }

        this.m_playersTracked.add(var1);
        this.FilterChunksToBeSentToClient(var1);
    }

    public void UpdateMovingPlayer(EntityPlayerMP var1)
    {
        double var2 = var1.posX - var1.managedPosX;
        double var4 = var1.posZ - var1.managedPosZ;
        double var6 = var2 * var2 + var4 * var4;

        if (var6 >= 64.0D)
        {
            int var8 = MathHelper.floor_double(var1.posX / 16.0D);
            int var9 = MathHelper.floor_double(var1.posZ / 16.0D);
            int var10 = MathHelper.floor_double(var1.managedPosX / 16.0D);
            int var11 = MathHelper.floor_double(var1.managedPosZ / 16.0D);

            if (var10 != var8 || var11 != var9)
            {
                int var12;
                int var13;
                FCChunkTrackerEntry var14;

                for (var12 = var8 - this.m_iChunkViewDistance; var12 <= var8 + this.m_iChunkViewDistance; ++var12)
                {
                    for (var13 = var9 - this.m_iChunkViewDistance; var13 <= var9 + this.m_iChunkViewDistance; ++var13)
                    {
                        if (!this.AreWithinAxisDistance(var12, var13, var10, var11, this.m_iChunkViewDistance))
                        {
                            var14 = this.GetOrCreateTrackerEntry(var12, var13);
                            var14.AddPlayerWatching(var1);
                        }
                    }
                }

                for (var12 = var10 - this.m_iChunkViewDistance; var12 <= var10 + this.m_iChunkViewDistance; ++var12)
                {
                    for (var13 = var11 - this.m_iChunkViewDistance; var13 <= var11 + this.m_iChunkViewDistance; ++var13)
                    {
                        if (!this.AreWithinAxisDistance(var12, var13, var8, var9, this.m_iChunkViewDistance))
                        {
                            var14 = this.GetTrackerEntry(var12, var13);

                            if (var14 != null)
                            {
                                var14.RemovePlayerWatching(var1);
                            }
                        }
                    }
                }

                this.FilterChunksToBeSentToClient(var1);
                var1.managedPosX = var1.posX;
                var1.managedPosZ = var1.posZ;
            }
        }
    }

    public void RemoveChunkFromTracker(FCChunkTrackerEntry var1)
    {
        ChunkCoordIntPair var2 = var1.m_coord;
        long var3 = this.ComputeTrackerEntryKey(var2.chunkXPos, var2.chunkZPos);
        this.m_trackerEntriesMap.remove(var3);

        if (var1.RequiresClientUpdate())
        {
            this.m_entriesRequiringClientUpdate.remove(this);
        }

        this.m_worldServer.theChunkProviderServer.unloadChunksIfNotNearSpawn(var2.chunkXPos, var2.chunkZPos);
    }

    public void FilterChunksToBeSentToClient(EntityPlayerMP var1)
    {
        LinkedList var2 = var1.m_chunksToBeSentToClient;
        var1.m_chunksToBeSentToClient = new LinkedList();
        int var3 = MathHelper.floor_double(var1.posX / 16.0D);
        int var4 = MathHelper.floor_double(var1.posZ / 16.0D);
        FCChunkTrackerEntry var5 = this.GetOrCreateTrackerEntry(var3, var4);

        if (var2.contains(var5.getChunkLocation()))
        {
            var1.m_chunksToBeSentToClient.add(var5.getChunkLocation());
        }

        for (int var6 = 1; var6 <= this.m_iChunkViewDistance; ++var6)
        {
            int var7 = var3 - var6;
            int var8 = var4 - var6;

            for (int var9 = 0; var9 < 4; ++var9)
            {
                int var10 = var6 * 2 + 1;
                int var11 = this.m_xzOffsets[var9][0];
                int var12 = this.m_xzOffsets[var9][1];

                for (int var13 = 0; var13 < var10 - 1; ++var13)
                {
                    FCChunkTrackerEntry var14 = this.GetOrCreateTrackerEntry(var7, var8);

                    if (var2.contains(var14.getChunkLocation()))
                    {
                        var1.m_chunksToBeSentToClient.add(var14.getChunkLocation());
                    }

                    var7 += var11;
                    var8 += var12;
                }
            }
        }
    }

    public void RemovePlayer(EntityPlayerMP var1)
    {
        int var2 = MathHelper.floor_double(var1.managedPosX / 16.0D);
        int var3 = MathHelper.floor_double(var1.managedPosZ / 16.0D);

        for (int var4 = var2 - this.m_iChunkViewDistance; var4 <= var2 + this.m_iChunkViewDistance; ++var4)
        {
            for (int var5 = var3 - this.m_iChunkViewDistance; var5 <= var3 + this.m_iChunkViewDistance; ++var5)
            {
                FCChunkTrackerEntry var6 = this.GetTrackerEntry(var4, var5);

                if (var6 != null)
                {
                    var6.RemovePlayerWatching(var1);
                }
            }
        }

        this.m_playersTracked.remove(var1);
    }

    public boolean IsChunkWatchedByPlayerAndSentToClient(EntityPlayerMP var1, int var2, int var3)
    {
        FCChunkTrackerEntry var4 = this.GetTrackerEntry(var2, var3);
        return var4 != null && var4.getPlayersInChunk().contains(var1) && !var1.m_chunksToBeSentToClient.contains(var4.getChunkLocation());
    }

    private boolean AreWithinAxisDistance(int var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1 - var3;
        int var7 = var2 - var4;
        return var6 >= -var5 && var6 <= var5 ? var7 >= -var5 && var7 <= var5 : false;
    }

    public static int GetFurthestViewableBlock(int var0)
    {
        return var0 * 16 - 16;
    }

    public boolean IsChunkBeingWatched(int var1, int var2)
    {
        return this.GetTrackerEntry(var1, var2) != null;
    }

    private long ComputeTrackerEntryKey(int var1, int var2)
    {
        return (long)var1 + 2147483647L | (long)var2 + 2147483647L << 32;
    }

    private FCChunkTrackerEntry GetTrackerEntry(int var1, int var2)
    {
        long var3 = this.ComputeTrackerEntryKey(var1, var2);
        return (FCChunkTrackerEntry)this.m_trackerEntriesMap.getValueByKey(var3);
    }

    private FCChunkTrackerEntry GetOrCreateTrackerEntry(int var1, int var2)
    {
        FCChunkTrackerEntry var3 = this.GetTrackerEntry(var1, var2);

        if (var3 == null)
        {
            var3 = new FCChunkTrackerEntry(this, var1, var2);
            this.m_trackerEntriesMap.add(this.ComputeTrackerEntryKey(var1, var2), var3);
        }

        return var3;
    }
}
