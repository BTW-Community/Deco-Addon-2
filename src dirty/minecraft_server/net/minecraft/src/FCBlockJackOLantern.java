package net.minecraft.src;

import java.util.Random;

public class FCBlockJackOLantern extends Block
{
    protected FCBlockJackOLantern(int var1)
    {
        super(var1, Material.pumpkin);
        this.setTickRandomly(true);
        this.setHardness(1.0F);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setLightValue(1.0F);
        this.setUnlocalizedName("litpumpkin");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = MathHelper.floor_double((double)var5.rotationYaw * 4.0D / 360.0D + 2.5D) & 3;
        int var8 = var1.getBlockMetadata(var2, var3, var4) & -4;
        var8 |= var7;
        var1.SetBlockMetadataWithNotify(var2, var3, var4, var8, 2);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        int var5 = var1.getBlockMetadata(var2, var3, var4) | 8;
        var1.SetBlockMetadataWithNotify(var2, var3, var4, var5, 2);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.CheckForExtinguish(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.CheckForExtinguish(var1, var2, var3, var4);
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
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

    private void CheckForExtinguish(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if ((var5 & 8) != 0 && this.HasWaterToSidesOrTop(var1, var2, var3, var4))
        {
            this.ExtinguishLantern(var1, var2, var3, var4);
        }
    }

    private void ExtinguishLantern(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.pumpkin.blockID, var5 & 3);
        var1.playAuxSFX(2227, var2, var3, var4, 0);
    }
}
