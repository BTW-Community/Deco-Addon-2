package net.minecraft.src;

public class FCItemRefinedPickAxe extends FCItemPickaxe
{
    protected FCItemRefinedPickAxe(int var1)
    {
        super(var1, EnumToolMaterial.SOULFORGED_STEEL);
        this.setUnlocalizedName("fcItemPickAxeRefined");
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

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3 != null && var3.blockMaterial == FCBetterThanWolves.fcMaterialSoulforgedSteel ? true : super.canHarvestBlock(var1, var2, var3, var4, var5, var6);
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3 != null && var3.blockMaterial == FCBetterThanWolves.fcMaterialSoulforgedSteel ? this.efficiencyOnProperMaterial : super.getStrVsBlock(var1, var2, var3, var4, var5, var6);
    }

    protected boolean GetCanBePlacedAsBlock()
    {
        return false;
    }
}
