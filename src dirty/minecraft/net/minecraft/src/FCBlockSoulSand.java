package net.minecraft.src;

public class FCBlockSoulSand extends BlockSoulSand
{
    private Icon m_filterIcon;

    public FCBlockSoulSand(int var1)
    {
        super(var1);
        this.SetShovelsEffectiveOn();
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSoulSand.itemID, 3, 0, var6);
        return true;
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        return var1.itemID == FCBetterThanWolves.fcItemGroundNetherrack.itemID || var1.itemID == FCBetterThanWolves.fcItemSoulDust.itemID || var1.itemID == Item.lightStoneDust.itemID;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return true;
    }

    public boolean CanNetherWartGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_filterIcon = var1.registerIcon("fcBlockHopper_soulsand");
    }

    public Icon GetHopperFilterIcon()
    {
        return this.m_filterIcon;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderStandardFullBlock(this, var2, var3, var4);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return true;
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.RenderStandardFullBlockMovedByPiston(this, var2, var3, var4);
    }
}
