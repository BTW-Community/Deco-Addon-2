package net.minecraft.src;

import java.util.Random;

public class FCBlockMilk extends FCBlockFalling
{
    public static final double m_dHeight = 0.0625D;
    public static final int m_iDecayTickRate = 10;

    public FCBlockMilk(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialMilk);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockMilk");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
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
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4))
        {
            int var6 = this.GetDecayLevel(var1, var2, var3, var4);

            if (var6 < 1)
            {
                ++var6;
                this.SetDecayLevel(var1, var2, var3, var4, var6);
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 10);
            }
            else
            {
                var1.setBlockToAir(var2, var3, var4);
            }
        }
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand var1)
    {
        var1.metadata = this.SetDecayLevel(var1.metadata, 0);
    }

    public int GetDecayLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetDecayLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetDecayLevel(int var1)
    {
        return var1 & 1;
    }

    public void SetDecayLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetDecayLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetDecayLevel(int var1, int var2)
    {
        var1 &= -2;
        return var1 | var2;
    }
}
