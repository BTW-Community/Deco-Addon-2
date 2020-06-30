package net.minecraft.src;

public class FCItemArmor extends ItemArmor
{
    private final int m_iArmorWeight;

    public FCItemArmor(int var1, EnumArmorMaterial var2, int var3, int var4, int var5)
    {
        super(var1, var2, var3, var4);
        this.m_iArmorWeight = var5;
    }

    public int GetWeightWhenWorn()
    {
        return this.m_iArmorWeight;
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        super.onCreated(var1, var2, var3);

        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            if (this.getArmorMaterial() == EnumArmorMaterial.CLOTH)
            {
                var3.playSound("step.cloth", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
            }
            else
            {
                var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
            }
        }
    }
}
