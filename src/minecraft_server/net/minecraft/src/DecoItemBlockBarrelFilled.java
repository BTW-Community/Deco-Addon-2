package net.minecraft.src;

public class DecoItemBlockBarrelFilled  extends ItemMultiTextureTile
{
    public DecoItemBlockBarrelFilled(int var1, Block var2, String[] var3)
    {
        super(var1, var2, var3);
        this.maxStackSize = 16;
    }

	public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        if (!var2.worldObj.isRemote)
        {
            if (this.m_iBlockID == DecoDefs.barrelFullOak.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(DecoDefs.barrelEmpty, 1, 0));
            }
            else if (this.m_iBlockID == DecoDefs.barrelFullSpruce.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(DecoDefs.barrelEmpty, 1, 1));
            }
            else if (this.m_iBlockID == DecoDefs.barrelFullBirch.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(DecoDefs.barrelEmpty, 1, 2));
            }
            else if (this.m_iBlockID == DecoDefs.barrelFullJungle.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(DecoDefs.barrelEmpty, 1, 3));
            }
        }
    }
}
