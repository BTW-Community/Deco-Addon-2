package net.minecraft.src;

public class FCBlockFenceWood extends FCBlockFence
{
    public FCBlockFenceWood(int var1)
    {
        super(var1, "wood", FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.setResistance(5.0F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fence");
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcBlockWoodCornerItemStubID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
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
