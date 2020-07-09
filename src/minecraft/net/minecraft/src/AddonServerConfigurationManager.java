package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.server.MinecraftServer;

public class AddonServerConfigurationManager extends ServerConfigurationManager {
	private final MinecraftServer mcServer;
    private EnumGameType gameType;
	
	public AddonServerConfigurationManager(MinecraftServer par1MinecraftServer) {
		super(par1MinecraftServer);
		mcServer = par1MinecraftServer;
	}

    public void initializeConnectionToPlayer(INetworkManager networkManager, EntityPlayerMP player)
    {
        NBTTagCompound playerNBT = this.readPlayerDataFromFile(player);
        player.setWorld(this.mcServer.worldServerForDimension(player.dimension));
        player.theItemInWorldManager.setWorld((WorldServer)player.worldObj);
        String socketAddress = "local";

        if (networkManager.getSocketAddress() != null)
        {
            socketAddress = networkManager.getSocketAddress().toString();
        }

        this.mcServer.getLogAgent().logInfo(player.username + "[" + socketAddress + "] logged in with entity id " + player.entityId + " at (" + player.posX + ", " + player.posY + ", " + player.posZ + ")");
        WorldServer worldServer = this.mcServer.worldServerForDimension(player.dimension);
        ChunkCoordinates spawnPoint = worldServer.getSpawnPoint();
        this.func_72381_a(player, (EntityPlayerMP)null, worldServer);
        NetServerHandler netServerHandler = new NetServerHandler(this.mcServer, networkManager, player);
        netServerHandler.sendPacketToPlayer(new Packet1Login(player.entityId, worldServer.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType(), worldServer.getWorldInfo().isHardcoreModeEnabled(), worldServer.provider.dimensionId, worldServer.difficultySetting, worldServer.getHeight(), this.getMaxPlayers()));
        netServerHandler.sendPacketToPlayer(new Packet6SpawnPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ));
        netServerHandler.sendPacketToPlayer(new Packet202PlayerAbilities(player.capabilities));
        netServerHandler.sendPacketToPlayer(new Packet16BlockItemSwitch(player.inventory.currentItem));
        this.func_96456_a((ServerScoreboard)worldServer.getScoreboard(), player);
        this.updateTimeAndWeatherForPlayer(player, worldServer);
        this.sendPacketToAllPlayers(new Packet3Chat(EnumChatFormatting.YELLOW + player.getTranslatedEntityName() + EnumChatFormatting.YELLOW + " joined the game."));
        this.playerLoggedIn(player);
        netServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        this.mcServer.getNetworkThread().addPlayer(netServerHandler);
        netServerHandler.sendPacketToPlayer(new Packet4UpdateTime(worldServer.getTotalWorldTime(), worldServer.getWorldTime()));

        if (this.mcServer.getTexturePack().length() > 0)
        {
            player.requestTexturePackLoad(this.mcServer.getTexturePack(), this.mcServer.textureSize());
        }

        Iterator potionIterator = player.getActivePotionEffects().iterator();

        while (potionIterator.hasNext())
        {
            PotionEffect var9 = (PotionEffect)potionIterator.next();
            netServerHandler.sendPacketToPlayer(new Packet41EntityEffect(player.entityId, var9));
        }

        player.addSelfToInternalCraftingInventory();

        if (playerNBT != null && playerNBT.hasKey("Riding"))
        {
            Entity riddenEntity = EntityList.createEntityFromNBT(playerNBT.getCompoundTag("Riding"), worldServer);

            if (riddenEntity != null)
            {
                riddenEntity.field_98038_p = true;
                worldServer.spawnEntityInWorld(riddenEntity);
                player.mountEntity(riddenEntity);
                riddenEntity.field_98038_p = false;
            }
        }

        FCBetterThanWolves.ServerPlayerConnectionInitialized(netServerHandler, player);
        AddonManager.ServerPlayerConnectionInitialized(netServerHandler, player);
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
            var3.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
        }

        Object var5;

        if (this.mcServer.isDemo())
        {
            var5 = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
        }
        else
        {
            var5 = new AddonItemInWorldManager(this.mcServer.worldServerForDimension(0));
        }

        return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), par1Str, (ItemInWorldManager)var5);
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
     * creates and returns a respawned player based on the provided PlayerEntity. Args are the PlayerEntityMP to
     * respawn, an INT for the dimension to respawn into (usually 0), and a boolean value that is true if the player
     * beat the game rather than dying
     */
    public EntityPlayerMP respawnPlayer(EntityPlayerMP par1EntityPlayerMP, int par2, boolean par3)
    {
        par1EntityPlayerMP.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(par1EntityPlayerMP);
        par1EntityPlayerMP.getServerForPlayer().getEntityTracker().removeEntityFromAllTrackingPlayers(par1EntityPlayerMP);
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
            var11 = new AddonItemInWorldManager(this.mcServer.worldServerForDimension(var7));
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

        var12.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(var12.dimension, (byte)var12.worldObj.difficultySetting, var12.worldObj.getWorldInfo().getTerrainType(), var12.worldObj.getHeight(), var12.theItemInWorldManager.getGameType()));
        var4 = var10.getSpawnPoint();
        var12.playerNetServerHandler.sendPacketToPlayer(new Packet6SpawnPosition(var4.posX, var4.posY, var4.posZ));
        var12.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(var12.experience, var12.experienceTotal, var12.experienceLevel));
        this.updateTimeAndWeatherForPlayer(var12, var10);
        var10.GetChunkTracker().AddPlayer(var12);
        var10.spawnEntityInWorld(var12);
        this.playerEntityList.add(var12);
        var12.addSelfToInternalCraftingInventory();
        var12.setEntityHealth(var12.getHealth());
        var12.playerNetServerHandler.setPlayerLocation(var12.posX, var12.posY, var12.posZ, var12.rotationYaw, var12.rotationPitch);
        return var12;
    }
}