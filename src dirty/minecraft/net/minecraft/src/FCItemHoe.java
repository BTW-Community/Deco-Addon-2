package net.minecraft.src;

public class FCItemHoe extends FCItemTool
{
    protected EnumToolMaterial theToolMaterial;

    public FCItemHoe(int var1, EnumToolMaterial var2)
    {
        super(var1, 1, var2);

        if (var2.getHarvestLevel() <= 2)
        {
            this.efficiencyOnProperMaterial /= 8.0F;
        }
        else
        {
            this.efficiencyOnProperMaterial /= 4.0F;
        }
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return false;
    }

    public boolean IsToolTypeEfficientVsBlockType(Block var1)
    {
        return var1.AreHoesEffectiveOn();
    }

    protected boolean CanToolStickInBlock(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        return super.CanToolStickInBlock(var1, var2, var3, var4, var5, var6) || var2.AreShovelsEffectiveOn();
    }

    protected float GetVisualVerticalOffsetAsBlock()
    {
        return 0.8F;
    }

    protected float GetBlockBoundingBoxHeight()
    {
        return 0.79F;
    }
}
