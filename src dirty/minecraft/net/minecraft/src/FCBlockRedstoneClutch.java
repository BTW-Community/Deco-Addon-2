package net.minecraft.src;

import java.util.Random;

public class FCBlockRedstoneClutch extends FCBlockGearBox
{
    protected FCBlockRedstoneClutch(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockRedstoneClutch");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);

        if (var6 && (var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4)))
        {
            var6 = false;
        }

        this.UpdateMechPoweredState(var1, var2, var3, var4, var6);
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        super.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var5, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.goldNugget.itemID, 2, 0, var6);
        return true;
    }

    public boolean IsIncineratedInCrucible()
    {
        return false;
    }

    protected boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);

        if (var5 && (var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4)))
        {
            var5 = false;
        }

        return this.IsGearBoxOn(var1, var2, var3, var4) == var5;
    }
}
