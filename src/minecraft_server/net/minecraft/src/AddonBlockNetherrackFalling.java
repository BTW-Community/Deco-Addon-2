package net.minecraft.src;

import java.util.Random;

public class AddonBlockNetherrackFalling extends FCBlockNetherrackFalling
{
    private Icon m_iconEmbers;

    public AddonBlockNetherrackFalling(int var1)
    {
        super(var1);
        this.SetCanBeCookedByKiln(true);
    }

    public void OnCookedByKiln(World var1, int var2, int var3, int var4) {
        var1.setBlockWithNotify(var2, var3, var4, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, AddonDefs.basalt.blockID, 0);
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4) {
        return 8;
    }
}
