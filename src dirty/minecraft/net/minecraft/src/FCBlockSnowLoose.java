package net.minecraft.src;

import java.util.Random;

public class FCBlockSnowLoose extends FCBlockFalling
{
    public static final float m_fHardness = 0.2F;
    public static final int m_iChanceOfHardeningIncrement = 1;
    public static final int m_iChanceOfMeltingIncrement = 1;
    private Icon[] m_iconsHardening;

    public FCBlockSnowLoose(int var1)
    {
        super(var1, Material.craftedSnow);
        this.setHardness(0.2F);
        this.SetShovelsEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundSnowFootstep);
        this.setUnlocalizedName("fcBlockSnowLoose");
        this.setTickRandomly(true);
        this.setLightOpacity(4);
        Block.useNeighborBrightness[var1] = true;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.snowball.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 8;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (var1.provider.isHellWorld)
        {
            var1.setBlockToAir(var2, var3, var4);
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

            for (int var5 = 0; var5 < 8; ++var5)
            {
                var1.spawnParticle("largesmoke", (double)var2 + Math.random(), (double)var3 + Math.random(), (double)var4 + Math.random(), 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            this.ScheduleCheckForFall(var1, var2, var3, var4);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.HasStickySnowNeighborInContact(var1, var2, var3, var4) || this.HasFallingBlockRestingOn(var1, var2, var3, var4))
        {
            this.CheckForFall(var1, var2, var3, var4);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6;

        if (FCUtilsMisc.IsIKInColdBiome(var1, var2, var4))
        {
            if (var5.nextInt(1) == 0)
            {
                var6 = this.GetHardeningLevel(var1, var2, var3, var4);

                if (var6 < 7)
                {
                    this.SetHardeningLevel(var1, var2, var3, var4, var6 + 1);
                }
                else
                {
                    this.ConvertToSolidSnow(var1, var2, var3, var4);
                }
            }
        }
        else if (var5.nextInt(1) == 0)
        {
            var6 = this.GetHardeningLevel(var1, var2, var3, var4);

            if (var6 > 0)
            {
                this.SetHardeningLevel(var1, var2, var3, var4, var6 - 1);
            }
            else
            {
                this.Melt(var1, var2, var3, var4);
            }
        }
    }

    public void OnPlayerWalksOnBlock(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        this.CheckForFall(var1, var2, var3, var4);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBeSetOnFireDirectlyByItem(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        this.Melt(var1, var2, var3, var4);
        return true;
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 60;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetHardeningLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHardeningLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetHardeningLevel(int var1)
    {
        return (var1 & 14) >> 1;
    }

    public void SetHardeningLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetHardeningLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetHardeningLevel(int var1, int var2)
    {
        var1 &= -15;
        return var1 | var2 << 1;
    }

    private void ConvertToSolidSnow(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockSnowSolid.blockID);
    }

    private void Melt(World var1, int var2, int var3, int var4)
    {
        FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var2, var3, var4);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconsHardening = new Icon[8];

        for (int var2 = 0; var2 < 8; ++var2)
        {
            this.m_iconsHardening[var2] = var1.registerIcon("fcOverlaySnowLoose_" + var2);
        }
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            int var6 = this.GetHardeningLevel(var1.blockAccess, var2, var3, var4);

            if (var6 >= 0 && var6 <= 7)
            {
                this.RenderBlockWithTexture(var1, var2, var3, var4, this.m_iconsHardening[var6]);
            }
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.renderBlockAsItemVanilla(this, var2, var3);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, this.m_iconsHardening[0]);
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.SetRenderAllFaces(true);
        var1.setRenderBounds(this.GetFixedBlockBoundsFromPool());
        var1.renderStandardBlock(this, var2, var3, var4);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_iconsHardening[this.GetHardeningLevel(var5)]);
        var1.SetRenderAllFaces(false);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.EmitHardeningParticles(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var5);
    }

    private void EmitHardeningParticles(World var1, double var2, double var4, double var6, Random var8)
    {
        for (int var9 = 0; var9 < 1; ++var9)
        {
            double var10 = var2 - 0.6D + var8.nextDouble() * 1.2D;
            double var12 = var4 - 0.6D + var8.nextDouble() * 1.2D;
            double var14 = var6 - 0.6D + var8.nextDouble() * 1.2D;
            var1.spawnParticle("fcwhitecloud", var10, var12, var14, 0.0D, 0.0D, 0.0D);
        }
    }
}
