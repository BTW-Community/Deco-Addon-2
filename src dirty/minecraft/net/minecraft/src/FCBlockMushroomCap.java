package net.minecraft.src;

import java.util.Random;

public class FCBlockMushroomCap extends BlockMushroomCap
{
    protected final int m_iMushroomType;

    public FCBlockMushroomCap(int var1, int var2)
    {
        super(var1, Material.wood, var2);
        this.m_iMushroomType = var2;
        this.setHardness(0.2F);
        this.setStepSound(soundWoodFootstep);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.HIGH);
        this.setUnlocalizedName("mushroom");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.m_iMushroomType != 0 ? FCBetterThanWolves.fcItemMushroomRed.itemID : FCBetterThanWolves.fcItemMushroomBrown.itemID;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }
}
