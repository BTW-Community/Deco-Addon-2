package net.minecraft.src;

public class FCItemBrickUnfired extends FCItemPlacesAsBlock
{
    public FCItemBrickUnfired(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockUnfiredBrick.blockID);
        this.m_bRequireNoEntitiesInTargetBlock = true;
        this.setUnlocalizedName("fcItemBrickUnfired");
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

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockUnfiredClay.blockID;
    }
}
