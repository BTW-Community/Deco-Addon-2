package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockPlanksPaintedSidingAndCornerAndDecorative extends AddonBlockSidingAndCornerAndDecorative
{
    protected AddonBlockPlanksPaintedSidingAndCornerAndDecorative(int var1, String var2, String var3)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks, var2, 2.0F, 5.0F, Block.soundWoodFootstep, var3);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(5, 20);
    }

    public boolean DoesBenchHaveLeg(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (this.blockID == FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID)
        {
            int var5 = var1.getBlockId(var2, var3 - 1, var4);

            if (var5 == Block.fence.blockID)
            {
                return true;
            }
        }

        return super.DoesBenchHaveLeg(var1, var2, var3, var4);
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 12 ? super.GetItemIDDroppedOnSaw(var1, var2, var3, var4) : (var5 == 14 ? AddonDefs.paintedPlanksSidingAndCorner[var5].blockID : (this.GetIsCorner(var1, var2, var3, var4) ? FCBetterThanWolves.fcItemGear.itemID : AddonDefs.paintedPlanksMouldingAndDecorative[var5].blockID));
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        if (this.IsDecorative(var1, var2, var3, var4))
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);
            return var5 == 14 ? 2 : super.GetItemCountDroppedOnSaw(var1, var2, var3, var4);
        }
        else
        {
            return 2;
        }
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        if (this.IsDecorative(var1, var2, var3, var4))
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);
            return var5 == 14 ? 1 : super.GetItemDamageDroppedOnSaw(var1, var2, var3, var4);
        }
        else
        {
            return this.GetIsCorner(var1, var2, var3, var4) ? 0 : this.damageDropped(var1.getBlockMetadata(var2, var3, var4));
        }
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
        return !IsDecorativeFromMetadata(var1) && this.GetIsCorner(var1) ? 1 : 2;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        if (var1 == FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID)
        {
            var3.add(new ItemStack(var1, 1, 12));
            var3.add(new ItemStack(var1, 1, 0));
            var3.add(new ItemStack(var1, 1, 1));
        }
        else
        {
            super.getSubBlocks(var1, var2, var3);
        }
    }
}