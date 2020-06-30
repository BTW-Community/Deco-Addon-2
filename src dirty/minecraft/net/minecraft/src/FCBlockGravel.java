package net.minecraft.src;

import java.util.Random;

public class FCBlockGravel extends FCBlockFallingFullBlock
{
    public FCBlockGravel(int var1)
    {
        super(var1, Material.sand);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("gravel");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var3 <= 0 && var2.nextInt(10) == 0 ? Item.flint.itemID : this.blockID;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (var1.rand.nextInt(10) == 0)
        {
            this.DropItemsIndividualy(var1, var3, var4, var5, Item.flint.itemID, 1, 0, 1.0F);
        }
        else
        {
            super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);
        }
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileGravel.itemID, 6, 0, var6);
        return true;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}
