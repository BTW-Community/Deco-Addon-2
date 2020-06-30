package net.minecraft.src;

public class FCBlockPressurePlatePlanks extends FCBlockPressurePlate
{
    protected FCBlockPressurePlatePlanks(int var1)
    {
        super(var1, "wood", FCBetterThanWolves.fcMaterialPlanks, EnumMobType.everything);
        this.setHardness(0.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("pressurePlate");
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }
}
