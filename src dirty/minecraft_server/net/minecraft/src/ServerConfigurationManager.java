package net.minecraft.src;

import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.server.MinecraftServer;

public abstract class ServerConfigurationManager
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");

    /** Reference to the MinecraftServer object. */
    private final MinecraftServer mcServer;

    /** A list of player entities that exist on this server. */
    public final List playerEntityList = new ArrayList();
    private final BanList bannedPlayers = new BanList(new File("banned-players.txt"));
    private final BanList bannedIPs = new BanList(new File("banned-ips.txt"));

    /** A set containing the OPs. */
    private Set ops = new HashSet();

    /** The Set of all whitelisted players. */
    private Set whiteListedPlayers = new HashSet();

    /** Reference to the PlayerNBTManager object. */
    private IPlayerFileData playerNBTManagerObj;

    /**
     * Server setting to only allow OPs and whitelisted players to join the server.
     */
    private boolean whiteListEnforced;

    /** The maximum number of players that can be connected at a time. */
    protected int maxPlayers;
    protected int viewDistance;
    private EnumGameType gameType;

    /** True if all players are allowed to use commands (cheats). */
    private boolean commandsAllowedForAll;

    /**
     * index into playerEntities of player to ping, updated every tick; currently hardcoded to max at 200 players
     */
    private int playerPingIndex = 0;

    public ServerConfigurationManager(MinecraftServer par1MinecraftServer)
    {
        this.mcServer = par1MinecraftServer;
        this.bannedPlayers.setListActive(false);
        this.bannedIPs.setListActive(false);
        this.maxPlayers = 8;
    }

    public void initializeConnectionToPlayer(INetworkManager par1INetworkManager, EntityPlayerMP par2EntityPlayerMP)
    {	
        NBTTagCompound var3 = this.readPlayerDataFromFile(par2EntityPlayerMP);
        par2EntityPlayerMP.setWorld(this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension));
        par2EntityPlayerMP.theItemInWorldManager.setWorld((WorldServer)par2EntityPlayerMP.worldObj);
        String var4 = "local";

        if (par1INetworkManager.getRemoteAddress() != null)
        {
            var4 = par1INetworkManager.getRemoteAddress().toString();
        }

        this.mcServer.getLogAgent().func_98233_a(par2EntityPlayerMP.username + "[" + var4 + "] logged in with entity id " + par2EntityPlayerMP.entityId + " at (" + par2EntityPlayerMP.posX + ", " + par2EntityPlayerMP.posY + ", " + par2EntityPlayerMP.posZ + ")");
        WorldServer var5 = this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension);
        ChunkCoordinates var6 = var5.getSpawnPoint();
        this.func_72381_a(par2EntityPlayerMP, (EntityPlayerMP)null, var5);
        NetServerHandler var7 = new NetServerHandler(this.mcServer, par1INetworkManager, par2EntityPlayerMP);
        var7.sendPacket(new Packet1Login(par2EntityPlayerMP.entityId, var5.getWorldInfo().getTerrainType(), par2EntityPlayerMP.theItemInWorldManager.getGameType(), var5.getWorldInfo().isHardcoreModeEnabled(), var5.provider.dimensionId, var5.difficultySetting, var5.getHeight(), this.getMaxPlayers()));
        var7.sendPacket(new Packet6SpawnPosition(var6.posX, var6.posY, var6.posZ));
        var7.sendPacket(new Packet202PlayerAbilities(par2EntityPlayerMP.capabilities));
        var7.sendPacket(new Packet16BlockItemSwitch(par2EntityPlayerMP.inventory.currentItem));
        this.func_96456_a((ServerScoreboard)var5.getScoreboard(), par2EntityPlayerMP);
        this.updateTimeAndWeatherForPlayer(par2EntityPlayerMP, var5);
        this.sendPacketToAllPlayers(new Packet3Chat(EnumChatFormatting.YELLOW + par2EntityPlayerMP.getTranslatedEntityName() + EnumChatFormatting.YELLOW + " joined the game."));
        this.playerLoggedIn(par2EntityPlayerMP);
        var7.setPlayerLocation(par2EntityPlayerMP.posX, par2EntityPlayerMP.posY, par2EntityPlayerMP.posZ, par2EntityPlayerMP.rotationYaw, par2EntityPlayerMP.rotationPitch);
        this.mcServer.getNetworkThread().addPlayer(var7);
        var7.sendPacket(new Packet4UpdateTime(var5.getTotalWorldTime(), var5.getWorldTime()));

        if (this.mcServer.getTexturePack().length() > 0)
        {
            par2EntityPlayerMP.requestTexturePackLoad(this.mcServer.getTexturePack(), this.mcServer.textureSize());
        }

        Iterator var8 = par2EntityPlayerMP.getActivePotionEffects().iterator();

        while (var8.hasNext())
        {
            PotionEffect var9 = (PotionEffect)var8.next();
            var7.sendPacket(new Packet41EntityEffect(par2EntityPlayerMP.entityId, var9));
        }

        par2EntityPlayerMP.addSelfToInternalCraftingInventory();

        if (var3 != null && var3.hasKey("Riding"))
        {
            Entity var10 = EntityList.createEntityFromNBT(var3.getCompoundTag("Riding"), var5);

            if (var10 != null)
            {
                var10.field_98038_p = true;
                var5.spawnEntityInWorld(var10);
                par2EntityPlayerMP.mountEntity(var10);
                var10.field_98038_p = false;
            }
        }

        FCBetterThanWolves.ServerPlayerConnectionInitialized(var7, par2EntityPlayerMP);
    }

    protected void func_96456_a(ServerScoreboard par1ServerScoreboard, EntityPlayerMP par2EntityPlayerMP)
    {
        HashSet var3 = new HashSet();
        Iterator var4 = par1ServerScoreboard.func_96525_g().iterator();

        while (var4.hasNext())
        {
            ScorePlayerTeam var5 = (ScorePlayerTeam)var4.next();
            par2EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet209SetPlayerTeam(var5, 0));
        }

        for (int var10 = 0; var10 < 3; ++var10)
        {
            ScoreObjective var6 = par1ServerScoreboard.func_96539_a(var10);

            if (var6 != null && !var3.contains(var6))
            {
                List var7 = par1ServerScoreboard.func_96550_d(var6);
                Iterator var8 = var7.iterator();

                while (var8.hasNext())
                {
                    Packet var9 = (Packet)var8.next();
                    par2EntityPlayerMP.playerNetServerHandler.sendPacket(var9);
                }

                var3.add(var6);
            }
        }
    }

    /**
     * Sets the NBT manager to the one for the WorldServer given.
     */
    public void setPlayerManager(WorldServer[] par1ArrayOfWorldServer)
    {
        this.playerNBTManagerObj = par1ArrayOfWorldServer[0].getSaveHandler().getPlayerNBTManager();
    }

    public void func_72375_a(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        WorldServer var3 = par1EntityPlayerMP.getServerForPlayer();

        if (par2WorldServer != null)
        {
            par2WorldServer.GetChunkTracker().RemovePlayer(par1EntityPlayerMP);
        }

        var3.GetChunkTracker().AddPlayer(par1EntityPlayerMP);
        var3.theChunkProviderServer.loadChunk((int)par1EntityPlayerMP.posX >> 4, (int)par1EntityPlayerMP.posZ >> 4);
    }

    public int getEntityViewDistance()
    {
        return FCChunkTracker.GetFurthestViewableBlock(this.getViewDistance());
    }

    /**
     * called during player login. reads the player information from disk.
     */
    public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP par1EntityPlayerMP)
    {
        NBTTagCompound var2 = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
        NBTTagCompound var3;

        if (par1EntityPlayerMP.getCommandSenderName().equals(this.mcServer.getServerOwner()) && var2 != null)
        {
            par1EntityPlayerMP.readFromNBT(var2);
            var3 = var2;
            System.out.println("loading single player");
        }
        else
        {
            var3 = this.playerNBTManagerObj.readPlayerData(par1EntityPlayerMP);
        }

        return var3;
    }

    /**
     * also stores the NBTTags if this is an intergratedPlayerList
     */
    protected void writePlayerData(EntityPlayerMP par1EntityPlayerMP)
    {
        this.playerNBTManagerObj.writePlayerData(par1EntityPlayerMP);
    }

    /**
     * Called when a player successfully logs in. Reads player data from disk and inserts the player into the world.
     */
    public void playerLoggedIn(EntityPlayerMP par1EntityPlayerMP)
    {
        this.sendPacketToAllPlayers(new Packet201PlayerInfo(par1EntityPlayerMP.username, true, 1000));
        this.playerEntityList.add(par1EntityPlayerMP);
        WorldServer var2 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        var2.spawnEntityInWorld(par1EntityPlayerMP);
        this.func_72375_a(par1EntityPlayerMP, (WorldServer)null);

        for (int var3 = 0; var3 < this.playerEntityList.size(); ++var3)
        {
            EntityPlayerMP var4 = (EntityPlayerMP)this.playerEntityList.get(var3);
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet201PlayerInfo(var4.username, true, var4.ping));
        }
    }

    /**
     * using player's dimension, update their movement when in a vehicle (e.g. cart, boat)
     */
    public void serverUpdateMountedMovingPlayer(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.getServerForPlayer().GetChunkTracker().UpdateMovingPlayer(par1EntityPlayerMP);
    }

    /**
     * Called when a player disconnects from the game. Writes player data to disk and removes them from the world.
     */
    public void playerLoggedOut(EntityPlayerMP par1EntityPlayerMP)
    {
        this.writePlayerData(par1EntityPlayerMP);
        WorldServer var2 = par1EntityPlayerMP.getServerForPlayer();

        if (par1EntityPlayerMP.ridingEntity != null)
        {
            var2.removeEntity(par1EntityPlayerMP.ridingEntity);
            System.out.println("removing player mount");
        }

        var2.removeEntity(par1EntityPlayerMP);
        var2.GetChunkTracker().RemovePlayer(par1EntityPlayerMP);
        this.playerEntityList.remove(par1EntityPlayerMP);
        this.sendPacketToAllPlayers(new Packet201PlayerInfo(par1EntityPlayerMP.username, false, 9999));
    }

    /**
     * checks ban-lists, then white-lists, then space for the server. Returns null on success, or an error message
     */
    public String allowUserToConnect(SocketAddress par1SocketAddress, String par2Str)
    {
        if (this.bannedPlayers.isBanned(par2Str))
        {
            BanEntry var6 = (BanEntry)this.bannedPlayers.getBannedList().get(par2Str);
            String var7 = "You are banned from this server!\nReason: " + var6.getBanReason();

            if (var6.getBanEndDate() != null)
            {
                var7 = var7 + "\nYour ban will be removed on " + dateFormat.format(var6.getBanEndDate());
            }

            return var7;
        }
        else if (!this.isAllowedToLogin(par2Str))
        {
            return "You are not white-listed on this server!";
        }
        else
        {
            String var3 = par1SocketAddress.toString();
            var3 = var3.substring(var3.indexOf("/") + 1);
            var3 = var3.substring(0, var3.indexOf(":"));

            if (this.bannedIPs.isBanned(var3))
            {
                BanEntry var4 = (BanEntry)this.bannedIPs.getBannedList().get(var3);
                String var5 = "Your IP address is banned from this server!\nReason: " + var4.getBanReason();

                if (var4.getBanEndDate() != null)
                {
                    var5 = var5 + "\nYour ban will be removed on " + dateFormat.format(var4.getBanEndDate());
                }

                return var5;
            }
            else
            {
                return this.playerEntityList.size() >= this.maxPlayers ? "The server is full!" : null;
            }
        }
    }

    /**
     * also checks for multiple logins
     */
    public EntityPlayerMP createPlayerForUser(String par1Str)
    {
        ArrayList var2 = new ArrayList();
        EntityPlayerMP var3;

        for (int var4 = 0; var4 < this.playerEntityList.size(); ++var4)
        {
            var3 = (EntityPlayerMP)this.playerEntityList.get(var4);

            if (var3.username.equalsIgnoreCase(par1Str))
            {
                var2.add(var3);
            }
        }

        Iterator var6 = var2.iterator();

        while (var6.hasNext())
        {
            var3 = (EntityPlayerMP)var6.next();
            var3.playerNetServerHandler.kickPlayer("You logged in from another location");
        }

        Object var5;

        if (this.mcServer.isDemo())
        {
            var5 = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
        }
        else
        {
            var5 = new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
        }

        return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), par1Str, (ItemInWorldManager)var5);
    }

    /**
     * moves provided player from overworld to nether or vice versa
     */
    public void transferPlayerToDimension(EntityPlayerMP par1EntityPlayerMP, int par2)
    {
        int var3 = par1EntityPlayerMP.dimension;
        WorldServer var4 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        par1EntityPlayerMP.dimension = par2;
        WorldServer var5 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet9Respawn(par1EntityPlayerMP.dimension, (byte)par1EntityPlayerMP.worldObj.difficultySetting, var5.getWorldInfo().getTerrainType(), var5.getHeight(), par1EntityPlayerMP.theItemInWorldManager.getGameType()));
        var4.removePlayerEntityDangerously(par1EntityPlayerMP);
        par1EntityPlayerMP.isDead = false;
        this.transferEntityToWorld(par1EntityPlayerMP, var3, var4, var5);
        this.func_72375_a(par1EntityPlayerMP, var4);
        par1EntityPlayerMP.playerNetServerHandler.setPlayerLocation(par1EntityPlayerMP.posX, par1EntityPlayerMP.posY, par1EntityPlayerMP.posZ, par1EntityPlayerMP.rotationYaw, par1EntityPlayerMP.rotationPitch);
        par1EntityPlayerMP.theItemInWorldManager.setWorld(var5);
        this.updateTimeAndWeatherForPlayer(par1EntityPlayerMP, var5);
        this.syncPlayerInventory(par1EntityPlayerMP);
        Iterator var6 = par1EntityPlayerMP.getActivePotionEffects().iterator();

        while (var6.hasNext())
        {
            PotionEffect var7 = (PotionEffect)var6.next();
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet41EntityEffect(par1EntityPlayerMP.entityId, var7));
        }

        par1EntityPlayerMP.FlagStrongestMagneticPointDirty();
        par1EntityPlayerMP.m_lTimeOfLastDimensionSwitch = var5.getWorldTime();
    }

    /**
     * Transfers an entity from a world to another world.
     */
    public void transferEntityToWorld(Entity par1Entity, int par2, WorldServer par3WorldServer, WorldServer par4WorldServer)
    {
        double var5 = par1Entity.posX;
        double var7 = par1Entity.posZ;
        double var9 = 8.0D;
        double var11 = par1Entity.posX;
        double var13 = par1Entity.posY;
        double var15 = par1Entity.posZ;
        float var17 = par1Entity.rotationYaw;
        par3WorldServer.theProfiler.startSection("moving");

        if (par1Entity.dimension == -1)
        {
            var5 /= var9;
            var7 /= var9;
            par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);

            if (par1Entity.isEntityAlive())
            {
                par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
            }
        }
        else if (par1Entity.dimension == 0)
        {
            var5 *= var9;
            var7 *= var9;
            par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);

            if (par1Entity.isEntityAlive())
            {
                par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
            }
        }
        else
        {
            ChunkCoordinates var18;

            if (par2 == 1)
            {
                var18 = par4WorldServer.getSpawnPoint();
            }
            else
            {
                var18 = par4WorldServer.getEntrancePortalLocation();
            }

            var5 = (double)var18.posX;
            par1Entity.posY = (double)var18.posY;
            var7 = (double)var18.posZ;
            par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, 90.0F, 0.0F);

            if (par1Entity.isEntityAlive())
            {
                par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
            }
        }

        par3WorldServer.theProfiler.endSection();

        if (par2 != 1)
        {
            par3WorldServer.theProfiler.startSection("placing");
            var5 = (double)MathHelper.clamp_int((int)var5, -29999872, 29999872);
            var7 = (double)MathHelper.clamp_int((int)var7, -29999872, 29999872);

            if (par1Entity.isEntityAlive())
            {
                par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);
                this.FlagChunksAroundTeleportingEntityForCheckForUnload(par4WorldServer, par1Entity);
                par4WorldServer.getDefaultTeleporter().placeInPortal(par1Entity, var11, var13, var15, var17);
                par4WorldServer.spawnEntityInWorld(par1Entity);
                par4WorldServer.updateEntityWithOptionalForce(par1Entity, false);
            }

            par3WorldServer.theProfiler.endSection();
        }

        par1Entity.setWorld(par4WorldServer);
    }

    /**
     * self explanitory
     */
    public void onTick()
    {
        if (++this.playerPingIndex > 600)
        {
            this.playerPingIndex = 0;
        }

        if (this.playerPingIndex < this.playerEntityList.size())
        {
            EntityPlayerMP var1 = (EntityPlayerMP)this.playerEntityList.get(this.playerPingIndex);
            this.sendPacketToAllPlayers(new Packet201PlayerInfo(var1.username, true, var1.ping));
        }
    }

    /**
     * sends a packet to all players
     */
    public void sendPacketToAllPlayers(Packet par1Packet)
    {
        for (int var2 = 0; var2 < this.playerEntityList.size(); ++var2)
        {
            ((EntityPlayerMP)this.playerEntityList.get(var2)).playerNetServerHandler.sendPacket(par1Packet);
        }
    }

    /**
     * Sends a packet to all players in the specified Dimension
     */
    public void sendPacketToAllPlayersInDimension(Packet par1Packet, int par2)
    {
        for (int var3 = 0; var3 < this.playerEntityList.size(); ++var3)
        {
            EntityPlayerMP var4 = (EntityPlayerMP)this.playerEntityList.get(var3);

            if (var4.dimension == par2)
            {
                var4.playerNetServerHandler.sendPacket(par1Packet);
            }
        }
    }

    /**
     * returns a string containing a comma-seperated list of player names
     */
    public String getPlayerListAsString()
    {
        String var1 = "";

        for (int var2 = 0; var2 < this.playerEntityList.size(); ++var2)
        {
            if (var2 > 0)
            {
                var1 = var1 + ", ";
            }

            var1 = var1 + ((EntityPlayerMP)this.playerEntityList.get(var2)).username;
        }

        return var1;
    }

    /**
     * Returns an array of the usernames of all the connected players.
     */
    public String[] getAllUsernames()
    {
        String[] var1 = new String[this.playerEntityList.size()];

        for (int var2 = 0; var2 < this.playerEntityList.size(); ++var2)
        {
            var1[var2] = ((EntityPlayerMP)this.playerEntityList.get(var2)).username;
        }

        return var1;
    }

    public BanList getBannedPlayers()
    {
        return this.bannedPlayers;
    }

    public BanList getBannedIPs()
    {
        return this.bannedIPs;
    }

    /**
     * This adds a username to the ops list, then saves the op list
     */
    public void addOp(String par1Str)
    {
        this.ops.add(par1Str.toLowerCase());
    }

    /**
     * This removes a username from the ops list, then saves the op list
     */
    public void removeOp(String par1Str)
    {
        this.ops.remove(par1Str.toLowerCase());
    }

    /**
     * Determine if the player is allowed to connect based on current server settings.
     */
    public boolean isAllowedToLogin(String par1Str)
    {
        par1Str = par1Str.trim().toLowerCase();
        return !this.whiteListEnforced || this.ops.contains(par1Str) || this.whiteListedPlayers.contains(par1Str);
    }

    /**
     * Returns true if the specific player is allowed to use commands.
     */
    public boolean areCommandsAllowed(String par1Str)
    {
        return this.ops.contains(par1Str.trim().toLowerCase()) || this.mcServer.isSinglePlayer() && this.mcServer.worldServers[0].getWorldInfo().areCommandsAllowed() && this.mcServer.getServerOwner().equalsIgnoreCase(par1Str) || this.commandsAllowedForAll;
    }

    /**
     * gets the player entity for the player with the name specified
     */
    public EntityPlayerMP getPlayerEntity(String par1Str)
    {
        Iterator var2 = this.playerEntityList.iterator();

        while (var2.hasNext())
        {
            EntityPlayerMP var3 = (EntityPlayerMP)var2.next();

            if (var3.username.equalsIgnoreCase(par1Str))
            {
                return var3;
            }
        }

        return null;
    }

    /**
     * Find all players in a specified range and narrowing down by other parameters
     */
    public List findPlayers(ChunkCoordinates par1ChunkCoordinates, int par2, int par3, int par4, int par5, int par6, int par7, Map par8Map, String par9Str, String par10Str)
    {
        if (this.playerEntityList.isEmpty())
        {
            return null;
        }
        else
        {
            Object var11 = new ArrayList();
            boolean var12 = par4 < 0;
            int var13 = par2 * par2;
            int var14 = par3 * par3;
            par4 = MathHelper.abs_int(par4);

            for (int var15 = 0; var15 < this.playerEntityList.size(); ++var15)
            {
                EntityPlayerMP var16 = (EntityPlayerMP)this.playerEntityList.get(var15);
                boolean var17;

                if (par9Str != null)
                {
                    var17 = par9Str.startsWith("!");

                    if (var17)
                    {
                        par9Str = par9Str.substring(1);
                    }

                    if (var17 == par9Str.equalsIgnoreCase(var16.getEntityName()))
                    {
                        continue;
                    }
                }

                if (par10Str != null)
                {
                    var17 = par10Str.startsWith("!");

                    if (var17)
                    {
                        par10Str = par10Str.substring(1);
                    }

                    ScorePlayerTeam var18 = var16.getTeam();
                    String var19 = var18 == null ? "" : var18.func_96661_b();

                    if (var17 == par10Str.equalsIgnoreCase(var19))
                    {
                        continue;
                    }
                }

                if (par1ChunkCoordinates != null && (par2 > 0 || par3 > 0))
                {
                    float var20 = par1ChunkCoordinates.getDistanceSquaredToChunkCoordinates(var16.getCommandSenderPosition());

                    if (par2 > 0 && var20 < (float)var13 || par3 > 0 && var20 > (float)var14)
                    {
                        continue;
                    }
                }

                if (this.func_96457_a(var16, par8Map) && (par5 == EnumGameType.NOT_SET.getID() || par5 == var16.theItemInWorldManager.getGameType().getID()) && (par6 <= 0 || var16.experienceLevel >= par6) && var16.experienceLevel <= par7)
                {
                    ((List)var11).add(var16);
                }
            }

            if (par1ChunkCoordinates != null)
            {
                Collections.sort((List)var11, new PlayerPositionComparator(par1ChunkCoordinates));
            }

            if (var12)
            {
                Collections.reverse((List)var11);
            }

            if (par4 > 0)
            {
                var11 = ((List)var11).subList(0, Math.min(par4, ((List)var11).size()));
            }

            return (List)var11;
        }
    }

    private boolean func_96457_a(EntityPlayer par1EntityPlayer, Map par2Map)
    {
        if (par2Map != null && par2Map.size() != 0)
        {
            Iterator var3 = par2Map.entrySet().iterator();
            Entry var4;
            boolean var5;
            int var6;

            do
            {
                if (!var3.hasNext())
                {
                    return true;
                }

                var4 = (Entry)var3.next();
                String var7 = (String)var4.getKey();
                var5 = false;

                if (var7.endsWith("_min") && var7.length() > 4)
                {
                    var5 = true;
                    var7 = var7.substring(0, var7.length() - 4);
                }

                Scoreboard var8 = par1EntityPlayer.getWorldScoreboard();
                ScoreObjective var9 = var8.getObjective(var7);

                if (var9 == null)
                {
                    return false;
                }

                Score var10 = par1EntityPlayer.getWorldScoreboard().func_96529_a(par1EntityPlayer.getEntityName(), var9);
                var6 = var10.func_96652_c();

                if (var6 < ((Integer)var4.getValue()).intValue() && var5)
                {
                    return false;
                }
            }
            while (var6 <= ((Integer)var4.getValue()).intValue() || var5);

            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * sends a packet to players within d3 of point (x,y,z)
     */
    public void sendPacketToPlayersAroundPoint(double par1, double par3, double par5, double par7, int par9, Packet par10Packet)
    {
        this.sendToAllNearExcept((EntityPlayer)null, par1, par3, par5, par7, par9, par10Packet);
    }

    /**
     * params: srcPlayer,x,y,z,d,dimension. The packet is not sent to the srcPlayer, but all other players where
     * dx*dx+dy*dy+dz*dz<d*d
     */
    public void sendToAllNearExcept(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, double par8, int par10, Packet par11Packet)
    {
        for (int var12 = 0; var12 < this.playerEntityList.size(); ++var12)
        {
            EntityPlayerMP var13 = (EntityPlayerMP)this.playerEntityList.get(var12);

            if (var13 != par1EntityPlayer && var13.dimension == par10)
            {
                double var14 = par2 - var13.posX;
                double var16 = par4 - var13.posY;
                double var18 = par6 - var13.posZ;

                if (var14 * var14 + var16 * var16 + var18 * var18 < par8 * par8)
                {
                    var13.playerNetServerHandler.sendPacket(par11Packet);
                }
            }
        }
    }

    /**
     * Saves all of the players' current states.
     */
    public void saveAllPlayerData()
    {
        for (int var1 = 0; var1 < this.playerEntityList.size(); ++var1)
        {
            this.writePlayerData((EntityPlayerMP)this.playerEntityList.get(var1));
        }
    }

    /**
     * Add the specified player to the white list.
     */
    public void addToWhiteList(String par1Str)
    {
        this.whiteListedPlayers.add(par1Str);
    }

    /**
     * Remove the specified player from the whitelist.
     */
    public void removeFromWhitelist(String par1Str)
    {
        this.whiteListedPlayers.remove(par1Str);
    }

    /**
     * Returns the whitelisted players.
     */
    public Set getWhiteListedPlayers()
    {
        return this.whiteListedPlayers;
    }

    public Set getOps()
    {
        return this.ops;
    }

    /**
     * Either does nothing, or calls readWhiteList.
     */
    public void loadWhiteList() {}

    /**
     * Updates the time and weather for the given player to those of the given world
     */
    public void updateTimeAndWeatherForPlayer(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet4UpdateTime(par2WorldServer.getTotalWorldTime(), par2WorldServer.getWorldTime()));

        if (par2WorldServer.isRaining())
        {
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet70GameEvent(1, 1));
        }

        if (par2WorldServer.worldInfo.isThundering())
        {
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet70GameEvent(7, 1));
        }
    }

    /**
     * sends the players inventory to himself
     */
    public void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new Packet16BlockItemSwitch(par1EntityPlayerMP.inventory.currentItem));
    }

    /**
     * Returns the number of players currently on the server.
     */
    public int getCurrentPlayerCount()
    {
        return this.playerEntityList.size();
    }

    /**
     * Returns the maximum number of players allowed on the server.
     */
    public int getMaxPlayers()
    {
        return this.maxPlayers;
    }

    /**
     * Returns an array of usernames for which player.dat exists for.
     */
    public String[] getAvailablePlayerDat()
    {
        return this.mcServer.worldServers[0].getSaveHandler().getPlayerNBTManager().getAvailablePlayerDat();
    }

    public boolean isWhiteListEnabled()
    {
        return this.whiteListEnforced;
    }

    public void setWhiteListEnabled(boolean par1)
    {
        this.whiteListEnforced = par1;
    }

    public List getPlayerList(String par1Str)
    {
        ArrayList var2 = new ArrayList();
        Iterator var3 = this.playerEntityList.iterator();

        while (var3.hasNext())
        {
            EntityPlayerMP var4 = (EntityPlayerMP)var3.next();

            if (var4.getPlayerIP().equals(par1Str))
            {
                var2.add(var4);
            }
        }

        return var2;
    }

    /**
     * Gets the View Distance.
     */
    public int getViewDistance()
    {
        return this.viewDistance;
    }

    public MinecraftServer getServerInstance()
    {
        return this.mcServer;
    }

    /**
     * On integrated servers, returns the host's player data to be written to level.dat.
     */
    public NBTTagCompound getHostPlayerData()
    {
        return null;
    }

    private void func_72381_a(EntityPlayerMP par1EntityPlayerMP, EntityPlayerMP par2EntityPlayerMP, World par3World)
    {
        if (par2EntityPlayerMP != null)
        {
            par1EntityPlayerMP.theItemInWorldManager.setGameType(par2EntityPlayerMP.theItemInWorldManager.getGameType());
        }
        else if (this.gameType != null)
        {
            par1EntityPlayerMP.theItemInWorldManager.setGameType(this.gameType);
        }

        par1EntityPlayerMP.theItemInWorldManager.initializeGameType(par3World.getWorldInfo().getGameType());
    }

    /**
     * Kicks everyone with "Server closed" as reason.
     */
    public void removeAllPlayers()
    {
        while (!this.playerEntityList.isEmpty())
        {
            ((EntityPlayerMP)this.playerEntityList.get(0)).playerNetServerHandler.kickPlayer("Server closed");
        }
    }

    /**
     * Sends the given string to every player as chat message.
     */
    public void sendChatMsg(String par1Str)
    {
        this.mcServer.logInfo(par1Str);
        this.sendPacketToAllPlayers(new Packet3Chat(par1Str));
    }

    /**
     * Called on respawn
     */
    public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP par1EntityPlayerMP, int par2, boolean par3)
    {
        par1EntityPlayerMP.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(par1EntityPlayerMP);
        par1EntityPlayerMP.getServerForPlayer().getEntityTracker().untrackEntity(par1EntityPlayerMP);
        par1EntityPlayerMP.getServerForPlayer().GetChunkTracker().RemovePlayer(par1EntityPlayerMP);
        this.playerEntityList.remove(par1EntityPlayerMP);
        this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension).removePlayerEntityDangerously(par1EntityPlayerMP);
        ChunkCoordinates var4 = null;
        boolean var5 = false;
        String var6 = null;
        int var7 = par2;

        if (!par3 && par1EntityPlayerMP.HasRespawnCoordinates())
        {
            ChunkCoordinates var8 = new ChunkCoordinates();
            int var9 = par1EntityPlayerMP.GetValidatedRespawnCoordinates(this.mcServer.worldServerForDimension(par1EntityPlayerMP.m_iSpawnDimension), var8);

            if (var9 == 0)
            {
                var4 = var8;
                var7 = par1EntityPlayerMP.m_iSpawnDimension;
            }
            else if (var9 == 1)
            {
                var6 = "Your respawn location was invalid";
            }
            else if (var9 == 2)
            {
                var6 = "The beacon to which you were bound is no longer present";
            }
            else if (var9 == 3)
            {
                var6 = "The beacon to which you are bound was too far away";
                var5 = true;
            }
            else if (var9 == 4)
            {
                var6 = "The beacon to which you are bound was obstructed";
                var5 = true;
            }
            else
            {
                var6 = "Your respawn failed for an unknown reason";
            }
        }

        Object var11;

        if (this.mcServer.isDemo())
        {
            var11 = new DemoWorldManager(this.mcServer.worldServerForDimension(var7));
        }
        else
        {
            var11 = new ItemInWorldManager(this.mcServer.worldServerForDimension(var7));
        }

        EntityPlayerMP var12 = new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(var7), par1EntityPlayerMP.username, (ItemInWorldManager)var11);
        var12.playerNetServerHandler = par1EntityPlayerMP.playerNetServerHandler;
        par1EntityPlayerMP.dimension = var7;
        var12.clonePlayer(par1EntityPlayerMP, par3);
        var12.entityId = par1EntityPlayerMP.entityId;
        WorldServer var10 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        this.func_72381_a(var12, par1EntityPlayerMP, var10);

        if (var4 != null)
        {
            var12.setLocationAndAngles((double)((float)var4.posX + 0.5F), (double)((float)var4.posY + 0.1F), (double)((float)var4.posZ + 0.5F), 0.0F, 0.0F);
            var5 = true;
        }
        else if (!par3)
        {
            FCUtilsHardcoreSpawn.HandleHardcoreSpawn(this.mcServer, par1EntityPlayerMP, var12);
        }

        if (var5)
        {
            var12.setSpawnChunk(par1EntityPlayerMP.getBedLocation(), par1EntityPlayerMP.isSpawnForced(), par1EntityPlayerMP.m_iSpawnDimension);
        }

        if (var6 != null)
        {
            FCUtilsWorld.SendPacketToPlayer(var12.playerNetServerHandler, new Packet3Chat(var6));
        }

        var10.theChunkProviderServer.loadChunk((int)var12.posX >> 4, (int)var12.posZ >> 4);

        while (!var10.getCollidingBoundingBoxes(var12, var12.boundingBox).isEmpty())
        {
            var12.setPosition(var12.posX, var12.posY + 1.0D, var12.posZ);
        }

        var12.playerNetServerHandler.sendPacket(new Packet9Respawn(var12.dimension, (byte)var12.worldObj.difficultySetting, var12.worldObj.getWorldInfo().getTerrainType(), var12.worldObj.getHeight(), var12.theItemInWorldManager.getGameType()));
        var4 = var10.getSpawnPoint();
        var12.playerNetServerHandler.sendPacket(new Packet6SpawnPosition(var4.posX, var4.posY, var4.posZ));
        var12.playerNetServerHandler.sendPacket(new Packet43Experience(var12.experience, var12.experienceTotal, var12.experienceLevel));
        this.updateTimeAndWeatherForPlayer(var12, var10);
        var10.GetChunkTracker().AddPlayer(var12);
        var10.spawnEntityInWorld(var12);
        this.playerEntityList.add(var12);
        var12.addSelfToInternalCraftingInventory();
        var12.setEntityHealth(var12.getHealth());
        var12.playerNetServerHandler.setPlayerLocation(var12.posX, var12.posY, var12.posZ, var12.rotationYaw, var12.rotationPitch);
        return var12;
    }

    private void FlagChunksAroundTeleportingEntityForCheckForUnload(WorldServer var1, Entity var2)
    {
        int var3 = MathHelper.floor_double(var2.posX / 16.0D);
        int var4 = MathHelper.floor_double(var2.posZ / 16.0D);
        byte var5 = 9;
        var1.AddChunkRangeToCheckForUnloadList(var3 - var5, var4 - var5, var3 + var5, var4 + var5);
    }
}
