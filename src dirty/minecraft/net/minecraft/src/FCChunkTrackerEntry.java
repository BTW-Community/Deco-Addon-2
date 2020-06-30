package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

class FCChunkTrackerEntry
{
    private final FCChunkTracker m_chunkTracker;
    public final ChunkCoordIntPair m_coord;
    private final List m_playersWatching = new ArrayList();
    private short[] m_locationsRequiringClientUpdate = new short[64];
    private int m_iNumLocationsRequiringClientUpdate = 0;
    private int m_iVerticalChunksToUpdatePlayersBitfield;

    public FCChunkTrackerEntry(FCChunkTracker var1, int var2, int var3)
    {
        this.m_chunkTracker = var1;
        this.m_coord = new ChunkCoordIntPair(var2, var3);
        var1.m_worldServer.theChunkProviderServer.loadChunk(var2, var3);
    }

    public void AddPlayerWatching(EntityPlayerMP var1)
    {
        if (this.m_playersWatching.contains(var1))
        {
            throw new IllegalStateException("Failed to add player. " + var1 + " already is in chunk " + this.m_coord.chunkXPos + ", " + this.m_coord.chunkZPos);
        }
        else
        {
            this.m_playersWatching.add(var1);
            var1.m_chunksToBeSentToClient.add(this.m_coord);
        }
    }

    public boolean RequiresClientUpdate()
    {
        return this.m_iNumLocationsRequiringClientUpdate > 0;
    }

    public void RemovePlayerWatching(EntityPlayerMP var1)
    {
        if (this.m_playersWatching.contains(var1))
        {
            var1.playerNetServerHandler.sendPacket(new Packet51MapChunk(this.m_chunkTracker.m_worldServer.getChunkFromChunkCoords(this.m_coord.chunkXPos, this.m_coord.chunkZPos), true, 0));
            this.m_playersWatching.remove(var1);
            var1.m_chunksToBeSentToClient.remove(this.m_coord);

            if (this.m_playersWatching.isEmpty())
            {
                this.m_chunkTracker.RemoveChunkFromTracker(this);
            }
        }
    }

    private short GetBitEncodingForLocalPos(int var1, int var2, int var3)
    {
        return (short)(var1 << 12 | var3 << 8 | var2);
    }

    public void FlagBlockForUpdate(int var1, int var2, int var3)
    {
        this.m_iVerticalChunksToUpdatePlayersBitfield |= 1 << (var2 >> 4);

        if (this.m_iNumLocationsRequiringClientUpdate < 64)
        {
            short var4 = this.GetBitEncodingForLocalPos(var1, var2, var3);

            for (int var5 = 0; var5 < this.m_iNumLocationsRequiringClientUpdate; ++var5)
            {
                if (this.m_locationsRequiringClientUpdate[var5] == var4)
                {
                    return;
                }
            }

            this.m_locationsRequiringClientUpdate[this.m_iNumLocationsRequiringClientUpdate++] = var4;
        }
    }

    private void SendToPlayersWatchingNotWaitingFullChunk(Packet var1)
    {
        for (int var2 = 0; var2 < this.m_playersWatching.size(); ++var2)
        {
            EntityPlayerMP var3 = (EntityPlayerMP)this.m_playersWatching.get(var2);

            if (!var3.m_chunksToBeSentToClient.contains(this.m_coord))
            {
                var3.playerNetServerHandler.sendPacket(var1);
            }
        }
    }

    public void SendUpdatesToWatchingPlayers()
    {
        if (this.m_iNumLocationsRequiringClientUpdate != 0)
        {
            int var1 = this.m_coord.chunkXPos * 16;
            int var2 = this.m_coord.chunkZPos * 16;
            int var3;
            int var4;
            int var5;

            if (this.m_iNumLocationsRequiringClientUpdate == 1)
            {
                var3 = var1 + (this.m_locationsRequiringClientUpdate[0] >> 12 & 15);
                var4 = this.m_locationsRequiringClientUpdate[0] & 255;
                var5 = var2 + (this.m_locationsRequiringClientUpdate[0] >> 8 & 15);
                this.SendToPlayersWatchingNotWaitingFullChunk(new Packet53BlockChange(var3, var4, var5, this.m_chunkTracker.m_worldServer));

                if (this.m_chunkTracker.m_worldServer.blockHasTileEntity(var3, var4, var5))
                {
                    this.SendTileEntityToPlayersWatchingChunk(this.m_chunkTracker.m_worldServer.getBlockTileEntity(var3, var4, var5));
                }
            }
            else
            {
                int var6;

                if (this.m_iNumLocationsRequiringClientUpdate == 64)
                {
                    this.SendToPlayersWatchingNotWaitingFullChunk(new Packet51MapChunk(this.m_chunkTracker.m_worldServer.getChunkFromChunkCoords(this.m_coord.chunkXPos, this.m_coord.chunkZPos), false, this.m_iVerticalChunksToUpdatePlayersBitfield));

                    for (var3 = 0; var3 < 16; ++var3)
                    {
                        if ((this.m_iVerticalChunksToUpdatePlayersBitfield & 1 << var3) != 0)
                        {
                            var4 = var3 << 4;
                            List var7 = this.m_chunkTracker.m_worldServer.getAllTileEntityInBox(var1, var4, var2, var1 + 16, var4 + 16, var2 + 16);

                            for (var6 = 0; var6 < var7.size(); ++var6)
                            {
                                this.SendTileEntityToPlayersWatchingChunk((TileEntity)var7.get(var6));
                            }
                        }
                    }
                }
                else
                {
                    this.SendToPlayersWatchingNotWaitingFullChunk(new Packet52MultiBlockChange(this.m_coord.chunkXPos, this.m_coord.chunkZPos, this.m_locationsRequiringClientUpdate, this.m_iNumLocationsRequiringClientUpdate, this.m_chunkTracker.m_worldServer));

                    for (var3 = 0; var3 < this.m_iNumLocationsRequiringClientUpdate; ++var3)
                    {
                        var4 = var1 + (this.m_locationsRequiringClientUpdate[var3] >> 12 & 15);
                        var5 = this.m_locationsRequiringClientUpdate[var3] & 255;
                        var6 = var2 + (this.m_locationsRequiringClientUpdate[var3] >> 8 & 15);

                        if (this.m_chunkTracker.m_worldServer.blockHasTileEntity(var4, var5, var6))
                        {
                            this.SendTileEntityToPlayersWatchingChunk(this.m_chunkTracker.m_worldServer.getBlockTileEntity(var4, var5, var6));
                        }
                    }
                }
            }

            this.m_iNumLocationsRequiringClientUpdate = 0;
            this.m_iVerticalChunksToUpdatePlayersBitfield = 0;
        }
    }

    private void SendTileEntityToPlayersWatchingChunk(TileEntity var1)
    {
        Packet var2 = var1.getDescriptionPacket();

        if (var2 != null)
        {
            this.SendToPlayersWatchingNotWaitingFullChunk(var2);
        }
    }

    public ChunkCoordIntPair getChunkLocation()
    {
        return this.m_coord;
    }

    public List getPlayersInChunk()
    {
        return this.m_playersWatching;
    }
}
