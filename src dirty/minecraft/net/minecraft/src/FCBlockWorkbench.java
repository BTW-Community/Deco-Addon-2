package net.minecraft.src;

public class FCBlockWorkbench extends BlockWorkbench
{
    protected FCBlockWorkbench(int var1)
    {
        super(var1);
        this.SetBlockMaterial(FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("workbench");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0) ? true : super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 1, 0, var6);
        return true;
    }
}
