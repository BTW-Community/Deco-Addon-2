package net.minecraft.src;

public class FCBlockEndStone extends FCBlockFullBlock
{
    public FCBlockEndStone(int var1, Material var2)
    {
        super(var1, var2);
        this.SetCanBeCookedByKiln(true);
    }

    public void OnCookedByKiln(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemEnderSlag.itemID, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcAestheticOpaque.blockID, 10);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }
}
