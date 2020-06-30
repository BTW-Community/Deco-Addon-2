package net.minecraft.src;

import java.util.Random;

public class FCBlockPumpkinCarved extends BlockPumpkin
{
    protected FCBlockPumpkinCarved(int var1)
    {
        super(var1, false);
        this.setHardness(1.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("pumpkin");
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4) {}

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return var5 == 0 || blocksList[var5].blockMaterial.isReplaceable();
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        super.breakBlock(var1, var2, var3, var4, var5, var6);

        if (!var1.isRemote)
        {
            var1.playAuxSFX(2251, var2, var3, var4, 0);
        }
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1 & 3;

        if (var2)
        {
            ++var3;

            if (var3 > 3)
            {
                var3 = 0;
            }
        }
        else
        {
            --var3;

            if (var3 < 0)
            {
                var3 = 3;
            }
        }

        return var1 & -4 | var3;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var5.CanGrazeOnRoughVegetation();
    }
}
