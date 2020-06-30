package net.minecraft.src;

import java.util.Random;

public class FCBlockLeaves extends BlockLeaves
{
    protected static final int m_iAdjacentTreeBlockArrayWidth = 32;
    protected static final int iArrayWidthHalf = 16;
    protected static final int m_iAdjacentTreeBlockSearchDist = 4;
    protected static final int m_iAdjacentTreeBlockChunkCheckDist = 5;
    protected int[][][] m_iAdjacentTreeBlocks = new int[32][32][32];

    protected FCBlockLeaves(int var1)
    {
        super(var1);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setLightOpacity(1);
        this.SetFireProperties(FCEnumFlammability.LEAVES);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("leaves");
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.5F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            int var8 = var5 & 3;
            byte var9 = 20;

            if (var8 == 3)
            {
                var9 = 40;
            }

            if (var1.rand.nextInt(var9) == 0)
            {
                int var10 = this.idDropped(var5, var1.rand, var7);
                this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.isRemote)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if ((var6 & 8) != 0 && (var6 & 4) == 0 && var1.checkChunksExist(var2 - 5, var3 - 5, var4 - 5, var2 + 5, var3 + 5, var4 + 5))
            {
                this.UpdateAdjacentTreeBlockArray(var1, var2, var3, var4);
                int var7 = this.m_iAdjacentTreeBlocks[16][16][16];

                if (var7 >= 0)
                {
                    int var8 = var6 & 7;
                    var1.SetBlockMetadataWithNotify(var2, var3, var4, var8, 4);
                }
                else
                {
                    this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                    var1.setBlockToAir(var2, var3, var4);
                }
            }
        }
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var6;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
        this.GenerateAshOnBurn(var1, var2, var3, var4);
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return this.createStackedBlock(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return var1.provider.dimensionId != -1;
    }

    protected void GenerateAshOnBurn(World var1, int var2, int var3, int var4)
    {
        for (int var5 = var3; var5 > 0; --var5)
        {
            if (FCBlockAshGroundCover.CanAshReplaceBlock(var1, var2, var5, var4))
            {
                int var6 = var1.getBlockId(var2, var5 - 1, var4);
                Block var7 = Block.blocksList[var6];

                if (var7 != null && var7.CanGroundCoverRestOnBlock(var1, var2, var5 - 1, var4))
                {
                    var1.setBlockWithNotify(var2, var5, var4, FCBetterThanWolves.fcBlockAshGroundCover.blockID);
                    break;
                }
            }
            else if (var1.getBlockId(var2, var5, var4) != Block.fire.blockID)
            {
                break;
            }
        }
    }

    protected void UpdateAdjacentTreeBlockArray(World var1, int var2, int var3, int var4)
    {
        int var5;
        int var6;
        int var7;
        int var8;

        for (var5 = -4; var5 <= 4; ++var5)
        {
            for (var6 = -4; var6 <= 4; ++var6)
            {
                for (var7 = -4; var7 <= 4; ++var7)
                {
                    var8 = var1.getBlockId(var2 + var5, var3 + var6, var4 + var7);

                    if (var8 == Block.wood.blockID && !((FCBlockLog)((FCBlockLog)Block.wood)).IsDeadStump(var1, var2 + var5, var3 + var6, var4 + var7))
                    {
                        this.m_iAdjacentTreeBlocks[var5 + 16][var6 + 16][var7 + 16] = 0;
                    }
                    else if (var8 == Block.leaves.blockID)
                    {
                        this.m_iAdjacentTreeBlocks[var5 + 16][var6 + 16][var7 + 16] = -2;
                    }
                    else
                    {
                        this.m_iAdjacentTreeBlocks[var5 + 16][var6 + 16][var7 + 16] = -1;
                    }
                }
            }
        }

        for (var5 = 1; var5 <= 4; ++var5)
        {
            for (var6 = -4; var6 <= 4; ++var6)
            {
                for (var7 = -4; var7 <= 4; ++var7)
                {
                    for (var8 = -4; var8 <= 4; ++var8)
                    {
                        if (this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16][var8 + 16] == var5 - 1)
                        {
                            if (this.m_iAdjacentTreeBlocks[var6 + 16 - 1][var7 + 16][var8 + 16] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16 - 1][var7 + 16][var8 + 16] = var5;
                            }

                            if (this.m_iAdjacentTreeBlocks[var6 + 16 + 1][var7 + 16][var8 + 16] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16 + 1][var7 + 16][var8 + 16] = var5;
                            }

                            if (this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16 - 1][var8 + 16] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16 - 1][var8 + 16] = var5;
                            }

                            if (this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16 + 1][var8 + 16] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16 + 1][var8 + 16] = var5;
                            }

                            if (this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16][var8 + 16 - 1] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16][var8 + 16 - 1] = var5;
                            }

                            if (this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16][var8 + 16 + 1] == -2)
                            {
                                this.m_iAdjacentTreeBlocks[var6 + 16][var7 + 16][var8 + 16 + 1] = var5;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.IsRainingAtPos(var2, var3 + 1, var4) && !var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) && var5.nextInt(15) == 1)
        {
            var1.spawnParticle("dripWater", (double)var2 + var5.nextDouble(), (double)var3 - 0.05D, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}
