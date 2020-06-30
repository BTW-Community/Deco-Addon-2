package net.minecraft.src;

public class FCItemClub extends Item
{
    private int m_iWeaponDamage;
    public static final int m_iWeaponDamageBone = 4;
    public static final int m_iDurabilityBone = 20;

    public FCItemClub(int var1, int var2, int var3, String var4)
    {
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(var2);
        this.m_iWeaponDamage = var3;
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.setUnlocalizedName(var4);
        this.setCreativeTab(CreativeTabs.tabCombat);
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

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
    {
        if (Block.blocksList[var3].getBlockHardness(var2, var4, var5, var6) > 0.0F)
        {
            var1.damageItem(2, var7);
        }

        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.m_iWeaponDamage;
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("mob.zombie.woodbreak", 0.1F, 1.25F + var2.rand.nextFloat() * 0.25F);
        }

        super.onCreated(var1, var2, var3);
    }
}
