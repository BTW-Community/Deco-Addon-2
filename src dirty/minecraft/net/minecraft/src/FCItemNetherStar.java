package net.minecraft.src;

public class FCItemNetherStar extends ItemSimpleFoiled
{
    public FCItemNetherStar(int var1)
    {
        super(var1);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("netherStar");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("ambient.cave.cave4", 0.5F, var1.worldObj.rand.nextFloat() * 0.05F + 0.5F);
        }
    }
}
