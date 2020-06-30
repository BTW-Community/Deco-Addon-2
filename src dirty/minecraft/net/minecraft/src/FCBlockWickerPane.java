package net.minecraft.src;

public class FCBlockWickerPane extends FCBlockPane
{
    private Icon m_filterIcon;

    protected FCBlockWickerPane(int var1)
    {
        super(var1, "fcBlockWicker", "fcBlockWicker", FCBetterThanWolves.fcMaterialWicker, false);
        this.setHardness(0.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.WICKER);
        this.setLightOpacity(4);
        Block.useNeighborBrightness[var1] = true;
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockWickerPane");
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWickerPiece.itemID, 1, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 8) != 0;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return var1.itemID == Block.gravel.blockID;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_filterIcon = var1.registerIcon("fcBlockHopper_wicker");
    }

    public Icon GetHopperFilterIcon()
    {
        return this.m_filterIcon;
    }
}
