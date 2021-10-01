package net.minecraft.src;

public class DecoBlockPlanksPaintedMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    protected DecoBlockPlanksPaintedMouldingAndDecorative(int var1, String var2, String var3, int var4, String var5)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks, var2, var3, var4, 2.0F, 5.0F, Block.soundWoodFootstep, var5);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime / 4);
        this.SetFireProperties(5, 20);
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
}
