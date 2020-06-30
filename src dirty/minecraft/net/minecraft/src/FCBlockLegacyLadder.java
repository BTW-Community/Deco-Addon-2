package net.minecraft.src;

public class FCBlockLegacyLadder extends FCBlockLadderBase
{
    protected FCBlockLegacyLadder(int var1)
    {
        super(var1);
        this.setUnlocalizedName("ladder");
        this.setCreativeTab((CreativeTabs)null);
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return var1;
    }

    public int SetFacing(int var1, int var2)
    {
        return MathHelper.clamp_int(var2, 2, 5);
    }
}
