package net.minecraft.src;

public class FCItemShovel extends FCItemTool
{
    public FCItemShovel(int var1, EnumToolMaterial var2)
    {
        super(var1, 1, var2);
    }

    protected FCItemShovel(int var1, EnumToolMaterial var2, int var3)
    {
        super(var1, 1, var2);
        this.setMaxDamage(var3);
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return this.IsToolTypeEfficientVsBlockType(var3);
    }

    public boolean IsToolTypeEfficientVsBlockType(Block var1)
    {
        return var1.AreShovelsEffectiveOn();
    }

    protected float GetVisualVerticalOffsetAsBlock()
    {
        return 0.7F;
    }

    protected float GetVisualRollOffsetAsBlock()
    {
        return -15.0F;
    }
}
