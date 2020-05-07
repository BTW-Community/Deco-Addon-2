package net.minecraft.src;

public class AddonItemShears extends FCItemShears {
    public AddonItemShears(int id)
    {
        super(id);
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return !var3.blockMaterial.isToolNotRequired() && this.canHarvestBlock(var1, var2, var3, var4, var5, var6) ? true : var3 == Block.cloth || var3 == Block.leaves || var3 == FCBetterThanWolves.fcBlockBloodLeaves || var3 == FCBetterThanWolves.fcWoolSlab || var3 == FCBetterThanWolves.fcBlockWoolSlabTop || var3 == Block.vine || var3 == FCBetterThanWolves.fcBlockHempCrop || var3 == AddonDefs.carpet;
    }
}
