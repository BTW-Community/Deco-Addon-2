package net.minecraft.src;

public class FCBlockMushroomCapLegacy extends FCBlockMushroomCap
{
    public FCBlockMushroomCapLegacy(int var1, int var2)
    {
        super(var1, var2);
        this.SetFireProperties(FCEnumFlammability.NONE);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        int var5 = FCBetterThanWolves.fcBlockMushroomCapBrown.blockID;

        if (this.m_iMushroomType != 0)
        {
            var5 = FCBetterThanWolves.fcBlockMushroomCapRed.blockID;
        }

        var1.setBlock(var2, var3, var4, var5, var1.getBlockMetadata(var2, var3, var4), 2);
    }
}
