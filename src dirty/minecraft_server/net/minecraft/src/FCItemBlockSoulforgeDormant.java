package net.minecraft.src;

public class FCItemBlockSoulforgeDormant extends ItemBlock
{
    public FCItemBlockSoulforgeDormant(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockSoulforgeDormant");
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("random.anvil_land", 0.3F, var1.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
    }
}
