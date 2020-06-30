package net.minecraft.src;

public class FCItemSword extends ItemSword
{
    private final EnumToolMaterial m_material;

    public FCItemSword(int var1, EnumToolMaterial var2)
    {
        super(var1, var2);
        this.m_material = var2;

        if (this.m_material == EnumToolMaterial.WOOD)
        {
            this.SetBuoyant();
            this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_TOOLS);
            this.SetIncineratedInCrucible();
        }

        this.SetInfernalMaxEnchantmentCost(this.m_material.GetInfernalMaxEnchantmentCost());
        this.SetInfernalMaxNumEnchants(this.m_material.GetInfernalMaxNumEnchants());
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return this.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6);
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        if (this.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6))
        {
            return 15.0F;
        }
        else
        {
            Material var7 = var3.blockMaterial;
            return var7 != Material.plants && var7 != Material.vine && var7 != Material.coral && var7 == Material.leaves && var7 == Material.pumpkin ? super.getStrVsBlock(var1, var2, var3, var4, var5, var6) : 1.5F;
        }
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID == Block.web.blockID || var3.blockID == FCBetterThanWolves.fcBlockWeb.blockID;
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.weapon ? true : super.IsEnchantmentApplicable(var1);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            if (this.m_material == EnumToolMaterial.WOOD)
            {
                var3.playSound("mob.zombie.woodbreak", 0.1F, 1.25F + var2.rand.nextFloat() * 0.25F);
            }
            else if (this.m_material == EnumToolMaterial.STONE)
            {
                var3.playSound("random.anvil_land", 0.5F, var2.rand.nextFloat() * 0.25F + 1.75F);
            }
            else
            {
                var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
            }
        }

        super.onCreated(var1, var2, var3);
    }
}
