package net.minecraft.src;

public class FCItemShovelStone extends FCItemShovel
{
    public FCItemShovelStone(int var1)
    {
        super(var1, EnumToolMaterial.STONE);
        this.efficiencyOnProperMaterial /= 3.0F;
        this.setUnlocalizedName("shovelStone");
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3 == Block.blockClay;
    }
}
