package net.minecraft.src;

import java.util.Random;

public class FCBlockTurntable extends BlockContainer implements FCIBlockMechanical
{
    private static final int iTurntableTickRate = 10;

    protected FCBlockTurntable(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(2.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockTurntable");
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
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityTurntable();
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
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.IsBlockMechanicalOn(var1, var2, var3, var4);

        if (var7 != var6)
        {
            this.EmitTurntableParticles(var1, var2, var3, var4, var5);
            this.SetBlockMechanicalOn(var1, var2, var3, var4, var6);
            var1.markBlockForUpdate(var2, var3, var4);
        }

        boolean var8 = var1.isBlockGettingPowered(var2, var3, var4);
        boolean var9 = this.IsBlockRedstoneOn(var1, var2, var3, var4);

        if (var9 != var8)
        {
            this.SetBlockRedstoneOn(var1, var2, var3, var4, var8);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();

        if (var10 != null)
        {
            return false;
        }
        else
        {
            if (!var1.isRemote)
            {
                int var11 = this.GetSwitchSetting(var1, var2, var3, var4);
                ++var11;

                if (var11 > 3)
                {
                    var11 = 0;
                }

                this.SetSwitchSetting(var1, var2, var3, var4, var11);
                var1.markBlockForUpdate(var2, var3, var4);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                var1.playAuxSFX(1001, var2, var3, var4, 0);
            }

            return true;
        }
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
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
        return FCUtilsMechPower.IsBlockPoweredByAxleToSide(var1, var2, var3, var4, 0);
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 0;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakTurntable(var1, var2, var3, var4);
    }

    public boolean IsBlockMechanicalOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    public void SetBlockMechanicalOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -2;

        if (var5)
        {
            var6 |= 1;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean IsBlockRedstoneOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 2) > 0;
    }

    public void SetBlockRedstoneOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -3;

        if (var5)
        {
            var6 |= 2;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int GetSwitchSetting(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 12) >> 2;
    }

    public void SetSwitchSetting(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 4 || var5 < 0)
        {
            var5 = 0;
        }

        int var6 = var1.getBlockMetadata(var2, var3, var4) & -13;
        var6 |= var5 << 2;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    void EmitTurntableParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 5; ++var6)
        {
            float var7 = (float)var2 + var5.nextFloat();
            float var8 = (float)var3 + var5.nextFloat() * 0.5F + 1.0F;
            float var9 = (float)var4 + var5.nextFloat();
            var1.spawnParticle("smoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
        }
    }

    private void BreakTurntable(World var1, int var2, int var3, int var4)
    {
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Item.redstone.itemID, 0);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 16, 0, 0.75F);

        for (int var5 = 0; var5 < 2; ++var5)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 0);
        }

        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }
}
