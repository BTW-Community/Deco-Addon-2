package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockWoodSidingAndCornerAndDecorative extends DecoBlockSidingAndCornerAndDecorative
{
    protected DecoBlockWoodSidingAndCornerAndDecorative(int var1, String var2, String var3)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks, var2, 2.0F, 5.0F, Block.soundWoodFootstep, var3);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(5, 20);
        this.setCreativeTab(null);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return IsDecorativeFromMetadata(var1) ? FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID : (this.GetIsCorner(var1) ? FCBetterThanWolves.fcBlockWoodCornerItemStubID : FCBetterThanWolves.fcBlockWoodSidingItemStubID);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = this.GetWoodTypeFromBlockID();

        if (IsDecorativeFromMetadata(var1))
        {
            byte var3;

            if (var1 == 12)
            {
                var3 = 0;
            }
            else
            {
                var3 = 1;
            }

            return FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType(var2, var3);
        }
        else
        {
            return var2;
        }
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
        return var5 == 12 ? super.GetItemIDDroppedOnSaw(var1, var2, var3, var4) : (var5 == 14 ? FCBetterThanWolves.fcBlockWoodCornerItemStubID : (this.GetIsCorner(var1, var2, var3, var4) ? FCBetterThanWolves.fcItemGear.itemID : FCBetterThanWolves.fcBlockWoodMouldingItemStubID));
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
            return var5 == 14 ? this.GetWoodTypeFromBlockID() : super.GetItemDamageDroppedOnSaw(var1, var2, var3, var4);
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

    protected int GetWoodTypeFromBlockID()
    {
        byte var1;

        if (this.blockID == FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID)
        {
            var1 = 0;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID)
        {
            var1 = 1;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID)
        {
            var1 = 2;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID)
        {
            var1 = 3;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID)
        {
            var1 = 4;
        }
        else if (this.blockID == DecoDefs.cherrySidingAndCorner.blockID)
        {
        	var1 = 5;
        }
        else
        {
        	var1 = 6;
        }

        return var1;
    }

    public int GetNumSawDustDroppedForType(int var1)
    {
        return !IsDecorativeFromMetadata(var1) && this.GetIsCorner(var1) ? 1 : 2;
    }
}