package net.minecraft.src;

public class FCItemBlockMiningCharge extends ItemBlock
{
    public FCItemBlockMiningCharge(int var1)
    {
        super(var1);
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var3, var4, var5, var6);
        FCBlockMiningCharge.CreatePrimedEntity(var2, var7.i, var7.j, var7.k, var6);
        var2.playAuxSFX(2236, var3, var4, var5, FCBetterThanWolves.fcBlockMiningCharge.blockID);
        return true;
    }
}
