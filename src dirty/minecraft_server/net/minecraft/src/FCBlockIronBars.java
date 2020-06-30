package net.minecraft.src;

public class FCBlockIronBars extends FCBlockPane
{
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
}
