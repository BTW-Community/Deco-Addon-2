package net.minecraft.src;

import java.util.Random;

public class FCBlockLavaPillow extends FCBlockFullBlock
{
    public FCBlockLavaPillow(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(0.8F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("fcBlockLavaPillow");
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

        if (var1.isAirBlock(var2, var3, var4))
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);

            if (!this.HasWaterToSidesOrTop(var1, var2, var3, var4))
            {
                byte var7 = 1;
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.lavaMoving.blockID, var7);
                int var10 = var7 + 1;

                for (int var8 = 2; var8 <= 5; ++var8)
                {
                    FCUtilsBlockPos var9 = new FCUtilsBlockPos(var2, var3, var4, var8);

                    if (var1.isAirBlock(var9.i, var9.j, var9.k))
                    {
                        var1.setBlockAndMetadataWithNotify(var9.i, var9.j, var9.k, Block.lavaMoving.blockID, var10);
                    }
                }
            }
        }
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    public boolean IsBlockDestroyedByBlockDispenser(int var1)
    {
        return true;
    }
}
