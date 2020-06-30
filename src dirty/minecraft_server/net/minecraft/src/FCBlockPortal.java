package net.minecraft.src;

import java.util.Random;

public class FCBlockPortal extends BlockPortal
{
    private static final int m_iChanceOfCheckingForPossession = 10;
    private static final int m_iCreaturePossessionRange = 16;

    public FCBlockPortal(int var1)
    {
        super(var1);
        this.setHardness(-1.0F);
        this.setLightValue(0.75F);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("portal");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var5.nextInt(10) == 0 && var1.provider.isSurfaceWorld())
        {
            EntityCreature.AttemptToPossessCreaturesAroundBlock(var1, var2, var3, var4, 1, 16);
        }

        FCUtilsWorld.GameProgressSetNetherBeenAccessedServerOnly();
    }

    /**
     * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World var1, int var2, int var3, int var4)
    {
        byte var5 = 0;
        byte var6 = 0;

        if (var1.getBlockId(var2 - 1, var3, var4) == Block.obsidian.blockID || var1.getBlockId(var2 + 1, var3, var4) == Block.obsidian.blockID)
        {
            var5 = 1;
        }

        if (var1.getBlockId(var2, var3, var4 - 1) == Block.obsidian.blockID || var1.getBlockId(var2, var3, var4 + 1) == Block.obsidian.blockID)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (var1.getBlockId(var2 - var5, var3, var4 - var6) != Block.obsidian.blockID)
            {
                var2 -= var5;
                var4 -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        int var10 = var1.getBlockId(var2 + var5 * var7, var3 + var8, var4 + var6 * var7);

                        if (var9)
                        {
                            if (var10 != Block.obsidian.blockID)
                            {
                                return false;
                            }
                        }
                        else if (!var1.isAirBlock(var2 + var5 * var7, var3 + var8, var4 + var6 * var7) && var10 != Block.fire.blockID && var10 != FCBetterThanWolves.fcBlockCampfireLarge.blockID && var10 != FCBetterThanWolves.fcBlockCampfireMedium.blockID && var10 != FCBetterThanWolves.fcBlockCampfireSmall.blockID && var10 != FCBetterThanWolves.fcBlockCampfireUnlit.blockID)
                        {
                            return false;
                        }
                    }
                }
            }

            for (var7 = 0; var7 < 2; ++var7)
            {
                for (var8 = 0; var8 < 3; ++var8)
                {
                    var1.setBlock(var2 + var5 * var7, var3 + var8, var4 + var6 * var7, Block.portal.blockID, 0, 2);
                }
            }

            FCUtilsWorld.GameProgressSetNetherBeenAccessedServerOnly();
            return true;
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5;
        float var6;

        if (var1.getBlockId(var2 - 1, var3, var4) != this.blockID && var1.getBlockId(var2 + 1, var3, var4) != this.blockID)
        {
            var5 = 0.125F;
            var6 = 0.5F;
        }
        else
        {
            var5 = 0.5F;
            var6 = 0.125F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var5), 0.0D, (double)(0.5F - var6), (double)(0.5F + var5), 1.0D, (double)(0.5F + var6));
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }
}
