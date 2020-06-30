package net.minecraft.src;

import java.util.Random;

public class FCBlockFurnaceBrick extends FCBlockFurnace
{
    protected final FCModelBlock m_modelBlockInterior = new FCModelBlockFurnaceBrick();
    protected final float m_fClickYTopPortion = 0.375F;
    protected final float m_fClickYBottomPortion = 0.375F;

    protected FCBlockFurnaceBrick(int var1, boolean var2)
    {
        super(var1, var2);
        this.SetPicksEffectiveOn();
        this.setHardness(2.0F);
        this.setResistance(3.33F);
        this.setUnlocalizedName("fcBlockFurnaceBrick");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityFurnaceBrick();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);
        int var11 = var10 & 7;

        if (var11 != var6)
        {
            return false;
        }
        else
        {
            ItemStack var12 = var5.getCurrentEquippedItem();
            FCTileEntityFurnaceBrick var13 = (FCTileEntityFurnaceBrick)var1.getBlockTileEntity(var2, var3, var4);
            ItemStack var14 = var13.GetCookStack();

            if (var8 > 0.375F)
            {
                if (var14 != null)
                {
                    var13.GivePlayerCookStack(var5, var6);
                    return true;
                }

                if (var12 != null && this.IsValidCookItem(var12))
                {
                    if (!var1.isRemote)
                    {
                        var13.AddCookStack(new ItemStack(var12.itemID, 1, var12.getItemDamage()));
                    }

                    --var12.stackSize;
                    return true;
                }
            }
            else if (var8 < 0.375F && var12 != null)
            {
                Item var15 = var12.getItem();
                int var16 = var12.getItemDamage();

                if (var15.GetCanBeFedDirectlyIntoBrickOven(var16))
                {
                    if (!var1.isRemote)
                    {
                        int var17 = var13.AttemptToAddFuel(var12);

                        if (var17 > 0)
                        {
                            if (this.isActive)
                            {
                                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 0.2F + var1.rand.nextFloat() * 0.1F, var1.rand.nextFloat() * 0.25F + 1.25F);
                            }
                            else
                            {
                                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            }

                            var12.stackSize -= var17;
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 4 + var1.nextInt(6);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.brick.itemID;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return !FCUtilsWorld.DoesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) ? false : super.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4) & 7;
        return var7 != var5;
    }

    public void updateFurnaceBlockState(boolean var1, World var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        TileEntity var8 = var2.getBlockTileEntity(var3, var4, var5);
        keepFurnaceInventory = true;

        if (var1)
        {
            var2.setBlock(var3, var4, var5, FCBetterThanWolves.fcBlockFurnaceBrickBurning.blockID);
        }
        else
        {
            var2.setBlock(var3, var4, var5, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID);
        }

        keepFurnaceInventory = false;

        if (!var6)
        {
            var7 &= 7;
        }
        else
        {
            var7 |= 8;
        }

        var2.SetBlockMetadataWithNotify(var3, var4, var5, var7, 2);

        if (var8 != null)
        {
            var8.validate();
            var2.setBlockTileEntity(var3, var4, var5, var8);
        }
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (!this.isActive)
        {
            FCTileEntityFurnaceBrick var5 = (FCTileEntityFurnaceBrick)var1.getBlockTileEntity(var2, var3, var4);

            if (var5.GetVisualFuelLevel() > 0)
            {
                return true;
            }
        }

        return false;
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        if (!this.isActive)
        {
            FCTileEntityFurnaceBrick var5 = (FCTileEntityFurnaceBrick)var1.getBlockTileEntity(var2, var3, var4);

            if (var5.AttemptToLight())
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);
                return true;
            }
        }

        return false;
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (!this.isActive)
        {
            FCTileEntityFurnaceBrick var5 = (FCTileEntityFurnaceBrick)var1.getBlockTileEntity(var2, var3, var4);

            if (var5.HasValidFuel())
            {
                return 60;
            }
        }

        return 0;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    protected int IDDroppedSilkTouch()
    {
        return FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID;
    }

    public boolean GetIsBlockWarm(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.isActive;
    }

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsValidCookItem(ItemStack var1)
    {
        return FurnaceRecipes.smelting().getSmeltingResult(var1.getItem().itemID) != null;
    }
}
