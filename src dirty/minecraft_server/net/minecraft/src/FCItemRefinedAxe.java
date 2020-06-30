package net.minecraft.src;

public class FCItemRefinedAxe extends FCItemAxe
{
    protected FCItemRefinedAxe(int var1)
    {
        super(var1, EnumToolMaterial.SOULFORGED_STEEL);
        this.setUnlocalizedName("fcItemHatchetRefined");
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        return var1;
    }

    protected boolean GetCanBePlacedAsBlock()
    {
        return false;
    }
}
