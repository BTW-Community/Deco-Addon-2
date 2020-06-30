package net.minecraft.src;

import java.util.Random;

public class FCBlockLogSmouldering extends FCBlockFalling
{
    private static final int m_iChanceOfDecay = 5;
    private static final int m_iChanceOfExtinguishInRain = 5;
    private static final float m_fExplosionStrength = 1.0F;
    private Icon m_iconEmbers;

    protected FCBlockLogSmouldering(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.setTickRandomly(true);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockLogSmouldering");
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        float var5 = super.getBlockHardness(var1, var2, var3, var4);
        var1.getBlockMetadata(var2, var3, var4);

        if (this.GetIsStump(var1, var2, var3, var4))
        {
            var5 *= 3.0F;
        }

        return var5;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return !this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return this.GetIsStump(var2, var3, var4, var5);
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (this.GetIsStump(var2.getBlockMetadata(var3, var4, var5)))
        {
            var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockStumpCharred.blockID);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.HasWaterToSidesOrTop(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (!this.GetIsStump(var6) && this.GetBurnLevel(var6) > 0)
            {
                super.updateTick(var1, var2, var3, var4, var5);
            }
        }
        else
        {
            this.ConvertToCinders(var1, var2, var3, var4);
            var1.playAuxSFX(2227, var2, var3, var4, 0);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForGoOutInRain(var1, var2, var3, var4))
        {
            FCBlockFire.CheckForSmoulderingSpreadFromLocation(var1, var2, var3, var4);
            int var6 = this.GetBurnLevel(var1, var2, var3, var4);

            if (var6 == 0)
            {
                if (!FCBlockFire.HasFlammableNeighborsWithinSmoulderRange(var1, var2, var3, var4))
                {
                    int var7 = var1.getBlockMetadata(var2, var3, var4);
                    var7 = this.SetBurnLevel(var7, 1);

                    if (this.IsSupportedBySolidBlocks(var1, var2, var3, var4))
                    {
                        var7 = this.SetShouldSuppressSnapOnFall(var7, true);
                    }

                    var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
                    this.ScheduleCheckForFall(var1, var2, var3, var4);
                }
            }
            else if (var5.nextInt(5) == 0)
            {
                if (var6 < 3)
                {
                    this.SetBurnLevel(var1, var2, var3, var4, var6 + 1);
                }
                else
                {
                    this.ConvertToCinders(var1, var2, var3, var4);
                }
            }
        }
    }

    public boolean GetIsBlockWarm(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand var1)
    {
        if (!this.GetShouldSuppressSnapOnFall(var1.metadata))
        {
            var1.worldObj.playAuxSFX(2276, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ), 0);
            var1.metadata = this.SetShouldSuppressSnapOnFall(var1.metadata, true);
        }
    }

    protected void OnFallingUpdate(FCEntityFallingBlock var1)
    {
        if (var1.worldObj.isRemote)
        {
            this.EmitSmokeParticles(var1.worldObj, var1.posX, var1.posY, var1.posZ, var1.worldObj.rand, this.GetBurnLevel(var1.metadata));
        }
    }

    public boolean OnFinishedFalling(EntityFallingSand var1, float var2)
    {
        if (!var1.worldObj.isRemote)
        {
            int var3 = MathHelper.floor_double(var1.posX);
            int var4 = MathHelper.floor_double(var1.posY);
            int var5 = MathHelper.floor_double(var1.posZ);
            int var6 = MathHelper.ceiling_float_int(var2 - 5.0F);

            if (var6 >= 0 && !Material.water.equals(var1.worldObj.getBlockMaterial(var3, var4, var5)) && var1.rand.nextInt(5) < var6)
            {
                this.Explode(var1.worldObj, (double)var3 + 0.5D, (double)var4 + 0.5D, (double)var5 + 0.5D);
                return false;
            }
        }

        return true;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1000;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.Explode(var1, (double)var3 + 0.5D, (double)var4 + 0.5D, (double)var5 + 0.5D);
    }

    public int GetBurnLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.GetBurnLevel(var5);
    }

    public int GetBurnLevel(int var1)
    {
        return var1 & 3;
    }

    public void SetBurnLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetBurnLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetBurnLevel(int var1, int var2)
    {
        var1 &= -4;
        return var1 | var2;
    }

    public boolean GetShouldSuppressSnapOnFall(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.GetShouldSuppressSnapOnFall(var5);
    }

    public boolean GetShouldSuppressSnapOnFall(int var1)
    {
        return (var1 & 4) != 0;
    }

    public int SetShouldSuppressSnapOnFall(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 4;
        }
        else
        {
            var1 &= -5;
        }

        return var1;
    }

    public boolean GetIsStump(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.GetIsStump(var5);
    }

    public boolean GetIsStump(int var1)
    {
        return (var1 & 8) != 0;
    }

    public int SetIsStump(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 8;
        }
        else
        {
            var1 &= -9;
        }

        return var1;
    }

    private boolean CheckForGoOutInRain(World var1, int var2, int var3, int var4)
    {
        if (var1.rand.nextInt(5) == 0 && var1.IsRainingAtPos(var2, var3 + 1, var4))
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
            this.ConvertToCinders(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void ConvertToCinders(World var1, int var2, int var3, int var4)
    {
        if (this.GetIsStump(var1, var2, var3, var4))
        {
            int var5 = FCBetterThanWolves.fcBlockWoodCinders.SetIsStump(0, true);
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockWoodCinders.blockID, var5);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockWoodCinders.blockID);
        }
    }

    private void EmitSmokeParticles(World var1, double var2, double var4, double var6, Random var8, int var9)
    {
        for (int var10 = 0; var10 < 5; ++var10)
        {
            double var11 = var2 - 0.6D + var8.nextDouble() * 1.2D;
            double var13 = var4 + 0.25D + var8.nextDouble() * 0.25D;
            double var15 = var6 - 0.6D + var8.nextDouble() * 1.2D;

            if (var9 > 0)
            {
                var1.spawnParticle("fcwhitesmoke", var11, var13, var15, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                var1.spawnParticle("largesmoke", var11, var13, var15, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        if (!var1.isRemote)
        {
            var5.AddSecondaryExplosionNoFX((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, 1.0F, true, false);
        }
    }

    private void Explode(World var1, double var2, double var4, double var6)
    {
        var1.NewExplosionNoFX((Entity)null, var2, var4, var6, 1.0F, true, false);
        this.NotifyNearbyAnimalsFinishedFalling(var1, MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6));
        var1.playAuxSFX(2277, MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 0);
    }

    protected boolean IsSupportedBySolidBlocks(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];
        return var5 != null && var5.HasLargeCenterHardPointToFacing(var1, var2, var3 - 1, var4, 1, false);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconEmbers = var1.registerIcon("fcOverlayLogEmbers");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockLog(this, var2, var3, var4);
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var1.blockAccess, var2, var3, var4, this.m_iconEmbers);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.renderBlockAsItemVanilla(this, var2, var3);
        FCClientUtilsRender.RenderInvBlockFullBrightWithTexture(var1, this, -0.5F, -0.5F, -0.5F, this.m_iconEmbers);
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.SetRenderAllFaces(true);
        var1.setRenderBounds(this.GetFixedBlockBoundsFromPool());
        var1.renderStandardBlock(this, var2, var3, var4);
        FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var1.blockAccess, var2, var3, var4, this.m_iconEmbers);
        var1.SetRenderAllFaces(false);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.EmitSmokeParticles(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var5, this.GetBurnLevel(var1, var2, var3, var4));

        if (var5.nextInt(24) == 0)
        {
            float var6 = 0.1F + var5.nextFloat() * 0.1F;
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "fire.fire", var6, var5.nextFloat() * 0.7F + 0.3F, false);
        }
    }
}
