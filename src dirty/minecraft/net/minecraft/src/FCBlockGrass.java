package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

public class FCBlockGrass extends BlockGrass
{
    public static final int m_iGrassSpreadFromLightLevel = 11;
    public static final int m_iGrassSpreadToLightLevel = 11;
    public static final int m_iGrassSurviveMinimumLightLevel = 9;
    private boolean m_bTempHasSnowOnTop;
    private Icon iconGrassTop;
    private Icon iconSnowSide;
    private Icon iconGrassSideOverlay;

    protected FCBlockGrass(int var1)
    {
        super(var1);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.SetHoesEffectiveOn();
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("grass");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockId(var2, var3 + 1, var4);
        Block var7 = Block.blocksList[var6];
        int var8 = var1.GetBlockNaturalLightValueMaximum(var2, var3 + 1, var4);
        int var9 = var8 - var1.skylightSubtracted;

        if (var8 >= 9 && Block.lightOpacity[var6] <= 2 && (var7 == null || var7.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, false)))
        {
            if (var9 >= 11)
            {
                CheckForGrassSpreadFromLocation(var1, var2, var3, var4);
            }
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockDirtLoose.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 6, 0, var6);
        return true;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);
        this.OnDirtDugWithImproperTool(var1, var3, var4, var5);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
        this.OnDirtDugWithImproperTool(var1, var2, var3, var4);
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 0)
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return true;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (!var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return var1 != null && var1.getItem() instanceof FCItemHoe;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);

        if (!var2.isRemote)
        {
            var2.playAuxSFX(2001, var3, var4, var5, this.blockID);

            if (var2.rand.nextInt(25) == 0)
            {
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemHempSeeds), var6);
            }
        }

        return true;
    }

    public static void CheckForGrassSpreadFromLocation(World var0, int var1, int var2, int var3)
    {
        if (var0.provider.dimensionId != 1 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var0, var1, var2, var3))
        {
            int var4 = var1 + var0.rand.nextInt(3) - 1;
            int var5 = var2 + var0.rand.nextInt(5) - 3;
            int var6 = var3 + var0.rand.nextInt(3) - 1;
            Block var7 = Block.blocksList[var0.getBlockId(var4, var5, var6)];

            if (var7 != null)
            {
                var7.AttempToSpreadGrassToBlock(var0, var4, var5, var6);
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.iconGrassTop = var1.registerIcon("grass_top");
        this.iconSnowSide = var1.registerIcon("snow_side");
        this.iconGrassSideOverlay = var1.registerIcon("grass_side_overlay");
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_bTempHasSnowOnTop ? 16777215 : super.colorMultiplier(var1, var2, var3, var4);
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1 ? this.iconGrassTop : (var5 == 0 ? Block.dirt.getBlockTextureFromSide(var5) : (this.m_bTempHasSnowOnTop ? this.iconSnowSide : this.blockIcon));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.m_bTempHasSnowOnTop = this.IsSnowCoveringTopSurface(var5, var2, var3, var4);

        if (this.m_bTempHasSnowOnTop)
        {
            return var1.renderStandardBlock(this, var2, var3, var4);
        }
        else
        {
            int var6 = this.colorMultiplier(var5, var2, var3, var4);
            float var7 = (float)(var6 >> 16 & 255) / 255.0F;
            float var8 = (float)(var6 >> 8 & 255) / 255.0F;
            float var9 = (float)(var6 & 255) / 255.0F;
            return Minecraft.isAmbientOcclusionEnabled() ? var1.renderGrassBlockWithAmbientOcclusion(this, var2, var3, var4, var7, var8, var9, BlockGrass.getIconSideOverlay()) : var1.renderGrassBlockWithColorMultiplier(this, var2, var3, var4, var7, var8, var9, BlockGrass.getIconSideOverlay());
        }
    }
}
