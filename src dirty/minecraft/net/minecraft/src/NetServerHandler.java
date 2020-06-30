package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class NetServerHandler extends NetHandler
{
    /** The underlying network manager for this server handler. */
    public final INetworkManager netManager;

    /** Reference to the MinecraftServer object. */
    private final MinecraftServer mcServer;

    /** This is set to true whenever a player disconnects from the server. */
    public boolean connectionClosed = false;

    /** Reference to the EntityPlayerMP object. */
    public EntityPlayerMP playerEntity;

    /** incremented each tick */
    private int currentTicks;

    /**
     * player is kicked if they float for over 80 ticks without flying enabled
     */
    private int ticksForFloatKick;
    private boolean field_72584_h;
    private int keepAliveRandomID;
    private long keepAliveTimeSent;
    private static Random randomGenerator = new Random();
    private long ticksOfLastKeepAlive;
    private int chatSpamThresholdCount = 0;
    private int creativeItemCreationSpamThresholdTally = 0;

    /** The last known x position for this connection. */
    private double lastPosX;

    /** The last known y position for this connection. */
    private double lastPosY;

    /** The last known z position for this connection. */
    private double lastPosZ;

    /** is true when the player has moved since his last movement packet */
    private boolean hasMoved = true;
    private IntHashMap field_72586_s = new IntHashMap();

    public NetServerHandler(MinecraftServer par1, INetworkManager par2, EntityPlayerMP par3)
    {
        this.mcServer = par1;
        this.netManager = par2;
        par2.setNetHandler(this);
        this.playerEntity = par3;
        par3.playerNetServerHandler = this;
    }

    /**
     * run once each game tick
     */
    public void networkTick()
    {
        this.field_72584_h = false;
        ++this.currentTicks;
        this.mcServer.theProfiler.startSection("packetflow");
        this.netManager.processReadPackets();
        this.mcServer.theProfiler.endStartSection("keepAlive");

        if ((long)this.currentTicks - this.ticksOfLastKeepAlive > 20L)
        {
            this.ticksOfLastKeepAlive = (long)this.currentTicks;
            this.keepAliveTimeSent = System.nanoTime() / 1000000L;
            this.keepAliveRandomID = randomGenerator.nextInt();
            this.sendPacketToPlayer(new Packet0KeepAlive(this.keepAliveRandomID));
        }

        if (this.chatSpamThresholdCount > 0)
        {
            --this.chatSpamThresholdCount;
        }

        if (this.creativeItemCreationSpamThresholdTally > 0)
        {
            --this.creativeItemCreationSpamThresholdTally;
        }

        this.mcServer.theProfiler.endStartSection("playerTick");
        this.mcServer.theProfiler.endSection();
    }

    public void kickPlayerFromServer(String par1Str)
    {
        if (!this.connectionClosed)
        {
            this.playerEntity.mountEntityAndWakeUp();
            this.sendPacketToPlayer(new Packet255KickDisconnect(par1Str));
            this.netManager.serverShutdown();
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet3Chat(EnumChatFormatting.YELLOW + this.playerEntity.username + " left the game."));
            this.mcServer.getConfigurationManager().playerLoggedOut(this.playerEntity);
            this.connectionClosed = true;
        }
    }

    public void handleFlying(Packet10Flying par1Packet10Flying)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
        this.field_72584_h = true;

        if (!this.playerEntity.playerConqueredTheEnd)
        {
            double var3;

            if (!this.hasMoved)
            {
                var3 = par1Packet10Flying.yPosition - this.lastPosY;

                if (par1Packet10Flying.xPosition == this.lastPosX && var3 * var3 < 0.01D && par1Packet10Flying.zPosition == this.lastPosZ)
                {
                    this.hasMoved = true;
                }
            }

            if (this.hasMoved)
            {
                double var5;
                double var7;
                double var9;
                double var11;
                float var13;
                float var14;
                double var15;

                if (this.playerEntity.ridingEntity != null)
                {
                    var13 = this.playerEntity.rotationYaw;
                    var14 = this.playerEntity.rotationPitch;
                    this.playerEntity.ridingEntity.updateRiderPosition();
                    var5 = this.playerEntity.posX;
                    var7 = this.playerEntity.posY;
                    var9 = this.playerEntity.posZ;
                    var15 = 0.0D;
                    var11 = 0.0D;

                    if (par1Packet10Flying.rotating)
                    {
                        var13 = par1Packet10Flying.yaw;
                        var14 = par1Packet10Flying.pitch;
                    }

                    if (par1Packet10Flying.moving && par1Packet10Flying.yPosition == -999.0D && par1Packet10Flying.stance == -999.0D)
                    {
                        if (Math.abs(par1Packet10Flying.xPosition) > 1.0D || Math.abs(par1Packet10Flying.zPosition) > 1.0D)
                        {
                            System.err.println(this.playerEntity.username + " was caught trying to crash the server with an invalid position.");
                            this.kickPlayerFromServer("Nope!");
                            return;
                        }

                        var15 = par1Packet10Flying.xPosition;
                        var11 = par1Packet10Flying.zPosition;
                    }

                    this.playerEntity.onGround = par1Packet10Flying.onGround;
                    this.playerEntity.onUpdateEntity();
                    this.playerEntity.moveEntity(var15, 0.0D, var11);
                    this.playerEntity.setPositionAndRotation(var5, var7, var9, var13, var14);
                    this.playerEntity.motionX = var15;
                    this.playerEntity.motionZ = var11;

                    if (this.playerEntity.ridingEntity != null)
                    {
                        var2.uncheckedUpdateEntity(this.playerEntity.ridingEntity, true);
                    }

                    if (this.playerEntity.ridingEntity != null)
                    {
                        this.playerEntity.ridingEntity.updateRiderPosition();
                    }

                    this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
                    this.lastPosX = this.playerEntity.posX;
                    this.lastPosY = this.playerEntity.posY;
                    this.lastPosZ = this.playerEntity.posZ;
                    var2.updateEntity(this.playerEntity);
                    return;
                }

                if (this.playerEntity.isPlayerSleeping())
                {
                    this.playerEntity.onUpdateEntity();
                    this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
                    var2.updateEntity(this.playerEntity);
                    return;
                }

                var3 = this.playerEntity.posY;
                this.lastPosX = this.playerEntity.posX;
                this.lastPosY = this.playerEntity.posY;
                this.lastPosZ = this.playerEntity.posZ;
                var5 = this.playerEntity.posX;
                var7 = this.playerEntity.posY;
                var9 = this.playerEntity.posZ;
                var13 = this.playerEntity.rotationYaw;
                var14 = this.playerEntity.rotationPitch;

                if (par1Packet10Flying.moving && par1Packet10Flying.yPosition == -999.0D && par1Packet10Flying.stance == -999.0D)
                {
                    par1Packet10Flying.moving = false;
                }

                if (par1Packet10Flying.moving)
                {
                    var5 = par1Packet10Flying.xPosition;
                    var7 = par1Packet10Flying.yPosition;
                    var9 = par1Packet10Flying.zPosition;
                    var11 = par1Packet10Flying.stance - par1Packet10Flying.yPosition;

                    if (!this.playerEntity.isPlayerSleeping() && (var11 > 1.65D || var11 < 0.1D))
                    {
                        this.kickPlayerFromServer("Illegal stance");
                        this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " had an illegal stance: " + var11);
                        return;
                    }

                    if (Math.abs(par1Packet10Flying.xPosition) > 3.2E7D || Math.abs(par1Packet10Flying.zPosition) > 3.2E7D)
                    {
                        this.kickPlayerFromServer("Illegal position");
                        return;
                    }
                }

                if (par1Packet10Flying.rotating)
                {
                    var13 = par1Packet10Flying.yaw;
                    var14 = par1Packet10Flying.pitch;
                }

                this.playerEntity.onUpdateEntity();
                this.playerEntity.ySize = 0.0F;
                this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, var13, var14);

                if (!this.hasMoved)
                {
                    return;
                }

                var11 = var5 - this.playerEntity.posX;
                var15 = var7 - this.playerEntity.posY;
                double var17 = var9 - this.playerEntity.posZ;
                double var19 = Math.min(Math.abs(var11), Math.abs(this.playerEntity.motionX));
                double var21 = Math.min(Math.abs(var15), Math.abs(this.playerEntity.motionY));
                double var23 = Math.min(Math.abs(var17), Math.abs(this.playerEntity.motionZ));
                double var25 = var19 * var19 + var21 * var21 + var23 * var23;

                if (var25 > 100.0D && (!this.mcServer.isSinglePlayer() || !this.mcServer.getServerOwner().equals(this.playerEntity.username)))
                {
                    this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " moved too quickly! " + var11 + "," + var15 + "," + var17 + " (" + var19 + ", " + var21 + ", " + var23 + ")");
                    this.setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
                    return;
                }

                float var27 = 0.0625F;
                boolean var28 = this.getCollidingBoundingBoxesIgnoreSpecifiedEntities(var2, this.playerEntity, this.playerEntity.boundingBox.copy().contract((double)var27, (double)var27, (double)var27)).isEmpty();

                if (this.playerEntity.onGround && !par1Packet10Flying.onGround && var15 > 0.0D && !this.playerEntity.isOnLadder())
                {
                    this.playerEntity.AddExhaustionForJump();
                }

                this.playerEntity.moveEntity(var11, var15, var17);
                this.playerEntity.onGround = par1Packet10Flying.onGround;
                this.playerEntity.addMovementStat(var11, var15, var17);
                double var29 = var15;
                var11 = var5 - this.playerEntity.posX;
                var15 = var7 - this.playerEntity.posY;

                if (var15 > -0.5D || var15 < 0.5D)
                {
                    var15 = 0.0D;
                }

                var17 = var9 - this.playerEntity.posZ;
                var25 = var11 * var11 + var15 * var15 + var17 * var17;
                boolean var31 = false;

                if (var25 > 0.0625D && !this.playerEntity.isPlayerSleeping() && !this.playerEntity.theItemInWorldManager.isCreative())
                {
                    var31 = true;
                    this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " moved wrongly!");
                }

                this.playerEntity.setPositionAndRotation(var5, var7, var9, var13, var14);
                boolean var32 = this.getCollidingBoundingBoxesIgnoreSpecifiedEntities(var2, this.playerEntity, this.playerEntity.boundingBox.copy().contract((double)var27, (double)var27, (double)var27)).isEmpty();

                if (var28 && (var31 || !var32) && !this.playerEntity.isPlayerSleeping())
                {
                    this.setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, var13, var14);
                    return;
                }

                AxisAlignedBB var33 = this.playerEntity.boundingBox.copy().expand((double)var27, (double)var27, (double)var27).addCoord(0.0D, -0.55D, 0.0D);

                if (!this.mcServer.isFlightAllowed() && !this.playerEntity.theItemInWorldManager.isCreative() && !var2.checkBlockCollision(var33) && var2.checkNoEntityCollision(var33, this.playerEntity))
                {
                    if (var29 >= -0.03125D)
                    {
                        ++this.ticksForFloatKick;

                        if (this.ticksForFloatKick > 80)
                        {
                            this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " was kicked for floating too long!");
                            this.kickPlayerFromServer("Flying is not enabled on this server");
                            return;
                        }
                    }
                }
                else
                {
                    this.ticksForFloatKick = 0;
                }

                this.playerEntity.onGround = par1Packet10Flying.onGround;
                this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
                this.playerEntity.updateFlyingState(this.playerEntity.posY - var3, par1Packet10Flying.onGround);
            }
        }
    }

    /**
     * Moves the player to the specified destination and rotation
     */
    public void setPlayerLocation(double par1, double par3, double par5, float par7, float par8)
    {
        this.hasMoved = false;
        this.lastPosX = par1;
        this.lastPosY = par3;
        this.lastPosZ = par5;
        this.playerEntity.setPositionAndRotation(par1, par3, par5, par7, par8);
        this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet13PlayerLookMove(par1, par3 + 1.6200000047683716D, par3, par5, par7, par8, false));
    }

    public void handleBlockDig(Packet14BlockDig par1Packet14BlockDig)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);

        if (par1Packet14BlockDig.status == 4)
        {
            this.playerEntity.dropOneItem(false);
        }
        else if (par1Packet14BlockDig.status == 3)
        {
            this.playerEntity.dropOneItem(true);
        }
        else if (par1Packet14BlockDig.status == 5)
        {
            this.playerEntity.stopUsingItem();
        }
        else
        {
            boolean var3 = false;

            if (par1Packet14BlockDig.status == 0)
            {
                var3 = true;
            }

            if (par1Packet14BlockDig.status == 1)
            {
                var3 = true;
            }

            if (par1Packet14BlockDig.status == 2)
            {
                var3 = true;
            }

            int var4 = par1Packet14BlockDig.xPosition;
            int var5 = par1Packet14BlockDig.yPosition;
            int var6 = par1Packet14BlockDig.zPosition;

            if (var3)
            {
                double var7 = this.playerEntity.posX - ((double)var4 + 0.5D);
                double var9 = this.playerEntity.posY - ((double)var5 + 0.5D) + 1.5D;
                double var11 = this.playerEntity.posZ - ((double)var6 + 0.5D);
                double var13 = var7 * var7 + var9 * var9 + var11 * var11;

                if (var13 > 36.0D)
                {
                    return;
                }

                if (var5 >= this.mcServer.getBuildLimit())
                {
                    return;
                }
            }

            if (par1Packet14BlockDig.status == 0)
            {
                if (!this.mcServer.func_96290_a(var2, var4, var5, var6, this.playerEntity))
                {
                    this.playerEntity.theItemInWorldManager.onBlockClicked(var4, var5, var6, par1Packet14BlockDig.face);
                }
                else
                {
                    this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var4, var5, var6, var2));
                }
            }
            else if (par1Packet14BlockDig.status == 2)
            {
                this.playerEntity.theItemInWorldManager.uncheckedTryHarvestBlock(var4, var5, var6, par1Packet14BlockDig.face);

                if (var2.getBlockId(var4, var5, var6) != 0)
                {
                    this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var4, var5, var6, var2));
                }
            }
            else if (par1Packet14BlockDig.status == 1)
            {
                this.playerEntity.theItemInWorldManager.cancelDestroyingBlock(var4, var5, var6);

                if (var2.getBlockId(var4, var5, var6) != 0)
                {
                    this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var4, var5, var6, var2));
                }
            }
        }
    }

    public void handlePlace(Packet15Place par1Packet15Place)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
        ItemStack var3 = this.playerEntity.inventory.getCurrentItem();
        boolean var4 = false;
        int var5 = par1Packet15Place.getXPosition();
        int var6 = par1Packet15Place.getYPosition();
        int var7 = par1Packet15Place.getZPosition();
        int var8 = par1Packet15Place.getDirection();

        if (par1Packet15Place.getDirection() == 255)
        {
            if (var3 == null)
            {
                return;
            }

            this.playerEntity.theItemInWorldManager.tryUseItem(this.playerEntity, var2, var3);
        }
        else if (par1Packet15Place.getYPosition() >= this.mcServer.getBuildLimit() - 1 && (par1Packet15Place.getDirection() == 1 || par1Packet15Place.getYPosition() >= this.mcServer.getBuildLimit()))
        {
            this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("" + EnumChatFormatting.GRAY + "Height limit for building is " + this.mcServer.getBuildLimit()));
            var4 = true;
        }
        else
        {
            if (this.hasMoved && this.playerEntity.getDistanceSq((double)var5 + 0.5D, (double)var6 + 0.5D, (double)var7 + 0.5D) < 64.0D && !this.mcServer.func_96290_a(var2, var5, var6, var7, this.playerEntity))
            {
                this.playerEntity.theItemInWorldManager.activateBlockOrUseItem(this.playerEntity, var2, var3, var5, var6, var7, var8, par1Packet15Place.getXOffset(), par1Packet15Place.getYOffset(), par1Packet15Place.getZOffset());
            }

            var4 = true;
        }

        if (var4)
        {
            this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var5, var6, var7, var2));

            if (var8 == 0)
            {
                --var6;
            }

            if (var8 == 1)
            {
                ++var6;
            }

            if (var8 == 2)
            {
                --var7;
            }

            if (var8 == 3)
            {
                ++var7;
            }

            if (var8 == 4)
            {
                --var5;
            }

            if (var8 == 5)
            {
                ++var5;
            }

            this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var5, var6, var7, var2));
        }

        var3 = this.playerEntity.inventory.getCurrentItem();

        if (var3 != null && var3.stackSize == 0)
        {
            this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = null;
            var3 = null;
        }

        if (var3 == null || var3.getMaxItemUseDuration() == 0)
        {
            this.playerEntity.playerInventoryBeingManipulated = true;
            this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = ItemStack.copyItemStack(this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem]);
            Slot var9 = this.playerEntity.openContainer.getSlotFromInventory(this.playerEntity.inventory, this.playerEntity.inventory.currentItem);
            this.playerEntity.openContainer.detectAndSendChanges();
            this.playerEntity.playerInventoryBeingManipulated = false;

            if (!ItemStack.areItemStacksEqual(this.playerEntity.inventory.getCurrentItem(), par1Packet15Place.getItemStack()))
            {
                this.sendPacketToPlayer(new Packet103SetSlot(this.playerEntity.openContainer.windowId, var9.slotNumber, this.playerEntity.inventory.getCurrentItem()));
            }
        }
    }

    public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj)
    {
        this.mcServer.getLogAgent().logInfo(this.playerEntity.username + " lost connection: " + par1Str);
        this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet3Chat(EnumChatFormatting.YELLOW + this.playerEntity.getTranslatedEntityName() + " left the game."));
        this.mcServer.getConfigurationManager().playerLoggedOut(this.playerEntity);
        this.connectionClosed = true;

        if (this.mcServer.isSinglePlayer() && this.playerEntity.username.equals(this.mcServer.getServerOwner()))
        {
            this.mcServer.getLogAgent().logInfo("Stopping singleplayer server as player logged out");
            this.mcServer.initiateShutdown();
        }
    }

    /**
     * Default handler called for packets that don't have their own handlers in NetClientHandler; currentlly does
     * nothing.
     */
    public void unexpectedPacket(Packet par1Packet)
    {
        this.mcServer.getLogAgent().logWarning(this.getClass() + " wasn\'t prepared to deal with a " + par1Packet.getClass());
        this.kickPlayerFromServer("Protocol error, unexpected packet");
    }

    public void sendPacket(Packet var1)
    {
        this.sendPacketToPlayer(var1);
    }

    /**
     * addToSendQueue. if it is a chat packet, check before sending it
     */
    public void sendPacketToPlayer(Packet par1Packet)
    {
        if (par1Packet instanceof Packet3Chat)
        {
            Packet3Chat var2 = (Packet3Chat)par1Packet;
            int var3 = this.playerEntity.getChatVisibility();

            if (var3 == 2)
            {
                return;
            }

            if (var3 == 1 && !var2.getIsServer())
            {
                return;
            }
        }

        try
        {
            this.netManager.addToSendQueue(par1Packet);
        }
        catch (Throwable var5)
        {
            CrashReport var6 = CrashReport.makeCrashReport(var5, "Sending packet");
            CrashReportCategory var4 = var6.makeCategory("Packet being sent");
            var4.addCrashSectionCallable("Packet ID", new CallablePacketID(this, par1Packet));
            var4.addCrashSectionCallable("Packet class", new CallablePacketClass(this, par1Packet));
            throw new ReportedException(var6);
        }
    }

    public void handleBlockItemSwitch(Packet16BlockItemSwitch par1Packet16BlockItemSwitch)
    {
        if (par1Packet16BlockItemSwitch.id >= 0 && par1Packet16BlockItemSwitch.id < InventoryPlayer.getHotbarSize())
        {
            this.playerEntity.inventory.currentItem = par1Packet16BlockItemSwitch.id;
        }
        else
        {
            this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " tried to set an invalid carried item");
        }
    }

    public void handleChat(Packet3Chat par1Packet3Chat)
    {
        if (this.playerEntity.getChatVisibility() == 2)
        {
            this.sendPacketToPlayer(new Packet3Chat("Cannot send chat message."));
        }
        else
        {
            String var2 = par1Packet3Chat.message;

            if (var2.length() > 100)
            {
                this.kickPlayerFromServer("Chat message too long");
            }
            else
            {
                var2 = var2.trim();

                for (int var3 = 0; var3 < var2.length(); ++var3)
                {
                    if (!ChatAllowedCharacters.isAllowedCharacter(var2.charAt(var3)))
                    {
                        this.kickPlayerFromServer("Illegal characters in chat");
                        return;
                    }
                }

                if (var2.startsWith("/"))
                {
                    this.handleSlashCommand(var2);
                }
                else
                {
                    if (this.playerEntity.getChatVisibility() == 1)
                    {
                        this.sendPacketToPlayer(new Packet3Chat("Cannot send chat message."));
                        return;
                    }

                    var2 = "<" + this.playerEntity.getTranslatedEntityName() + "> " + var2;
                    this.mcServer.getLogAgent().logInfo(var2);
                    this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet3Chat(var2, false));
                }

                this.chatSpamThresholdCount += 20;

                if (this.chatSpamThresholdCount > 200 && !this.mcServer.getConfigurationManager().areCommandsAllowed(this.playerEntity.username))
                {
                    this.kickPlayerFromServer("disconnect.spam");
                }
            }
        }
    }

    /**
     * Processes a / command
     */
    private void handleSlashCommand(String par1Str)
    {
        this.mcServer.getCommandManager().executeCommand(this.playerEntity, par1Str);
    }

    public void handleAnimation(Packet18Animation par1Packet18Animation)
    {
        if (par1Packet18Animation.animate == 1)
        {
            this.playerEntity.swingItem();
        }
    }

    /**
     * runs registerPacket on the given Packet19EntityAction
     */
    public void handleEntityAction(Packet19EntityAction par1Packet19EntityAction)
    {
        if (par1Packet19EntityAction.state == 1)
        {
            this.playerEntity.setSneaking(true);
        }
        else if (par1Packet19EntityAction.state == 2)
        {
            this.playerEntity.setSneaking(false);
        }
        else if (par1Packet19EntityAction.state == 4)
        {
            this.playerEntity.setSprinting(true);
        }
        else if (par1Packet19EntityAction.state == 5)
        {
            this.playerEntity.setSprinting(false);
        }
        else if (par1Packet19EntityAction.state == 3)
        {
            this.playerEntity.wakeUpPlayer(false, true, true);
            this.hasMoved = false;
        }
    }

    public void handleKickDisconnect(Packet255KickDisconnect par1Packet255KickDisconnect)
    {
        this.netManager.networkShutdown("disconnect.quitting", new Object[0]);
    }

    /**
     * returns 0 for memoryMapped connections
     */
    public int packetSize()
    {
        return this.netManager.packetSize();
    }

    public void handleUseEntity(Packet7UseEntity par1Packet7UseEntity)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
        Entity var3 = var2.getEntityByID(par1Packet7UseEntity.targetEntity);

        if (var3 != null)
        {
            boolean var4 = this.playerEntity.canEntityBeSeen(var3);
            double var5 = 36.0D;

            if (!var4)
            {
                var5 = 9.0D;
            }

            if (var3 instanceof FCIEntityPacketHandler && ((FCIEntityPacketHandler)var3).ShouldServerTreatAsOversized())
            {
                var4 = true;
                var5 = 256.0D;
            }

            if (this.playerEntity.getDistanceSqToEntity(var3) < var5)
            {
                if (par1Packet7UseEntity.isLeftClick == 0)
                {
                    this.playerEntity.interactWith(var3);
                }
                else if (par1Packet7UseEntity.isLeftClick == 1)
                {
                    this.playerEntity.attackTargetEntityWithCurrentItem(var3);
                }
            }
        }
    }

    public void handleClientCommand(Packet205ClientCommand par1Packet205ClientCommand)
    {
        if (par1Packet205ClientCommand.forceRespawn == 1)
        {
            if (this.playerEntity.playerConqueredTheEnd)
            {
                this.playerEntity = this.mcServer.getConfigurationManager().respawnPlayer(this.playerEntity, 0, true);
            }
            else if (this.playerEntity.getServerForPlayer().getWorldInfo().isHardcoreModeEnabled())
            {
                if (this.mcServer.isSinglePlayer() && this.playerEntity.username.equals(this.mcServer.getServerOwner()))
                {
                    this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it\'s game over!");
                    this.mcServer.deleteWorldAndStopServer();
                }
                else
                {
                    BanEntry var2 = new BanEntry(this.playerEntity.username);
                    var2.setBanReason("Death in Hardcore");
                    this.mcServer.getConfigurationManager().getBannedPlayers().put(var2);
                    this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it\'s game over!");
                }
            }
            else
            {
                if (this.playerEntity.getHealth() > 0)
                {
                    return;
                }

                this.playerEntity = this.mcServer.getConfigurationManager().respawnPlayer(this.playerEntity, 0, false);
            }
        }
    }

    /**
     * If this returns false, all packets will be queued for the main thread to handle, even if they would otherwise be
     * processed asynchronously. Used to avoid processing packets on the client before the world has been downloaded
     * (which happens on the main thread)
     */
    public boolean canProcessPacketsAsync()
    {
        return true;
    }

    /**
     * respawns the player
     */
    public void handleRespawn(Packet9Respawn par1Packet9Respawn) {}

    public void handleCloseWindow(Packet101CloseWindow par1Packet101CloseWindow)
    {
        this.playerEntity.closeInventory();
    }

    public void handleWindowClick(Packet102WindowClick par1Packet102WindowClick)
    {
        if (this.playerEntity.openContainer.windowId == par1Packet102WindowClick.window_Id && this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity))
        {
            ItemStack var2 = this.playerEntity.openContainer.slotClick(par1Packet102WindowClick.inventorySlot, par1Packet102WindowClick.mouseClick, par1Packet102WindowClick.holdingShift, this.playerEntity);
            int var4;

            if (ItemStack.areItemStacksEqual(par1Packet102WindowClick.itemStack, var2))
            {
                this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet106Transaction(par1Packet102WindowClick.window_Id, par1Packet102WindowClick.action, true));
                this.playerEntity.playerInventoryBeingManipulated = true;
                this.playerEntity.openContainer.detectAndSendChanges();
                this.playerEntity.updateHeldItem();
                this.playerEntity.playerInventoryBeingManipulated = false;

                if (par1Packet102WindowClick.holdingShift == 2)
                {
                    int var3 = par1Packet102WindowClick.mouseClick;

                    if (var3 >= 0 && var3 < 9)
                    {
                        var4 = par1Packet102WindowClick.inventorySlot;
                        this.playerEntity.sendSlotContents(this.playerEntity.openContainer, var4, (ItemStack)this.playerEntity.openContainer.inventoryItemStacks.get(var4));
                        var3 += this.playerEntity.openContainer.inventorySlots.size() - 9;

                        if (var3 >= 0 && var3 < this.playerEntity.openContainer.inventorySlots.size())
                        {
                            this.playerEntity.sendSlotContents(this.playerEntity.openContainer, var3, (ItemStack)this.playerEntity.openContainer.inventoryItemStacks.get(var3));
                        }
                    }
                }
            }
            else
            {
                this.field_72586_s.addKey(this.playerEntity.openContainer.windowId, Short.valueOf(par1Packet102WindowClick.action));
                this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet106Transaction(par1Packet102WindowClick.window_Id, par1Packet102WindowClick.action, false));
                this.playerEntity.openContainer.setPlayerIsPresent(this.playerEntity, false);
                ArrayList var5 = new ArrayList();

                for (var4 = 0; var4 < this.playerEntity.openContainer.inventorySlots.size(); ++var4)
                {
                    var5.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(var4)).getStack());
                }

                this.playerEntity.sendContainerAndContentsToPlayer(this.playerEntity.openContainer, var5);
            }
        }
    }

    public void handleEnchantItem(Packet108EnchantItem par1Packet108EnchantItem)
    {
        if (this.playerEntity.openContainer.windowId == par1Packet108EnchantItem.windowId && this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity))
        {
            this.playerEntity.openContainer.enchantItem(this.playerEntity, par1Packet108EnchantItem.enchantment);
            this.playerEntity.openContainer.detectAndSendChanges();
        }
    }

    /**
     * Handle a creative slot packet.
     */
    public void handleCreativeSetSlot(Packet107CreativeSetSlot par1Packet107CreativeSetSlot)
    {
        if (this.playerEntity.theItemInWorldManager.isCreative())
        {
            boolean var2 = par1Packet107CreativeSetSlot.slot < 0;
            ItemStack var3 = par1Packet107CreativeSetSlot.itemStack;
            boolean var4 = par1Packet107CreativeSetSlot.slot >= 1 && par1Packet107CreativeSetSlot.slot < 36 + InventoryPlayer.getHotbarSize();
            boolean var5 = var3 == null || var3.itemID < Item.itemsList.length && var3.itemID >= 0 && Item.itemsList[var3.itemID] != null;
            boolean var6 = var3 == null || var3.getItemDamage() >= 0 && var3.getItemDamage() >= 0 && var3.stackSize <= 64 && var3.stackSize > 0;

            if (var4 && var5 && var6)
            {
                if (var3 == null)
                {
                    this.playerEntity.inventoryContainer.putStackInSlot(par1Packet107CreativeSetSlot.slot, (ItemStack)null);
                }
                else
                {
                    this.playerEntity.inventoryContainer.putStackInSlot(par1Packet107CreativeSetSlot.slot, var3);
                }

                this.playerEntity.inventoryContainer.setPlayerIsPresent(this.playerEntity, true);
            }
            else if (var2 && var5 && var6 && this.creativeItemCreationSpamThresholdTally < 200)
            {
                this.creativeItemCreationSpamThresholdTally += 20;
                EntityItem var7 = this.playerEntity.dropPlayerItem(var3);

                if (var7 != null)
                {
                    var7.setAgeToCreativeDespawnTime();
                }
            }
        }
    }

    public void handleTransaction(Packet106Transaction par1Packet106Transaction)
    {
        Short var2 = (Short)this.field_72586_s.lookup(this.playerEntity.openContainer.windowId);

        if (var2 != null && par1Packet106Transaction.shortWindowId == var2.shortValue() && this.playerEntity.openContainer.windowId == par1Packet106Transaction.windowId && !this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity))
        {
            this.playerEntity.openContainer.setPlayerIsPresent(this.playerEntity, true);
        }
    }

    /**
     * Updates Client side signs
     */
    public void handleUpdateSign(Packet130UpdateSign par1Packet130UpdateSign)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);

        if (var2.blockExists(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition))
        {
            TileEntity var3 = var2.getBlockTileEntity(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition);

            if (var3 instanceof TileEntitySign)
            {
                TileEntitySign var4 = (TileEntitySign)var3;

                if (!var4.isEditable())
                {
                    this.mcServer.logWarning("Player " + this.playerEntity.username + " just tried to change non-editable sign");
                    return;
                }
            }

            int var5;
            int var8;

            for (var5 = 0; var5 < 4; ++var5)
            {
                boolean var6 = true;

                if (par1Packet130UpdateSign.signLines[var5].length() > 15)
                {
                    var6 = false;
                }
                else
                {
                    for (var8 = 0; var8 < par1Packet130UpdateSign.signLines[var5].length(); ++var8)
                    {
                        if (ChatAllowedCharacters.allowedCharacters.indexOf(par1Packet130UpdateSign.signLines[var5].charAt(var8)) < 0)
                        {
                            var6 = false;
                        }
                    }
                }

                if (!var6)
                {
                    par1Packet130UpdateSign.signLines[var5] = "!?";
                }
            }

            if (var3 instanceof TileEntitySign)
            {
                var5 = par1Packet130UpdateSign.xPosition;
                int var9 = par1Packet130UpdateSign.yPosition;
                var8 = par1Packet130UpdateSign.zPosition;
                TileEntitySign var7 = (TileEntitySign)var3;
                System.arraycopy(par1Packet130UpdateSign.signLines, 0, var7.signText, 0, 4);
                var7.onInventoryChanged();
                var2.markBlockForUpdate(var5, var9, var8);
            }
        }
    }

    /**
     * Handle a keep alive packet.
     */
    public void handleKeepAlive(Packet0KeepAlive par1Packet0KeepAlive)
    {
        if (par1Packet0KeepAlive.randomId == this.keepAliveRandomID)
        {
            int var2 = (int)(System.nanoTime() / 1000000L - this.keepAliveTimeSent);
            this.playerEntity.ping = (this.playerEntity.ping * 3 + var2) / 4;
        }
    }

    /**
     * determine if it is a server handler
     */
    public boolean isServerHandler()
    {
        return true;
    }

    /**
     * Handle a player abilities packet.
     */
    public void handlePlayerAbilities(Packet202PlayerAbilities par1Packet202PlayerAbilities)
    {
        this.playerEntity.capabilities.isFlying = par1Packet202PlayerAbilities.getFlying() && this.playerEntity.capabilities.allowFlying;
    }

    public void handleAutoComplete(Packet203AutoComplete par1Packet203AutoComplete)
    {
        StringBuilder var2 = new StringBuilder();
        String var3;

        for (Iterator var4 = this.mcServer.getPossibleCompletions(this.playerEntity, par1Packet203AutoComplete.getText()).iterator(); var4.hasNext(); var2.append(var3))
        {
            var3 = (String)var4.next();

            if (var2.length() > 0)
            {
                var2.append("\u0000");
            }
        }

        this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet203AutoComplete(var2.toString()));
    }

    public void handleClientInfo(Packet204ClientInfo par1Packet204ClientInfo)
    {
        this.playerEntity.updateClientInfo(par1Packet204ClientInfo);

        if (!par1Packet204ClientInfo.IsBTWInstalled())
        {
            this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("\u00a74" + "WARNING: You do not currently have Better Than Wolves installed on your system."));
            this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("\u00a74" + "This server requires all clients to have Better Than Wolves version " + "4.B0000002"));
            this.mcServer.getLogAgent().logInfo(this.playerEntity.username + " logged in without BTW installed");
        }
    }

    public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload)
    {
        DataInputStream var2;
        ItemStack var3;
        ItemStack var4;

        if ("MC|BEdit".equals(par1Packet250CustomPayload.channel))
        {
            try
            {
                var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
                var3 = Packet.readItemStack(var2);

                if (!ItemWritableBook.validBookTagPages(var3.getTagCompound()))
                {
                    throw new IOException("Invalid book tag!");
                }

                var4 = this.playerEntity.inventory.getCurrentItem();

                if (var3 != null && var3.itemID == Item.writableBook.itemID && var3.itemID == var4.itemID)
                {
                    var4.setTagInfo("pages", var3.getTagCompound().getTagList("pages"));
                }
            }
            catch (Exception var14)
            {
                var14.printStackTrace();
            }
        }
        else if ("MC|BSign".equals(par1Packet250CustomPayload.channel))
        {
            try
            {
                var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
                var3 = Packet.readItemStack(var2);

                if (!ItemEditableBook.validBookTagContents(var3.getTagCompound()))
                {
                    throw new IOException("Invalid book tag!");
                }

                var4 = this.playerEntity.inventory.getCurrentItem();

                if (var3 != null && var3.itemID == Item.writtenBook.itemID && var4.itemID == Item.writableBook.itemID)
                {
                    var4.setTagInfo("author", new NBTTagString("author", this.playerEntity.username));
                    var4.setTagInfo("title", new NBTTagString("title", var3.getTagCompound().getString("title")));
                    var4.setTagInfo("pages", var3.getTagCompound().getTagList("pages"));
                    var4.itemID = Item.writtenBook.itemID;
                }
            }
            catch (Exception var13)
            {
                var13.printStackTrace();
            }
        }
        else
        {
            int var5;

            if ("MC|TrSel".equals(par1Packet250CustomPayload.channel))
            {
                try
                {
                    var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
                    var5 = var2.readInt();
                    Container var6 = this.playerEntity.openContainer;

                    if (var6 instanceof ContainerMerchant)
                    {
                        ((ContainerMerchant)var6).setCurrentRecipeIndex(var5);
                    }
                }
                catch (Exception var12)
                {
                    var12.printStackTrace();
                }
            }
            else
            {
                String var8;
                int var15;

                if ("MC|AdvCdm".equals(par1Packet250CustomPayload.channel))
                {
                    if (!this.mcServer.isCommandBlockEnabled())
                    {
                        this.playerEntity.sendChatToPlayer(this.playerEntity.translateString("advMode.notEnabled", new Object[0]));
                    }
                    else if (this.playerEntity.canCommandSenderUseCommand(2, "") && this.playerEntity.capabilities.isCreativeMode)
                    {
                        try
                        {
                            var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
                            var5 = var2.readInt();
                            var15 = var2.readInt();
                            int var7 = var2.readInt();
                            var8 = Packet.readString(var2, 256);
                            TileEntity var9 = this.playerEntity.worldObj.getBlockTileEntity(var5, var15, var7);

                            if (var9 != null && var9 instanceof TileEntityCommandBlock)
                            {
                                ((TileEntityCommandBlock)var9).setCommand(var8);
                                this.playerEntity.worldObj.markBlockForUpdate(var5, var15, var7);
                                this.playerEntity.sendChatToPlayer("Command set: " + var8);
                            }
                        }
                        catch (Exception var11)
                        {
                            var11.printStackTrace();
                        }
                    }
                    else
                    {
                        this.playerEntity.sendChatToPlayer(this.playerEntity.translateString("advMode.notAllowed", new Object[0]));
                    }
                }
                else if ("MC|Beacon".equals(par1Packet250CustomPayload.channel))
                {
                    if (this.playerEntity.openContainer instanceof ContainerBeacon)
                    {
                        try
                        {
                            var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
                            var5 = var2.readInt();
                            var15 = var2.readInt();
                            ContainerBeacon var16 = (ContainerBeacon)this.playerEntity.openContainer;
                            Slot var18 = var16.getSlot(0);

                            if (var18.getHasStack())
                            {
                                var18.decrStackSize(1);
                                TileEntityBeacon var19 = var16.getBeacon();
                                var19.setPrimaryEffect(var5);
                                var19.setSecondaryEffect(var15);
                                var19.onInventoryChanged();
                            }
                        }
                        catch (Exception var10)
                        {
                            var10.printStackTrace();
                        }
                    }
                }
                else if ("MC|ItemName".equals(par1Packet250CustomPayload.channel) && this.playerEntity.openContainer instanceof ContainerRepair)
                {
                    ContainerRepair var17 = (ContainerRepair)this.playerEntity.openContainer;

                    if (par1Packet250CustomPayload.data != null && par1Packet250CustomPayload.data.length >= 1)
                    {
                        var8 = ChatAllowedCharacters.filerAllowedCharacters(new String(par1Packet250CustomPayload.data));

                        if (var8.length() <= 30)
                        {
                            var17.updateItemName(var8);
                        }
                    }
                    else
                    {
                        var17.updateItemName("");
                    }
                }
            }
        }
    }

    public List getCollidingBoundingBoxesIgnoreSpecifiedEntities(World var1, Entity var2, AxisAlignedBB var3)
    {
        ArrayList var4 = new ArrayList();
        int var5 = MathHelper.floor_double(var3.minX);
        int var6 = MathHelper.floor_double(var3.maxX + 1.0D);
        int var7 = MathHelper.floor_double(var3.minY);
        int var8 = MathHelper.floor_double(var3.maxY + 1.0D);
        int var9 = MathHelper.floor_double(var3.minZ);
        int var10 = MathHelper.floor_double(var3.maxZ + 1.0D);

        for (int var11 = var5; var11 < var6; ++var11)
        {
            for (int var12 = var9; var12 < var10; ++var12)
            {
                if (var1.blockExists(var11, 64, var12))
                {
                    for (int var13 = var7 - 1; var13 < var8; ++var13)
                    {
                        Block var14 = Block.blocksList[var1.getBlockId(var11, var13, var12)];

                        if (var14 != null)
                        {
                            var14.addCollisionBoxesToList(var1, var11, var13, var12, var3, var4, var2);
                        }
                    }
                }
            }
        }

        double var17 = 0.25D;
        List var18 = var1.getEntitiesWithinAABBExcludingEntity(var2, var3.expand(var17, 2.0D, var17));
        Iterator var19 = var18.iterator();

        while (var19.hasNext())
        {
            Entity var15 = (Entity)var19.next();

            if (!(var15 instanceof FCIEntityIgnoreServerValidation))
            {
                AxisAlignedBB var16 = var15.getBoundingBox();

                if (var16 != null && var16.intersectsWith(var3))
                {
                    var4.add(var16);
                }

                var16 = var2.getCollisionBox(var15);

                if (var16 != null && var16.intersectsWith(var3))
                {
                    var4.add(var16);
                }
            }
        }

        return var4;
    }

    public void HandleStartBlockHarvest(FCPacket166StartBlockHarvest var1)
    {
        WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
        int var3 = var1.iIPos;
        int var4 = var1.iJPos;
        int var5 = var1.iKPos;
        double var6 = this.playerEntity.posX - ((double)var3 + 0.5D);
        double var8 = this.playerEntity.posY - ((double)var4 + 0.5D) + 1.5D;
        double var10 = this.playerEntity.posZ - ((double)var5 + 0.5D);
        double var12 = var6 * var6 + var8 * var8 + var10 * var10;

        if (var12 <= 36.0D && var4 < this.mcServer.getBuildLimit())
        {
            if (!this.IsBlockWithinSpawnProtection(var2, var3, var4, var5))
            {
                this.playerEntity.SetMiningSpeedModifier(var1.GetMiningSpeedModifier());
                this.playerEntity.theItemInWorldManager.onBlockClicked(var3, var4, var5, var1.iFace);
            }
            else
            {
                FCUtilsWorld.SendPacketToPlayer(this.playerEntity.playerNetServerHandler, new Packet53BlockChange(var3, var4, var5, var2));
            }
        }
    }

    private boolean IsBlockWithinSpawnProtection(World var1, int var2, int var3, int var4)
    {
        return this.mcServer.func_96290_a(var1, var2, var3, var4, this.playerEntity);
    }
}
