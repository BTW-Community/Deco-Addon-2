package net.minecraft.src;

public class FCItemNetherBrickUnfired extends FCItemPlacesAsBlock
{
    public FCItemNetherBrickUnfired(int var1)
    {
        super(var1, FCBetterThanWolves.fcUnfiredPottery.blockID, 7);
        this.SetNeutralBuoyant();
        this.setUnlocalizedName("fcItemBrickNetherUnfired");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("mob.slime.attack", 0.25F, (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.1F + 0.7F);
        }

        super.onCreated(var1, var2, var3);
    }
}
