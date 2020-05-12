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

    public void initializeConnectionToPlayer(INetworkManager par1INetworkManager, EntityPlayerMP par2EntityPlayerMP)
    {
        NBTTagCompound var3 = this.readPlayerDataFromFile(par2EntityPlayerMP);
        par2EntityPlayerMP.setWorld(this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension));
        par2EntityPlayerMP.theItemInWorldManager.setWorld((WorldServer)par2EntityPlayerMP.worldObj);
        String var4 = "local";

        if (par1INetworkManager.getSocketAddress() != null)
        {
            var4 = par1INetworkManager.getSocketAddress().toString();
        }

        this.mcServer.getLogAgent().logInfo(par2EntityPlayerMP.username + "[" + var4 + "] logged in with entity id " + par2EntityPlayerMP.entityId + " at (" + par2EntityPlayerMP.posX + ", " + par2EntityPlayerMP.posY + ", " + par2EntityPlayerMP.posZ + ")");
        WorldServer var5 = this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension);
        ChunkCoordinates var6 = var5.getSpawnPoint();
        this.func_72381_a(par2EntityPlayerMP, (EntityPlayerMP)null, var5);
        NetServerHandler var7 = new NetServerHandler(this.mcServer, par1INetworkManager, par2EntityPlayerMP);
        var7.sendPacketToPlayer(new Packet1Login(par2EntityPlayerMP.entityId, var5.getWorldInfo().getTerrainType(), par2EntityPlayerMP.theItemInWorldManager.getGameType(), var5.getWorldInfo().isHardcoreModeEnabled(), var5.provider.dimensionId, var5.difficultySetting, var5.getHeight(), this.getMaxPlayers()));
        var7.sendPacketToPlayer(new Packet6SpawnPosition(var6.posX, var6.posY, var6.posZ));
        var7.sendPacketToPlayer(new Packet202PlayerAbilities(par2EntityPlayerMP.capabilities));
        var7.sendPacketToPlayer(new Packet16BlockItemSwitch(par2EntityPlayerMP.inventory.currentItem));
        this.func_96456_a((ServerScoreboard)var5.getScoreboard(), par2EntityPlayerMP);
        this.updateTimeAndWeatherForPlayer(par2EntityPlayerMP, var5);
        this.sendPacketToAllPlayers(new Packet3Chat(EnumChatFormatting.YELLOW + par2EntityPlayerMP.getTranslatedEntityName() + EnumChatFormatting.YELLOW + " joined the game."));
        this.playerLoggedIn(par2EntityPlayerMP);
        var7.setPlayerLocation(par2EntityPlayerMP.posX, par2EntityPlayerMP.posY, par2EntityPlayerMP.posZ, par2EntityPlayerMP.rotationYaw, par2EntityPlayerMP.rotationPitch);
        this.mcServer.getNetworkThread().addPlayer(var7);
        var7.sendPacketToPlayer(new Packet4UpdateTime(var5.getTotalWorldTime(), var5.getWorldTime()));

        if (this.mcServer.getTexturePack().length() > 0)
        {
            par2EntityPlayerMP.requestTexturePackLoad(this.mcServer.getTexturePack(), this.mcServer.textureSize());
        }

        Iterator var8 = par2EntityPlayerMP.getActivePotionEffects().iterator();

        while (var8.hasNext())
        {
            PotionEffect var9 = (PotionEffect)var8.next();
            var7.sendPacketToPlayer(new Packet41EntityEffect(par2EntityPlayerMP.entityId, var9));
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
        AddonManager.ServerPlayerConnectionInitialized(var7, par2EntityPlayerMP);
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

    public void setGameType(EnumGameType par1EnumGameType)
    {
        this.gameType = par1EnumGameType;
    }
}