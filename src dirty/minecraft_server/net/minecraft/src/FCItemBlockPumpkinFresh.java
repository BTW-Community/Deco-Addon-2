package net.minecraft.src;

public class FCItemBlockPumpkinFresh extends ItemBlock
{
    public FCItemBlockPumpkinFresh(int var1)
    {
        super(var1);
        this.setMaxStackSize(16);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var2.itemID == Block.pumpkin.blockID)
        {
            if (!var1.worldObj.isRemote)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(Item.pumpkinSeeds, 4, 0));
            }

            if (var1.m_iTimesCraftedThisTick == 0)
            {
                var1.playSound("mob.slime.attack", 0.5F, (var1.worldObj.rand.nextFloat() - var1.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
            }
        }
    }
}
