package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
}