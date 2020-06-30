package net.minecraft.src;

import java.util.Random;

public class FCBlockUnfiredClay extends Block
{
    public FCBlockUnfiredClay(int var1)
    {
        super(var1, Material.clay);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockUnfiredClay");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.clay.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 9;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void OnRotatedOnTurntable(World var1, int var2, int var3, int var4)
    {
        var1.playAuxSFX(2252, var2, var3, var4, this.blockID);
    }

    public int RotateOnTurntable(World var1, int var2, int var3, int var4, boolean var5, int var6)
    {
        var6 = super.RotateOnTurntable(var1, var2, var3, var4, var5, var6);
        var6 = this.TurntableCraftingRotation(var1, var2, var3, var4, var5, var6);
        return var6;
    }

    public int GetRotationsToCraftOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public int GetNewBlockIDCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcUnfiredPottery.blockID;
    }

    public int GetNewMetadataCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}
