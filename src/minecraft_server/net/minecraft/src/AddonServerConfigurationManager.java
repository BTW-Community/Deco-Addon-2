package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.server.MinecraftServer;

public class AddonServerConfigurationManager extends ServerConfigurationManager {
	private MinecraftServer mcServer;
    private EnumGameType gameType;

	public AddonServerConfigurationManager(MinecraftServer par1MinecraftServer) {
		super(par1MinecraftServer);
        this.mcServer = par1MinecraftServer;
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
            var5 = new AddonItemInWorldManager(this.mcServer.worldServerForDimension(0));
        }

        return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), par1Str, (ItemInWorldManager)var5);
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
        NetServerHandler var7 = new AddonNetServerHandler(this.mcServer, par1INetworkManager, par2EntityPlayerMP);
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
}