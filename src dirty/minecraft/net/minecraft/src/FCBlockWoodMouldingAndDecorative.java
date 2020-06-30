package net.minecraft.src;

import java.util.Random;

public class FCBlockWoodMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    public static final int m_iOakTableTopTextureID = 93;
    public static final int m_iOakTableLegTextureID = 94;

    protected FCBlockWoodMouldingAndDecorative(int var1, String var2, String var3, int var4, String var5)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks, var2, var3, var4, 2.0F, 5.0F, Block.soundWoodFootstep, var5);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime / 4);
        this.SetFireProperties(5, 20);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.IsDecorative(var1) ? FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID : FCBetterThanWolves.fcBlockWoodMouldingItemStubID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        byte var2;

        if (this.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID)
        {
            var2 = 0;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID)
        {
            var2 = 1;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID)
        {
            var2 = 2;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockID)
        {
            var2 = 3;
        }
        else
        {
            var2 = 4;
        }

        if (!this.IsDecorative(var1))
        {
            return var2;
        }
        else
        {
            byte var3;

            if (var1 == 12)
            {
                var3 = 0;
            }
            else if (var1 != 13 && var1 != 14)
            {
                var3 = 2;
            }
            else
            {
                var3 = 1;
            }

            return FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType(var2, var3);
        }
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsDecorative(var1, var2, var3, var4) ? super.GetItemIDDroppedOnSaw(var1, var2, var3, var4) : FCBetterThanWolves.fcBlockWoodCornerItemStubID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsDecorative(var1, var2, var3, var4) ? super.GetItemCountDroppedOnSaw(var1, var2, var3, var4) : 2;
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.IsDecorative(var5) ? super.GetItemDamageDroppedOnSaw(var1, var2, var3, var4) : this.damageDropped(var5);
    }

    public boolean DoesTableHaveLeg(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (this.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID)
        {
            int var5 = var1.getBlockId(var2, var3 - 1, var4);

            if (var5 == Block.fence.blockID)
            {
                return true;
            }
        }

        return super.DoesTableHaveLeg(var1, var2, var3, var4);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2271, var3, var4, var5, 0);
        int var7 = this.GetNumSawDustDroppedForType(var6);
        this.DropItemsIndividualy(var1, var3, var4, var5, FCBetterThanWolves.fcItemSawDust.itemID, var7, 0, 1.0F);
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion var1)
    {
        return false;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        float var6 = 1.0F;

        if (var5 != null)
        {
            var6 = 1.0F / var5.explosionSize;
        }

        int var7 = this.GetNumSawDustDroppedForType(var1.getBlockMetadata(var2, var3, var4));
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, var7, 0, var6);
    }

    public int GetNumSawDustDroppedForType(int var1)
    {
        return this.IsDecorative(var1) ? 2 : 1;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = var5.getBlockMetadata(var2, var3, var4);
        return var6 == 15 && this.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID ? RenderOakTable(var1, var5, var2, var3, var4, this) : super.RenderBlock(var1, var2, var3, var4);
    }

    public static boolean RenderOakTable(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
    {
        FCBlockMouldingAndDecorative var6 = (FCBlockMouldingAndDecorative)var5;
        var0.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakTop);

        if (var6.DoesTableHaveLeg(var1, var2, var3, var4))
        {
            var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D);
            FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakLeg);
        }

        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        if (this.blockID == FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID)
        {
            int var5 = FCItemBlockWoodMouldingDecorativeStub.GetBlockType(var2);
            int var6 = FCItemBlockWoodMouldingDecorativeStub.GetWoodType(var2);
            byte var7;

            if (var5 == 0)
            {
                var7 = 12;
            }
            else if (var5 == 1)
            {
                var7 = 13;
            }
            else
            {
                var7 = 15;
            }

            Block var4;

            if (var6 == 0)
            {
                var4 = FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative;
            }
            else if (var6 == 1)
            {
                var4 = FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative;
            }
            else if (var6 == 2)
            {
                var4 = FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative;
            }
            else if (var6 == 3)
            {
                var4 = FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative;
            }
            else
            {
                var4 = FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative;
            }

            if (var7 == 15 && var4.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID)
            {
                RenderOakTableInvBlock(var1, var4);
            }
            else
            {
                this.RenderDecorativeInvBlock(var1, var4, var7, var3);
            }
        }
        else
        {
            var1.setRenderBounds(this.GetBlockBoundsFromPoolForItemRender(var2));
            Icon var8;

            switch (var2)
            {
                case 1:
                    var8 = FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockIcon;
                    break;

                case 2:
                    var8 = FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockIcon;
                    break;

                case 3:
                    var8 = FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockIcon;
                    break;

                case 4:
                    var8 = FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative.blockIcon;
                    break;

                default:
                    var8 = FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockIcon;
            }

            FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, var8);
        }
    }

    public static void RenderOakTableInvBlock(RenderBlocks var0, Block var1)
    {
        var0.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakTop);
        var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakLeg);
    }
}
