package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockBlockDispenser extends BlockContainer
{
    private final int iBlockDispenserTickRate = 4;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconFront;

    protected FCBlockBlockDispenser(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.5F);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("fcBlockBlockDispenser");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 4;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockDispenser.blockID;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, Block.GetOppositeFacing(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote)
        {
            FCTileEntityBlockDispenser var10 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof EntityPlayerMP)
            {
                FCContainerBlockDispenser var11 = new FCContainerBlockDispenser(var5.inventory, var10);
                FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var11, FCBetterThanWolves.fcBlockDispenserContainerID);
            }
        }

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityBlockDispenser();
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
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.ValidateBlockDispenser(var1, var2, var3, var4);
        boolean var6 = this.IsReceivingRedstonePower(var1, var2, var3, var4);

        if (var6)
        {
            if (!this.IsRedstoneOn(var1, var2, var3, var4))
            {
                this.SetRedstoneOn(var1, var2, var3, var4, true);
                this.DispenseBlockOrItem(var1, var2, var3, var4);
            }
        }
        else if (this.IsRedstoneOn(var1, var2, var3, var4))
        {
            this.SetRedstoneOn(var1, var2, var3, var4, false);
            this.ConsumeFacingBlock(var1, var2, var3, var4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public int GetFacing(int var1)
    {
        return var1 & -9;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= 8;
        var1 |= var2;
        return var1;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (super.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            var1.markBlockForUpdate(var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        return this.IsRedstoneOn(var1, var2, var3, var4) == this.IsReceivingRedstonePower(var1, var2, var3, var4);
    }

    public boolean IsRedstoneOn(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 8) > 0;
    }

    private void SetRedstoneOn(World var1, int var2, int var3, int var4, boolean var5)
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

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    private boolean IsReceivingRedstonePower(World var1, int var2, int var3, int var4)
    {
        return var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4);
    }

    private boolean AddBlockToInventory(World var1, int var2, int var3, int var4, Block var5, FCUtilsBlockPos var6)
    {
        this.ValidateBlockDispenser(var1, var2, var3, var4);
        ItemStack var7 = var5.GetStackRetrievedByBlockDispenser(var1, var6.i, var6.j, var6.k);

        if (var7 == null)
        {
            return false;
        }
        else
        {
            FCTileEntityBlockDispenser var8 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);
            int var9 = var7.stackSize;
            boolean var10 = FCUtilsInventory.AddItemStackToInventory(var8, var7);
            return var10 || var7.stackSize < var9;
        }
    }

    private boolean ConsumeEntityAtTargetLoc(World var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        this.ValidateBlockDispenser(var1, var2, var3, var4);
        List var8 = null;
        var8 = var1.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB((double)var5, (double)var6, (double)var7, (double)(var5 + 1), (double)(var6 + 1), (double)(var7 + 1)));

        if (var8 != null && var8.size() > 0)
        {
            FCTileEntityBlockDispenser var9 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);

            for (int var10 = 0; var10 < var8.size(); ++var10)
            {
                Entity var11 = (Entity)var8.get(var10);

                if (!var11.isDead)
                {
                    int var13;

                    if (var11 instanceof EntityMinecart)
                    {
                        EntityMinecart var16 = (EntityMinecart)var11;
                        var13 = var16.getMinecartType();

                        if (var16.riddenByEntity != null)
                        {
                            var16.riddenByEntity.mountEntity(var16);
                        }

                        var16.setDead();

                        switch (var13)
                        {
                            case 1:
                                FCUtilsInventory.AddSingleItemToInventory(var9, Item.minecartCrate.itemID, 0);
                                break;

                            case 2:
                                FCUtilsInventory.AddSingleItemToInventory(var9, Item.minecartPowered.itemID, 0);
                                break;

                            default:
                                FCUtilsInventory.AddSingleItemToInventory(var9, Item.minecartEmpty.itemID, 0);
                        }

                        var1.playAuxSFX(1001, var2, var3, var4, 0);
                        return true;
                    }

                    if (var11 instanceof EntityBoat)
                    {
                        var11.setDead();
                        FCUtilsInventory.AddSingleItemToInventory(var9, Item.boat.itemID, 0);
                        var1.playAuxSFX(1001, var2, var3, var4, 0);
                        return true;
                    }

                    if (var11 instanceof FCEntityWolf)
                    {
                        FCEntityWolf var15 = (FCEntityWolf)var11;
                        var1.playAuxSFX(2239, var2, var3, var4, 0);
                        var11.setDead();
                        FCUtilsInventory.AddSingleItemToInventory(var9, FCBetterThanWolves.fcCompanionCube.blockID, 0);

                        for (var13 = 0; var13 < 2; ++var13)
                        {
                            this.SpitOutItem(var1, var2, var3, var4, new ItemStack(Item.silk));
                        }

                        return true;
                    }

                    if (var11 instanceof FCEntityChicken)
                    {
                        FCEntityChicken var14 = (FCEntityChicken)var11;
                        var1.playAuxSFX(2240, var2, var3, var4, 0);
                        var11.setDead();
                        FCUtilsInventory.AddSingleItemToInventory(var9, Item.egg.itemID, 0);
                        return true;
                    }

                    if (var11 instanceof FCEntitySheep)
                    {
                        FCEntitySheep var12 = (FCEntitySheep)var11;

                        if (!var12.getSheared() && !var12.isChild())
                        {
                            var12.setSheared(true);
                            FCUtilsInventory.AddSingleItemToInventory(var9, FCBetterThanWolves.fcItemWool.itemID, BlockCloth.getDyeFromBlock(var12.getFleeceColor()));
                            var11.attackEntityFrom(DamageSource.generic, 0);

                            for (var13 = 0; var13 < 2; ++var13)
                            {
                                this.SpitOutItem(var1, var2, var3, var4, new ItemStack(Item.silk));
                            }

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private void ConsumeFacingBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);

        if (!this.ConsumeEntityAtTargetLoc(var1, var2, var3, var4, var6.i, var6.j, var6.k) && !var1.isAirBlock(var6.i, var6.j, var6.k))
        {
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);
            Block var8 = Block.blocksList[var7];

            if (var8 != null)
            {
                int var9 = var1.getBlockMetadata(var6.i, var6.j, var6.k);

                if (this.AddBlockToInventory(var1, var2, var3, var4, var8, var6) || var8.IsBlockDestroyedByBlockDispenser(var9))
                {
                    var8.OnRemovedByBlockDispenser(var1, var6.i, var6.j, var6.k);
                }
            }
        }
    }

    public void OnRemovedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        FCTileEntityBlockDispenser var5 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);
        FCUtilsInventory.ClearInventoryContents(var5);
        super.OnRemovedByBlockDispenser(var1, var2, var3, var4);
    }

    private boolean DispenseBlockOrItem(World var1, int var2, int var3, int var4)
    {
        this.ValidateBlockDispenser(var1, var2, var3, var4);
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        Block var7 = Block.blocksList[var1.getBlockId(var6.i, var6.j, var6.k)];

        if (var7 == null || var7.blockMaterial.isReplaceable() || !var7.blockMaterial.isSolid())
        {
            FCTileEntityBlockDispenser var8 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);
            ItemStack var9 = var8.GetCurrentItemToDispense();

            if (var9 != null)
            {
                Object var10 = null;
                boolean var11 = true;

                if (var9.getItem().OnItemUsedByBlockDispenser(var9, var1, var2, var3, var4, var5))
                {
                    var1.playAuxSFX(2241, var2, var3, var4, var5);
                    var8.OnDispenseCurrentSlot();
                    return true;
                }
            }
        }

        var1.playAuxSFX(2238, var2, var3, var4, 0);
        return false;
    }

    private boolean ValidateBlockDispenser(World var1, int var2, int var3, int var4)
    {
        TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

        if (var5 instanceof FCTileEntityBlockDispenser)
        {
            return true;
        }
        else
        {
            FCTileEntityBlockDispenser var6 = new FCTileEntityBlockDispenser();

            if (var5 instanceof TileEntityDispenser)
            {
                TileEntityDispenser var7 = (TileEntityDispenser)var5;
                int var8 = var7.getSizeInventory();
                int var9 = var6.getSizeInventory();

                for (int var10 = 0; var10 < var8 && var10 < var9; ++var10)
                {
                    ItemStack var11 = var7.getStackInSlot(var10);

                    if (var11 != null)
                    {
                        var6.setInventorySlotContents(var10, var11.copy());
                    }
                }
            }

            var1.setBlockTileEntity(var2, var3, var4, var6);
            return false;
        }
    }

    public void SpitOutItem(World var1, int var2, int var3, int var4, ItemStack var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(0, 0, 0, var6);
        double var8 = (double)var2 + (double)var7.i * 0.5D + 0.5D;
        double var10 = (double)(var3 + var7.j) + 0.2D;
        double var12 = (double)var4 + (double)var7.k * 0.5D + 0.5D;
        double var14;

        if (var6 > 2)
        {
            var14 = 0.1D;
        }
        else
        {
            var14 = (double)var7.j;
        }

        EntityItem var16 = new EntityItem(var1, var8, var10, var12, var5);
        double var17 = var1.rand.nextDouble() * 0.1D + 0.2D;
        var16.motionX = (double)var7.i * var17;
        var16.motionY = var14 * var17 + 0.2D;
        var16.motionZ = (double)var7.k * var17;
        var16.motionX += var1.rand.nextGaussian() * 0.0075D * 6.0D;
        var16.motionY += var1.rand.nextGaussian() * 0.0075D * 6.0D;
        var16.motionZ += var1.rand.nextGaussian() * 0.0075D * 6.0D;
        var1.spawnEntityInWorld(var16);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockBlockDispenser_top");
        this.blockIcon = var2;
        this.m_IconFront = var1.registerIcon("fcBlockBlockDispenser_front");
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockBlockDispenser_bottom");
        this.m_IconBySideArray[1] = var2;
        Icon var3 = var1.registerIcon("fcBlockBlockDispenser_side");
        this.m_IconBySideArray[2] = var3;
        this.m_IconBySideArray[3] = var3;
        this.m_IconBySideArray[4] = var3;
        this.m_IconBySideArray[5] = var3;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 3 ? this.m_IconFront : this.m_IconBySideArray[var1];
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var6 == var5 ? this.m_IconFront : this.m_IconBySideArray[var5];
    }
}
