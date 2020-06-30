package net.minecraft.src;

public class FCItemBattleAxe extends FCItemAxe
{
    private final int m_iWeaponDamage;

    protected FCItemBattleAxe(int var1)
    {
        super(var1, EnumToolMaterial.SOULFORGED_STEEL);
        this.m_iWeaponDamage = 4 + this.toolMaterial.getDamageVsEntity();
        this.setUnlocalizedName("fcItemAxeBattle");
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

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.m_iWeaponDamage;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        var1.damageItem(1, var3);
        return true;
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID != Block.web.blockID && var3.blockID != FCBetterThanWolves.fcBlockWeb.blockID ? super.getStrVsBlock(var1, var2, var3, var4, var5, var6) : 15.0F;
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID != Block.web.blockID && var3.blockID != FCBetterThanWolves.fcBlockWeb.blockID ? super.canHarvestBlock(var1, var2, var3, var4, var5, var6) : true;
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID != Block.web.blockID && var3.blockID != FCBetterThanWolves.fcBlockWeb.blockID ? super.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6) : true;
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.weapon ? true : super.IsEnchantmentApplicable(var1);
    }

    protected boolean GetCanBePlacedAsBlock()
    {
        return false;
    }
}
