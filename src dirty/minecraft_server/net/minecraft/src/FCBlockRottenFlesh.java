package net.minecraft.src;

public class FCBlockRottenFlesh extends Block
{
    public static final float m_fHardness = 0.6F;

    public FCBlockRottenFlesh(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.6F);
        this.SetBuoyancy(1.0F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("fcBlockRottenFlesh");
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Item.rottenFlesh.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 5;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}
