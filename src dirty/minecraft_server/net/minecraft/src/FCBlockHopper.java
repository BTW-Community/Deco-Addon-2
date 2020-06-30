package net.minecraft.src;

import java.util.Random;

public class FCBlockHopper extends BlockContainer implements FCIBlockMechanical
{
    protected static final int m_iTickRate = 10;
    protected static final float m_fSpoutHeight = 0.25F;
    protected static final float m_fSpoutWidth = 0.375F;
    protected static final float m_fSpoutHalfWidth = 0.1875F;

    protected FCBlockHopper(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockHopper");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 10;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);

        if (this.IsBlockOn(var1, var2, var3, var4) != var6 && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }

        ((FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4)).m_bOutputBlocked = false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote)
        {
            FCTileEntityHopper var10 = (FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof EntityPlayerMP)
            {
                FCContainerHopper var11 = new FCContainerHopper(var5.inventory, var10);
                FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var11, FCBetterThanWolves.fcHopperContainerID);
            }
        }

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityHopper();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.IsBlockOn(var1, var2, var3, var4);
        boolean var8 = this.IsHopperFull(var1, var2, var3, var4);
        boolean var9 = this.IsRedstoneOutputOn(var1, var2, var3, var4);

        if (var7 != var6)
        {
            var1.playAuxSFX(2233, var2, var3, var4, 0);
            this.SetBlockOn(var1, var2, var3, var4, var6);
        }

        if (var8 != var9)
        {
            var1.playAuxSFX(2234, var2, var3, var4, 0);
            this.SetRedstoneOutputOn(var1, var2, var3, var4, var8);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, (IInventory)var1.getBlockTileEntity(var2, var3, var4));
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote)
        {
            if (var5 instanceof EntityItem)
            {
                this.OnEntityItemCollidedWithBlock(var1, var2, var3, var4, (EntityItem)var5);
            }
            else if (var5 instanceof EntityXPOrb)
            {
                this.OnEntityXPOrbCollidedWithBlock(var1, var2, var3, var4, (EntityXPOrb)var5);
            }
        }
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.IsRedstoneOutputOn(var1, var2, var3, var4) ? 15 : 0;
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.25D, 0.6875D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var7.GetFirstIntersection();
    }

    public void OnArrowCollide(World var1, int var2, int var3, int var4, EntityArrow var5)
    {
        if (!var1.isRemote && !var5.isDead && var5.CanHopperCollect())
        {
            Vec3 var6 = Vec3.createVectorHelper(var5.posX, var5.posY, var5.posZ);
            AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3 + 0.9F), (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)var3 + 1.1F), (double)((float)(var4 + 1)));

            if (var7.isVecInside(var6))
            {
                FCTileEntityHopper var8 = (FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4);
                ItemStack var9 = new ItemStack(var5.GetCorrespondingItem(), 1, 0);

                if (var8.CanCurrentFilterProcessItem(var9) && FCUtilsInventory.AddItemStackToInventoryInSlotRange(var8, var9, 0, 17))
                {
                    var5.setDead();
                    var1.playAuxSFX(2231, var2, var3, var4, 0);
                }
            }
        }
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 1, 0, var6);
        return true;
    }

    public boolean CanOutputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return FCUtilsMechPower.IsBlockPoweredByAxle(var1, var2, var3, var4, this) || FCUtilsMechPower.IsBlockPoweredByHandCrank(var1, var2, var3, var4);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 >= 2;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakHopper(var1, var2, var3, var4);
    }

    public boolean IsBlockOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    public void SetBlockOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5)
        {
            var6 |= 1;
        }
        else
        {
            var6 &= -2;
        }

        var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var6);
    }

    public boolean IsHopperFull(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 2) > 0;
    }

    public void SetHopperFull(World var1, int var2, int var3, int var4, boolean var5)
    {
        boolean var6 = this.IsHopperFull(var1, var2, var3, var4);

        if (var6 != var5)
        {
            int var7 = var1.getBlockMetadata(var2, var3, var4);

            if (var5)
            {
                var7 |= 2;
            }
            else
            {
                var7 &= -3;
            }

            var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var7);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public boolean IsRedstoneOutputOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 4) > 0;
    }

    public void SetRedstoneOutputOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 != this.IsRedstoneOutputOn(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var5)
            {
                var6 |= 4;
            }
            else
            {
                var6 &= -5;
            }

            var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var6);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this.blockID);
        }
    }

    public boolean HasFilter(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetHasFilter(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5)
        {
            var6 |= 8;
        }
        else
        {
            var6 &= -9;
        }

        var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var6);
    }

    public void BreakHopper(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    private void OnEntityItemCollidedWithBlock(World var1, int var2, int var3, int var4, EntityItem var5)
    {
        float var6 = 1.0F;
        AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3 + var6), (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)var3 + var6 + 0.05F), (double)((float)(var4 + 1)));

        if (var5.boundingBox.intersectsWith(var7) && !var5.isDead)
        {
            Item var8 = Item.itemsList[var5.getEntityItem().itemID];
            ItemStack var9 = var5.getEntityItem();
            FCTileEntityHopper var10 = (FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4);

            if (var10.CanCurrentFilterProcessItem(var9))
            {
                Item var11 = var10.GetFilterItem();

                if (var11 != null && var11.CanTransformItemIfFilter(var9))
                {
                    int var12 = var8.itemID;

                    if (var12 == Block.gravel.blockID)
                    {
                        ItemStack var13 = new ItemStack(Block.sand.blockID, var9.stackSize, 0);
                        boolean var14 = false;
                        int var17;

                        if (FCUtilsInventory.AddItemStackToInventoryInSlotRange(var10, var13, 0, 17))
                        {
                            var17 = var9.stackSize;
                            var5.setDead();
                        }
                        else
                        {
                            var17 = var5.getEntityItem().stackSize - var13.stackSize;
                            ItemStack var10000 = var5.getEntityItem();
                            var10000.stackSize -= var17;
                        }

                        if (var17 > 0)
                        {
                            var1.playAuxSFX(2231, var2, var3, var4, 0);
                            ItemStack var15 = new ItemStack(Item.flint.itemID, var17, 0);
                            EntityItem var16 = new EntityItem(var1, var5.posX, var5.posY, var5.posZ, var15);
                            var16.delayBeforeCanPickup = 10;
                            var1.spawnEntityInWorld(var16);
                        }
                    }
                    else if (var12 == FCBetterThanWolves.fcItemGroundNetherrack.itemID)
                    {
                        this.ConvertItemAndIncrementSouls(var1, var2, var3, var4, var5, FCBetterThanWolves.fcItemHellfireDust.itemID, 0);
                    }
                    else if (var12 == FCBetterThanWolves.fcItemSoulDust.itemID)
                    {
                        this.ConvertItemAndIncrementSouls(var1, var2, var3, var4, var5, FCBetterThanWolves.fcItemSawDust.itemID, 0);
                    }
                    else if (var12 == Item.lightStoneDust.itemID)
                    {
                        this.ConvertItemAndIncrementSouls(var1, var2, var3, var4, var5, FCBetterThanWolves.fcItemBrimstone.itemID, 0);
                    }
                }
                else if (FCUtilsInventory.AddItemStackToInventoryInSlotRange(var10, var5.getEntityItem(), 0, 17))
                {
                    var1.playAuxSFX(2231, var2, var3, var4, 0);
                    var5.setDead();
                }
            }
        }
    }

    private void ConvertItemAndIncrementSouls(World var1, int var2, int var3, int var4, EntityItem var5, int var6, int var7)
    {
        FCTileEntityHopper var8 = (FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4);
        ItemStack var9 = new ItemStack(var6, var5.getEntityItem().stackSize, var7);
        EntityItem var10 = new EntityItem(var1, var5.posX, var5.posY, var5.posZ, var9);
        var10.delayBeforeCanPickup = 10;
        var8.IncrementContainedSoulCount(var9.stackSize);
        var1.spawnEntityInWorld(var10);
        var1.playAuxSFX(2228, var2, var3, var4, 0);
        var5.setDead();
    }

    private void OnEntityXPOrbCollidedWithBlock(World var1, int var2, int var3, int var4, EntityXPOrb var5)
    {
        if (!var5.isDead)
        {
            float var6 = 1.0F;
            AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3 + var6), (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)var3 + var6 + 0.05F), (double)((float)(var4 + 1)));

            if (var5.boundingBox.intersectsWith(var7))
            {
                FCTileEntityHopper var8 = (FCTileEntityHopper)var1.getBlockTileEntity(var2, var3, var4);
                Item var9 = var8.GetFilterItem();

                if (var9 != null && var9.itemID == Block.slowSand.blockID && var8.AttemptToSwallowXPOrb(var1, var2, var3, var4, var5))
                {
                    var1.playAuxSFX(2231, var2, var3, var4, 0);
                }
            }
        }
    }
}
