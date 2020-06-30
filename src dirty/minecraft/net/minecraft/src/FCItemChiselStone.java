package net.minecraft.src;

public class FCItemChiselStone extends FCItemChisel
{
    protected FCItemChiselStone(int var1)
    {
        super(var1, EnumToolMaterial.STONE, 8);
        this.SetFilterableProperties(2);
        this.efficiencyOnProperMaterial /= 2.0F;
        this.setUnlocalizedName("fcItemChiselStone");
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        float var7 = super.getStrVsBlock(var1, var2, var3, var4, var5, var6);

        if (var3.blockID == Block.web.blockID || var3.blockID == FCBetterThanWolves.fcBlockWeb.blockID)
        {
            var7 *= 2.0F;
        }

        return var7;
    }
}
