package net.minecraft.src;

import java.util.Random;

public class FCBlockFire extends BlockFire
{
    private Icon[] m_StokedFireTopTextureArray;

    protected FCBlockFire(int var1)
    {
        super(var1);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else if (this.DoesBlockBelowExtiguishFire(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else
        {
            int var6 = var1.getBlockId(var2, var3 - 1, var4);
            boolean var7 = false;

            if (var6 == Block.netherrack.blockID)
            {
                var7 = true;
            }
            else if (var1.provider.dimensionId == 1 && var6 == Block.bedrock.blockID)
            {
                var7 = true;
            }
            else if (this.HasInfiniteBurnNeighbor(var1, var2, var3, var4))
            {
                var7 = true;
            }

            if (!var7 && var1.isRaining() && (var1.IsRainingAtPos(var2, var3, var4) || var1.IsRainingAtPos(var2 - 1, var3, var4) || var1.IsRainingAtPos(var2 + 1, var3, var4) || var1.IsRainingAtPos(var2, var3, var4 - 1) || var1.IsRainingAtPos(var2, var3, var4 + 1)))
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
            else
            {
                int var8 = var1.getBlockMetadata(var2, var3, var4);

                if (var8 < 15)
                {
                    var1.setBlockMetadata(var2, var3, var4, var8 + var5.nextInt(3) / 2);
                }

                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var5.nextInt(10));

                if (!var7 && !this.canNeighborBurn(var1, var2, var3, var4))
                {
                    if (!var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) || var8 > 3)
                    {
                        var1.setBlockWithNotify(var2, var3, var4, 0);
                    }
                }
                else if (!var7 && !this.canBlockCatchFire(var1, var2, var3 - 1, var4) && var8 == 15 && var5.nextInt(4) == 0)
                {
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
                else
                {
                    boolean var9 = var1.isBlockHighHumidity(var2, var3, var4);
                    byte var10 = 0;

                    if (var9)
                    {
                        var10 = -50;
                    }

                    this.TryToDestroyBlockWithFire(var1, var2 + 1, var3, var4, 300 + var10, var5, var8);
                    this.TryToDestroyBlockWithFire(var1, var2 - 1, var3, var4, 300 + var10, var5, var8);
                    this.TryToDestroyBlockWithFire(var1, var2, var3 - 1, var4, 250 + var10, var5, var8);
                    this.TryToDestroyBlockWithFire(var1, var2, var3 + 1, var4, 250 + var10, var5, var8);
                    this.TryToDestroyBlockWithFire(var1, var2, var3, var4 - 1, 300 + var10, var5, var8);
                    this.TryToDestroyBlockWithFire(var1, var2, var3, var4 + 1, 300 + var10, var5, var8);
                    CheckForFireSpreadFromLocation(var1, var2, var3, var4, var5, var8);
                }
            }
        }
    }

    /**
     * Checks the specified block coordinate to see if it can catch fire.  Args: blockAccess, x, y, z
     */
    public boolean canBlockCatchFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcAestheticOpaque.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 == 3)
            {
                return true;
            }
        }

        return super.canBlockCatchFire(var1, var2, var3, var4);
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean HasInfiniteBurnNeighbor(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 6; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
            var6.AddFacingAsOffset(var5);
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);
            Block var8 = Block.blocksList[var7];

            if (var8 != null && var8.DoesInfiniteBurnToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
            {
                return true;
            }
        }

        return false;
    }

    public boolean DoesBlockBelowExtiguishFire(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null ? var6.DoesExtinguishFireAbove(var1, var2, var3 - 1, var4) : false;
    }

    protected void TryToDestroyBlockWithFire(World var1, int var2, int var3, int var4, int var5, Random var6, int var7)
    {
        int var8 = abilityToCatchFire[var1.getBlockId(var2, var3, var4)];

        if (var6.nextInt(var5) < var8)
        {
            OnBlockDestroyedByFire(var1, var2, var3, var4, var7, false);
        }
    }

    protected static void OnBlockDestroyedByFire(World var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var0.getBlockId(var1, var2, var3);
        Block var7 = Block.blocksList[var6];

        if (var7 != null)
        {
            var7.OnDestroyedByFire(var0, var1, var2, var3, var4, var5);
        }
    }

    public static void CheckForFireSpreadFromLocation(World var0, int var1, int var2, int var3, Random var4, int var5)
    {
        boolean var6 = var0.isBlockHighHumidity(var1, var2, var3);

        for (int var7 = var1 - 1; var7 <= var1 + 1; ++var7)
        {
            for (int var8 = var3 - 1; var8 <= var3 + 1; ++var8)
            {
                for (int var9 = var2 - 1; var9 <= var2 + 4; ++var9)
                {
                    if (var7 != var1 || var9 != var2 || var8 != var3)
                    {
                        int var10 = 100;

                        if (var9 > var2 + 1)
                        {
                            var10 += (var9 - (var2 + 1)) * 100;
                        }

                        CheckForFireSpreadToOneBlockLocation(var0, var7, var9, var8, var4, var5, var6, var10);
                    }
                }
            }
        }
    }

    public static void CheckForSmoulderingSpreadFromLocation(World var0, int var1, int var2, int var3)
    {
        boolean var4 = var0.isBlockHighHumidity(var1, var2, var3);

        for (int var5 = var1 - 1; var5 <= var1 + 1; ++var5)
        {
            for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
            {
                for (int var7 = var2; var7 <= var2 + 1; ++var7)
                {
                    if (var5 != var1 || var7 != var2 || var6 != var3)
                    {
                        byte var8 = 50;
                        CheckForFireSpreadToOneBlockLocation(var0, var5, var7, var6, var0.rand, 0, var4, var8);
                    }
                }
            }
        }
    }

    public static boolean HasFlammableNeighborsWithinSmoulderRange(World var0, int var1, int var2, int var3)
    {
        for (int var4 = var1 - 1; var4 <= var1 + 1; ++var4)
        {
            for (int var5 = var3 - 1; var5 <= var3 + 1; ++var5)
            {
                for (int var6 = var2; var6 <= var2 + 1; ++var6)
                {
                    if ((var4 != var1 || var6 != var2 || var5 != var3) && IsFlammableOrHasFlammableNeighbors(var0, var4, var6, var5))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean IsFlammableOrHasFlammableNeighbors(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];
        return abilityToCatchFire[var4] > 0 || chanceToEncourageFire[var4] > 0 || var5 != null && var5.GetCanBeSetOnFireDirectly(var0, var1, var2, var3) || CanFireReplaceBlock(var0, var1, var2, var3) && (chanceToEncourageFire[var0.getBlockId(var1 - 1, var2, var3)] > 0 || chanceToEncourageFire[var0.getBlockId(var1 + 1, var2, var3)] > 0 || chanceToEncourageFire[var0.getBlockId(var1, var2 - 1, var3)] > 0 || chanceToEncourageFire[var0.getBlockId(var1, var2 + 1, var3)] > 0 || chanceToEncourageFire[var0.getBlockId(var1, var2, var3 - 1)] > 0 || chanceToEncourageFire[var0.getBlockId(var1, var2, var3 + 1)] > 0);
    }

    private static void CheckForFireSpreadToOneBlockLocation(World var0, int var1, int var2, int var3, Random var4, int var5, boolean var6, int var7)
    {
        int var8 = GetChanceOfNeighborsEncouragingFireCustom(var0, var1, var2, var3);

        if (var8 > 0)
        {
            int var9 = (var8 + 61) / (var5 + 30);

            if (var6)
            {
                var9 /= 2;
            }

            if (var9 > 0 && var4.nextInt(var7) <= var9 && (!var0.isRaining() || !var0.IsRainingAtPos(var1, var2, var3)) && !var0.IsRainingAtPos(var1 - 1, var2, var3) && !var0.IsRainingAtPos(var1 + 1, var2, var3) && !var0.IsRainingAtPos(var1, var2, var3 - 1) && !var0.IsRainingAtPos(var1, var2, var3 + 1))
            {
                int var10 = var5 + var4.nextInt(5) / 4;

                if (var10 > 15)
                {
                    var10 = 15;
                }

                if (CanFireReplaceBlock(var0, var1, var2, var3))
                {
                    var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.fire.blockID, var10);
                }
                else
                {
                    Block var11 = Block.blocksList[var0.getBlockId(var1, var2, var3)];

                    if (var11 != null && var11.GetCanBeSetOnFireDirectly(var0, var1, var2, var3))
                    {
                        var11.SetOnFireDirectly(var0, var1, var2, var3);
                    }
                }
            }
        }
    }

    public static void CheckForFireSpreadAndDestructionToOneBlockLocation(World var0, int var1, int var2, int var3)
    {
        CheckForFireSpreadAndDestructionToOneBlockLocation(var0, var1, var2, var3, var0.rand, 0, 100);
    }

    public static void CheckForFireSpreadAndDestructionToOneBlockLocation(World var0, int var1, int var2, int var3, Random var4, int var5, int var6)
    {
        int var7 = abilityToCatchFire[var0.getBlockId(var1, var2, var3)];
        boolean var8 = var0.isBlockHighHumidity(var1, var2, var3);
        int var9 = 250;

        if (var8)
        {
            var9 -= 50;
        }

        if (var4.nextInt(var9) < var7)
        {
            OnBlockDestroyedByFire(var0, var1, var2, var3, var5, true);
        }
        else
        {
            CheckForFireSpreadToOneBlockLocation(var0, var1, var2, var3, var4, var5, var8, var6);
        }
    }

    protected static int GetChanceOfNeighborsEncouragingFireCustom(World var0, int var1, int var2, int var3)
    {
        if (!CanFireReplaceBlock(var0, var1, var2, var3))
        {
            Block var5 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
            return var5 != null && var5.GetCanBeSetOnFireDirectly(var0, var1, var2, var3) ? var5.GetChanceOfFireSpreadingDirectlyTo(var0, var1, var2, var3) : 0;
        }
        else
        {
            int var4 = GetChanceToEncourageFire(var0, var1 + 1, var2, var3, 0);
            var4 = GetChanceToEncourageFire(var0, var1 - 1, var2, var3, var4);
            var4 = GetChanceToEncourageFire(var0, var1, var2 - 1, var3, var4);
            var4 = GetChanceToEncourageFire(var0, var1, var2 + 1, var3, var4);
            var4 = GetChanceToEncourageFire(var0, var1, var2, var3 - 1, var4);
            var4 = GetChanceToEncourageFire(var0, var1, var2, var3 + 1, var4);
            return var4;
        }
    }

    public static int GetChanceToEncourageFire(World var0, int var1, int var2, int var3, int var4)
    {
        int var5 = chanceToEncourageFire[var0.getBlockId(var1, var2, var3)];
        return var5 > var4 ? var5 : var4;
    }

    public static boolean CanBlockBeDestroyedByFire(int var0)
    {
        return abilityToCatchFire[var0] > 0;
    }

    public static boolean CanFireReplaceBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var4 == null || var4.GetCanBlockBeReplacedByFire(var0, var1, var2, var3);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_StokedFireTopTextureArray = new Icon[] {var1.registerIcon("fcBlockFireStokedTopStub_0", new FCClientTextureFire("fcBlockFireStokedTopStub_0", 0)), var1.registerIcon("fcBlockFireStokedTopStub_1", new FCClientTextureFire("fcBlockFireStokedTopStub_1", 1))};
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderBlockFire(this, var2, var3, var4);

        if (var5.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBlockFireStoked.blockID)
        {
            Tessellator var6 = Tessellator.instance;
            Icon var7 = this.m_StokedFireTopTextureArray[0];
            Icon var8 = this.m_StokedFireTopTextureArray[1];

            if ((var2 + var4 & 1) != 0)
            {
                var7 = this.m_StokedFireTopTextureArray[1];
                var8 = this.m_StokedFireTopTextureArray[0];
            }

            var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var6.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
            float var9 = 1.0F;
            double var10 = (double)var2 + 0.5D - 0.5D;
            double var12 = (double)var2 + 0.5D + 0.5D;
            double var14 = (double)var4 + 0.5D - 0.5D;
            double var16 = (double)var4 + 0.5D + 0.5D;
            double var18 = (double)var2 + 0.5D - 0.5D;
            double var20 = (double)var2 + 0.5D + 0.5D;
            double var22 = (double)var4 + 0.5D - 0.5D;
            double var24 = (double)var4 + 0.5D + 0.5D;
            double var26 = (double)var8.getMinU();
            double var28 = (double)var8.getMinV();
            double var30 = (double)var8.getMaxU();
            double var32 = (double)var8.getMaxV();
            var6.addVertexWithUV(var18, (double)((float)var3 + var9), (double)(var4 + 0), var26, var28);
            var6.addVertexWithUV(var10, (double)(var3 + 0), (double)(var4 + 0), var26, var32);
            var6.addVertexWithUV(var10, (double)(var3 + 0), (double)(var4 + 1), var30, var32);
            var6.addVertexWithUV(var18, (double)((float)var3 + var9), (double)(var4 + 1), var30, var28);
            var6.addVertexWithUV(var20, (double)((float)var3 + var9), (double)(var4 + 1), var26, var28);
            var6.addVertexWithUV(var12, (double)(var3 + 0), (double)(var4 + 1), var26, var32);
            var6.addVertexWithUV(var12, (double)(var3 + 0), (double)(var4 + 0), var30, var32);
            var6.addVertexWithUV(var20, (double)((float)var3 + var9), (double)(var4 + 0), var30, var28);
            var26 = (double)var7.getMinU();
            var28 = (double)var7.getMinV();
            var30 = (double)var7.getMaxU();
            var32 = (double)var7.getMaxV();
            var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var9), var24, var26, var28);
            var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var16, var26, var32);
            var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var16, var30, var32);
            var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var9), var24, var30, var28);
            var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var9), var22, var26, var28);
            var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var14, var26, var32);
            var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var14, var30, var32);
            var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var9), var22, var30, var28);
        }

        return true;
    }
}
