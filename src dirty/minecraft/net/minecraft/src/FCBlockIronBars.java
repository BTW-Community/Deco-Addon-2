package net.minecraft.src;

public class FCBlockIronBars extends FCBlockPane
{
    private Icon m_filterIcon;

    protected FCBlockIronBars(int var1)
    {
        super(var1, "fenceIron", "fenceIron", Material.iron, true);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fenceIron");
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return var1.getMaxStackSize() > 1 && (var2 & 1) == 0;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_filterIcon = var1.registerIcon("fcBlockHopper_ironbars");
    }

    public Icon GetHopperFilterIcon()
    {
        return this.m_filterIcon;
    }
}
