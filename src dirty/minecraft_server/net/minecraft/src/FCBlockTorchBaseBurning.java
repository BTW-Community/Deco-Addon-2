package net.minecraft.src;

public class FCBlockTorchBaseBurning extends FCBlockTorchBase
{
    protected FCBlockTorchBaseBurning(int var1)
    {
        super(var1);
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnFluidFlowIntoBlock(World var1, int var2, int var3, int var4, BlockFluid var5)
    {
        if (var5.blockMaterial == Material.water)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcBlockTorchNetherUnlit.blockID, 1, 0));
        }
        else
        {
            super.OnFluidFlowIntoBlock(var1, var2, var3, var4, var5);
        }
    }
}
