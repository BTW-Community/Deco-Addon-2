package net.minecraft.src;

public class FCBlockCobblestoneMossy extends Block
{
    public FCBlockCobblestoneMossy(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("stoneMoss");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 6, 0, var6);
        return true;
    }
}
