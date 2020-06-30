package net.minecraft.src;

public class ItemInWorldManager
{
    /** The world object that this object is connected to. */
    public World theWorld;

    /** The EntityPlayerMP object that this object is connected to. */
    public EntityPlayerMP thisPlayerMP;
    private EnumGameType gameType;

    /** True if the player is destroying a block */
    private boolean isDestroyingBlock;
    private int initialDamage;
    private int partiallyDestroyedBlockX;
    private int partiallyDestroyedBlockY;
    private int partiallyDestroyedBlockZ;
    private int curblockDamage;

    /**
     * Set to true when the "finished destroying block" packet is received but the block wasn't fully damaged yet. The
     * block will not be destroyed while this is false.
     */
    private boolean receivedFinishDiggingPacket;
    private int posX;
    private int posY;
    private int posZ;
    private int field_73093_n;
    private int durabilityRemainingOnBlock;
    private int m_iHarvestingFromFacing;

    public ItemInWorldManager(World par1World)
    {
        this.gameType = EnumGameType.NOT_SET;
        this.durabilityRemainingOnBlock = -1;
        this.theWorld = par1World;
    }

    public void setGameType(EnumGameType par1EnumGameType)
    {
        this.gameType = par1EnumGameType;
        par1EnumGameType.configurePlayerCapabilities(this.thisPlayerMP.capabilities);
        this.thisPlayerMP.sendPlayerAbilities();
    }

    public EnumGameType getGameType()
    {
        return this.gameType;
    }

    /**
     * Get if we are in creative game mode.
     */
    public boolean isCreative()
    {
        return this.gameType.isCreative();
    }

    /**
     * if the gameType is currently NOT_SET then change it to par1
     */
    public void initializeGameType(EnumGameType par1EnumGameType)
    {
        if (this.gameType == EnumGameType.NOT_SET)
        {
            this.gameType = par1EnumGameType;
        }

        this.setGameType(this.gameType);
    }

    public void updateBlockRemoving()
    {
        ++this.curblockDamage;
        int var1;
        float var2;
        int var3;

        if (this.receivedFinishDiggingPacket)
        {
            var1 = this.curblockDamage - this.field_73093_n;
            int var4 = this.theWorld.getBlockId(this.posX, this.posY, this.posZ);

            if (var4 == 0)
            {
                this.receivedFinishDiggingPacket = false;
            }
            else
            {
                Block var5 = Block.blocksList[var4];
                var2 = var5.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, this.posX, this.posY, this.posZ) * (float)(var1 + 1);
                var3 = (int)(var2 * 10.0F);

                if (var3 != this.durabilityRemainingOnBlock)
                {
                    this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, this.posX, this.posY, this.posZ, var3);
                    this.durabilityRemainingOnBlock = var3;
                }

                if (var2 >= 1.0F)
                {
                    this.receivedFinishDiggingPacket = false;
                    this.tryHarvestBlock(this.posX, this.posY, this.posZ, this.m_iHarvestingFromFacing);
                }
            }
        }
        else if (this.isDestroyingBlock)
        {
            var1 = this.theWorld.getBlockId(this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ);
            Block var6 = Block.blocksList[var1];

            if (var6 == null)
            {
                this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, -1);
                this.durabilityRemainingOnBlock = -1;
                this.isDestroyingBlock = false;
            }
            else
            {
                int var7 = this.curblockDamage - this.initialDamage;
                var2 = var6.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ) * (float)(var7 + 1);
                var3 = (int)(var2 * 10.0F);

                if (var3 != this.durabilityRemainingOnBlock)
                {
                    this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, var3);
                    this.durabilityRemainingOnBlock = var3;
                }
            }
        }
    }

    /**
     * if not creative, it calls destroyBlockInWorldPartially untill the block is broken first. par4 is the specific
     * side. tryHarvestBlock can also be the result of this call
     */
    public void onBlockClicked(int par1, int par2, int par3, int par4)
    {
        if (!this.gameType.isAdventure() || this.thisPlayerMP.canCurrentToolHarvestBlock(par1, par2, par3))
        {
            if (this.isCreative())
            {
                if (!this.theWorld.extinguishFire((EntityPlayer)null, par1, par2, par3, par4))
                {
                    this.tryHarvestBlock(par1, par2, par3, par4);
                }
            }
            else
            {
                this.initialDamage = this.curblockDamage;
                float var5 = 1.0F;
                int var6 = this.theWorld.getBlockId(par1, par2, par3);

                if (var6 > 0)
                {
                    Block.blocksList[var6].onBlockClicked(this.theWorld, par1, par2, par3, this.thisPlayerMP);
                    var5 = Block.blocksList[var6].getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, par1, par2, par3);
                }

                if (var6 > 0 && var5 >= 1.0F)
                {
                    this.tryHarvestBlock(par1, par2, par3, par4);
                }
                else
                {
                    this.isDestroyingBlock = true;
                    this.partiallyDestroyedBlockX = par1;
                    this.partiallyDestroyedBlockY = par2;
                    this.partiallyDestroyedBlockZ = par3;
                    int var7 = (int)(var5 * 10.0F);
                    this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, par1, par2, par3, var7);
                    this.durabilityRemainingOnBlock = var7;
                }
            }
        }
    }

    public void uncheckedTryHarvestBlock(int par1, int par2, int par3)
    {
        this.uncheckedTryHarvestBlock(par1, par2, par3, 1);
    }

    public void uncheckedTryHarvestBlock(int var1, int var2, int var3, int var4)
    {
        if (var1 == this.partiallyDestroyedBlockX && var2 == this.partiallyDestroyedBlockY && var3 == this.partiallyDestroyedBlockZ)
        {
            int var5 = this.curblockDamage - this.initialDamage;
            int var6 = this.theWorld.getBlockId(var1, var2, var3);

            if (var6 != 0)
            {
                Block var7 = Block.blocksList[var6];
                float var8 = var7.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, var1, var2, var3) * (float)(var5 + 1);

                if (var8 >= 0.7F)
                {
                    this.isDestroyingBlock = false;
                    this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, var1, var2, var3, -1);
                    this.tryHarvestBlock(var1, var2, var3, var4);
                }
                else if (!this.receivedFinishDiggingPacket)
                {
                    this.isDestroyingBlock = false;
                    this.receivedFinishDiggingPacket = true;
                    this.posX = var1;
                    this.posY = var2;
                    this.posZ = var3;
                    this.field_73093_n = this.initialDamage;
                    this.m_iHarvestingFromFacing = var4;
                }
            }
        }
    }

    /**
     * note: this ignores the pars passed in and continues to destroy the onClickedBlock
     */
    public void cancelDestroyingBlock(int par1, int par2, int par3)
    {
        this.isDestroyingBlock = false;
        this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.entityId, this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, -1);
    }

    /**
     * Removes a block and triggers the appropriate events
     */
    private boolean removeBlock(int par1, int par2, int par3)
    {
        Block var4 = Block.blocksList[this.theWorld.getBlockId(par1, par2, par3)];
        int var5 = this.theWorld.getBlockMetadata(par1, par2, par3);

        if (var4 != null)
        {
            var4.onBlockHarvested(this.theWorld, par1, par2, par3, var5, this.thisPlayerMP);
        }

        boolean var6 = this.theWorld.setBlockToAir(par1, par2, par3);

        if (var4 != null && var6)
        {
            var4.onBlockDestroyedByPlayer(this.theWorld, par1, par2, par3, var5);
            this.theWorld.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(this.thisPlayerMP, var4, par1, par2, par3);
        }

        return var6;
    }

    /**
     * Attempts to right-click use an item by the given EntityPlayer in the given World
     */
    public boolean tryUseItem(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack)
    {
        int var4 = par3ItemStack.stackSize;
        int var5 = par3ItemStack.getItemDamage();
        ItemStack var6 = par3ItemStack.useItemRightClick(par2World, par1EntityPlayer);

        if (var6 == par3ItemStack && (var6 == null || var6.stackSize == var4 && var6.getMaxItemUseDuration() <= 0 && var6.getItemDamage() == var5))
        {
            return false;
        }
        else
        {
            par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem] = var6;

            if (this.isCreative())
            {
                var6.stackSize = var4;

                if (var6.isItemStackDamageable())
                {
                    var6.setItemDamage(var5);
                }
            }

            if (var6.stackSize == 0)
            {
                par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem] = null;
            }

            if (!par1EntityPlayer.isUsingItem())
            {
                ((EntityPlayerMP)par1EntityPlayer).sendContainerToPlayer(par1EntityPlayer.inventoryContainer);
            }

            return true;
        }
    }

    /**
     * Activate the clicked on block, otherwise use the held item. Args: player, world, itemStack, x, y, z, side,
     * xOffset, yOffset, zOffset
     */
    public boolean activateBlockOrUseItem(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        int var11;

        if (!par1EntityPlayer.isSneaking() || par1EntityPlayer.getHeldItem() == null)
        {
            var11 = par2World.getBlockId(par4, par5, par6);

            if (var11 > 0 && Block.blocksList[var11].onBlockActivated(par2World, par4, par5, par6, par1EntityPlayer, par7, par8, par9, par10))
            {
                return true;
            }
        }

        if (par3ItemStack == null)
        {
            return false;
        }
        else if (this.isCreative())
        {
            var11 = par3ItemStack.getItemDamage();
            int var12 = par3ItemStack.stackSize;
            boolean var13 = par3ItemStack.tryPlaceItemIntoWorld(par1EntityPlayer, par2World, par4, par5, par6, par7, par8, par9, par10);
            par3ItemStack.setItemDamage(var11);
            par3ItemStack.stackSize = var12;
            return var13;
        }
        else
        {
            return par3ItemStack.tryPlaceItemIntoWorld(par1EntityPlayer, par2World, par4, par5, par6, par7, par8, par9, par10);
        }
    }

    /**
     * Sets the world instance.
     */
    public void setWorld(WorldServer par1WorldServer)
    {
        this.theWorld = par1WorldServer;
    }

    /**
     * Attempts to harvest a block at the given coordinate
     */
    public boolean tryHarvestBlock(int par1, int par2, int par3)
    {
        return this.tryHarvestBlock(par1, par2, par3, 1);
    }

    public boolean tryHarvestBlock(int var1, int var2, int var3, int var4)
    {
        if (this.gameType.isAdventure() && !this.thisPlayerMP.canCurrentToolHarvestBlock(var1, var2, var3))
        {
            return false;
        }
        else if (this.isCreative())
        {
            int var5 = this.theWorld.getBlockId(var1, var2, var3);
            this.theWorld.getBlockMetadata(var1, var2, var3);
            this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, var1, var2, var3, var5 + (this.theWorld.getBlockMetadata(var1, var2, var3) << 12));
            boolean var7 = this.removeBlock(var1, var2, var3);
            FCUtilsWorld.SendPacketToPlayer(this.thisPlayerMP.playerNetServerHandler, new Packet53BlockChange(var1, var2, var3, this.theWorld));
            return var7;
        }
        else
        {
            return this.SurvivalTryHarvestBlock(var1, var2, var3, var4);
        }
    }

    public boolean SurvivalTryHarvestBlock(int var1, int var2, int var3, int var4)
    {
        int var5 = this.theWorld.getBlockId(var1, var2, var3);
        Block var6 = Block.blocksList[var5];

        if (var6 == null)
        {
            return false;
        }
        else
        {
            int var7 = this.theWorld.getBlockMetadata(var1, var2, var3);
            this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, var1, var2, var3, var5 + (var7 << 12));
            ItemStack var8 = this.thisPlayerMP.getCurrentEquippedItem();
            boolean var9 = true;
            boolean var10 = false;
            boolean var11 = this.thisPlayerMP.canHarvestBlock(Block.blocksList[var5], var1, var2, var3);

            if (!var11)
            {
                var10 = var6.CanConvertBlock(var8, this.theWorld, var1, var2, var3);

                if (var10)
                {
                    var9 = !var6.ConvertBlock(var8, this.theWorld, var1, var2, var3, var4);
                }
            }

            if (var9)
            {
                var9 = this.removeBlock(var1, var2, var3);

                if (var9 && !var10)
                {
                    if (var11)
                    {
                        Block.blocksList[var5].harvestBlock(this.theWorld, this.thisPlayerMP, var1, var2, var3, var7);
                    }
                    else
                    {
                        Block.blocksList[var5].OnBlockDestroyedWithImproperTool(this.theWorld, this.thisPlayerMP, var1, var2, var3, var7);
                    }
                }
            }

            if (var8 != null)
            {
                var8.onBlockDestroyed(this.theWorld, var5, var1, var2, var3, this.thisPlayerMP);

                if (var8.stackSize == 0)
                {
                    this.thisPlayerMP.destroyCurrentEquippedItem();
                }
            }

            return var9;
        }
    }
}
